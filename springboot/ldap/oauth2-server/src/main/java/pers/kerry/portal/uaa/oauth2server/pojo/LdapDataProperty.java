package pers.kerry.portal.uaa.oauth2server.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @date: 2021/3/29 2:43 下午
 * @author: kerry
 */
@ConfigurationProperties("custom.ldap.data")
public class LdapDataProperty {
    private String searchBase;
    private String url;
    private String manageDn;
    private String managePassword;

    public String getSearchBase() {
        return searchBase;
    }

    public void setSearchBase(String searchBase) {
        this.searchBase = searchBase;
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
