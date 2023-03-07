package api.endpoints;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.UserPOJO;
import io.restassured.response.Response;

//User endpoints - created to handle CRUD requests
//created to perform Create, Read, Update, Delete, request to the USER API
public class UserEndPoints {
	
	
	public static Response createUser(UserPOJO payload) {
		
		Response response = given()
		  .contentType("application/json")
		  .accept("application/json")
		  .body(payload)
		.when()
		   .post(Routes.user_post_url);
		
		return response;       
		
	}
	
	public static Response getUser(String userName) {
		
		Response response = given()
		  .accept("application/json")
		  .pathParam("username", userName)
		.when()
		   .get(Routes.user_get_url);
		
		return response;       
		
	}
	
	public static Response updateUser(String userName, UserPOJO payload) {
		
		Response response = given()
		  .contentType("application/json")
		  .accept("application/json")
		  .body(payload)
		  .pathParam("username", userName)

		.when()
		   .put(Routes.user_put_url);
		
		return response;       
		
	}
	
	public static Response deleteUser(String userName) {
		
		Response response = given()
		  .accept("application/json")
		  .pathParam("username", userName)
		.when()
		   .delete(Routes.user_delete_url);
		
		return response;       
		
	}
	

}
