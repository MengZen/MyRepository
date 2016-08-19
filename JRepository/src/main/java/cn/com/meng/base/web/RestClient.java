package cn.com.meng.base.web;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.expect;

public class RestClient {

	@Before
	public void setUp() {
		RestAssured.baseURI = "http://127.0.0.1";
		RestAssured.port = 8888;
		RestAssured.basePath = "/services/organization";
	}

	@Test
	public void testEventRestService() {

		Response response = null;
        response = expect().
                statusCode(200).
                when().get("/organizationInfo/3352113");
        System.out.println("根据机构ID获取机构信息:" + response.asString());
	}
}
