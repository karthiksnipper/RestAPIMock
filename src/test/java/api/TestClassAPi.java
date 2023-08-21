package api;
import static org.hamcrest.Matcher.*;

import java.text.ParseException;

import static io.restassured.RestAssured.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import pojo.createbooking.BookingCreatePojo;

public class TestClassAPi {
	
	
	
@Test(priority = 2)	
public void createToken(ITestContext context) throws ParseException {
//	BookingCreatePojo jo = new BookingCreatePojo();
//	jo.setFirstname("Jim");
//	jo.setLastname("Brown");
//	jo.setTotalprice(111);
//	jo.setDepositpaid(true);
//	     jo.setCheckin("2018-01-01");
//	     jo.setCheckout("2019-01-01");
//	jo.setAdditionalneeds("Breakfast");
	
	
	String authToken =given()
		.contentType("application/json")
		.body("{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}")
	.when()
		.post("https://restful-booker.herokuapp.com/auth")
	.then()
	     .statusCode(200).
	     log()
		 .all()
		 .extract()
		 .jsonPath()
		  .get("token");
	
	System.out.println("printed:"+authToken);
	
	context.setAttribute("token_access",authToken);
	
}


@Test(priority = 1)
public void restBookerCreate(ITestContext context) {
	
	
int bookingId =given()
			.baseUri("https://restful-booker.herokuapp.com/")
			.basePath("booking")
			.contentType("application/json")
			.body("{\r\n"
					+ "    \"firstname\" : \"Jim\",\r\n"
					+ "    \"lastname\" : \"Brown\",\r\n"
					+ "    \"totalprice\" : 111,\r\n"
					+ "    \"depositpaid\" : true,\r\n"
					+ "    \"bookingdates\" : {\r\n"
					+ "        \"checkin\" : \"2018-01-01\",\r\n"
					+ "        \"checkout\" : \"2019-01-01\"\r\n"
					+ "    },\r\n"
					+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
					+ "}")
		.when()
			.post()
		.then().statusCode(200)
		     .
		     log()
			 .all()
			 .extract()
			 .jsonPath()
			 .get("bookingid");
		
		context.setAttribute("booking_Id", bookingId);
	System.out.println(bookingId);
}

@Test(priority = 3)
public void restBooker_Get(ITestContext context) {
	int bookingId=(Integer) context.getAttribute("booking_Id");
	
		given()
		    .baseUri("https://restful-booker.herokuapp.com/")
			.basePath("booking/"+bookingId)
			.accept("application/json")
	   .when()
			.get()
   	   .then()
   	        .statusCode(200)
			.log()
			.all();
	
}

@Test(priority = 4)
public void restBooker_Put(ITestContext context) {
	int bookingId=(Integer) context.getAttribute("booking_Id");
	String tokenGen =(String) context.getAttribute("token_access");
	System.out.println(tokenGen);
	given()
	.baseUri("https://restful-booker.herokuapp.com/")
	.basePath("booking/"+bookingId)
	.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
	.contentType("application/json").accept("application/json")
	.body("{\r\n"
			+ "    \"firstname\" : \"James\",\r\n"
			+ "    \"lastname\" : \"Brown\",\r\n"
			+ "    \"totalprice\" : 111,\r\n"
			+ "    \"depositpaid\" : true,\r\n"
			+ "    \"bookingdates\" : {\r\n"
			+ "        \"checkin\" : \"2018-01-01\",\r\n"
			+ "        \"checkout\" : \"2019-01-01\"\r\n"
			+ "    },\r\n"
			+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
			+ "}")
    .when()
	       .put()
    .then().statusCode(200)
     .
     log()
	 .all();
	
}

@Test(priority = 5)
public void restBooker_Delete(ITestContext context) {
	int bookingId=(Integer) context.getAttribute("booking_Id");
	String tokenGen =(String) context.getAttribute("token_access");
	
	given()                         
	.headers("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
	.baseUri("https://restful-booker.herokuapp.com/")
	.basePath("booking/"+bookingId)
	.when()
	     .delete()
	.then().statusCode(201)
	.
	log()
	.all();
	
}



}
