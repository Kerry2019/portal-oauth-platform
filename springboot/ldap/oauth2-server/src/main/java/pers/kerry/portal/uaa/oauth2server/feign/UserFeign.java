package pers.kerry.portal.uaa.oauth2server.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2019/9/25 12:15
 * @history: 1.2019/9/25 created by kerry.wu
 */
@FeignClient( name = "feign-user-oauth2Server",url = "http://paperbee.definesys.com/api/paperbee/user")
public interface UserFeign {

    /**
     * 根据用户名获取密码
     * @param username
     * @return
     */
    @ResponseBody
    @GetMapping("/loadUserByUsername")
    public String loadUserByUsername(@RequestParam("username")String username);

}

