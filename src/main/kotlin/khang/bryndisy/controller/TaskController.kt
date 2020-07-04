package khang.bryndisy.controller

import khang.bryndisy.model.Task
import khang.bryndisy.repository.TaskRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/task")
class TaskController @Autowired constructor(private val taskRepo: TaskRepository) {

    @GetMapping
    fun getAllTasks(): ResponseEntity<List<Task>> {
        return ResponseEntity.ok(taskRepo.findAll())
    }

    @PostMapping
    fun createTask(@RequestBody task: Task): ResponseEntity<Task> {
        return ResponseEntity.ok(taskRepo.save(task))
    }
}