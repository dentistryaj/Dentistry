package com.dentistry.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;


@Entity
@Table(name = "topic")
public class Topic implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "topic_name")
	private String topicName;
	
	@Column(name = "topic_link")
	private String topicLink;
	
	@Column(name = "topic_description")
	private String topicDesc;
	
	@ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="parent_id")
	
	private Topic parentTopic;
	
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="parentTopic",cascade=CascadeType.MERGE)
    private Set<Topic> childTopics = new HashSet<Topic>();
	


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicLink() {
		return topicLink;
	}

	public void setTopicLink(String topicLink) {
		this.topicLink = topicLink;
	}

	@JsonIgnore
	public Topic getParentTopic() {
		return parentTopic;
	}

	public void setParentTopic(Topic parentTopic) {
		this.parentTopic = parentTopic;
	}

	public Set<Topic> getChildTopics() {
		return childTopics;
	}

	public void setChildTopics(Set<Topic> childTopics) {
		this.childTopics = childTopics;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTopicDesc() {
		return topicDesc;
	}

	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}
	
	


}
