package com.sergio.social.database.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sergio.social.enums.BadgeEnum;
import com.sergio.social.enums.VisibilityEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude={"friendshipRequestSent", "friendshipRequestReceived"})
public class User implements Serializable{
	
	private static final long serialVersionUID = 879996506005336846L;

	@Id
	@Column(nullable = false, unique = true)
	@Size(min = 5, max = 10)
	@Pattern(regexp = "^[a-zA-Z0-9]*", message = "The length should be between 5 to 10. Alphanumeric")
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String role;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "originUser")
	private Set<Friend> friendshipRequestSent = new HashSet<>();
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "destinationUser")
	private Set<Friend> friendshipRequestReceived= new HashSet<>();
	
	@Column
	@Enumerated(EnumType.STRING)
	private BadgeEnum badge;
	
	@Column
	@Enumerated(EnumType.STRING)
	private VisibilityEnum visibility;

}
