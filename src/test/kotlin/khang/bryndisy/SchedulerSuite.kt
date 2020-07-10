package khang.bryndisy

import khang.bryndisy.model.Task
import khang.bryndisy.service.SimpleOptimizer
import khang.bryndisy.service.adapter.TasksOptimizer
import org.bson.types.ObjectId
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SchedulerSuite {
    private val optimizer: TasksOptimizer = SimpleOptimizer()
    private val initTasks: (Int) -> Task = { index: Int ->
        if (index < 5) {
            Task(ObjectId.get().toHexString(),
                    "say hello$index",
                    Duration.ofDays(index.toLong())
                    , LocalDateTime.of(LocalDate.of(2020, 9, 5)
                    , LocalTime.of(2, 2)))
        } else {
            Task(ObjectId.get().toHexString(),
                    "say hello$index",
                    Duration.ofDays(index.toLong())
                    , LocalDateTime.of(LocalDate.of(2020, 9, 4)
                    , LocalTime.of(2, 2)))
        }
    }

    @Test
    fun `scheduler should optimize the tasks`() {
        val tasks: List<Task> = List(9, initTasks)
        val optimizedTasks = optimizer.optimizeTasks(tasks)

        print("Now is: ${LocalDate.now().dayOfYear} \n")

        for (task in optimizedTasks.get()) print("(Sta:${task.startDate.dayOfYear}" +
                ", Dur:${task.duration.toDays()}" +
                ", Dea:${task.deadline.dayOfYear})\n")
    }
}