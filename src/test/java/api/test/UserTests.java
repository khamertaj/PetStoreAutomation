package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.configtests.Configtest;
import api.endpoints.UserEndPoints;
import api.payload.UserPOJO;
import io.restassured.response.Response;

public class UserTests extends Configtest{
	
	Faker faker;
	UserPOJO userpayload;
	
	@BeforeClass
	void setup() {
		
		faker = new Faker();
		userpayload = new UserPOJO();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		userpayload.setPassword(faker.internet().password());
		userpayload.setPhone(faker.phoneNumber().cellPhone());	
	}
	
	
	@Test(priority=1)
	void test_createUser() {
		
		Response response = UserEndPoints.createUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
						
	}
	
	@Test(priority=2)
	void test_getuserbyname() {
		
		Response response = UserEndPoints.getUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
				
	}

	@Test(priority=3)
	void test_updateUser() {
		
		// update data  using payload
		
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		
		Response response = UserEndPoints.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// check the data for update user
		Response responseAfterUpdate = UserEndPoints.getUser(this.userpayload.getUsername());
		responseAfterUpdate.then().log().all();
		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
						
	}
	
	@Test(priority=4)
	void test_deleteuserbyname() {
		
		Response response = UserEndPoints.deleteUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
				
	}
}
