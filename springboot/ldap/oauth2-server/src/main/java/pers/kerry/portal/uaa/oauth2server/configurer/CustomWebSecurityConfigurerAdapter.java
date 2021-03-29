package pers.kerry.portal.uaa.oauth2server.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import pers.kerry.portal.uaa.oauth2server.handler.LogoutHandlerImpl;
import pers.kerry.portal.uaa.oauth2server.pojo.LdapSecurityProperty;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2019/9/25 10:01
 * @history: 1.2019/9/25 created by kerry.wu
 */

@Configuration
@Order(1)
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationFailureHandler appLoginFailureHandler;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private LdapSecurityProperty ldapSecurityProperty;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/uaa/login")
                .antMatchers("/oauth/authorize","/oauth/token")
                .antMatchers("/logout")

                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()

                .and()
                // 自定义登录页面，这里配置了 loginPage, 就会通过 LoginController 的 login 接口加载登录页面
                .formLogin()
                .loginPage("/uaa/login")
                //当用户直接访问登录页时，登录成功后默认重定向到defaultSuccessUrl
                .defaultSuccessUrl("https://www.baidu.com/")
                .permitAll()
                //加上了 successHandler、failureHandler、exceptionHandling 会导致跳转页面有问题
                .failureHandler(appLoginFailureHandler)
                .failureUrl("/uaa/login?error=true")

                //注销
                .and()
                .logout()
                .addLogoutHandler(new LogoutHandlerImpl())

                .and()
                .csrf()
                .disable();
    }

    /**
     * web ignore比较适合配置前端相关的静态资源，它是完全绕过spring security的所有filter的
     * ingore是完全绕过了spring security的所有filter，相当于不走spring security
     * permitall没有绕过spring security，其中包含了登录的以及匿名的
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/oauth/logout",
                "/paas/**",
                "/uaa/open/**");
    }

    /**
     * 创建该实例，为了保证 密码模式中可以实现AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     *
     * ldap 账号密码配置
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userSearchBase(ldapSecurityProperty.getSearchBase())
                .userSearchFilter(ldapSecurityProperty.getSearchFilter())
                .contextSource()
                .url(ldapSecurityProperty.getUrl())
                .managerDn(ldapSecurityProperty.getManageDn())
                .managerPassword(ldapSecurityProperty.getManagePassword());
    }

    /**
     * 注册 PasswordEncoder 接口的Bean
     * 对于没有使用自定义PasswordEncoder加密方式的，都会默认用该 加密器
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
