package com.mario.bean.odata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mario.bean.Lecture;
import com.mario.bean.repo.LectureRepo;
import com.sap.dbs.dbx.i068191.annotation.processor.core.datasource.ODataInterface;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LectureODataAgent implements ODataInterface{
	
	@Autowired
	LectureRepo lectureRepo;
	
	public List<?> getEntitySet(){
		return lectureRepo.findAll();
	}

	@Override
	public Object getEntity(Map<String, ?> keys) {
		log.debug("getEntity called");
		Integer id = (Integer) keys.get("Id");
		log.debug("getEntity id is " + id.intValue());
		return lectureRepo.findById(id);
	}

	@Override
	public List<?> getRelatedEntity(Object source, String relatedEntityName, Map<String, Object> keys) {
		return null;
	}

	@Override
	public void createEntity(Object dataToCreate) {
		log.debug("createEntity called");
		Lecture p = (Lecture)dataToCreate;
		if (!lectureRepo.exists(p.getId())) {
			lectureRepo.save((Lecture)dataToCreate);
		}
	}

	@Override
	public void deleteEntity(Map<String, ?> keys) {
		log.debug("deleteEntity called");
		Integer id = (Integer)keys.get("Id");
		lectureRepo.delete(id);
	}

	@Override
	public void updateEntity(Object dataToUpdate) {
		log.debug("updateEntity called");
		Lecture p = (Lecture)dataToUpdate;
		lectureRepo.save(p);
	}
}
