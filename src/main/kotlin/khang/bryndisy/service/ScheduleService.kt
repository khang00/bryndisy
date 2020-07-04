package khang.bryndisy.service

import khang.bryndisy.model.Schedule
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.util.*

@Service
interface ScheduleService {
    fun getSchedule(id: ObjectId): Schedule
    fun optimizeSchedule(schedule: Schedule): Optional<Schedule>
}