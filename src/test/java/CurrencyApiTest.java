import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

public class CurrencyApiTest {

    final String authKey = "ab594c14986612f6167a975e1c369e71edab6900";
    final String url = "https://api.beyonic.com/api/currencies";
    @Test
    public void Get_Currencies_Endpoint_Should_Return_Non_Zero_Number_Of_Results() throws Exception{
        HttpHelper.ResponseDetails response = HttpHelper.sendGetRequest(url, authKey);
        ObjectMapper mapper = new ObjectMapper();
        CurrenciesResponse currenciesResponse = mapper.readValue(response.response, CurrenciesResponse.class);
        Assert.assertEquals(200, response.httpResponseStatus);
        Assert.assertTrue(currenciesResponse.getCount() > 0);
    }



    public static class CurrenciesResponse {
        public int count;
        public String next;
        public String previous;
        public  Currency [] currency;

        @JsonCreator
        public CurrenciesResponse(@JsonProperty("count") int count,
                                  @JsonProperty("next") String next,
                                  @JsonProperty("previous") String previous,
                                  @JsonProperty("results") Currency [] currency ) {
            this.count = count;
            this.next = next;
            this.previous = previous;
            this.currency = currency;
        }

        @JsonGetter("count")
        public int getCount() {
            return count;
        }
    }

    public static class Currency {
       public int id;
       public String name;
       public float usdRate;
       public Country country;
       public String code;

       @JsonCreator
        public Currency(@JsonProperty("id") int id,
                        @JsonProperty("name") String name,
                        @JsonProperty("usd_rate") float usdRate,
                        @JsonProperty("country") Country country,
                        @JsonProperty("code") String code) {
            this.id = id;
            this.name = name;
            this.usdRate = usdRate;
            this.country = country;
            this.code = code;
        }
    }

    public static class Country {
       public String iso;
       public String iso3;
        public String isoNumeric;
        public String name;
        public String printableName;
        public String phonePrefix;
        public boolean supported;

        @JsonCreator
        public Country(@JsonProperty("iso") String iso,
                       @JsonProperty("iso3") String iso3,
                       @JsonProperty("iso_numeric") String isoNumeric,
                       @JsonProperty("name") String name,
                       @JsonProperty("printable_name") String printableName,
                       @JsonProperty("phone_prefix") String phonePrefix,
                       @JsonProperty("supported") boolean supported) {
            this.iso = iso;
            this.iso3 = iso3;
            this.isoNumeric = isoNumeric;
            this.name = name;
            this.printableName = printableName;
            this.phonePrefix = phonePrefix;
            this.supported = supported;
        }
    }
}
