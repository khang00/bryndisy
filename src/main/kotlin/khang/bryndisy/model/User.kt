package khang.bryndisy.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Duration

@Document
data class User(@Id val id: String = ObjectId.get().toHexString(),
                val name: String,
                val tasks: Map<String, Task> = hashMapOf(),
                val workDurationPerDay: Duration = Duration.ofHours(8))