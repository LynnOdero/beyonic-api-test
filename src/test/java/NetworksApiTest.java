import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

public class NetworksApiTest {

    final String authKey = "ab594c14986612f6167a975e1c369e71edab6900";
    final String url = "https://api.beyonic.com/api/networks";

    // The response status being returned is 403 Forbidden instead of 200 OK hence the test should be failing
    @Test
    public void Get_Networks_Endpoint_Should_Should_Return_Non_Zero_Number_Of_Results() {
        HttpHelper.ResponseDetails response = HttpHelper.sendGetRequest(url, authKey);
        Assert.assertEquals(200, response.httpResponseStatus);
    }
}
