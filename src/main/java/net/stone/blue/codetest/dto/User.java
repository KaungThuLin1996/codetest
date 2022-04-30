package net.stone.blue.codetest.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import net.stone.blue.codetest.annotation.Custom;

@Custom(name = "anno2")
@Data
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final Long id;
	private final String name;
	private final String password;
	private final Integer age;
	private final Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public String getUsername() {
		return name;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
