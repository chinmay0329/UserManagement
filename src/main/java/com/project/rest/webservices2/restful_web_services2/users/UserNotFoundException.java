package com.project.rest.webservices2.restful_web_services2.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException 
{
	public UserNotFoundException(String message)
	{
		super(message);
		
		// with no annotation on this class, it will give
		// white label page error, with msg :
		// 500 response code, with saying that 
		//"usernotfound exception occurred"
		
		// so to display the proper response code
		// we will annotate the class with @ResponseStatus
		
//now even after you annotate, response code will be shown as 404 BUTTTT
// error message displayed will be huge, so to reduce that 
// comment "spring boot dev tools" from pom.xml and then restart and run
// again, you will get a shorter error message.
		
// and anyways when you run a JAVA jar file, spring boot dev tools is
//automatically disabled
	}
}
