package pers.kerry.portal.uaa.oauth2server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import pers.kerry.portal.uaa.oauth2server.pojo.OauthClientEO;
import pers.kerry.portal.uaa.oauth2server.pojo.OauthConfigEO;
import pers.kerry.portal.uaa.oauth2server.pojo.UserEO;

import java.util.Date;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2019/10/24 22:27
 * @history: 1.2019/10/24 created by kerry.wu
 */
@Component
public class PaasDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询client
     *
     * @param key
     * @return
     */
    public List<OauthClientEO> queryClients(String key,int page,int pageSize) {
        Query query = new Query();
        Criteria criteriaClientId = Criteria.where("client_id").regex(key);
        Criteria criteriaUri = Criteria.where("web_server_redirect_uri").regex(key);
        Criteria criteriaGrant = Criteria.where("authorized_grant_types").regex(key);
        query.addCriteria(new Criteria().orOperator(criteriaClientId, criteriaUri, criteriaGrant));
        //分页
        Sort sort=new Sort(Sort.Direction.DESC, "creation_date");
        Pageable pageable=PageRequest.of(page-1,pageSize,sort);
        query.with(pageable);
        return mongoTemplate.find(query, OauthClientEO.class);
    }

    /**
     * 查询 client 的数量
     * @param key
     * @return
     */
    public long countClients(String key){
        Query query = new Query();
        Criteria criteriaClientId = Criteria.where("client_id").regex(key);
        Criteria criteriaUri = Criteria.where("web_server_redirect_uri").regex(key);
        Criteria criteriaGrant = Criteria.where("authorized_grant_types").regex(key);
        query.addCriteria(new Criteria().orOperator(criteriaClientId, criteriaUri, criteriaGrant));
        return mongoTemplate.count(query,"oauth_client");
    }

    /**
     * 删除client
     *
     * @param clientId
     */
    public void deleteClient(String clientId) {
        Query query = new Query(Criteria.where("client_id").is(clientId));
        mongoTemplate.remove(query, OauthClientEO.class);
    }

    /**
     * 新增client
     *
     * @param client
     */
    public void addClient(OauthClientEO client) {
        client.setCreationDate(new Date());
        mongoTemplate.save(client);
    }

    /**
     * 更新client
     *
     * @param client
     */
    public void updateClient(OauthClientEO client) {
        Query query = new Query(Criteria.where("client_id").is(client.getClientId()));
        Update update = new Update().set("client_secret", client.getClientSecret())
                .set("authorized_grant_types", client.getAuthorizedGrantTypes())
                .set("web_server_redirect_uri", client.getWebServerRedirectUri())
                .set("access_token_validity", client.getAccessTokenValidity())
                .set("refresh_token_validity", client.getRefreshTokenValidity())
                .set("client_description", client.getClientDescription());
        mongoTemplate.updateFirst(query, update, OauthClientEO.class);
    }

    /**
     * 查询存在某clientId的数量
     *
     * @param clientId
     * @return
     */
    public long countClientId(String clientId) {
        Query query = new Query(Criteria.where("client_id").is(clientId));
        return mongoTemplate.count(query, OauthClientEO.class);
    }

    /**
     * 查询 oauth_config 配置表
     *
     * @return
     */
    public OauthConfigEO getOauthConfig() {
        return mongoTemplate.findOne(new Query(), OauthConfigEO.class);
    }

    /**
     * 更新 oauth_config.login_page
     *
     * @param loginPage
     */
    public void updateOauthConfigLoginPage(String loginPage) {
        Query query = new Query();
        Update update = new Update().set("login_page", loginPage);
        mongoTemplate.updateFirst(query, update, OauthConfigEO.class);
    }

    /**
     * 通过clientId 查询 client
     *
     * @param clientId
     * @return
     */
    public OauthClientEO getClientByClientId(String clientId) {
        Query query = new Query(Criteria.where("client_id").is(clientId));
        return mongoTemplate.findOne(query, OauthClientEO.class);
    }

    /**
     * 查询user
     *
     * @param key
     * @return
     */
    public List<UserEO> queryUsers(String key, int page, int pageSize) {
        Query query = new Query();
        Criteria criteriaUsername = Criteria.where("username").regex(key);
        Criteria criteriaName = Criteria.where("name").regex(key);
        query.addCriteria(new Criteria().orOperator(criteriaUsername, criteriaName));
        //分页
        Sort sort=new Sort(Sort.Direction.DESC, "creation_date");
        Pageable pageable=PageRequest.of(page-1,pageSize,sort);
        query.with(pageable);
        return mongoTemplate.find(query, UserEO.class);
    }

    /**
     * 根据username获取user
     * @param username
     * @return
     */
    public UserEO getUserByUsername(String username){
        Query query=new Query();
        Criteria criteriaUsername = Criteria.where("username").is(username);
        query.addCriteria(criteriaUsername);
        return mongoTemplate.findOne(query,UserEO.class);
    }

    /**
     * 查询 user 的数量
     * @param key
     * @return
     */
    public long countUsers(String key){
        Query query = new Query();
        Criteria criteriaUsername = Criteria.where("username").regex(key);
        Criteria criteriaName = Criteria.where("name").regex(key);
        query.addCriteria(new Criteria().orOperator(criteriaUsername, criteriaName));
        return mongoTemplate.count(query,"user");
    }

    /**
     * 删除client
     *
     * @param username
     */
    public void deleteUser(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        mongoTemplate.remove(query, UserEO.class);
    }

    /**
     * 新增client
     *
     * @param user
     */
    public void addUser(UserEO user) {
        user.setCreationDate(new Date());
        mongoTemplate.save(user);
    }

    /**
     * 更新user
     *
     * @param user
     */
    public void updateUser(UserEO user) {
        Query query = new Query(Criteria.where("username").is(user.getUsername()));
        Update update = new Update()
                .set("password", user.getPassword())
                .set("name",user.getName())
                .set("email",user.getEmail());
        mongoTemplate.updateFirst(query, update, UserEO.class);
    }

    /**
     * 查询存在某username的数量
     *
     * @param username
     * @return
     */
    public long countUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.count(query, UserEO.class);
    }




}
