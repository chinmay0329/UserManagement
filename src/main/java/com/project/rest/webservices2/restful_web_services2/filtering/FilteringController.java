package com.project.rest.webservices2.restful_web_services2.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController 
{
	
	// now to implement dynamic filtering, we need to make use
	// of MappingJacksonValue and return type should be of this 
	//instead of Somebean
	
	// then use SimpleBeanPropertyFilter to make a filter
	// and then pass that filter as an arg to FilterProvider
// and then add these filters made by FilterProvider to MappingJacksonValue
	
	// now further **** do not forget to add @JsonFilter on the class Somebean and give a name
	// which you passed as a first argument in SimpleFilterProvider
	@GetMapping("/filtering")
	public MappingJacksonValue filtering()
	{
		Somebean someBean=new Somebean("f1", "f2", "f3");
		MappingJacksonValue mapping=new MappingJacksonValue(someBean);
		
		SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider fProvider= new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
		
		mapping.setFilters(fProvider);
		
		return mapping;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringList()
	{
//		List<Somebean> list= new ArrayList<Somebean>();
//		list.add(new Somebean("f1", "f2", "f3"));
//		list.add(new Somebean("f2", "f2", "f3"));
//		return list;
		
		List<Somebean> list= new ArrayList<Somebean>();
		list.add(new Somebean("f1", "f2", "f3"));
		list.add(new Somebean("f2", "f2", "f3"));
		
		MappingJacksonValue mapping=new MappingJacksonValue(list);
		
		SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		FilterProvider fProvider= new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
		
		mapping.setFilters(fProvider);
		
		return mapping;
		
		

	}
}
