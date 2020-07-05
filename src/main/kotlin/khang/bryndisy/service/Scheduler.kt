package khang.bryndisy.service

import khang.bryndisy.model.Schedule
import khang.bryndisy.model.Task
import org.bson.types.ObjectId
import java.util.*
import java.util.stream.Stream
import kotlin.streams.toList

class Scheduler : ScheduleService {
    override fun getSchedule(id: ObjectId): Schedule {
        TODO("Difficult to test optimizeSchedule function if wires up repository")
    }

    override fun optimizeSchedule(schedule: Schedule): Optional<Schedule> {
        return if (isOptimizable(schedule)) {
            val optimizedTasks = sortTask(schedule).toList().foldRight({ listOf() }, computeStartDateOfTasks).invoke()
            val optimizedSchedule = schedule.copy(tasks = optimizedTasks)
            Optional.of(optimizedSchedule)
        } else {
            Optional.empty()
        }
    }

    private val isOptimizable: (Schedule) -> Boolean = { schedule ->
        val minStartDate = schedule.tasks.minBy { it.startDate }?.startDate
        val maxDeadline = schedule.tasks.maxBy { it.deadline }?.deadline
        schedule.tasks.foldRight(minStartDate, { task , acc -> acc?.plus(task.duration) })!! < maxDeadline
    }

    private val sortTask: (Schedule) -> Stream<Task> = { schedule ->
        schedule.tasks.parallelStream().sorted(compareBy({ it.deadline }, { it.duration }))
    }

    private val computeStartDateOfTasks: (Task, () -> List<Task>) -> () -> List<Task> = { task, tasks ->
        { tasks.invoke().plus(computeStartDateOfTaskPinnedByNow(task, tasks.invoke())) }
    }

    private val computeStartDateOfTaskPinnedByDeadline: (Task, List<Task>) -> Task = { task, tasks ->
        if (tasks.isEmpty()) {
            task.copy(startDate = task.deadline - task.duration)
        } else {
            task.copy(startDate = tasks.last().startDate - task.duration)
        }
    }

    private val computeStartDateOfTaskPinnedByNow: (Task, List<Task>) -> Task = { task, tasks ->
        if (tasks.isEmpty()) {
            task
        } else {
            task.copy(startDate = tasks.last().startDate + tasks.last().duration)
        }
    }
}