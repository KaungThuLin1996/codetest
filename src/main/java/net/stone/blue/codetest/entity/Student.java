package net.stone.blue.codetest.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;
	@Column(name = "age")
	private Integer age;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "student_authority", joinColumns = {
			@JoinColumn(name = "student_id", referencedColumnName = "student_id")}, inverseJoinColumns = {
					@JoinColumn(name = "authority_id", referencedColumnName = "authority_id")
			})
	private List<Authority> authorities;
}
