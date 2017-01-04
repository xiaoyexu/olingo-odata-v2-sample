package com.mario.bean.odata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mario.bean.Address;
import com.mario.bean.Person;
import com.mario.bean.repo.AddressRepo;
import com.sap.dbs.dbx.i068191.annotation.processor.core.datasource.ODataInterface;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddressODataAgent implements ODataInterface{
	
	@Autowired
	AddressRepo addressRepo;
	
	public List<?> getEntitySet(){
		return addressRepo.findAll();
	}

	@Override
	public Object getEntity(Map<String, ?> keys) {
		log.debug("getEntity called");
		Integer id = (Integer) keys.get("Id");
		log.debug("getEntity id is " + id.intValue());
		return addressRepo.findById(id);
	}

	@Override
	public List<?> getRelatedEntity(Object source, String relatedEntityName, Map<String, Object> keys) {
		return null;
	}

	@Override
	public void createEntity(Object dataToCreate) {
		log.debug("createEntity called");
		Address p = (Address)dataToCreate;
		if (!addressRepo.exists(p.getId())) {
			addressRepo.save(p);
		}
	}

	@Override
	public void deleteEntity(Map<String, ?> keys) {
		log.debug("deleteEntity called");
		Integer id = (Integer)keys.get("Id");
		addressRepo.delete(id);
	}

	@Override
	public void updateEntity(Object dataToUpdate) {
		log.debug("updateEntity called");
		Address p = (Address)dataToUpdate;
		addressRepo.save(p);
	}
}
