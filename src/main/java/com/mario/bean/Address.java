package com.mario.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="Address")
public class Address {
	@Getter
	@Setter
	@EdmKey
	@EdmProperty
	@Id
	private Integer id;
	
	@Getter
	@Setter
	@EdmProperty
	private String country;
	
	@Getter
	@Setter
	@EdmProperty
	private String city;
	
	@Getter
	@Setter
	@EdmProperty
	private String street;
	
	@Getter
	@Setter
	//@EdmProperty
	@ManyToOne  
	@JoinColumn(name="creator_person_id", referencedColumnName="person_id")
    public Person creator;  

}
