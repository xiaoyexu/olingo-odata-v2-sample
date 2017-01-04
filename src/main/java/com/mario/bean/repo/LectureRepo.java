package com.mario.bean.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mario.bean.Lecture;

public interface LectureRepo extends JpaRepository<Lecture, Integer>{
	public Lecture findById(Integer id);
}
