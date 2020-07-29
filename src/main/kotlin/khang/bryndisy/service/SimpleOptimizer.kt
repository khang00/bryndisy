package khang.bryndisy.service

import khang.bryndisy.model.User
import khang.bryndisy.model.UserTask
import khang.bryndisy.service.adapter.TasksOptimizer
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.*
import java.util.stream.Stream
import kotlin.streams.toList

@Service
class SimpleOptimizer : TasksOptimizer {
    override fun optimizeTasks(user: User): Optional<User> {
        val tasks = user.tasks.toList().map { it.second }
        val optimizedTasks = optimizeTasks(tasks, Duration.ofHours(24) - user.workDurationPerDay)
        return if (optimizedTasks.isPresent) {
            Optional.of(user.copy(tasks = optimizedTasks.get().associateBy({ it.id }, { it })))
        } else {
            Optional.empty()
        }
    }

    override fun optimizeTasks(userTasks: List<UserTask>, offHours: Duration): Optional<List<UserTask>> {
        return if (userTasks.isNotEmpty() && isOptimizable(userTasks)) {
            val partialComputeStartDateOfTasks =
                    { x: UserTask, y: () -> List<UserTask> -> computeStartDateOfTasks(x, y, offHours) }

            Optional.of(sortUserTask(userTasks).toList().foldRight({ listOf() }, partialComputeStartDateOfTasks).invoke())
        } else {
            Optional.empty()
        }
    }

    private val isOptimizable: (List<UserTask>) -> Boolean = { tasks ->
        val minStartDate = tasks.minBy { it.startDate }?.startDate
        val maxDeadline = tasks.maxBy { it.deadline }?.deadline
        tasks.foldRight(minStartDate, { task, acc -> acc?.plus(task.duration) })!! < maxDeadline
    }

    private val sortUserTask: (List<UserTask>) -> Stream<UserTask> = { tasks ->
        tasks.parallelStream().sorted(compareBy({ it.deadline }, { it.priority }, { it.duration }))
    }

    private val computeStartDateOfTasks: (UserTask, () -> List<UserTask>, Duration) -> () -> List<UserTask> = { task, tasks, offHours ->
        { tasks.invoke().plus(startDatePinnedByNow(task, tasks.invoke(), offHours)) }
    }

    private val startDatePinnedByDeadline: (UserTask, List<UserTask>, Duration) -> UserTask = { task, tasks, offHours ->
        val durationWithBreak = (task.duration.toSeconds() / offHours.toSeconds()) *
                offHours.toSeconds() +
                task.duration.toSeconds()

        if (tasks.isEmpty()) {
            task.copy(startDate = task.deadline - Duration.ofSeconds(durationWithBreak))
        } else {
            task.copy(startDate = tasks.last().startDate - Duration.ofSeconds(durationWithBreak))
        }
    }

    private val startDatePinnedByNow: (UserTask, List<UserTask>, Duration) -> UserTask = { task, tasks, offHours ->
        if (tasks.isEmpty()) {
            task
        } else {
            val durationWithBreak = (tasks.last().duration.toSeconds() / offHours.toSeconds()) *
                    offHours.toSeconds() +
                    tasks.last().duration.toSeconds()
            task.copy(startDate = tasks.last().startDate + Duration.ofSeconds(durationWithBreak))
        }
    }
}