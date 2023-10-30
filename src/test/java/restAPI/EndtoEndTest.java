package restAPI;
import org.testng.annotations.Test;

import org.testng.Assert;
import io.restassured.response.Response;

	public class EndtoEndTest extends Methods {

		@Test
		public void runtestCases() {
			
			Response response = getEmployee();
			Assert.assertEquals(response.statusCode(), 200);
			Assert.assertEquals(numberOfEmployees, 3);

			
			response = createNewEmployee("John", "Dae", "100000", "johnDae@gmail.com");
			Assert.assertEquals(response.statusCode(), 201);
			Assert.assertEquals(employeeName, "John");
			response = getEmployee();
			numberOfEmployees = response.jsonPath().getList("employees").size();
			Assert.assertEquals(numberOfEmployees, 4);

			response = updateEmployee("5");
			Assert.assertEquals(response.statusCode(), 200);
			Assert.assertEquals(employeeName, "Tom");
			Assert.assertNotEquals(response.jsonPath().getString("firstName"), "John");


			response = getEmployee("5");
			Assert.assertEquals(response.statusCode(), 200);
			Assert.assertEquals(employeeName, "Tom");
		
			response = deleteEmployee("5");
			Assert.assertEquals(response.statusCode(), 200);
			response=getEmployee("5");
			Assert.assertNotEquals(response.jsonPath().getString("firstName"), "Tom");

			response=getEmployee("5");
			Assert.assertEquals(response.statusCode(), 400);
			
			
			response = getEmployee();
			Assert.assertEquals(response.statusCode(), 200);
			Assert.assertEquals(numberOfEmployees, 3);
		}
	}

