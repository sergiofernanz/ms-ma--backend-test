package com.sergio.social.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.sergio.social.database.model.User;
import com.sergio.social.enums.BadgeEnum;
import com.sergio.social.enums.VisibilityEnum;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void whenFindByUsername() {
		User user = User.builder().username("sergio22").password("sergio22").visibility(VisibilityEnum.HIDDEN).badge(BadgeEnum.FOREVER_ALONE).role("1").build();
		entityManager.persist(user);
		entityManager.flush();

		User testUser = userRepository.findByUsername(user.getUsername());

		assertThat(user.getUsername()).isEqualTo(testUser.getUsername());
	}
}
