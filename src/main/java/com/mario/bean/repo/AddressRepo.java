package com.mario.bean.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mario.bean.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{
	public Address findById(Integer id);
}
