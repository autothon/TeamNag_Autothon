package com.autothon.rest;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

public class RequestPayload {
   public String resource;
   public HashMap<String, String> qParams;
   public HashMap<String, String> params;
   public HashMap<String, String> headers;
   public String body;
   public ContentType contentType;
   public String cookieName;
   public RequestSpecification httpRequest;
   
   public RequestPayload(String requestType) {
	   resource="";
	   headers=new HashMap<String, String>();
	   cookieName="";
	   httpRequest.contentType(contentType);
	   if(requestType.toUpperCase().equals("GET"))	   			
	   			qParams=new HashMap<String, String>();
	   else {
		   body="";
		   params=new HashMap<String, String>();
	   }
	   
   }
}