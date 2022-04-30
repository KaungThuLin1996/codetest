package net.stone.blue.codetest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.stone.blue.codetest.dto.UserFactory;
import net.stone.blue.codetest.entity.Student;
import net.stone.blue.codetest.repository.StudentRepository;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
	
	private final StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}
	
	@Override
	public Optional<Student> getStudentByName(String name) {
		return studentRepository.findByName(name);
	}
	
	@Override
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}

	@Override
	public Student updateStudent(Long id, Student updatedStudent) {
		var oldStudent = getStudentById(id).get();
		oldStudent.setAge(updatedStudent.getAge());
		oldStudent.setName(updatedStudent.getName());
		oldStudent.setPassword(updatedStudent.getPassword());
		return studentRepository.save(oldStudent);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Student student = studentRepository.findByName(username)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
		return UserFactory.create(student);
	}

}
