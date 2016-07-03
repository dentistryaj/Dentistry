package com.dentistry.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dentistry.dao.UserDetailsDao;
import com.dentistry.model.UserDetails;

@Service("UserDetailsService")
public class UserDetailsServicesImpl implements UserDetailsServices {

	@Autowired
	UserDetailsDao dataDao;
	
	@Override
	public boolean addEntity(UserDetails employee) throws Exception {
		return dataDao.addEntity(employee);
	}

	@Override
	public UserDetails getEntityById(long id) throws Exception {
		return (UserDetails)dataDao.getEntityById(id);
	}

	@Override
	public List<UserDetails> getEntityList() throws Exception {
		return dataDao.getEntityList();
	}

	@Override
	public boolean deleteEntity(long id) throws Exception {
		return dataDao.deleteEntity(id);
	}

}
