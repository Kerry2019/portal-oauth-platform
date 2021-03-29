package pers.kerry.portal.uaa.oauth2server.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2019/10/27 17:10
 * @history: 1.2019/10/27 created by kerry.wu
 */
@Document(collection = "user")
public class UserEO {

    @Field("username")
    private String username;

    private String password;

    private String name;

    @Field("email")
    private String email;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy.MM.dd HH:mm:ss",timezone = "GMT+8")
    @Field("creation_date")
    private Date creationDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
