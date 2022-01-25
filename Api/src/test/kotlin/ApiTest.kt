import com.example.api.ApiClient
import org.junit.Assert.assertNotNull
import org.junit.Test

class ApiTest {
    @Test
    fun `get question`(){
      val response =   ApiClient.api.getQuestions().execute()
        assertNotNull(response.body()?.responseCode)
    }
}