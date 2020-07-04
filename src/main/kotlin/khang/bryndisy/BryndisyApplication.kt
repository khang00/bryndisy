package khang.bryndisy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@EnableMongoRepositories(basePackages = ["khang.bryndisy.repository"])
@SpringBootApplication
class BryndisyApplication

fun main(args: Array<String>) {
    runApplication<BryndisyApplication>(*args)
}
