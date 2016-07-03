package com.dentistry.dao;

import java.util.List;

import com.dentistry.model.UserDetails;

public interface UserDetailsDao {

	public boolean addEntity(UserDetails topic) throws Exception;
	public UserDetails getEntityById(long id) throws Exception;
	public List<UserDetails> getEntityList() throws Exception;
	public boolean deleteEntity(long id) throws Exception;
}
