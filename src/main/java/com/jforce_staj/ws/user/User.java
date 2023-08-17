package com.jforce_staj.ws.user;

import java.util.Collection; 
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.jforce_staj.ws.role.Role;
import com.jforce_staj.ws.shared.Views;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



//@Data
@RequiredArgsConstructor
@Getter
@Setter
@Entity// @Table ile databasedeki tablo adını istediğimiz şekilde belirleyebiliriz
@Table(name="users")
public class User  implements UserDetails{
	
	private static final long serialVersionUID = 1111777799564008626L;


	@Id
	@GeneratedValue
	private long id;
	
	
	//@Column(unique = true)//yapılabilir fakat diğer validation errorlara göre önceliksiz
	@NotNull
	//@Size(min = 4, max = 50)
	//@UniqueUsername
	@JsonView(Views.Base.class)
	private String username;
	
	@NotNull
	//@Size(min = 8, max = 50)
	//@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
	//@JsonIgnore
	private String password;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return AuthorityUtils.createAuthorityList("ROLE_" + role.getName().name());
	}



	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@JsonView(Views.Base.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;

	
}
