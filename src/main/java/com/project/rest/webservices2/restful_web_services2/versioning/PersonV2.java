package com.project.rest.webservices2.restful_web_services2.versioning;

import org.springframework.beans.factory.annotation.Autowired;

public class PersonV2 
{
	private Name name;

	public PersonV2(Name name) {
		this.name = name;
	}

	public Name getName() {
		return name;
	}
	
	
	
	
}
