package com.sergio.social.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sergio.social.api.request.UserRequestModel;
import com.sergio.social.api.response.APIResponse;
import com.sergio.social.api.response.util.APIStatus;
import com.sergio.social.api.response.util.ResponseUtil;
import com.sergio.social.database.model.User;
import com.sergio.social.enums.VisibilityEnum;
import com.sergio.social.service.UserService;

@RestController
@RequestMapping(APIName.USERS)
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ResponseUtil responseUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * Add new user
	 * 
	 * @param New User information
	 * @return The new user
	 */
	@RequestMapping(path = APIName.USER_REGISTER, method = RequestMethod.POST, produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> register(@RequestBody UserRequestModel user) {
		User existedUser = userService.getUserByUsername(user.getUsername());
		if (existedUser == null) {
			User createdUser = User.builder().username(user.getUsername())
					.password(passwordEncoder.encode(user.getPassword())).role("1").visibility(VisibilityEnum.PUBLIC)
					.build();

			userService.save(createdUser);
			return responseUtil.successResponse(createdUser);
		} else {
			return responseUtil.buildResponse(APIStatus.USER_ALREADY_EXIST, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
