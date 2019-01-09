package com.maulik.spring.mongodb.model;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UniqueWordRepository extends MongoRepository<AllObjectData, String> {
	
	/*//@Query("{ 'unique_word_name' : ?0 }")
	@Query("{ 'unique_word_name' : ?0 ,'unique_word_language' : ?0 }")
	public AllObjectData findUnqueKeyByString(String unique_word_name, String language);
	
	@Query("{ 'unique_word_name' : ?0 }")
	public AllObjectData findUnqueKeyByWord(String unique_word_name);*/
	@Query("{ 'fileObjects.uniqueWords.uniqueWord' : { $regex: ?0 } }")
	
//	@Query("{ 'AllObjectData.fileObjects.uniqueWords.uniqueWord' : { $regex: ?0 } }")
	
//	@Query("{ 'fileObjects' : { 'uniqueWords': { 'uniqueWords' :  { $regex: ?0 }  } } }")	
//	@Query({'AllObjectData.fileObjects.uniqueWords': {$filter:{  ['$$AllObjectData.fileObjects.uniqueWords.uniqueWord', ?0]}}})
	
//	 @Query(value = "{ 'AllObjectData.fileObjects.uniqueWords.uniqueWord' : ?0 }", fields = "{ 'AllObjectData.fileObjects.uniqueWords.uniqueWord' : ?0 }")
	
	public List<UniqueWords> getDataBySearchKey(String uniqueWord);

}
