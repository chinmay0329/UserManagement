package com.project.rest.webservices2.restful_web_services2.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.rest.webservices2.restful_web_services2.users.Post;

public interface PostRepository extends JpaRepository<Post, Integer>
{
	
}
