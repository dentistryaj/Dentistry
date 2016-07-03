package com.dentistry.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dentistry.model.Topic;

@Repository("topic")
@Scope("prototype")
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TopicDaoImpl implements TopicDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	private static final String ALL_TOPIC_QUERY = "select i from Topic i";
	
	
	public Topic getEntityById(long id) throws Exception {
		return (Topic)this.entityManager.find(Topic.class, id);
		
	}


	@Override
	public boolean addEntity(Topic topic) throws Exception {
		this.entityManager.merge(topic);
		this.entityManager.flush();
		return false;
	}


	@Override
	public List<Topic> getEntityList() throws Exception {
		List<Topic> topicList = null;
		topicList = this.entityManager
				.createQuery(ALL_TOPIC_QUERY, Topic.class).getResultList();
		return topicList;
	}


	@Override
	public boolean deleteEntity(long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
