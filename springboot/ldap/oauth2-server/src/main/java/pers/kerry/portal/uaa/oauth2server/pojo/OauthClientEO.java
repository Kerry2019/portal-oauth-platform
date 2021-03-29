package pers.kerry.portal.uaa.oauth2server.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2019/10/24 22:23
 * @history: 1.2019/10/24 created by kerry.wu
 */
@Document(collection = "oauth_client")
public class OauthClientEO {

    /**
     * 客户端编号
     */
    @Field("client_id")
    private String clientId;
    /**
     * 客户端密钥
     */
    @Field("client_secret")
    private String clientSecret;

    /**
     * 授权方式
     */
    @Field("authorized_grant_types")
    private String authorizedGrantTypes;
    /**
     * 重定向地址
     */
    @Field("web_server_redirect_uri")
    private String webServerRedirectUri;
    /**
     * access_token 有效期
     */
    @Field("access_token_validity")
    private int accessTokenValidity;
    /**
     * refresh_token 有效期
     */
    @Field("refresh_token_validity")
    private int refreshTokenValidity;

    /**
     * client客户端的描述内容
     */
    @Field("client_description")
    private String clientDescription;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy.MM.dd HH:mm:ss",timezone = "GMT+8")
    @Field("creation_date")
    private Date creationDate;

    /**
     * 附加字段
     */
    @Transient
    private List<String> authorizedGrantTypesList;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public int getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(int accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public int getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(int refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getClientDescription() {
        return clientDescription;
    }

    public void setClientDescription(String clientDescription) {
        this.clientDescription = clientDescription;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<String> getAuthorizedGrantTypesList() {
        return authorizedGrantTypesList;
    }

    public void setAuthorizedGrantTypesList(List<String> authorizedGrantTypesList) {
        this.authorizedGrantTypesList = authorizedGrantTypesList;
    }


}
