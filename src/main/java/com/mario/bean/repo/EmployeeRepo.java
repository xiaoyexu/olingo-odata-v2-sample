package com.mario.bean.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mario.bean.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	public Employee findById(Integer id);
}
