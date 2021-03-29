package pers.kerry.portal.uaa.oauth2server.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import pers.kerry.portal.uaa.oauth2server.pojo.LdapDataProperty;
import pers.kerry.portal.uaa.oauth2server.pojo.LdapSecurityProperty;

/**
 * @description:
 * @date: 2021/3/29 1:55 下午
 * @author: kerry
 */
@Configuration
public class LdapConfig {
    private final LdapDataProperty ldapDataProperty;

    public LdapConfig(LdapDataProperty ldapDataProperty) {
        this.ldapDataProperty = ldapDataProperty;
    }

    @Bean
    public LdapContextSource ldapContextSource(){
        LdapContextSource ldapContextSource=new LdapContextSource();
        ldapContextSource.setUrl(ldapDataProperty.getUrl());
        ldapContextSource.setBase(ldapDataProperty.getSearchBase());
        ldapContextSource.setUserDn(ldapDataProperty.getManageDn());
        ldapContextSource.setPassword(ldapDataProperty.getManagePassword());
        return ldapContextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate(){
        return new LdapTemplate(ldapContextSource());
    }
}
