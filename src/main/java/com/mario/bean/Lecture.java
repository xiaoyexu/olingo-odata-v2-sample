package com.mario.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import lombok.Getter;
import lombok.Setter;

@EdmEntityType
@EdmEntitySet
@Entity
@Table(name="Lecture")
public class Lecture {
	@Getter
	@Setter
	@EdmKey
	@EdmProperty
	@Id
	private Integer id;
	
	@Getter
	@Setter
	@EdmProperty
	private String name;
	
	@Getter
	@Setter
	//@EdmProperty
	@ManyToMany(mappedBy = "lectureList", fetch=FetchType.EAGER)  
	private List<Person> personList = new ArrayList<Person>();
}
