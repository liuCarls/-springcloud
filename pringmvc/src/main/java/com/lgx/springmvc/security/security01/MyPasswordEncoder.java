package com.lgx.springmvc.security.security01;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {
    private static Logger logger = Logger.getLogger(MyPasswordEncoder.class);
    @Override
    public String encode(CharSequence charSequence) {
        logger.debug("--------------encode--------------"+charSequence.toString());
        return charSequence.toString();

    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        logger.debug("--------------matches--------------"+s);
        return s.equals(charSequence.toString());
    }
}
