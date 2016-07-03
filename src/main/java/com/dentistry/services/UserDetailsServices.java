package com.dentistry.services;
import java.util.List;

import com.dentistry.model.UserDetails;
public interface UserDetailsServices {
	public boolean addEntity(UserDetails a) throws Exception;
	public UserDetails getEntityById(long id) throws Exception;
	public List<UserDetails> getEntityList() throws Exception;
	public boolean deleteEntity(long id) throws Exception;
}
