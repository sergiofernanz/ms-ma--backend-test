package com.sergio.social.api;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sergio.social.api.response.util.APIStatus;
import com.sergio.social.api.response.util.ResponseUtil;
import com.sergio.social.database.model.User;
import com.sergio.social.enums.BadgeEnum;
import com.sergio.social.enums.VisibilityEnum;
import com.sergio.social.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(secure = false)
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;

	@MockBean
	private ResponseUtil responseUtil;
	
	@Test
	public void registerUser() throws Exception {

		given(userService.getUserByUsername("sergio22")).willReturn(null);
        String jsonMessage = String.format("{\"username\": \"sergio22\",\"password\": \"sergio22\"}");

		mvc.perform(post(APIName.USERS+APIName.USER_REGISTER).contentType(MediaType.APPLICATION_JSON)
	            .content(jsonMessage)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void duplicateUser() throws Exception {
		User user = User.builder().username("sergio22").password("sergio22").visibility(VisibilityEnum.HIDDEN).badge(BadgeEnum.FOREVER_ALONE).role("1").build();

		given(userService.getUserByUsername("sergio22")).willReturn(user);
        String jsonMessage = String.format("{\"username\": \"sergio22\",\"password\": \"sergio22\"}");

		mvc.perform(post(APIName.USERS+APIName.USER_REGISTER).contentType(MediaType.APPLICATION_JSON)
	            .content(jsonMessage)).andDo(print()).andExpect(status().is(HttpStatus.UNPROCESSABLE_ENTITY.value())).andExpect(jsonPath("status",is(APIStatus.USER_ALREADY_EXIST.getCode())));
	}

	
}
