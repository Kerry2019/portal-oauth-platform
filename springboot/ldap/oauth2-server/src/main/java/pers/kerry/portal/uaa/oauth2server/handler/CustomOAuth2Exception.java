package pers.kerry.portal.uaa.oauth2server.handler;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2019/9/25 11:05
 * @history: 1.2019/9/25 created by kerry.wu
 */
@JsonSerialize(using = CustomOAuth2ExceptionSerializer.class)
public class CustomOAuth2Exception extends OAuth2Exception {
    public CustomOAuth2Exception(String msg) {
        super(msg);
    }
}
