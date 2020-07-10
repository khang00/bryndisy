package khang.bryndisy.service.adapter

import khang.bryndisy.model.Task
import khang.bryndisy.model.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

interface UserService {
    fun getAllUsers(): List<User>
    fun createUser(user: User): User
    fun createTaskForUser(id: String, task: Task): Optional<User>
}