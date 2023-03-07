package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.UserPOJO;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	UserPOJO userpayload;
	public Logger logger; // for logs
	
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
		
		//logs
				logger= LogManager.getLogger(this.getClass());
				
				logger.debug("debugging.....");
	}
	
	
	@Test(priority=1)
	void test_createUser() {
		
		logger.info("********** Creating user  ***************");
		
		Response response = UserEndPoints.createUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("**********User is creatged  ***************");
						
	}
	
	@Test(priority=2)
	void test_getuserbyname() {
		logger.info("********** Reading User Info ***************");
		Response response = UserEndPoints.getUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**********User info  is displayed ***************");
				
	}

	@Test(priority=3)
	void test_updateUser() {
		
		// update data  using payload
		logger.info("********** Updating User ***************");
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		
		Response response = UserEndPoints.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********** User updated ***************");
		// check the data for update user
		Response responseAfterUpdate = UserEndPoints.getUser(this.userpayload.getUsername());
		responseAfterUpdate.then().log().all();
		
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
						
	}
	
	@Test(priority=4)
	void test_deleteuserbyname() {
		logger.info("**********   Deleting User  ***************");
		
		Response response = UserEndPoints.deleteUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********** User deleted ***************");
				
	}
}
