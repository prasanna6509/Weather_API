package RestProject.RestProject;


	import io.restassured.http.ContentType;

	import static io.restassured.RestAssured.*;
	import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
	
	public class ByCityName extends TestBase {

	    private String City = "Kansas";
		private String Country = "us";
		private String APP_ID;

		public ByCityName() {
				super();
				APP_ID = getAPP_ID();
			}
		@Test
		public void Happy_Path_For_CityName() {
			given()
			.queryParam("q", City+","+Country)
			.queryParam("APPID", APP_ID).
			when().
		        get(getApplicationUrl()).
		    then().
			    assertThat().
			    statusCode(200).
			    and().
		        contentType(ContentType.JSON).
		        and().
		        body("name", equalTo("Kansas"));
		}
		@Test
		public void Sad_Path_For_CityName_BadRequest() {
			given()
				.queryParam("","")
				.queryParam("APPID", APP_ID).
			when().
		        get(getApplicationUrl()).
		    then().
			    assertThat().
			    statusCode(400);
		}
		
		@Test
		public void Sad_Path_For_CityName_NotFound() {
			given()
				.queryParam("q", Country)
				.queryParam("APPID", APP_ID).
			when().
		        get(getApplicationUrl()).
		    then().
			    assertThat().
			    statusCode(404);
		}
		
		@Test
		public void Sad_Path_For_CityName_UnAuthorized() {
			given()
				.queryParam("q", City +","+Country).
			when().
		        get(getApplicationUrl()).
		    then().
			    assertThat().
			    statusCode(401);
		}
		
	

}
