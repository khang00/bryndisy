package khang.bryndisy.service.adapter

import khang.bryndisy.model.Schedule
import org.bson.types.ObjectId
import java.util.*


interface ScheduleService {
    fun getSchedule(id: ObjectId): Optional<Schedule>
    fun saveSchedule(schedule: Schedule): Schedule
    fun optimizeSchedule(schedule: Schedule): Optional<Schedule>
}