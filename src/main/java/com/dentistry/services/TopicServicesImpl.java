package com.dentistry.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dentistry.dao.TopicDao;
import com.dentistry.model.Topic;

@Service("topicServices")
public class TopicServicesImpl implements TopicServices {

	@Autowired
	TopicDao dataDao;
	
	@Override
	public boolean addEntity(Topic employee) throws Exception {
		return dataDao.addEntity(employee);
	}

	@Override
	public Topic getEntityById(long id) throws Exception {
		return (Topic)dataDao.getEntityById(id);
	}

	@Override
	public List<Topic> getEntityList() throws Exception {
		return dataDao.getEntityList();
	}

	@Override
	public boolean deleteEntity(long id) throws Exception {
		return dataDao.deleteEntity(id);
	}

}
