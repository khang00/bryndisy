package khang.bryndisy.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Duration
import java.time.LocalDateTime

@Document
data class Task(@Id val id: ObjectId, val name: String, val duration: Duration, val deadline: LocalDateTime)