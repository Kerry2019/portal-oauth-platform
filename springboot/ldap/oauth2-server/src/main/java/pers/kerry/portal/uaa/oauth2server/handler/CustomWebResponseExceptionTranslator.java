package pers.kerry.portal.uaa.oauth2server.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2019/9/25 11:58
 * @history: 1.2019/9/25 created by kerry.wu
 */
@Component("customWebResponseExceptionTranslator")
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        CustomOAuth2Exception customOauthException=new CustomOAuth2Exception(e.getCause().getMessage());
//        InternalAuthenticationServiceException internalAuthenticationServiceException=e;
//        OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
        return ResponseEntity
                .status(customOauthException.getHttpErrorCode())
                .body(customOauthException);
    }
}
