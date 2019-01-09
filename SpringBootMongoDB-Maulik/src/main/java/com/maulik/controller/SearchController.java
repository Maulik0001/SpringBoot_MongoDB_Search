/**
 * 
 */
package com.maulik.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maulik.spring.mongodb.model.AllObjectData;
import com.maulik.spring.mongodb.model.FileObject;
import com.maulik.spring.mongodb.model.ObjectKeyWord;
import com.maulik.spring.mongodb.model.ObjectWordRepository;
import com.maulik.spring.mongodb.model.UniqueWordRepository;
import com.maulik.spring.mongodb.model.UniqueWords;

/**
 * @author Maulik Patel
 *
 */

@Controller
public class SearchController {

	@Autowired
	UniqueWordRepository uniqueWordRepository;
	
	@Autowired
	ObjectWordRepository objectWordRepository;
	
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) throws FileNotFoundException, IOException, ParseException {

		return "searchList";
	}

	@RequestMapping(value = "/addData", method = RequestMethod.GET)
	public String home(Model model) throws FileNotFoundException, IOException, ParseException {

		Date d = new Date();

		System.out.println("start time ::" + d.getTime());

		AllObjectData allObjData = new AllObjectData();

		JSONParser parser = new JSONParser();

		File folder = new File("C:\\Users\\maulikp.ELPL\\Desktop\\Mongo DB\\document\\text_from_swf\\");

		// retrieve file listing
		File[] fileList = folder.listFiles();

		if (fileList == null) {
			// throw an exception, return or do any other error handling here
			System.out.println("file list is null");
		}

		allObjData.setSyllabusYear("2018-19");
		List<FileObject> fileObjects = new ArrayList<FileObject>();

		// path is correct
		for (final File file : fileList) {

			FileObject fileObject = new FileObject();
			List<UniqueWords> uniqueWords = new ArrayList<UniqueWords>();

			Object obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;

			String status = (String) jsonObject.get("status");
			String objectId = (String) jsonObject.get("object_id");
			String guj = (String) jsonObject.get("guj");
			String hid = (String) jsonObject.get("hid");
			String eng = (String) jsonObject.get("eng");
			/*
			 * System.out.
			 * println("======================= File details ==========================");
			 * System.out.println("status: " + status); System.out.println("objectId: " +
			 * objectId); System.out.println("guj: " + guj); System.out.println("hid: " +
			 * hid); System.out.println("eng: " + eng);
			 */

			fileObject.setObjectStatus(status);
			fileObject.setObjectName(objectId);

			Set<String> uniqueGujKey = new HashSet<String>();

			String[] gujwords = guj.split("[ \\[“”\\-'\"\n\\t\\r.,;:+!?(){}]");
			for (String word : gujwords) {
				if (!word.equals("") && !word.equals(" "))
					uniqueGujKey.add(word.toLowerCase());
			}
			for (String s : uniqueGujKey) {
				UniqueWords uniqueword = new UniqueWords();
				Pattern p1 = Pattern.compile(s);
				Matcher m = p1.matcher(guj.toLowerCase());
				int count = 0;
				while (m.find()) {
					count++;
				}
				uniqueword.setUniqueWord(s);
				uniqueword.setUniqueWordLanguage("Gujarati");
				uniqueword.setUniqueWordLength(s.length() + "");
				uniqueword.setUnqueWordStatus("Active");
				uniqueword.setUnqueWordCount(count + "");

				uniqueWords.add(uniqueword);

				// System.out.println(s + " = " + count);

			}

			Set<String> uniqueHindiKey = new HashSet<String>();

			String[] hindiwords = hid.split("[ \\[“”\\-'\"\n\\t\\r.,;:+!?(){}]");
			for (String word : hindiwords) {
				if (!word.equals("") && !word.equals(" "))
					uniqueHindiKey.add(word.toLowerCase());
			}
			for (String s : uniqueHindiKey) {
				UniqueWords uniqueword = new UniqueWords();
				Pattern p1 = Pattern.compile(s);
				Matcher m = p1.matcher(hid.toLowerCase());
				int count = 0;
				while (m.find()) {
					count++;
				}
				uniqueword.setUniqueWord(s);
				uniqueword.setUniqueWordLanguage("Hindi");
				uniqueword.setUniqueWordLength(s.length() + "");
				uniqueword.setUnqueWordStatus("Active");
				uniqueword.setUnqueWordCount(count + "");

				uniqueWords.add(uniqueword);

			}

			Set<String> uniqueEngKey = new HashSet<String>();

			String[] engwords = eng.split("[ \\[“”\\-'\"\n\\t\\r.,;:+!?(){}]");
			for (String word : engwords) {
				if (!word.equals("") && !word.equals(" "))
					uniqueEngKey.add(word.toLowerCase());
			}
			for (String s : uniqueEngKey) {
				UniqueWords uniqueword = new UniqueWords();
				Pattern p1 = Pattern.compile(s);
				Matcher m = p1.matcher(eng.toLowerCase());
				int count = 0;
				while (m.find()) {
					count++;
				}
				uniqueword.setUniqueWord(s);
				uniqueword.setUniqueWordLanguage("English");
				uniqueword.setUniqueWordLength(s.length() + "");
				uniqueword.setUnqueWordStatus("Active");
				uniqueword.setUnqueWordCount(count + "");

				uniqueWords.add(uniqueword);

			}

			fileObject.setUniqueWords(uniqueWords);

			fileObjects.add(fileObject);

		}
		allObjData.setFileObjects(fileObjects);
		// close b'coz data one time insert only one time
		// mongoOps.insert(allObjData, UNOQUEKEYWORD_COLLECTION);

		uniqueWordRepository.save(allObjData);

		Date d1 = new Date();

		System.out.println("End time ::" + d1.getTime());
		return "searchList";
	}


	@RequestMapping(value = "/showAll", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<AllObjectData> showAll(Model model) {
		// List<employee> emplist =crudeService.findAll();
		List<AllObjectData> um = uniqueWordRepository.findAll();
		System.out.println("controoller call :::: ");
		// start

		System.out.println("showAll ::: ");
		// return emplist;
		return um;
	}

	/*public static final String DB_NAME = "objectkeyword";
	public static final String UNOQUEKEYWORD_COLLECTION = "uniquekeybyobject";
	public static final String MONGO_HOST = "localhost";
	public static final int MONGO_PORT = 27017;*/
	public static final String UNOQUEKEYWORD_COLLECTION = "uniquekeybyobject";
	
	@RequestMapping(value = "/searchByValue", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<AllObjectData> searchByValue(Model model, @RequestParam("searchText") String searchText) {

		System.out.println("searchText::" + searchText);
		
		Date d = new Date();
		Long starttime = d.getTime();
		System.out.println("start time ::"+starttime);
		
		List<AllObjectData> results = new ArrayList<AllObjectData>();
		
		List<AllObjectData> allObjectData = uniqueWordRepository.findAll();
		int check = 0;
		for(AllObjectData ao: allObjectData) {
			AllObjectData aob = new AllObjectData();
			aob.setSyllabusYear(ao.getSyllabusYear());
			
			
			List<FileObject> fobList = new ArrayList<FileObject>();
			for(FileObject fo : ao.getFileObjects()) {
				int a = 0;
				FileObject fob = new FileObject();
				fob.setObjectName(fo.getObjectName());
				fob.setObjectStatus(fo.getObjectStatus());
				List<UniqueWords> uwList =  new ArrayList<UniqueWords>();
				for(UniqueWords uw:fo.getUniqueWords()) {
					if(uw.getUniqueWord().contains(searchText)) {
						a=1;
						check = 1;
						UniqueWords uwd= new UniqueWords();
						
						uwd.setUniqueWord(uw.getUniqueWord());
						uwd.setUniqueWordLanguage(uw.getUniqueWordLanguage());
						uwd.setUnqueWordCount(uw.getUnqueWordCount());
						uwd.setUnqueWordStatus(uw.getUnqueWordStatus());
						uwd.setUniqueWordLength(uw.getUniqueWordLength());
					
						uwList.add(uwd);
					}
				}
				fob.setUniqueWords(uwList);
				if(a==1) {
					fobList.add(fob);
				}
			}
			aob.setFileObjects(fobList);
			
			if(check == 1) {
				results.add(aob);
			}
		}
		
		Date d1 = new Date();
		Long endtime = d1.getTime();
		System.out.println("end time ::"+endtime);
		System.out.println("time difference ::"+(endtime-starttime));
		
		return results;
	}

	@RequestMapping(value = "/searchPage", method = RequestMethod.GET)
	public String searchPage(Model model) throws FileNotFoundException, IOException, ParseException {

		return "searchPage";
	}

	@RequestMapping(value = "/addDataByObjectAndKeyword", method = RequestMethod.GET)
	public String AddDataByObjectAndKeyword(Model model) throws FileNotFoundException, IOException, ParseException {

		Date d = new Date();

		System.out.println("start time ::" + d.getTime());

		JSONParser parser = new JSONParser();

		File folder = new File("C:\\Users\\maulikp.ELPL\\Desktop\\Mongo DB\\document\\text_from_swf\\");

		// retrieve file listing
		File[] fileList = folder.listFiles();

		if (fileList == null) {
			// throw an exception, return or do any other error handling here
			System.out.println("file list is null");
		}
		for (final File file : fileList) {

			Object obj = parser.parse(new FileReader(file));
			JSONObject jsonObject = (JSONObject) obj;

			String status = (String) jsonObject.get("status");
			String objectId = (String) jsonObject.get("object_id");
			String guj = (String) jsonObject.get("guj");
			String hid = (String) jsonObject.get("hid");
			String eng = (String) jsonObject.get("eng");
			
			Set<String> uniqueGujKey = new HashSet<String>();

			String[] gujwords = guj.split("[ \\[“”\\-'\"\n\\t\\r.,;:+!?(){}]");
			for (String word : gujwords) {
				if (!word.equals("") && !word.equals(" "))
					uniqueGujKey.add(word.toLowerCase());
			}
			for (String s : uniqueGujKey) {
				ObjectKeyWord objectKeyWord = new ObjectKeyWord();
				Pattern p1 = Pattern.compile(s);
				Matcher m = p1.matcher(guj.toLowerCase());
				int count = 0;
				while (m.find()) {
					count++;
				}
				
				objectKeyWord.setObjectName(objectId);
				objectKeyWord.setUniqueKey(s);
				objectKeyWord.setKeyLanguage("Gujarati");
				objectKeyWord.setKeyCount(count);
			
				objectWordRepository.save(objectKeyWord);
			}

			Set<String> uniqueHindiKey = new HashSet<String>();

			String[] hindiwords = hid.split("[ \\[“”\\-'\"\n\\t\\r.,;:+!?(){}]");
			for (String word : hindiwords) {
				if (!word.equals("") && !word.equals(" "))
					uniqueHindiKey.add(word.toLowerCase());
			}
			for (String s : uniqueHindiKey) {
				ObjectKeyWord objectKeyWord = new ObjectKeyWord();
				Pattern p1 = Pattern.compile(s);
				Matcher m = p1.matcher(hid.toLowerCase());
				int count = 0;
				while (m.find()) {
					count++;
				}
				
				objectKeyWord.setObjectName(objectId);
				objectKeyWord.setUniqueKey(s);
				objectKeyWord.setKeyLanguage("Hindi");
				objectKeyWord.setKeyCount(count);
				
				objectWordRepository.save(objectKeyWord);
			}

			Set<String> uniqueEngKey = new HashSet<String>();

			String[] engwords = eng.split("[ \\[“”\\-'\"\n\\t\\r.,;:+!?(){}]");
			for (String word : engwords) {
				if (!word.equals("") && !word.equals(" "))
					uniqueEngKey.add(word.toLowerCase());
			}
			for (String s : uniqueEngKey) {
				ObjectKeyWord objectKeyWord = new ObjectKeyWord();
				
				Pattern p1 = Pattern.compile(s);
				Matcher m = p1.matcher(eng.toLowerCase());
				int count = 0;
				while (m.find()) {
					count++;
				}
				objectKeyWord.setObjectName(objectId);
				objectKeyWord.setUniqueKey(s);
				objectKeyWord.setKeyLanguage("English");
				objectKeyWord.setKeyCount(count);

				objectWordRepository.save(objectKeyWord);
			}
		}
	
		Date d1 = new Date();

		System.out.println("End time ::" + d1.getTime());
		return "searchListNew";
	}
	
	@RequestMapping(value = "/showAllNew", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ObjectKeyWord> showAllNew(Model model) {
		
		List<ObjectKeyWord> results = objectWordRepository.findAll();
		
		System.out.println("showAll New::: ");
		
		System.out.println("results size::"+results.size());
		return results;
	}
	
	
	@RequestMapping(value = "/DbSize", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String DbSize(Model model) {
		List<ObjectKeyWord> results = objectWordRepository.findAll();
		System.out.println("results size::"+results.size());
		return results.size()+"";
	}
	
	
	
	@RequestMapping(value = "/searchPageNew", method = RequestMethod.GET)
	public String searchPageNew(Model model) throws FileNotFoundException, IOException, ParseException {

		return "searchPageNew";
	}
	
	@RequestMapping(value = "/searchByValueNew", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ObjectKeyWord> searchByValueNew(Model model, @RequestParam("searchText") String searchText) {

		System.out.println("searchText::" + searchText);
		
		Date d = new Date();
		Long starttime = d.getTime();
		System.out.println("start time ::"+starttime);
		
		List<ObjectKeyWord> results = objectWordRepository.getDataBySearchKey(searchText);
		
		
		Collections.sort(results, new Comparator<ObjectKeyWord>() {
            public int compare(ObjectKeyWord s1, ObjectKeyWord s2) {
                // notice the cast to (Integer) to invoke compareTo
                return ((Integer)s1.getKeyCount()).compareTo(s2.getKeyCount());
            }
        });
		
		Collections.reverse(results);
		
		Date d1 = new Date();
		Long endtime = d1.getTime();
		System.out.println("end time ::"+endtime);
		System.out.println("time difference ::"+(endtime-starttime));
		
		System.out.println("total files ::"+results.size());
		
		return results;
	}
	
}
