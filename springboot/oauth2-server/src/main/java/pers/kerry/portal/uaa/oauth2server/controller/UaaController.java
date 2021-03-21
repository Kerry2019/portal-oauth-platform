package pers.kerry.portal.uaa.oauth2server.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.kerry.portal.uaa.oauth2server.dao.PaasDao;
import pers.kerry.portal.uaa.oauth2server.pojo.UserEO;
import pers.kerry.portal.uaa.oauth2server.pojo.UserProfileDTO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description: 单点登录内置接口
 * @author: kerry.wu
 * @since: 2019/10/9 22:42
 * @history: 1.2019/10/9 created by kerry.wu
 */
@Controller
@RequestMapping(value = "/uaa")
public class UaaController {
    private String JWT_SIGN_KEY="jwtSigningKey";
    @Resource
    private PaasDao paasDao;

    /**
     * 自定义登录页面
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return paasDao.getOauthConfig().getLoginPage();
    }

    /**
     * 解析token，获取用户信息
     * @param authentication
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @GetMapping("/parseJwt")
    public UserProfileDTO getCurrentUser1(Authentication authentication, HttpServletRequest request) throws UnsupportedEncodingException {
        String header = request.getHeader("Authorization");
        String token = header
                .replace("bearer ","")
                .replace("Bearer ","");
        Claims claims = Jwts.parser().setSigningKey(JWT_SIGN_KEY.getBytes("UTF-8")).parseClaimsJws(token).getBody();
        String username=(String) claims.get("user_name");
        UserEO userEO=paasDao.getUserByUsername(username);
        return new UserProfileDTO(userEO.getUsername(),userEO.getEmail(),userEO.getName());
    }


}
