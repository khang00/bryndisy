package khang.bryndisy.service

import khang.bryndisy.model.User
import khang.bryndisy.repository.UserRepository
import khang.bryndisy.service.adapter.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SimpleAuthenticator @Autowired constructor(private val userRepository: UserRepository) : AuthenticationService {
    override fun authenticate(user: User): Optional<User> {
        return userRepository.findByNameAndPassword(user.name, user.password)
    }
}