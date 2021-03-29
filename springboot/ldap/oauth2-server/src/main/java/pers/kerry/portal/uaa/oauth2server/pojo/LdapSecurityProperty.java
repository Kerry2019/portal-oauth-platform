package pers.kerry.portal.uaa.oauth2server.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @date: 2021/3/29 9:49 上午
 * @author: kerry
 */
@ConfigurationProperties("custom.ldap.security")
public class LdapSecurityProperty {
    private String searchBase;
    private String searchFilter;
    private String url;
    private String manageDn;
    private String managePassword;


    public String getSearchBase() {
        return searchBase;
    }

    public void setSearchBase(String searchBase) {
        this.searchBase = searchBase;
    }

    public String getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(String searchFilter) {
        this.searchFilter = searchFilter;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getManageDn() {
        return manageDn;
    }

    public void setManageDn(String manageDn) {
        this.manageDn = manageDn;
    }

    public String getManagePassword() {
        return managePassword;
    }

    public void setManagePassword(String managePassword) {
        this.managePassword = managePassword;
    }

}
