package com.jforce_staj.ws.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

//lombok calısmıyor

@Data
@Entity// @Table ile databasedeki tablo adını istediğimiz şekilde belirleyebiliriz
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	
	//@Column(unique = true)//yapılabilir fakat diğer validation errorlara göre önceliksiz
	@NotNull
	@Size(min = 4, max = 50)
	@UniqueUsername
	private String username;
	
	@NotNull
	@Size(min = 8, max = 50)
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
	private String password;
	
	
	 
}
