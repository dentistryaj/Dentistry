package com.dentistry.services;

import java.util.List;

import com.dentistry.model.Topic;

public interface TopicServices {
	public boolean addEntity(Topic a) throws Exception;
	public Topic getEntityById(long id) throws Exception;
	public List<Topic> getEntityList() throws Exception;
	public boolean deleteEntity(long id) throws Exception;
}
