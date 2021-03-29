package pers.kerry.portal.uaa.oauth2server.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2019/9/25 12:08
 * @history: 1.2019/9/25 created by kerry.wu
 */
@Component
public class LogoutHandlerImpl implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            final String refererUrl = request.getHeader("Referer");
            //实现自定义重定向
            response.sendRedirect(refererUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
