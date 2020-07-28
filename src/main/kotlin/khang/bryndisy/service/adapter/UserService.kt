package khang.bryndisy.service.adapter

import khang.bryndisy.model.Task
import khang.bryndisy.model.User
import java.util.*

interface UserService {
    fun getAllUsers(): List<User>
    fun getUserById(id: String): Optional<User>
    fun createUser(user: User): Optional<User>
    fun createTaskForUser(id: String, task: Task): Optional<User>
    fun getUserWithOptimizedTasks(id: String): Optional<User>
    fun updateTaskOfUser(user: User, updatedTask: Task): User
}