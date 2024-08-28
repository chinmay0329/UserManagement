package com.project.rest.webservices2.restful_web_services2.helloworld;

public class HelloWorldBean 
{
private String message;
	
	public HelloWorldBean(String message) {
		// TODO Auto-generated constructor stub
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
}
