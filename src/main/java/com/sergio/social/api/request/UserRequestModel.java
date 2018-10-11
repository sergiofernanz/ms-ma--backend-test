package com.sergio.social.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

	
@Getter
@Setter
@Builder
public class UserRequestModel {
 
	private String username;

	private String password;
	
    
}
