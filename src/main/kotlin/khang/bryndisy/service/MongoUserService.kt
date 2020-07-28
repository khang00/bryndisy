package khang.bryndisy.service

import khang.bryndisy.model.Task
import khang.bryndisy.model.User
import khang.bryndisy.repository.UserRepository
import khang.bryndisy.service.adapter.TasksOptimizer
import khang.bryndisy.service.adapter.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class MongoUserService @Autowired constructor(val userRepository: UserRepository,
                                              val tasksOptimizer: TasksOptimizer) : UserService {

    override fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    override fun createUser(user: User): Optional<User> {
        return userRepository.findByNameAndPassword(user.name, user.password)
                .map { Optional.empty<User>() }
                .orElse(Optional.of(userRepository.save(user)))
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

    override fun getUserWithOptimizedTasks(id: String): Optional<User> {
        val user = getUserById(id)
        return if (user.isPresent) {
            tasksOptimizer.optimizeTasks(user.get())
        } else {
            user
        }
    }

    override fun getUserById(id: String): Optional<User> {
        return userRepository.findById(id)
    }

    override fun updateTaskOfUser(userId: String, updatedTask: Task): Optional<User> {
        return userRepository.findById(userId).map { user ->
            val updatedTasks = user.tasks.filterKeys { it == updatedTask.id }.mapValues { updatedTask }
            userRepository.save(user.copy(tasks = user.tasks + updatedTasks))
        }
    }
}