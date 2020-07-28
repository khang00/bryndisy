package khang.bryndisy.service.adapter

import khang.bryndisy.model.Task
import khang.bryndisy.model.User
import java.time.Duration
import java.util.*


interface TasksOptimizer {
    fun optimizeTasks(user: User): Optional<User>
    fun optimizeTasks(tasks: List<Task>, offHours: Duration = Duration.ofHours(16)): Optional<List<Task>>
}