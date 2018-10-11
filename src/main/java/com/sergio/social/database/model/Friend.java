package com.sergio.social.database.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sergio.social.enums.FriendStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Friend implements Serializable {

	private static final long serialVersionUID = 6806904395281152865L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	private User originUser;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	private User destinationUser;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private FriendStatusEnum status;

}
