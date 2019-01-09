package com.maulik.spring.mongodb.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="uniquekeybyobject")
public class AllObjectData {

	// id will be used for storing MongoDB _id
	@Id
	private String id;
	private String syllabusYear;
	private List<FileObject> fileObjects;

	public AllObjectData() {
	}

	/*
	 * public AllObjectData(String i, String n, List<FileObject> a){ this.id=i;
	 * this.name=n; this.address=a; }
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSyllabusYear() {
		return syllabusYear;
	}

	public void setSyllabusYear(String syllabusYear) {
		this.syllabusYear = syllabusYear;
	}

	public List<FileObject> getFileObjects() {
		return fileObjects;
	}

	public void setFileObjects(List<FileObject> fileObjects) {
		this.fileObjects = fileObjects;
	}
	/*
	 * @Override public String toString(){ return id+"::"+name+"::"+address; }
	 */
}
