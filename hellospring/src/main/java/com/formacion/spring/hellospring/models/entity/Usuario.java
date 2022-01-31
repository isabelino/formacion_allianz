package com.formacion.spring.hellospring.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.UniqueConstraint;


@Entity
@Table(name= "usuarios")
public class Usuario implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true,length=20)
	private String username;
	
	@Column(length=60)
	private String password;
	
	private Boolean enabled;
	

	//@ManyToOne relacion muchos a uno
	//@OneToMany relacion uno a muchos
	//@OneToOne relacion uno a uno
	//@ManyToMany relacion muchos a muchos
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="usuarios_roles",joinColumns= @JoinColumn(name="usuario_id"),
	inverseJoinColumns = @JoinColumn(name="role_id"),uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","role_id"})}
	)
	private List<Role> roles;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
