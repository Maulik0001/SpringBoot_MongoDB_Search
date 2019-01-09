package com.maulik.spring.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="object_keyword")
public class ObjectKeyWord {

	// id will be used for storing MongoDB _id
	@Id
	private String id;
	private String objectName;
	private String uniqueKey;
	private String keyLanguage;
	private int keyCount;

	/*public ObjectKeyWord() {
	}*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getKeyLanguage() {
		return keyLanguage;
	}

	public void setKeyLanguage(String keyLanguage) {
		this.keyLanguage = keyLanguage;
	}

	public int getKeyCount() {
		return keyCount;
	}

	public void setKeyCount(int keyCount) {
		this.keyCount = keyCount;
	}
	
	
}
