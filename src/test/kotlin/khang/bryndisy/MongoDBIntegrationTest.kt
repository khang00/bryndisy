package khang.bryndisy

import com.mongodb.BasicDBObjectBuilder
import com.mongodb.DBObject
import khang.bryndisy.model.Task
import org.assertj.core.api.Assertions.assertThat
import org.bson.types.ObjectId
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.data.mongodb.core.MongoTemplate
import java.time.Duration
import java.time.LocalDateTime

@DataMongoTest
class MongoDBIntegrationTest {
    @Test
    fun test(@Autowired mongoTemplate: MongoTemplate) {
        val task = Task(ObjectId.get().toHexString(),
                "say hello",
                Duration.ofHours(2),
                LocalDateTime.of(2020, 12, 18, 5, 3))

        // when
        mongoTemplate.save(task)

        // then
        assertThat(mongoTemplate.findById(task.id, Task::class.java))
                .isNotNull
                .isEqualTo(task)
    }
}