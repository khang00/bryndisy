package khang.bryndisy.service

import khang.bryndisy.model.Task
import khang.bryndisy.service.adapter.TasksOptimizer
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Stream
import kotlin.streams.toList

@Service
class SimpleOptimizer : TasksOptimizer {
    override fun optimizeTasks(tasks: List<Task>): Optional<List<Task>> {
        return if (tasks.isNotEmpty() && isOptimizable(tasks)) {
            val optimizedTasks = sortTask(tasks).toList().foldRight({ listOf() }, computeStartDateOfTasks).invoke()
            Optional.of(optimizedTasks)
        } else {
            Optional.empty()
        }
    }

    private val isOptimizable: (List<Task>) -> Boolean = { tasks ->
        val minStartDate = tasks.minBy { it.startDate }?.startDate
        val maxDeadline = tasks.maxBy { it.deadline }?.deadline
        tasks.foldRight(minStartDate, { task, acc -> acc?.plus(task.duration) })!! < maxDeadline
    }

    private val sortTask: (List<Task>) -> Stream<Task> = { tasks ->
        tasks.parallelStream().sorted(compareBy({ it.deadline }, { it.duration }))
    }

    private val computeStartDateOfTasks: (Task, () -> List<Task>) -> () -> List<Task> = { task, tasks ->
        { tasks.invoke().plus(startDatePinnedByNow(task, tasks.invoke())) }
    }

    private val startDatePinnedByDeadline: (Task, List<Task>) -> Task = { task, tasks ->
        if (tasks.isEmpty()) {
            task.copy(startDate = task.deadline - task.duration)
        } else {
            task.copy(startDate = tasks.last().startDate - task.duration)
        }
    }

    private val startDatePinnedByNow: (Task, List<Task>) -> Task = { task, tasks ->
        if (tasks.isEmpty()) {
            task
        } else {
            task.copy(startDate = tasks.last().startDate + tasks.last().duration)
        }
    }
}