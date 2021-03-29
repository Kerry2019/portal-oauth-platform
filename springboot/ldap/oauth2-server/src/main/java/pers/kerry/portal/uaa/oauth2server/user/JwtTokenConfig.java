package pers.kerry.portal.uaa.oauth2server.user;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2019/9/25 12:12
 * @history: 1.2019/9/25 created by kerry.wu
 */
@Configuration
public  class JwtTokenConfig{
    private String JWT_SIGN_KEY="jwtSigningKey";


    /**
     * 使用jwtTokenStore存储token
     * @return
     */
    @Bean
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 用于生成jwt
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        //生成签名的key
        accessTokenConverter.setSigningKey(JWT_SIGN_KEY);
        return accessTokenConverter;
    }

    /**
     * 用于扩展JWT
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
    public TokenEnhancer jwtTokenEnhancer(){
        return new TokenEnhancerImpl();
    }


}
