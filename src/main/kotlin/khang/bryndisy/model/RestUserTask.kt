package khang.bryndisy.model

import java.time.Duration
import java.time.LocalDateTime

data class RestUserTask(override val id: String? = null,
                        override val name: String? = null,
                        override val duration: Duration? = null,
                        override val deadline: LocalDateTime? = null,
                        override val completed: Boolean? = null,
                        override val startDate: LocalDateTime? = null) : Task