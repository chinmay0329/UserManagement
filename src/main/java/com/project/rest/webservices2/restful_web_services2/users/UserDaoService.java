package com.project.rest.webservices2.restful_web_services2.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.Predicate;

@Component
public class UserDaoService 
{
	private static List<User> users=new ArrayList<>();
	private static int usersCount=0;
	static
	{
		users.add(new User(++usersCount,"Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount,"Eve", LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount,"Adam ka baap", LocalDate.now().minusYears(55)));
		users.add(new User(++usersCount,"Eve ka baap", LocalDate.now().minusYears(54)));
	}
	
	public List<User> findAll()
	{
		return users;
	}
	
	public User findById(int id) // this is retrieving a user
	{
//		for(int i=0;i<users.size();i++)
//		{
//			if(users.get(i).getId()==id)
//				return users.get(i);
//		}
//		return null;
		
		return users.stream().filter(e -> e.getId()==id).findFirst().orElse(null);
	}
	
	public User saveUser(User user)
	{
		user.setId(++usersCount);
		users.add(user);
		return user;
	}

	public void deleteUser(int id) 
	{	
		for(int i=0;i<users.size();i++)
		{
			if(users.get(i).getId()==id)
			{
				users.remove(i);
				break;
			}
		}
//		return null;
	}
}
