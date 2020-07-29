package khang.bryndisy.configuration

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import khang.bryndisy.model.UserTask
import org.springframework.boot.jackson.JsonComponent

@JsonComponent
class TaskJsonSerializer : JsonSerializer<UserTask>() {
    override fun serialize(value: UserTask?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        if (gen != null && value != null && serializers != null) {
            gen.writeStartObject()
            gen.writeStringField("id", value.id)
            gen.writeStringField("name", value.name)
            gen.writeNumberField("duration", value.duration.toHours())
            gen.writeObjectField("deadlineDate", value.deadline.toLocalDate())
            gen.writeObjectField("deadlineDate", value.deadline.toLocalTime())
            gen.writeBooleanField("completed", value.completed)
            gen.writeNumberField("priority", value.priority)
            gen.writeObjectField("startDate", value.startDate)
            gen.writeEndObject()
        }
    }
}