package WeatherReport;

import io.restassured.response.Response;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class RestAssuredClient {
    String url;
    String appid;
    Response response;
    public RestAssuredClient(String url,String appid) {
        this.url = url;
        this.appid = appid;
    }

    public String getResponseBody(String queryparam) {
        this.response = given().contentType(ContentType.ANY).
                            queryParam("q", queryparam).
                            queryParam("appid", this.appid).get(this.url);
        return this.response.asString();                    
    }
}