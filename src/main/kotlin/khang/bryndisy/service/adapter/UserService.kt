package khang.bryndisy.service.adapter

import khang.bryndisy.model.UserTask
import khang.bryndisy.model.User
import java.util.*

interface UserService {
    fun getAllUsers(): List<User>
    fun getUserById(id: String): Optional<User>
    fun createUser(user: User): Optional<User>
    fun createTaskForUser(id: String, userTask: UserTask): Optional<User>
    fun getUserWithOptimizedTasks(id: String): Optional<User>
    fun updateTaskOfUser(user: User, updatedTasks: Map<String, UserTask>): User
}