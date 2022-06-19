package com.okta.developer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author V.Sel
 * @date 2022/6/19
 * @desc
 */
@Slf4j
public class BCryptPasswordEncoderTest {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Test
    public void testEncode() {
        String password="123456";
        log.info("===>>> {} " ,encoder.encode(password));
        log.info("===>>> {} " ,encoder.matches(password,encoder.encode(password)));
        log.info("===>>> {} " ,encoder.upgradeEncoding(encoder.encode(password)));
    }

}
