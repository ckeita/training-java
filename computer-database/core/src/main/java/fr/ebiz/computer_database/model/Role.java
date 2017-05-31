package fr.ebiz.computer_database.model;

import javax.persistence.*;

/**
 * Created by ebiz on 31/05/17.
 */
@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
