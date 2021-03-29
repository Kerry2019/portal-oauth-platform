package pers.kerry.portal.uaa.oauth2server.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.query.SearchScope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pers.kerry.portal.uaa.oauth2server.dao.PaasDao;
import pers.kerry.portal.uaa.oauth2server.pojo.UserEO;
import pers.kerry.portal.uaa.oauth2server.pojo.UserProfileDTO;

import javax.annotation.Resource;
import javax.naming.Name;
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
    private final String JWT_SIGN_KEY="jwtSigningKey";
    private final PaasDao paasDao;
    private final LdapTemplate ldapTemplate;

    public UaaController(PaasDao paasDao, LdapTemplate ldapTemplate) {
        this.paasDao = paasDao;
        this.ldapTemplate = ldapTemplate;
    }

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

    /**
     * ldap 登录
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @GetMapping("/open/ldap-login")
    public Boolean openLdapLogin(@RequestParam("username") String username,
                                 @RequestParam("password")String password){
        EqualsFilter filter = new EqualsFilter("cn",username);
        return ldapTemplate.authenticate("",filter.toString(),password);
    }

}
