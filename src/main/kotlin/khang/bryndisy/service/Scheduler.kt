package khang.bryndisy.service

import khang.bryndisy.model.Schedule
import khang.bryndisy.model.Task
import org.bson.types.ObjectId
import java.time.LocalDateTime
import java.util.*

class Scheduler : ScheduleService {
    private val sortTask: (Schedule) -> MutableMap<LocalDateTime, MutableList<Task>> = { schedule ->
        schedule.tasks.groupByTo(mutableMapOf()) { it.deadline }.toSortedMap().also { keysAndListsOfTasks ->
            keysAndListsOfTasks.values.forEach { tasks -> tasks.sortBy { it.duration } }
        }
    }

    override fun optimizeSchedule(schedule: Schedule): Optional<Schedule> {
        val sortedTask = mutableListOf<Task>()

        sortTask(schedule).forEach { bucket ->
            bucket.value.let { tasks -> sortedTask.addAll(tasks) }
        }

        return Optional.of(Schedule(ObjectId.get(), "optimized", sortedTask))
    }

    override fun getSchedule(id: ObjectId): Schedule {
        TODO("Difficult to test optimizeSchedule function if wires up repository")
    }
}