package khang.bryndisy.service.adapter

import khang.bryndisy.model.Schedule
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*


interface ScheduleOptimizer {
    fun optimizeSchedule(schedule: Schedule): Optional<Schedule>
}