package pers.kerry.portal.uaa.oauth2server.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import pers.kerry.portal.uaa.oauth2server.user.OauthClientServiceImpl;
import pers.kerry.portal.uaa.oauth2server.user.UserDetailsServiceImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2019/9/25 10:02
 * @history: 1.2019/9/25 created by kerry.wu
 */

@Configuration
@EnableAuthorizationServer
public class CustomAuthorizationServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenStore tokenStore;
    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;
    @Autowired
    private WebResponseExceptionTranslator customWebResponseExceptionTranslator;
    /**
     * 用户验证，自定义实现userDetailsService接口
     */
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    /**
     * 客户端验证，自定义实现oauthClientService接口
     */
    @Autowired
    private OauthClientServiceImpl oauthClientServiceImpl;


    /**
     * 加上 allowFormAuthenticationForClients() 后，授权码模式中获取token时，可以直接将client_id、client_secret作为参数传入
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .withClientDetails(oauthClientServiceImpl)
                .clients(oauthClientServiceImpl);
    }


    /**
     * 密码password模式，需要实现该方法 authenticationManager
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsServiceImpl);

        //扩展token返回结果
        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancerList = new ArrayList();
            enhancerList.add(jwtTokenEnhancer);
            enhancerList.add(jwtAccessTokenConverter);
            tokenEnhancerChain.setTokenEnhancers(enhancerList);
            //jwt
            endpoints.tokenEnhancer(tokenEnhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }
        endpoints.exceptionTranslator(customWebResponseExceptionTranslator);
    }
}
