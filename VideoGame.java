package restassured_Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

public class VideoGame {
	
	@Test(priority=1)
	public void Get_all_games()
	{
		given()
		
		.when()
			.get("http://localhost:8080/app/videogames")
			
			.then()
			.statusCode(200);
	}
	//@Test(priority=2)
	public void Post_all_games()
	{
			HashMap obj= new HashMap();
			obj.put("id","200");
			obj.put("name", "Sam");
			obj.put("releaseDate", "2022-08-03");
			obj.put("reviewScore", "5");
			obj.put("category", "Adventure");
			obj.put("rating", "universal");
			
			Response res=
			
			given()
			 .contentType("application/json")
			 .body(obj)
			 
			 .when()
			 .post("http://localhost:8080/app/videogames")
			 
			 .then()
			 	.statusCode(200)
			 	.log().body()
			 	.extract().response();
			 	
			 	String jsonString=res.asString();
				Assert.assertEquals(jsonString.contains("Record Added Successfully"), true);
	}
	
	@Test(priority=3)
	public void Get_video_game()
	{
		given()
			.when()
			.get("http://localhost:8080/app/videogames/200")
			
			.then()
				.statusCode(200);		    
			    		
	}
	
	@Test(priority=4)
	public void Put_video_game()
	{
		HashMap obj= new HashMap();
		obj.put("id","200");
		obj.put("name", "John");
		obj.put("releaseDate", "2022-08-03");
		obj.put("reviewScore", "6");
		obj.put("category", "Adventure");
		obj.put("rating", "universal");
		
		given()
			.contentType("application/json")
			.body(obj)
			
			.when()
				.put("http://localhost:8080/app/videogames/200")
				
				.then()
					.statusCode(200)
					.log().body()
					.body("videoGame.id",equalTo("200"))
					.body("videoGame.name",equalTo("John"));					
	}
	
	@Test(priority=5)
	public void delete_video_game()
	{
		Response res=
		given()
			.when()
				.delete("http://localhost:8080/app/videogames/200")
				
				.then()
				.log().body()
				.extract().response();
				String jsonString=res.asString();
			Assert.assertEquals(jsonString.contains("Record Deleted Successfully"), true);
			
	}
	

}
