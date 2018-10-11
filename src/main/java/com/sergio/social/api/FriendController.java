package com.sergio.social.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sergio.social.api.response.APIResponse;
import com.sergio.social.api.response.util.APIStatus;
import com.sergio.social.api.response.util.ResponseUtil;
import com.sergio.social.database.model.User;
import com.sergio.social.enums.BadgeEnum;
import com.sergio.social.enums.FriendStatusEnum;
import com.sergio.social.enums.VisibilityEnum;
import com.sergio.social.service.FriendService;
import com.sergio.social.service.UserService;


@RestController
@RequestMapping(path = APIName.FRIEND, produces = MediaType.APPLICATION_JSON_VALUE)
public class FriendController {

	@Autowired
	private FriendService friendService;

	@Autowired
	private UserService userService;

	@Autowired
	private ResponseUtil responseUtil;

	@RequestMapping(path = APIName.FRIEND_LIST, method = RequestMethod.GET, produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> friendList() {
		User actualUser = userService.getActualUser();
		List<String> friends = friendService.getFriends(actualUser);
		if (CollectionUtils.isEmpty(friends))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return responseUtil.successResponse(friends);
	}

	@RequestMapping(path = APIName.REQUEST_FRIEND, method = RequestMethod.POST, produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> requestFriendship(@PathVariable(required = true) String destinationUsername) {
		User actualUser = userService.getActualUser();
		User destinationUser = userService.getUserByUsername(destinationUsername);
		if (destinationUser != null) {
			if(destinationUser.getVisibility().equals(VisibilityEnum.PUBLIC)) {
				friendService.request(actualUser, destinationUser);
			} else {
				return responseUtil.buildResponse(APIStatus.USER_NOT_VISIBLE, HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} else {
			return responseUtil.buildResponse(APIStatus.ERR_USER_NOT_EXIST, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(path = APIName.ACCEPT_FRIEND, method = RequestMethod.POST, produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> acceptFriendship(@PathVariable(required = true) String originUsername) {
		User actualUser = userService.getActualUser();
		User originUser = userService.getUserByUsername(originUsername);
		if (originUser != null) {
			friendService.updateFriendRequest(originUser, actualUser, FriendStatusEnum.ACCEPTED);
		} else {
			return responseUtil.buildResponse(APIStatus.ERR_USER_NOT_EXIST, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(path = APIName.DECLINE_FRIEND, method = RequestMethod.POST, produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> declineFriendship(@PathVariable(required = true) String originUsername) {
		User actualUser = userService.getActualUser();
		User originUser = userService.getUserByUsername(originUsername);
		if (originUser != null) {
			friendService.updateFriendRequest(originUser, actualUser, FriendStatusEnum.REFUSED);
			//We verify if the user have more than 3 refuses in their requests.
			int numFriendshipsDeclined = originUser.getFriendshipRequestSent().stream().filter(friend -> friend.getStatus().equals(FriendStatusEnum.REFUSED)).collect(Collectors.toList()).size();
			if(numFriendshipsDeclined>=3) {
				originUser.setBadge(BadgeEnum.FOREVER_ALONE);
				userService.save(originUser);
			}
		} else {
			return responseUtil.buildResponse(APIStatus.ERR_USER_NOT_EXIST, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
