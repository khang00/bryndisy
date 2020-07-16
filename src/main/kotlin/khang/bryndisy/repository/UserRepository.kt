package khang.bryndisy.repository

import khang.bryndisy.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : MongoRepository<User, String> {
    fun findByNameAndPassword(name: String, password: String): Optional<User>
}