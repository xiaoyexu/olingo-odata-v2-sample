package com.luigi.bean.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luigi.bean.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{
	public Student findById(Integer id);
}
