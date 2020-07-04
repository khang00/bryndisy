package khang.bryndisy.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.hateoas.RepresentationModel
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Document
data class Task(@Id val id: ObjectId,
                val name: String,
                val duration: Duration,
                val deadline: LocalDateTime,
                val startDate: LocalDateTime = LocalDateTime.of(LocalDate.now(),
                        LocalTime.of(LocalTime.now().hour, LocalTime.now().minute)))