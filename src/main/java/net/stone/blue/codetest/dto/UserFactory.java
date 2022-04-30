package net.stone.blue.codetest.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.stone.blue.codetest.annotation.Custom;
import net.stone.blue.codetest.entity.Student;

@Custom(name = "anno3")
public class UserFactory {

	public static User create(Student student) {
		var user = new User(student.getId(), student.getName(),
				new BCryptPasswordEncoder().encode(student.getPassword()), student.getAge(), mapToGrantedAuthorities(student));
		return user;
	}

	private static Collection<? extends GrantedAuthority> mapToGrantedAuthorities(Student student) {
		return student.getAuthorities().stream()
		.map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
		.collect(Collectors.toList());
	}
}
