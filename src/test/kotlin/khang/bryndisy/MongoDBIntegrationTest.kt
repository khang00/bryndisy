package khang.bryndisy

import khang.bryndisy.model.UserTask
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
        val task = UserTask(ObjectId.get().toHexString(),
                "say hello",
                Duration.ofHours(2),
                LocalDateTime.of(2020, 12, 18, 5, 3))

        // when
        mongoTemplate.save(task)

        // then
        assertThat(mongoTemplate.findById(task.id, UserTask::class.java))
                .isNotNull
                .isEqualTo(task)
    }
}