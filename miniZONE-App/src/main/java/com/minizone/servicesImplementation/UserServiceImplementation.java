package com.minizone.servicesImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minizone.entities.Cart;
import com.minizone.entities.User;
import com.minizone.exceptions.UserException;
import com.minizone.repositories.CartRepository;
import com.minizone.repositories.UserRepository;
import com.minizone.services.UserService;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;


	@Override
	public String addUser(User user) throws UserException {
		
		Cart cart = new Cart();
		
		String message = "Not added!";
		
		Optional<User> userOptional = userRepository.findByEmail(user.getUserEmail());
		/*
		if(!userOptional.isPresent()) {	
			
			Cart c = cartRepository.save(cart);
			user.setCart(c);
			
			userRepository.save(user);
			message= "User Added Successfully.";
			
		}else
			throw new UserException("Unable to add user on duplicate useremail...");
		*/
		return message;
	}

	@Override
	public String updateUser(User user, Long userID) throws UserException {
		
		String message = "Not updated!";
		
		Optional<User> userOptional = userRepository.findById(userID);
		
		if(userOptional.isPresent()) {		
			userRepository.save(user);
			message= "User Updated Successfully.";
		}else
			throw new UserException("User not found unable to update...");
		
		
		return message;
	}

	@Override
	public String deleteUserById(Long userID) throws UserException {
		String message = "Not updated!";
		
		Optional<User> userOptional = userRepository.findById(userID);
		
		if(userOptional.isPresent()) {		
			userRepository.delete(userOptional.get());
			message= "User Deleted Successfully.";
		}else
			throw new UserException("User not found unable to delete...");
		
		
		return message;
	}

	@Override
	public User getUserById(Long userID) throws UserException {

		Optional<User> userOptional = userRepository.findById(userID);
		
		List<User> user = new ArrayList<>();
		user.add(userOptional.get());
		
		if(user.isEmpty()) {
			throw new UserException("User not found by given id "+userID);
		}else
			return user.get(0);
		
	}

	@Override
	public List<User> getAllUsers() throws UserException {
		
		List<User> users = new ArrayList<>();
		
		users.addAll(userRepository.findAll());
		
		if(users.isEmpty()) {
			throw new UserException("No users found in the list");
		}else 
			return users;

	}

}

