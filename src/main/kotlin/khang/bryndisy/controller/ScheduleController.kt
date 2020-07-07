package khang.bryndisy.controller

import khang.bryndisy.model.Schedule
import khang.bryndisy.service.adapter.ScheduleService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/schedule")
@RestController
class ScheduleController @Autowired constructor(private val scheduleService: ScheduleService) {
    @PostMapping(path = ["/optimze"])
    fun optimizeSchedule(@RequestBody id : ObjectId) :ResponseEntity<Schedule> {
        val schedule = scheduleService.getSchedule(id)
        return if (schedule.isPresent) {
            ResponseEntity.ok(schedule.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun saveSchedule(@RequestBody schedule: Schedule) {
        scheduleService.saveSchedule(schedule)
    }
}