package khang.bryndisy.service.adapter

import khang.bryndisy.model.User
import java.util.*

interface AuthenticationService {
    fun authenticate(user :User) :Optional<User>
}