package net.stone.blue.codetest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.stone.blue.codetest.entity.Student;

public interface StudentService extends UserDetailsService {

	List<Student> getAllStudents();
	Optional<Student> getStudentById(Long id);
	Optional<Student> getStudentByName(String name);
	Student createStudent(Student student);
	void deleteStudent(Long id);
	Student updateStudent(Long id, Student newStudent);
}
