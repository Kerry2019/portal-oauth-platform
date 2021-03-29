package pers.kerry.portal.uaa.oauth2server.pojo;

/**
 * @description:
 * @date: 2021/3/21 5:26 下午
 * @author: kerry
 */
public class UserProfileDTO {
    private String name;
    private String email;
    private String nickname;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public UserProfileDTO(String name, String email, String nickname) {
        this.name = name;
        this.email = email;
        this.nickname = nickname;
    }
}
