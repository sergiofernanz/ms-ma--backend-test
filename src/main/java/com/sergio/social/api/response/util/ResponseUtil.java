package com.sergio.social.api.response.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.sergio.social.api.response.APIResponse;

@Component
public class ResponseUtil {

	private APIResponse _createSSOResponse(APIStatus apiStatus, Object data) {
		return new APIResponse(apiStatus, data);
	}

	// base method
	public ResponseEntity<APIResponse> buildResponse(APIStatus apiStatus, Object data, HttpStatus httpStatus) {
		return new ResponseEntity(_createSSOResponse(apiStatus, data), httpStatus);
	}

	// base method
	public ResponseEntity<APIResponse> buildResponse(APIStatus apiStatus, HttpStatus httpStatus) {
		return new ResponseEntity(_createSSOResponse(apiStatus, null), httpStatus);
	}
	public ResponseEntity<APIResponse> successResponse(Object data) {
		return buildResponse(APIStatus.OK, data, HttpStatus.OK);
	}

}
