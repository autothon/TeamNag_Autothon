package com.autothon.tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Map;
import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.DocumentContext;
import com.mongodb.util.JSON;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
		
		ArrayList<Integer> arr=new ArrayList<Integer>();
		
		String arrStr[]=response.split("retweet_count");
		for(int i=1;i<arrStr.length;i++) {
			String strB=arrStr[i].replace(":","").replace("\"","").split(",")[0];
			arr.get(Integer.parseInt(strB));
		}
		
		Collections.sort(arr);
		int a=arr.get(0);
		
		assertTrue(a==45);
		
		

		
		
	}
	
	public void getLikeCount()
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

		ArrayList<Integer> arr=new ArrayList<Integer>();
		
		String arrStr[]=response.split("favorite_count");
		for(int i=1;i<arrStr.length;i++) {
			String strB=arrStr[i].replace(":","").replace("\"","").split(",")[0];
			arr.get(Integer.parseInt(strB));
		}
		
		Collections.sort(arr);
		int a=arr.get(0);
		
		assertTrue(a==45);
		
	}
	
	public void getHashtag()
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
	
	
}

