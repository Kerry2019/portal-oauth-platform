package pers.kerry.portal.uaa.oauth2server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import pers.kerry.portal.uaa.oauth2server.pojo.LdapDataProperty;
import pers.kerry.portal.uaa.oauth2server.pojo.LdapSecurityProperty;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * 如果不加 @EnableResourceServer，在登录成功后会出现“重定向次数过多ERR_TOO_MANY_REDIRECTS”的错误
 *
 * @author: kerry.wu
 * @since: 2019/9/25 12:13
 * @history: 1.2019/9/25 created by kerry.wu
 */
@EnableConfigurationProperties({LdapSecurityProperty.class, LdapDataProperty.class})
@SpringBootApplication
@EnableResourceServer
@EnableFeignClients
public class Oauth2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2ServerApplication.class, args);
	}

}
