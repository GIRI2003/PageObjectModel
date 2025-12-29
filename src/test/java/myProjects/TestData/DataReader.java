package myProjects.TestData;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.type.ReferenceType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeVisitor;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	/*
	 * public List<HashMap<String, String>> getJsonDatatoMap() throws IOException {
	 * 
	 * // Convert JSON Path to File File file = new
	 * File(System.getProperty("user.dir")+
	 * "//src//test//java//myProjects//TestData//PurshaseOrder.json");
	 * 
	 * // Convert File to String -> FileUtils.readFileToString(jSon File,
	 * StandardCharsets.UTF_8); String jsonContent =
	 * FileUtils.readFileToString(file, StandardCharsets.UTF_8);
	 * 
	 * // Create object for ObjectMapper() and TypeReference<List<HashMap<String,
	 * String>>>() ObjectMapper mapper = new ObjectMapper();
	 * TypeReference<List<HashMap<String, String>>> type = new
	 * TypeReference<List<HashMap<String, String>>>(){};
	 * 
	 * // read the content from jsonContent and store the object in
	 * List<HashMap<String, String>> // Using mapper.readValue() -> Pass Content and
	 * type List<HashMap<String, String>> data = mapper.readValue(jsonContent,
	 * type); return data; }
	 */

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
//		JSON File to String -> FileUtils.readFileToString(filePath, Standard Character)
	String toString = FileUtils.readFileToString(new File(System.getProperty("user.dir") + 
			"\\src\\test\\java\\myProjects\\TestData\\PurshaseOrder.json"),
			StandardCharsets.UTF_8);

//	String to List -> mapper.readValue(string, TypeReference() {})
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> list = mapper.readValue(toString, new 
			TypeReference<List<HashMap<String, String>>>() {});

	return list;

	}

}
