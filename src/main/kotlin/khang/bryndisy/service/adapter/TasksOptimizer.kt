package khang.bryndisy.service.adapter

import khang.bryndisy.model.Schedule
import khang.bryndisy.model.Task
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*


interface TasksOptimizer {
    fun optimizeTasks(tasks: List<Task>): Optional<List<Task>>
}