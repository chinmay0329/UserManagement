package com.project.rest.webservices2.restful_web_services2.users;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.rest.webservices2.restful_web_services2.jpa.PostRepository;
import com.project.rest.webservices2.restful_web_services2.jpa.UserRepository;

import jakarta.persistence.PostRemove;
import jakarta.validation.Valid;

@RestController
public class UserJpaResource 
{
//	@Autowired
//	private UserDaoService service;
	
	private UserRepository repository;
	private PostRepository postRepository;
	
	//instead of Autowire annotation, u can use Constructor injection
	//like below
	
	public UserJpaResource (UserRepository repository, PostRepository postRepository)
	{
		this.repository=repository;
		this.postRepository=postRepository;
	}
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers()
	{
//		return service.findAll();
		return repository.findAll();
	}
	
	
	//To implement HATEOAS 
	// we need EntityModel
	// WebMvcLinkBuilder
	// first we need wrap the user in EntityModel
	// then we need to use WebMvcLinkBuilder to add the links 
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveAUser(@PathVariable int id)
	{
		Optional<User> retrievedUser= repository.findById(id);
		EntityModel<User> entityModel= EntityModel.of(retrievedUser.get());
		
		if(retrievedUser.isEmpty())
			throw new UserNotFoundException("id: "+id);
		
		WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		
//		System.out.println("retrieved user: "+retrievedUser);
		
		//doing the below for proper error msg display to the end customer
		// for ex if cust tries to get a user of non existing ID
		// they should be shown proper error message to get an understanding
		
			return entityModel;
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
//		service.deleteUser(id);
		repository.deleteById(id);
		
//		User deletedUser=service.deleteUser(id);
//		if(deletedUser==null)
//			throw new UserNotFoundException("id: "+id);
//		else
//			return deletedUser;
	}
	
	
	// for putting validations, first we need to add "spring boot validation"
	// in pom.xml , then add valid in here
	// then in User.java put the annotations on whichever fields u want
	// to have validation , then add custom method in 
	// customizedResponseEntity class to display our own customized 
	//message
	@PostMapping("/jpa/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user)
	{
		User savedUser=repository.save(user);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest() // this will take "/users"
					.path("/{id}")
					.buildAndExpand(savedUser.getId())
					.toUri();
		
		return ResponseEntity.created(location).build();
		//basically using this response entity to generate proper responses
		// for the end customer 
		
		
//		return service.saveUser(user);
	}
	
	@GetMapping(path="/jpa/users/{id}/posts")
	public List<Post> retrievePostOfUser(@PathVariable int id)
	{
		Optional<User> retrievedUser= repository.findById(id);
		
		if(retrievedUser.isEmpty())
			throw new UserNotFoundException("id: "+id);
		
		return retrievedUser.get().getPosts();
		
	}
	
	@PostMapping(path="/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id,@Valid @RequestBody Post post)
	{
		Optional<User> retrievedUser= repository.findById(id);
		
		if(retrievedUser.isEmpty())
			throw new UserNotFoundException("id: "+id);
		
		post.setUser(retrievedUser.get());
		
		Post savedPost= postRepository.save(post);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest() // this will take "/users"
				.path("/{id}")
				.buildAndExpand(post.getId())
				.toUri();
	
		return ResponseEntity.created(location).build();
		
		// below code is when we are not setting URI and return type of the 
		// method is just List<Post> 
		
//		Optional<User> retrievedUser= repository.findById(id);
//		
//		if(retrievedUser.isEmpty())
//			throw new UserNotFoundException("id: "+id);
//		
//		post.setUser(retrievedUser.get());
//		postRepository.save(post);
//		
//		return retrievedUser.get().getPosts();
	}
}
