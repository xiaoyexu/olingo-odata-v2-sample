package com.luigi.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name="Student")
public class Student {
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
	@EdmProperty
	private Integer age;
}
