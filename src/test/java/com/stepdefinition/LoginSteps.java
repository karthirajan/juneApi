package com.stepdefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pojo.Category;
import com.pojo.Pet;
import com.pojo.Tags;

import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginSteps  {

	@Given("^validating pet post method$")
	public void validating_pet_post_method() throws Throwable {
	    
		System.out.println("in");
		RequestSpecification rest = RestAssured.with();
		RequestSpecification request = rest.given().contentType(ContentType.JSON).baseUri("https://petstore.swagger.io/v2");

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		
		Pet pet = new Pet();
		
		pet.setId(3456);
		
		Category cat = new Category();
		cat.setId(1);
		cat.setName("dogs");
		pet.setCategory(cat);
		
		pet.setName("Tin Tin");
		
		List<String> photo = new ArrayList<String>();
		photo.add("");
		photo.add("");
		
		pet.setPhotoUrls(photo);
		
		
		List<Tags> t = new ArrayList<Tags>();
		Tags t1 = new Tags();
		t1.setId(1);
		t1.setName("GS");
		
		Tags t2 = new Tags();
		t2.setId(2);
		t2.setName("GS-Cross");
		
		t.add(t1);
		t.add(t2);
		
		pet.setTags(t);
		
		pet.setStatus("available");
		
		Response response = request.body(pet).headers(headers).post("/pet");
		//ResponseBody body = response.getBody();
		int i = response.getStatusCode();
		System.out.println(i);
		
	}
}