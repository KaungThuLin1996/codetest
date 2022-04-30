package net.stone.blue.codetest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "authority")
@Data
public class Authority implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "authority_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
	private Long id;
	@Column(name = "authority_name", length = 20)
	@Enumerated(EnumType.STRING)
	private AuthorityName name;
	
}
