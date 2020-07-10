package khang.bryndisy.service.adapter

import khang.bryndisy.model.Task
import khang.bryndisy.model.User
import java.util.*

interface UserService {
    fun getAllUsers(): List<User>
    fun getTasksOfUser(id: String): Optional<List<Task>>
    fun getOptimizedTasksOfUser(id: String): Optional<List<Task>>
    fun createUser(user: User): User
    fun createTaskForUser(id: String, task: Task): Optional<User>
}