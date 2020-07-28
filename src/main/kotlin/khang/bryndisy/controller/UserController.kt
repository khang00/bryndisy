package khang.bryndisy.controller

import khang.bryndisy.model.RestUserTask
import khang.bryndisy.model.User
import khang.bryndisy.model.UserTask
import khang.bryndisy.service.adapter.AuthenticationService
import khang.bryndisy.service.adapter.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/")
@RestController
class UserController @Autowired constructor(private val userService: UserService,
                                            private val authenticationService: AuthenticationService) {
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

    @GetMapping("/optimizedTasks")
    fun getUserWithOptimizedTasks(@RequestParam("id") id: String): ResponseEntity<User> {
        val user = userService.getUserWithOptimizedTasks(id)
        return if (user.isPresent) {
            ResponseEntity.ok(user.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/authenticate")
    fun authenticateUser(@RequestBody user: User): ResponseEntity<User> {
        return authenticationService.authenticate(user)
                .map { ResponseEntity.ok(it) }
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build())
    }

    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        return userService.createUser(user)
                .map { ResponseEntity.ok(it) }
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build())
    }

    @PostMapping("/userTask")
    fun createTaskForUser(@RequestBody payload: Pair<String, UserTask>): ResponseEntity<User> {
        val (userId: String, userTask: UserTask) = payload
        val userWrapper = userService.createTaskForUser(userId, userTask)
        return if (userWrapper.isPresent) {
            ResponseEntity.ok(userWrapper.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/userTask")
    fun updateTaskOfUser(@RequestBody payload: Pair<String, RestUserTask>): ResponseEntity<User> {
        val (userId: String, task: RestUserTask) = payload
        return userService.getUserById(userId).map { user ->
            val updatedTasks = user.tasks.filterKeys { it == task.id }.mapValues { UserTask.union(it.value, task) }
            userService.createUser(user.copy(tasks = user.tasks + updatedTasks))
                    .map { ResponseEntity.ok(it) }
                    .orElse(ResponseEntity.notFound().build())
        }.orElse(ResponseEntity.notFound().build())
    }
}