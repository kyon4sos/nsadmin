package org.nekostudio.security;

//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//
///**
// * @author neko
// */
//@Component
//@Slf4j
//public class CustomAccessDecisionManager implements AccessDecisionManager {
//    @Override
//    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection)
//            throws AccessDeniedException, InsufficientAuthenticationException {
//        log.info("AccessDecisionManager {}\n",authentication);
//        log.info("Object {}\n",o);
//    }
//
//    @Override
//    public boolean supports(ConfigAttribute configAttribute) {
//        return true;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//}
