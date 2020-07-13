package khang.bryndisy.controller

import khang.bryndisy.model.Task
import khang.bryndisy.model.User
import khang.bryndisy.service.adapter.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/")
@RestController
class UserController @Autowired constructor(val userService: UserService) {
    @GetMapping
    fun getUserById(@RequestParam("id") id: Optional<String>): ResponseEntity<List<User>> {
        return if (id.isPresent) {
            val user = userService.getUserById(id.get())
            if (user.isPresent) {
                ResponseEntity.ok(listOf(user.get()))
            } else {
                ResponseEntity.notFound().build()
            }
        } else {
            return ResponseEntity.ok(userService.getAllUsers())
        }
    }

    @GetMapping("/optimizedTasks/{id}")
    fun getUserWithOptimizedTasks(@RequestParam("id") id: String): ResponseEntity<User> {
        val user = userService.getUserWithOptimizedTasks(id)
        return if (user.isPresent) {
            ResponseEntity.ok(user.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity.ok(userService.createUser(user))
    }

    @PostMapping("/userTask")
    fun createTaskForUser(@RequestBody payload: Pair<String, Task>): ResponseEntity<User> {
        val (id: String, task: Task) = payload
        val userWrapper = userService.createTaskForUser(id, task)
        return if (userWrapper.isPresent) {
            ResponseEntity.ok(userWrapper.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }
}