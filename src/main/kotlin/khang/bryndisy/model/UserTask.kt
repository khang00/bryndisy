package khang.bryndisy.model

import org.bson.types.ObjectId
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class UserTask(override val id: String = ObjectId.get().toHexString(),
                    override val name: String,
                    override val duration: Duration,
                    override val deadline: LocalDateTime,
                    override val completed: Boolean = false,
                    override val startDate: LocalDateTime = LocalDateTime.of(LocalDate.now(),
                            LocalTime.of(LocalTime.now().hour, LocalTime.now().minute))) : Task {
    companion object {
        fun union(userTask: UserTask, restTask: RestUserTask): UserTask {
            return userTask.copy(id = restTask.id ?: userTask.id,
                    name = restTask.name ?: userTask.name,
                    duration = restTask.duration ?: userTask.duration,
                    deadline = restTask.deadline ?: userTask.deadline,
                    completed = restTask.completed ?: userTask.completed,
                    startDate = restTask.startDate ?: userTask.startDate
            )
        }
    }
}