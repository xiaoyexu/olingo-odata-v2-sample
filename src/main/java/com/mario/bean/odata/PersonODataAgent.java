package com.mario.bean.odata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.annotation.processor.core.util.AnnotationHelper.AnnotatedNavInfo;
import org.springframework.beans.factory.annotation.Autowired;

import com.mario.bean.Person;
import com.mario.bean.repo.PersonRepo;
import com.sap.dbs.dbx.i068191.annotation.processor.core.datasource.ODataInterface;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonODataAgent implements ODataInterface{
	
	@Autowired
	PersonRepo personRepo;
	
	public List<?> getEntitySet(){
		return personRepo.findAll();
	}

	@Override
	public Object getEntity(Map<String, ?> keys) {
		log.debug("getEntity called");
		Integer id = (Integer) keys.get("Id");
		log.debug("getEntity id is " + id.intValue());
		return personRepo.findById(id);
	}
	@Override
	public List<?> getRelatedEntity(Object source, String relatedEntityName, Map<String, Object> keys,
			AnnotatedNavInfo navInfo) {
		log.debug("getRelatedEntity called");
		log.debug("relatedEntityName is " + relatedEntityName);
		Person p = (Person)source;
		if (relatedEntityName.equalsIgnoreCase("AddressSet")) {
			return p.getAddressList();
		}
		return new ArrayList<>();
	}

	@Override
	public void createEntity(Object dataToCreate) {
		log.debug("createEntity called");
		Person p = (Person)dataToCreate;
		if (!personRepo.exists(p.getId())) {
			personRepo.save((Person)dataToCreate);
		}
	}

	@Override
	public void deleteEntity(Map<String, ?> keys) {
		log.debug("deleteEntity called");
		Integer id = (Integer)keys.get("Id");
		personRepo.delete(id);
	}

	@Override
	public void updateEntity(Object dataToUpdate) {
		log.debug("updateEntity called");
		Person p = (Person)dataToUpdate;
		personRepo.save(p);
	}
}
