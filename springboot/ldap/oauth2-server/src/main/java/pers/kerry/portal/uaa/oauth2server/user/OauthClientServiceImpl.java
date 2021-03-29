package pers.kerry.portal.uaa.oauth2server.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import pers.kerry.portal.uaa.oauth2server.common.EncoderUtil;
import pers.kerry.portal.uaa.oauth2server.dao.PaasDao;
import pers.kerry.portal.uaa.oauth2server.pojo.OauthClientEO;

import java.util.*;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2019/10/23 8:50
 * @history: 1.2019/10/23 created by kerry.wu
 */
@Service
public class OauthClientServiceImpl implements ClientDetailsService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PaasDao paasDao;


    @Override
    public BaseClientDetails loadClientByClientId(String clientId)  {
        OauthClientEO client = paasDao.getClientByClientId(clientId);
        if (client == null) {
            throw new ClientRegistrationException(clientId+"无效") ;
        }
        BaseClientDetails baseClientDetails=new BaseClientDetails();
        baseClientDetails.setClientId(client.getClientId());
        baseClientDetails.setClientSecret(EncoderUtil.encryptByBCrypt(client.getClientSecret()));
        baseClientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
        baseClientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
        baseClientDetails.setAuthorizedGrantTypes(Arrays.asList(client.getAuthorizedGrantTypes().split(",")));
        Collection<SimpleGrantedAuthority> collection = new HashSet<>();
        collection.add(new SimpleGrantedAuthority("ROLE_USER"));
        baseClientDetails.setAuthorities(collection);
        Set<String> scope = new TreeSet<String>();
        scope.add("user_info");
        baseClientDetails.setScope(scope);
        baseClientDetails.setAutoApproveScopes(scope);
        return baseClientDetails;
    }
}