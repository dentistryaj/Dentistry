package com.dentistry.dao;

import java.util.List;

import com.dentistry.model.Topic;

public interface TopicDao {

	public boolean addEntity(Topic topic) throws Exception;
	public Topic getEntityById(long id) throws Exception;
	public List<Topic> getEntityList() throws Exception;
	public boolean deleteEntity(long id) throws Exception;
}
