package khang.bryndisy

import khang.bryndisy.model.UserTask
import khang.bryndisy.service.SimpleOptimizer
import khang.bryndisy.service.adapter.TasksOptimizer
import org.bson.types.ObjectId
import org.junit.jupiter.api.Test
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SchedulerSuite {
    private val optimizer: TasksOptimizer = SimpleOptimizer()
    private val initTasks: (Int) -> UserTask = { index: Int ->
        if (index < 5) {
            UserTask(ObjectId.get().toHexString(),
                    "say hello$index",
                    Duration.ofDays(index.toLong())
                    , LocalDateTime.of(LocalDate.of(2020, 9, 5)
                    , LocalTime.of(2, 2)))
        } else {
            UserTask(ObjectId.get().toHexString(),
                    "say hello$index",
                    Duration.ofDays(index.toLong())
                    , LocalDateTime.of(LocalDate.of(2020, 9, 4)
                    , LocalTime.of(2, 2)))
        }
    }

    @Test
    fun `scheduler should optimize the tasks`() {
        val userTasks: List<UserTask> = List(9, initTasks)
        val optimizedTasks = optimizer.optimizeTasks(userTasks)

        print("Now is: ${LocalDate.now().dayOfYear} \n")

        for (task in optimizedTasks.get()) print("(Sta:${task.startDate.dayOfYear}" +
                ", Dur:${task.duration.toDays()}" +
                ", Dea:${task.deadline.dayOfYear})\n")
    }
}