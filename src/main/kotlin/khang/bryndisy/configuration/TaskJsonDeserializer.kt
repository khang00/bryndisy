package khang.bryndisy.configuration

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.BooleanNode
import com.fasterxml.jackson.databind.node.LongNode
import com.fasterxml.jackson.databind.node.TextNode
import khang.bryndisy.model.RestUserTask
import org.springframework.boot.jackson.JsonComponent
import java.time.Duration
import java.time.LocalDateTime

@JsonComponent
class TaskJsonDeserializer : JsonDeserializer<RestUserTask>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): RestUserTask {
        return if (p != null && ctxt != null) {
            val treeNode: TreeNode = p.codec.readTree(p)

            val id: String? = (treeNode["id"] as? TextNode)?.asText()
            val name: String? = (treeNode["name"] as? TextNode)?.asText()
            val duration: Long? = (treeNode["duration"] as? LongNode)?.asLong()
            val deadline: LocalDateTime? = (treeNode["deadline"] as? TextNode)?.asText()?.let { LocalDateTime.parse(it) }
            val completed: Boolean? = (treeNode["completed"] as? BooleanNode)?.asBoolean()
            val priority: Int? = (treeNode["priority"] as? LongNode)?.asInt()
            val startDate: LocalDateTime? = (treeNode["startDate"] as? TextNode)?.asText()?.let { LocalDateTime.parse(it) }

            RestUserTask(id, name, duration?.let { Duration.ofHours(it) }, deadline, completed, startDate, priority)
        } else {
            RestUserTask()
        }
    }
}