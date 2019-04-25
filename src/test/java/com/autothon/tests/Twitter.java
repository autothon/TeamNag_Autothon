package com.autothon.tests;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.mongodb.util.JSON;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;


//rahulonlinetutor@gmail.com
//https://mvnrepository.com/artifact/com.github.scribejava/scribejava-core/2.5.3

//https://mvnrepository.com/artifact/com.github.scribejava/scribejava-apis/2.5.3

public class Twitter {

	String ConsumerKey="HXBDwvuE0IWoBJrWhEP3awKeG";
	String ConsumerSecret="M5wLtxJqoA29XGPgdMg8ZD0KusF29JxNNepEyMKydVbMiWFrLB";
	String Token="477845563-xB1ZS6KtME9ghTFLJAAC5vq5XKt24aC6vYKKUdQz";
	String TokenSecret="MrDvlv98On06r6oVdLmgHvJyzVwPUeqmwKBGGOsdsd4P1";
	String id;
	@Test
	public void getRetweetCount()
	{
		
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
	Response res=	given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
		.queryParam("count", "50")
		.when().get("/home_timeline.json?screen_name=stepin_forum").then().extract().response();
	
	String response =res.asString();
	//String[] respStrings=response.split("retweet_count");
		/*
		 * int ret[]=new int[100]; for(int i=1;i<=respStrings.length;i++) { String
		 * str=respStrings[i].split(":")[1].split(",")[0];
		 * ret[--i]=(int)Integer.parseInt(str); } System.out.println(response);
		 */
	JsonPath js= new JsonPath(response);
	
	ArrayList< String> list=new ArrayList<String>();
	for(int i=0;i<50;i++) {
		HashMap<String, String> jso=js.get("entities["+i+"]");
		for(int j=0;j<jso.size();j++) {
			list.add(js.get("entities["+i+"].hashtags["+j +"0]").toString());
		}
	}
	
	System.out.println(list);
	
	
	
	
	
	}
	//@Test
	public void createTweet()
	{
		
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
	Response res=	given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
		.queryParam("status", "I am creating this tweet with Automation")
		.when().post("/update.json").then().extract().response();
	
	String response =res.asString();
	System.out.println(response);
	JsonPath js= new JsonPath(response);
	System.out.println("Below is the tweet added");
	//System.out.println(js.get("text"));
	//System.out.println(js.get("id"));
	id=js.get("id").toString();
	
	
	}
	//@Test
	public void E2E()
	{
		createTweet();
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
	Response res=	given().auth().oauth(ConsumerKey, ConsumerSecret, Token, TokenSecret)
		.when().post("/destroy/"+id+".json").then().extract().response();
	
	String response =res.asString();
	System.out.println(response);
	JsonPath js= new JsonPath(response);
	//System.out.println(js.get("text"));
	System.out.println("Tweet which got deleted with automation is below");
	//System.out.println(js.get("text"));
	//System.out.println(js.get("truncated"));
	
	
	}
	
	
}
