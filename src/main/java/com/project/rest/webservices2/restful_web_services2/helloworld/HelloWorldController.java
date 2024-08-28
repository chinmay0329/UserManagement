package com.project.rest.webservices2.restful_web_services2.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController 
{
//	@RequestMapping(value="hello-world", method=RequestMethod.GET )
	@GetMapping(path="/hello-world")
	public String helloWorld()
	{
		return "hello World!!!!";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("Hello World"); 
		//this is returning a JSON response
	}
	
	@GetMapping(path = "/hello-world/pathvariable/{name}")
	public HelloWorldBean helloWorldBeanPath(@PathVariable String name)
	{
		return new HelloWorldBean(String.format("Hello Bruhhh!! ,%s", name)); 
		//this is returning a JSON response
	}
}
