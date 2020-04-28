package com.Rakesh.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Rakesh.Model.AUser;
import com.Rakesh.Repo.Repo;

@RestController
@RequestMapping("/users")
public class ControllerREST {
	
	@Autowired
	Repo repo;
	
	// Api to Get all the users' details stored in the users' table
	@GetMapping("/all")
	public List<AUser> showUsers(){
		return (List<AUser>) repo.findAll();
	}
	
	// Api to add new user details
	@PostMapping("/add")
	public boolean addUser(@RequestBody AUser aUser) {
		repo.save(aUser);
		return repo.findById(aUser.geteMail()).get().equals(aUser);
	}
	
	// Api to get details of user searched by email id
	@GetMapping("/eMail/{eMail}")
	public Optional<AUser> findUser(@PathVariable("eMail") String eMail) {
		return (repo.findById(eMail));
	}
	
	// Api to validate the users' login and rights
	@GetMapping("/login")
	public List<String> user(@RequestParam(name="eMail") String eMail, @RequestParam(name="password") String password) {
		Optional<AUser> foundUser = repo.findById(eMail);
		if (foundUser.get()!=null && foundUser.get().getPassword() == password.hashCode()) {
			return Arrays.asList(foundUser.get().getfName(), foundUser.get().getRole());
		}
		return null;
	}
	
	// Api to edit a user's details searched by their email id
	@PutMapping("/edit/{eMail}")
	public AUser editUser(@RequestBody AUser aUser) {
		repo.save(aUser);
		return repo.findByeMail(aUser.geteMail());
	}
	
	// Api to delete a user details searched by email id
	@DeleteMapping("/delete/{eMail}")
	public boolean deleteUser(@PathVariable("eMail") String eMail) {
		repo.deleteById(eMail);
		return repo.findById(eMail).get()==null;
	}
}
