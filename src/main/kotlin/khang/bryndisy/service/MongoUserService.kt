package khang.bryndisy.service

import khang.bryndisy.model.Task
import khang.bryndisy.model.User
import khang.bryndisy.repository.UserRepository
import khang.bryndisy.service.adapter.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class MongoUserService @Autowired constructor(val userRepository: UserRepository) : UserService {
    override fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    override fun createUser(user: User): User {
        return userRepository.save(user)
    }

    override fun createTaskForUser(id: String, task: Task): Optional<User> {
        val userWrapper = userRepository.findById(id)
        return if (userWrapper.isPresent) {
            val user = userWrapper.get()
            val userWithTaskAdded = user.copy(tasks = user.tasks + Pair(task.id, task))
            Optional.of(userRepository.save(userWithTaskAdded))
        } else {
            Optional.empty()
        }
    }
}