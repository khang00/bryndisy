package khang.bryndisy

import khang.bryndisy.model.Schedule
import khang.bryndisy.model.Task
import khang.bryndisy.service.Scheduler
import org.bson.types.ObjectId
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SchedulerSuite {
    private val scheduler: Scheduler = Scheduler()

    private val initTasks: (Int) -> Task = { index: Int ->
        if (index > 5) {
            Task(ObjectId.get(),
                    "say hello$index",
                    Duration.ofDays(index.toLong())
                    , LocalDateTime.of(LocalDate.of(2020, 9, 5)
                    , LocalTime.of(2, 2)))
        } else {
            Task(ObjectId.get(),
                    "say hello$index",
                    Duration.ofDays(index.toLong())
                    , LocalDateTime.of(LocalDate.of(2020, 9, 4)
                    , LocalTime.of(2, 2)))
        }
    }

    @Test
    fun `scheduler should optimize the tasks`() {
        val tasks: List<Task> = List(9, initTasks)
        val schedule = Schedule(ObjectId.get(), "Test", tasks)
        val optimizedSchedule = scheduler.optimizeSchedule(schedule)

        for (task in optimizedSchedule.get().tasks) print("(${task.startDate.dayOfYear}" +
                ", ${(task.startDate + task.duration).dayOfYear}" +
                ", ${task.deadline.dayOfYear})\n")
    }
}