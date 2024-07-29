package com.spring.orm.dao;

import java.util.List;

import com.spring.orm.enities.Student; 

public interface StudentDao { 
	public int insert(Student s); 
	public void delete(int id); 
	public void delete(Student s); 
	public void update(Student s); 
	public Student getStudent(int id); 
	public List<Student> getAllStudents(); 
} 
