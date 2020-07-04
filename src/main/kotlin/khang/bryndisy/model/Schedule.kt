package khang.bryndisy.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Schedule(@Id val id: ObjectId, val name: String, val tasks: List<Task>)