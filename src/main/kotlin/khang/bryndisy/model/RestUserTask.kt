package khang.bryndisy.model

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class RestUserTask(override val id: String? = null,
                        override val name: String? = null,
                        override val duration: Duration? = null,
                        override val deadline: LocalDateTime? = null,
                        override val completed: Boolean? = null,
                        override val startDate: LocalDateTime? = null,
                        override val priority: Int? = null,
                        val deadlineDate: LocalDate? = null,
                        val deadlineTime: LocalTime? = null) : Task