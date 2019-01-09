package com.maulik.spring.mongodb.model;

public class UniqueWords {

	private String uniqueWord;
	private String uniqueWordLanguage;
	private String uniqueWordLength;
	private String unqueWordStatus;
	private String unqueWordCount;
	
	public UniqueWords() {
	}

	public String getUniqueWord() {
		return uniqueWord;
	}

	public void setUniqueWord(String uniqueWord) {
		this.uniqueWord = uniqueWord;
	}

	public String getUniqueWordLanguage() {
		return uniqueWordLanguage;
	}

	public void setUniqueWordLanguage(String uniqueWordLanguage) {
		this.uniqueWordLanguage = uniqueWordLanguage;
	}

	public String getUniqueWordLength() {
		return uniqueWordLength;
	}

	public void setUniqueWordLength(String uniqueWordLength) {
		this.uniqueWordLength = uniqueWordLength;
	}

	public String getUnqueWordStatus() {
		return unqueWordStatus;
	}

	public void setUnqueWordStatus(String unqueWordStatus) {
		this.unqueWordStatus = unqueWordStatus;
	}

	public String getUnqueWordCount() {
		return unqueWordCount;
	}

	public void setUnqueWordCount(String unqueWordCount) {
		this.unqueWordCount = unqueWordCount;
	}
	
}
