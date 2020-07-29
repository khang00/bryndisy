package khang.bryndisy.model

import java.time.Duration
import java.time.LocalDateTime

interface Task {
    val id: String?
    val name: String?
    val duration: Duration?
    val deadline: LocalDateTime?
    val completed: Boolean?
    val startDate: LocalDateTime?
    val priority: Int?
}