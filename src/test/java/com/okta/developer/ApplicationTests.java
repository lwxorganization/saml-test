package com.okta.developer;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

import com.okta.developer.auth.StoredUser;
import com.okta.developer.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author V.Sel
 * @date 2022/6/17
 * @desc
 */
@Slf4j
@SpringBootTest(classes = Application.class)
public class ApplicationTests {

    @Resource
    private UserRepository userRepository;

    @Test
    void contextLoads() {

        Optional<StoredUser> users= userRepository.findByUsernameIgnoreCase("samluser@oktaauth" +
                ".com");
        if (Objects.nonNull(users) && users.isPresent()){
            log.info("====>>> : {} " ,users.get());
        }

    }

}
