package com.ramazan.bean.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.*;


@Entity
public class Role {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;
	
	
	// FetchType.EAGER --> her rolü yüklediðinde ayrýcalýklarýda getir.!

	@ManyToMany(fetch = FetchType.EAGER) 
	@JoinTable(name = "Roles_Privileges",joinColumns = @JoinColumn(name = "role_id",referencedColumnName="id"),
						inverseJoinColumns = @JoinColumn(name = "privilege_id",referencedColumnName ="id"))
	private List<Privilege> privileges;

	@OneToMany(mappedBy = "role")
	private List<User> users;

	public Role() {
	}

	public Role(String name, List<Privilege> privileges, List<User> users) {
		super();
		this.name = name;
		this.privileges = privileges;
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
