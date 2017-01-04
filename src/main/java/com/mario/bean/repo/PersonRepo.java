package com.mario.bean.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mario.bean.Person;

public interface PersonRepo extends JpaRepository<Person, Integer>{
	public Person findById(Integer id);
}
