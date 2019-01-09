package com.maulik.spring.mongodb.model;

import java.util.List;

public class FileObject {

	private String objectStatus;
	private String objectName;
	private List<UniqueWords> uniqueWords;
	
	public FileObject() {
	}

	public String getObjectStatus() {
		return objectStatus;
	}

	public void setObjectStatus(String objectStatus) {
		this.objectStatus = objectStatus;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public List<UniqueWords> getUniqueWords() {
		return uniqueWords;
	}

	public void setUniqueWords(List<UniqueWords> uniqueWords) {
		this.uniqueWords = uniqueWords;
	}

}
