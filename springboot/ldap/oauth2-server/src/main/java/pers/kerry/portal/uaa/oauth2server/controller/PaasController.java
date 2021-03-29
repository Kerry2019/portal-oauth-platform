package pers.kerry.portal.uaa.oauth2server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.web.bind.annotation.*;
import pers.kerry.portal.uaa.oauth2server.common.PageQueryResult;
import pers.kerry.portal.uaa.oauth2server.common.Response;
import pers.kerry.portal.uaa.oauth2server.dao.PaasDao;
import pers.kerry.portal.uaa.oauth2server.pojo.OauthClientEO;
import pers.kerry.portal.uaa.oauth2server.pojo.UserEO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description: 单点登录配置平台页面的业务接口
 * @author: kerry.wu
 * @since: 2019/10/9 22:41
 * @history: 1.2019/10/9 created by kerry.wu
 */
@RestController
@RequestMapping(value = "/paas")
public class PaasController {
    private final PaasDao paasDao;
    private final LdapTemplate ldapTemplate;

    public PaasController(PaasDao paasDao,LdapTemplate ldapTemplate){
        this.paasDao=paasDao;
        this.ldapTemplate=ldapTemplate;
    }
    /**
     * 客户端（client）- 查询客户端
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/client/queryClients", method = RequestMethod.POST)
    public Response queryClients(@RequestBody Map<String, Object> request) {
        Integer page = (Integer) request.get("page");
        Integer pageSize = (Integer) request.get("pageSize");
        String key = request.get("key") == null ? "" : (String) request.get("key");

        List<OauthClientEO> detailsEOList=paasDao.queryClients(key,page,pageSize);
        long detailCount=paasDao.countClients(key);
        for(OauthClientEO oauthClientDetailsEO:detailsEOList){
            if( oauthClientDetailsEO.getAuthorizedGrantTypes()!=null){
              String[] arrays=  oauthClientDetailsEO.getAuthorizedGrantTypes().split(",");
              oauthClientDetailsEO.setAuthorizedGrantTypesList(new ArrayList<>(Arrays.asList(arrays)));
            }
        }
        PageQueryResult<OauthClientEO> pageQueryResult = new PageQueryResult<>(detailCount,detailsEOList);
        return Response.ok().data(pageQueryResult);
    }

    /**
     * 客户端（client）- 新增客户端
     *
     * @param detailsEO
     * @return
     */
    @RequestMapping(value = "/client/addClient", method = RequestMethod.POST)
    public Response addClient(@RequestBody OauthClientEO detailsEO) {
        //验证clientId唯一性
        long countClientId = paasDao.countClientId(detailsEO.getClientId());
        if (countClientId > 0) {
            return Response.error("客户端编号 不唯一");
        }
        //authorizedGrantTypesList 数据转换
        String authorizedGrantTypes = "";
        if (detailsEO.getAuthorizedGrantTypesList() != null) {
            for (String type : detailsEO.getAuthorizedGrantTypesList()) {
                authorizedGrantTypes = authorizedGrantTypes + type + ",";
            }
            authorizedGrantTypes=authorizedGrantTypes.substring(0, authorizedGrantTypes.length() - 1);
        }
        detailsEO.setAuthorizedGrantTypes(authorizedGrantTypes);
        //存数据
        paasDao.addClient(detailsEO);
        return Response.ok();
    }

    /**
     * 客户端（client）- 删除客户端
     *
     * @param detailsEO
     * @return
     */
    @RequestMapping(value = "/client/deleteClient", method = RequestMethod.POST)
    public Response deleteClient(@RequestBody OauthClientEO detailsEO) {
        paasDao.deleteClient(detailsEO.getClientId());
        return Response.ok();
    }

    /**
     * 客户端（client）- 编辑客户端
     *
     * @param detailsEO
     * @return
     */
    @RequestMapping(value = "/client/updateClient", method = RequestMethod.POST)
    public Response updateClient(@RequestBody OauthClientEO detailsEO) {
        //authorizedGrantTypesList 数据转换
        String authorizedGrantTypes = "";
        if (detailsEO.getAuthorizedGrantTypesList() != null) {
            for (String type : detailsEO.getAuthorizedGrantTypesList()) {
                authorizedGrantTypes = authorizedGrantTypes + type + ",";
            }
            authorizedGrantTypes=authorizedGrantTypes.substring(0, authorizedGrantTypes.length() - 1);
        }
        detailsEO.setAuthorizedGrantTypes(authorizedGrantTypes);
        //存数据
        paasDao.updateClient(detailsEO);
        return Response.ok();
    }

    /**
     * 更新 oauth_config.login_page
     * @param request
     * @return
     */
    @RequestMapping(value = "/config/updateLoginPage", method = RequestMethod.POST)
    public Response updateLoginPage(@RequestBody Map<String,String> request){
        String loginPage=request.get("loginPage");
        paasDao.updateOauthConfigLoginPage(loginPage);
        return Response.ok();
    }

    @RequestMapping(value = "/config/getConfig", method = RequestMethod.POST)
    public Response getConfig(){
        return Response.ok().data(paasDao.getOauthConfig());
    }


    /**
     * 用户（user）- 查询用户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/queryUsers", method = RequestMethod.POST)
    public Response queryUsers(@RequestBody Map<String, Object> request) {
        Integer page = (Integer) request.get("page");
        Integer pageSize = (Integer) request.get("pageSize");
        String key = request.get("key") == null ? "" : (String) request.get("key");

        List<UserEO> userEOList=paasDao.queryUsers(key,page,pageSize);
        long userCount=paasDao.countUsers(key);
        PageQueryResult<UserEO> pageQueryResult = new PageQueryResult<>(userCount,userEOList);
        return Response.ok().data(pageQueryResult);
    }

    /**
     * 用户（user）- 新增用户
     *
     * @param userEO
     * @return
     */
    @RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
    public Response addUser(@RequestBody UserEO userEO) {
        //验证clientId唯一性
        long countUsername = paasDao.countUsername(userEO.getUsername());
        if (countUsername > 0) {
            return Response.error("账号不能重复");
        }
        //存数据
        paasDao.addUser(userEO);
        return Response.ok();
    }

    /**
     * 用户（user）- 删除用户
     *
     * @param userEO
     * @return
     */
    @RequestMapping(value = "/user/deleteUser", method = RequestMethod.POST)
    public Response deleteUser(@RequestBody UserEO userEO) {
        paasDao.deleteUser(userEO.getUsername());
        return Response.ok();
    }

    /**
     * 用户（user）- 编辑用户
     *
     * @param userEO
     * @return
     */
    @RequestMapping(value = "/user/updateUser", method = RequestMethod.POST)
    public Response updateUser(@RequestBody UserEO userEO) {
        //存数据
        paasDao.updateUser(userEO);
        return Response.ok();
    }




}
