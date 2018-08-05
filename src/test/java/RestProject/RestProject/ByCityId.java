package RestProject.RestProject;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ByCityId extends TestBase {
	
	private String CityId = "2172797";
	private String APP_ID;

	public ByCityId() {
			super();
			APP_ID = getAPP_ID();
		}
	
	@Test
	public void Happy_Path_For_CityId() {
		given()
			.queryParam("id", CityId)
			.queryParam("APPID", APP_ID).
		when().
	        get(getApplicationUrl()).
	    then().
		    assertThat().
		    statusCode(200).
		    and().
	        contentType(ContentType.JSON).
	        and().
	        body("name", equalTo("Cairns"));
	}
	@Test
	public void Sad_Path_For_CityId_BadRequest() {
		given()
			.queryParam("id","")
			.queryParam("APPID", APP_ID).
		when().
	        get(getApplicationUrl()).
	    then().
		    assertThat().
		    statusCode(400);
	}
	
	
	
	@Test
	public void Sad_Path_For_CityId_UnAuthorized() {
		given()
			.queryParam("id", CityId).
		when().
	        get(getApplicationUrl()).
	    then().
		    assertThat().
		    statusCode(401);
	}
	

}



