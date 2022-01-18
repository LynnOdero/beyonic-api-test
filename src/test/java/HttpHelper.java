import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class HttpHelper {

    public static ResponseDetails sendGetRequest(String url, String auth) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Authorization", String.format("Token %s", auth));
            HttpResponse response = client.execute(httpGet);
            return new ResponseDetails(EntityUtils.toString(response.getEntity()),
                    response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseDetails("An error occurred while processing request", 500);
    }

    public static class ResponseDetails {
        public String response;
        public int httpResponseStatus;

        public ResponseDetails(String response, int httpResponseStatus) {
            this.response = response;
            this.httpResponseStatus = httpResponseStatus;
        }
    }
}
