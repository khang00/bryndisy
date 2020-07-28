package khang.bryndisy.model

import org.bson.types.ObjectId
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Task(val id: String = ObjectId.get().toHexString(),
                val name: String,
                val duration: Duration,
                val deadline: LocalDateTime,
                val completed: Boolean = false,
                val startDate: LocalDateTime = LocalDateTime.of(LocalDate.now(),
                        LocalTime.of(LocalTime.now().hour, LocalTime.now().minute)))