package net.stone.blue.codetest.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.stone.blue.codetest.annotation.Custom;
import net.stone.blue.codetest.entity.AuthorityName;
import net.stone.blue.codetest.entity.Student;
import net.stone.blue.codetest.exception.EntityNotFoundException;
import net.stone.blue.codetest.exception.EntityNotRegisterException;
import net.stone.blue.codetest.service.AuthorityServiceImpl;
import net.stone.blue.codetest.service.StudentServiceImpl;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
@Custom(name = "anno1")
public class StudentController {
	
	private final StudentServiceImpl studentServiceImpl;
	private final AuthorityServiceImpl authorityServiceImpl;
	private static final String NO_STUDENT_FOUND = "No student found!";
	private static final String STUDENT_CANNOT_REGISTER = "Student cannot be registered. Please check your request!";
	
	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> studentList = studentServiceImpl.getAllStudents();
		if (studentList.isEmpty()) {
			throw new EntityNotFoundException(NO_STUDENT_FOUND);
		}
		return new ResponseEntity<>(studentList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
		Student student = studentServiceImpl.getStudentById(id)
				.orElseThrow(() -> new EntityNotFoundException(NO_STUDENT_FOUND));
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Student> getStudentByName(@RequestParam String name) {
		Student student = studentServiceImpl.getStudentByName(name)
				.orElseThrow(() -> new EntityNotFoundException(NO_STUDENT_FOUND));
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		if (null == student.getName() || null == student.getAge() || null == student.getPassword()) {
			throw new EntityNotRegisterException(STUDENT_CANNOT_REGISTER);
		}
		var authority = authorityServiceImpl.getAuthorityByName(AuthorityName.USER).get();
		student.setAuthorities(List.of(authority));;
		studentServiceImpl.createStudent(student);
		return new ResponseEntity<>(student, HttpStatus.CREATED); 
	}
	
	@PutMapping("/{id}/update")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student updatedStudent) {
		Student student = null;
		try {
			student = studentServiceImpl.updateStudent(id, updatedStudent);
		} catch(NoSuchElementException e) {
			throw new EntityNotFoundException(NO_STUDENT_FOUND);
		}
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
		studentServiceImpl.getStudentById(id)
				.orElseThrow(() -> new EntityNotFoundException(NO_STUDENT_FOUND));
		studentServiceImpl.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
