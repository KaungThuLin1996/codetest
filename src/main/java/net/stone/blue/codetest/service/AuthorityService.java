package net.stone.blue.codetest.service;

import java.util.Optional;

import net.stone.blue.codetest.entity.Authority;
import net.stone.blue.codetest.entity.AuthorityName;

public interface AuthorityService {

	Optional<Authority> getAuthorityById(Long id);
	Optional<Authority> getAuthorityByName(AuthorityName name);
}
