package com.testtask.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import java.sql.Timestamp;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Size(min=3, max=16)
	@Column(name = "name", unique=true, nullable = false)
	private String name;

	@NotNull
	@Column(name = "createdDate", nullable = false)
	private Timestamp createdDate;

	@NotNull
	@Min(18)
	@Column(name = "age", nullable = false)
	private int age;

	@NotNull
	@Column(name = "isAdmin", nullable = false)
	private boolean isAdmin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (age != user.age) return false;
		if (isAdmin != user.isAdmin) return false;
		if (name != null ? !name.equals(user.name) : user.name != null) return false;
		return createdDate != null ? createdDate.equals(user.createdDate) : user.createdDate == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
		result = 31 * result + age;
		result = 31 * result + (isAdmin ? 1 : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", createdDate="
				+ createdDate + ", age=" + age + ", isAdmin=" + isAdmin + "]";
	}
	
	
	

}
