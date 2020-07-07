package khang.bryndisy.service

import khang.bryndisy.model.Schedule
import khang.bryndisy.repository.ScheduleRepository
import khang.bryndisy.service.adapter.ScheduleOptimizer
import khang.bryndisy.service.adapter.ScheduleService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class Scheduler @Autowired constructor(private val optimizer: ScheduleOptimizer,
                                       private val scheduleRepository: ScheduleRepository) : ScheduleService {

    override fun getSchedule(id: ObjectId): Optional<Schedule> {
        return scheduleRepository.findById(id)
    }

    override fun saveSchedule(schedule: Schedule): Schedule {
        return scheduleRepository.save(schedule)
    }

    override fun optimizeSchedule(schedule: Schedule): Optional<Schedule> {
        return optimizer.optimizeSchedule(schedule)
    }
}