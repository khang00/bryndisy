package khang.bryndisy.repository

import khang.bryndisy.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String>