package khang.bryndisy.service.adapter

import khang.bryndisy.model.UserTask
import khang.bryndisy.model.User
import java.time.Duration
import java.util.*


interface TasksOptimizer {
    fun optimizeTasks(user: User): Optional<User>
    fun optimizeTasks(userTasks: List<UserTask>, offHours: Duration = Duration.ofHours(16)): Optional<List<UserTask>>
}