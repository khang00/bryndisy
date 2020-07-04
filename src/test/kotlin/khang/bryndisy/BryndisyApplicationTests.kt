package khang.bryndisy

import org.bson.types.ObjectId
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest("SpringBootTest.WebEnvironment.MOCK,")
@AutoConfigureMockMvc
class BryndisyApplicationTests {
	@Autowired
	private lateinit var mockMvc: MockMvc

	@Test
	fun contextLoads() {

	}

	@Test
	fun `task controller should return list of tasks or empty list when empty`() {
		mockMvc.get("/task").andExpect {
			status { isOk }
			content { contentType(MediaType.APPLICATION_JSON) }
			content { json("[]") }
		}
	}
}
