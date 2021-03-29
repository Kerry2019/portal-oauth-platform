package pers.kerry.portal.uaa.oauth2server.pojo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description: oauth 配置表
 * @author: kerry.wu
 * @since: 2019/10/17 12:11
 * @history: 1.2019/10/17 created by kerry.wu
 */
@Document(collection = "oauth_config")
public class OauthConfigEO {
    @Field("login_page")
    private String loginPage;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
