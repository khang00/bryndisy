package khang.bryndisy.repository

import khang.bryndisy.model.Schedule
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ScheduleRepository : MongoRepository<Schedule, ObjectId>