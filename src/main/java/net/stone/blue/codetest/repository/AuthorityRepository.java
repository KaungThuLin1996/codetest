package net.stone.blue.codetest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.stone.blue.codetest.entity.Authority;
import net.stone.blue.codetest.entity.AuthorityName;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

	Optional<Authority> findByName(AuthorityName name);
}
