package com.autothon.tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.autothon.core.Config;
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
import java.util.List;


public class Twitter {


	String id;
	
	int likeCount,retweetCount;
	String hashTags[];
	
	@Test
	public void getRetweetCount()
	{
		
		String response=returnResponse();
		List<Integer> arr=new ArrayList<Integer>();
		
		String arrStr[]=response.split("retweet_count");
		for(int i=1;i<arrStr.length;i++) {
			String strB=arrStr[i].replace(":","").replace("\"","").split(",")[0];
			arr.add(Integer.parseInt(strB));
		}
		
		Collections.sort(arr);
		retweetCount=arr.get(arr.size()-1);
		System.out.println(retweetCount);
		assertTrue(retweetCount!=0);
	}
	
	public JSONObject getJSONSample() {
		JSONObject mainJSON=new JSONObject();
		mainJSON.put("top_retweet_count", retweetCount);
		mainJSON.put("top_like_count", likeCount);
		JSONArray hashtagArray= new JSONArray();
		for(String hashTag:hashTags) {
			hashtagArray.put(hashTag);
		}
		mainJSON.put("top_10_hashtags", hashtagArray);
		
		JSONArray bioGraphyArray= new JSONArray();
		for(String hashTag:hashTags) {
			bioGraphyArray.put(hashTag);
		}
		mainJSON.put("top_10_hashtags", bioGraphyArray);
		
		
		
		return mainJSON;
		
	}
	
	
	
	@Test
	public void getLikeCount()
	{
		
		String response=returnResponse();
		JsonPath js= new JsonPath(response);

		ArrayList<Integer> arr=new ArrayList<Integer>();
		
		String arrStr[]=response.split("favorite_count");
		for(int i=1;i<arrStr.length;i++) {
			String strB=arrStr[i].replace(":","").replace("\"","").split(",")[0];
			arr.add(Integer.parseInt(strB));
		}
		
		Collections.sort(arr);
		likeCount=arr.get(arr.size()-1);
		System.out.println(likeCount);
		assertTrue(likeCount!=0);
		
	}
	
	//@Test
	public void getHashtag()
	{	
		String response=returnResponse();
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
	
	
	String returnResponse() {
		RestAssured.baseURI=Config.APIHost;
		Response res=	given().auth().oauth(Config.ConsumerKey, Config.ConsumerSecret, Config.Token, Config.TokenSecret)
			.queryParam("count", "50")
			.when().get("/home_timeline.json?screen_name=stepin_forum").then().extract().response();
		
		String response =res.asString();
		return response;
	}
	
	
	@Test
	public void postJSON()
	{
		JSONObject resp=getJSONSample();
		RestAssured.baseURI="https://cgi-lib.berkeley.edu/ex/fup.html";
		Response res=	given().
				body(resp).
				when().post().
				then().extract().response();
		
		
		String response =res.asString();
		System.out.println(response);
	}
	
	
}

