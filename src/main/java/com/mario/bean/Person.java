package com.mario.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;

import lombok.Getter;
import lombok.Setter;

@EdmEntityType
@EdmEntitySet
@Entity
@Table(name="Person")
public class Person {
	@Getter
	@Setter
	@EdmKey
	@EdmProperty
	@Id
	@Column(name="person_id")
	private Integer id;
	
	@Getter
	@Setter
	@EdmProperty
	private String name;
	
	@Getter
	@Setter
	@EdmProperty
	private Integer age;
	
	@Getter
	@Setter
	@EdmNavigationProperty
	@OneToMany(mappedBy = "creator", fetch=FetchType.EAGER)  
	private List<Address> addressList = new ArrayList<Address>();
	
	@Getter
	@Setter
	@ManyToMany(cascade={CascadeType.PERSIST}, fetch=FetchType.EAGER)     
	@JoinTable(name="Person_Lecture", 
	joinColumns={ 
		@JoinColumn(name="person_id",referencedColumnName="person_id")    
	},    
	inverseJoinColumns={         
		@JoinColumn(name="lecture_id",referencedColumnName="id")    
	})     
	private List<Lecture> lectureList = new ArrayList<Lecture>();
}
