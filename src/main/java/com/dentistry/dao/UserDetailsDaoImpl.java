package com.dentistry.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.annotations.Cascade;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dentistry.model.UserDetails;


@Repository("userdetails")
public class UserDetailsDaoImpl implements UserDetailsDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public UserDetails getEntityById(long id) throws Exception {
		return (UserDetails)this.entityManager.find(UserDetails.class, id);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW )
	public boolean addEntity(UserDetails employee) throws Exception {
		this.entityManager.merge(employee);
		this.entityManager.flush();
		return false;
	}


	@Override
	public List<UserDetails> getEntityList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean deleteEntity(long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
