package com.Rakesh.Repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Rakesh.Model.AUser;

public interface Repo extends CrudRepository<AUser, String>{
	public AUser findByeMail(String eMail);
	public AUser findByPassword(String password);
}
