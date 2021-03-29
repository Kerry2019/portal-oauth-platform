package pers.kerry.portal.uaa.oauth2server.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pers.kerry.portal.uaa.oauth2server.dao.PaasDao;
import pers.kerry.portal.uaa.oauth2server.feign.UserFeign;
import pers.kerry.portal.uaa.oauth2server.pojo.UserEO;

import javax.annotation.Resource;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2019/9/25 12:13
 * @history: 1.2019/9/25 created by kerry.wu
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PaasDao paasDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws BadCredentialsException {
        //enable ：用户已失效
        //accountNonExpired：用户帐号已过期
        //credentialsNonExpired：坏的凭证
        //accountNonLocked：用户账号已锁定
        //String password= userFeign.loadUserByUsername(username);
        UserEO userEO=paasDao.getUserByUsername(username);
        return new User(username, new PasswordEncoderImpl().encode(userEO.getPassword()), AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));

    }


    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("22"));

    }

}
