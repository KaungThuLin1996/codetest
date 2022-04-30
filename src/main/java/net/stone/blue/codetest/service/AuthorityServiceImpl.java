package net.stone.blue.codetest.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.stone.blue.codetest.entity.Authority;
import net.stone.blue.codetest.entity.AuthorityName;
import net.stone.blue.codetest.repository.AuthorityRepository;

@Service
@AllArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
	
	private final AuthorityRepository authorityRepository;

	@Override
	public Optional<Authority> getAuthorityById(Long id) {
		return authorityRepository.findById(id);
	}

	@Override
	public Optional<Authority> getAuthorityByName(AuthorityName name) {
		return authorityRepository.findByName(name);
	}

}
