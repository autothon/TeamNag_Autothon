
package com.autothon.testing.restclient;

import io.restassured.response.Response;

public class RestAssuredRequest {
	
	

	   public static Response get(RequestPayload payload) {
		   	payload.httpRequest.queryParams(payload.qParams);
		   	payload.httpRequest.headers(payload.headers);
		   	payload.httpRequest.cookie(payload.cookieName);
		    Response resp = payload.httpRequest
		   		  .when().get(payload.resource, new Object[0])
		   		  .then().extract().response();
		   return resp;
		}
	   
	   public static Response post(RequestPayload payload) {
		   	payload.httpRequest.params(payload.params);
		   	payload.httpRequest.headers(payload.headers);
		   	payload.httpRequest.body(payload.body);
		   	Response resp = payload.httpRequest
		    	  .when().post(payload.resource, new Object[0])
		    	  .then().extract().response();
		    return resp;
		}
	   
	   public static Response put(RequestPayload payload) {
		   	payload.httpRequest.params(payload.params);
		   	payload.httpRequest.headers(payload.headers);
		   	payload.httpRequest.body(payload.body);
		   	  
		      Response resp = payload.httpRequest
		    		  .when().post(payload.resource, new Object[0])
		    		  .then().and().
		    		  extract().response();
		      return resp;
		}
	   
   
}