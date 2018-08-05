package RestProject.RestProject;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ByZipCode extends TestBase {
	
	private String ZipCode = "75006";
	private String Country = "us";
	private String APP_ID;

	public ByZipCode() {
			super();
			APP_ID = getAPP_ID();
		}
	@Test
	public void Happy_Path_For_ZipCode() {
		given()
			.queryParam("zip", ZipCode+","+Country)
			.queryParam("APPID", APP_ID).
		when().
	        get(getApplicationUrl()).
	    then().
		    assertThat().
		    statusCode(200).
		    and().
	        contentType(ContentType.JSON).
	        and().
	        body("name", equalTo("Lewisville"));
	}
	@Test
	public void Sad_Path_For_ZipCode_BadRequest() {
		given()
			.queryParam("zip", "," + Country)
			.queryParam("APPID", APP_ID).
		when().
	        get(getApplicationUrl()).
	    then().
		    assertThat().
		    statusCode(400);
	}
	
	@Test
	public void Sad_Path_For_ZipCode_NotFound() {
		given()
			.queryParam("zip", Country)
			.queryParam("APPID", APP_ID).
		when().
	        get(getApplicationUrl()).
	    then().
		    assertThat().
		    statusCode(404);
	}
	
	@Test
	public void Sad_Path_For_ZipCode_UnAuthorized() {
		given()
			.queryParam("zip", ZipCode +","+Country);
			
		when().
	        get(getApplicationUrl()).
	    then().
		    assertThat().
		    statusCode(401);
	}
	

}
