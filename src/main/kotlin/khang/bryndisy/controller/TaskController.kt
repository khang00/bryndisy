package khang.bryndisy.controller

import khang.bryndisy.model.Task
import khang.bryndisy.repository.TaskRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TaskController @Autowired constructor(private val taskRepo: TaskRepository) {

    @GetMapping("/task")
    fun getAllTasks(): ResponseEntity<List<Task>> {
        return ResponseEntity.ok(taskRepo.findAll())
    }

    @GetMapping("/task?id")
    fun getTaskById(@RequestParam("id") id: ObjectId): ResponseEntity<Task> {
        val task = taskRepo.findById(id)
        print(task.toString())
        return if (task.isPresent) {
            ResponseEntity.ok(task.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/task")
    fun createTask(@RequestBody task: Task): ResponseEntity<Task> {
        return ResponseEntity.ok(taskRepo.save(task))
    }
}