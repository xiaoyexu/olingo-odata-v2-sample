package com.luigi.bean.odata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.annotation.processor.core.util.AnnotationHelper.AnnotatedNavInfo;
import org.springframework.beans.factory.annotation.Autowired;

import com.luigi.bean.Student;
import com.luigi.bean.repo.StudentRepo;
import com.sap.dbs.dbx.i068191.annotation.processor.core.datasource.ODataInterface;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentODataAgent implements ODataInterface{
	
	@Autowired
	StudentRepo studentRepo;
	
	public List<?> getEntitySet(){
		return studentRepo.findAll();
	}

	@Override
	public Object getEntity(Map<String, ?> keys) {
		log.debug("getEntity called");
		Integer id = (Integer) keys.get("Id");
		log.debug("getEntity id is " + id.intValue());
		return studentRepo.findById(id);
	}

	@Override
	public List<?> getRelatedEntity(Object source, String relatedEntityName, Map<String, Object> keys,
			AnnotatedNavInfo navInfo) {
		return new ArrayList<>();
	}

	@Override
	public void createEntity(Object dataToCreate) {
		log.debug("createEntity called");
		Student p = (Student)dataToCreate;
		if (!studentRepo.exists(p.getId())) {
			studentRepo.save((Student)dataToCreate);
		}
	}

	@Override
	public void deleteEntity(Map<String, ?> keys) {
		log.debug("deleteEntity called");
		Integer id = (Integer)keys.get("Id");
		studentRepo.delete(id);
	}

	@Override
	public void updateEntity(Object dataToUpdate) {
		log.debug("updateEntity called");
		Student p = (Student)dataToUpdate;
		studentRepo.save(p);
	}
}
