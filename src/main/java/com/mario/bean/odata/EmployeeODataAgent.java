package com.mario.bean.odata;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.annotation.processor.core.util.AnnotationHelper.AnnotatedNavInfo;
import org.springframework.beans.factory.annotation.Autowired;

import com.mario.bean.Employee;
import com.mario.bean.repo.EmployeeRepo;
import com.sap.dbs.dbx.i068191.annotation.processor.core.datasource.ODataInterface;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeODataAgent implements ODataInterface{
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	public List<?> getEntitySet(){
		return employeeRepo.findAll();
	}

	@Override
	public Object getEntity(Map<String, ?> keys) {
		log.debug("getEntity called");
		Integer id = (Integer) keys.get("Id");
		log.debug("getEntity id is " + id.intValue());
		return employeeRepo.findById(id);
	}

	@Override
	public List<?> getRelatedEntity(Object source, String relatedEntityName, Map<String, Object> keys,
			Field sourceField) {
		return new ArrayList<>();
	}

	@Override
	public void createEntity(Object dataToCreate) {
		log.debug("createEntity called");
		Employee p = (Employee)dataToCreate;
		if (!employeeRepo.exists(p.getId())) {
			employeeRepo.save((Employee)dataToCreate);
		}
	}

	@Override
	public void deleteEntity(Map<String, ?> keys) {
		log.debug("deleteEntity called");
		Integer id = (Integer)keys.get("Id");
		employeeRepo.delete(id);
	}

	@Override
	public void updateEntity(Object dataToUpdate) {
		log.debug("updateEntity called");
		Employee p = (Employee)dataToUpdate;
		employeeRepo.save(p);
	}
}
