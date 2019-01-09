package com.maulik.spring.mongodb.model;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ObjectWordRepository extends MongoRepository<ObjectKeyWord, String> {
	
	@Query("{ 'uniqueKey' : { $regex: ?0 } }")
	public List<ObjectKeyWord> getDataBySearchKey(String uniqueWord);

}
