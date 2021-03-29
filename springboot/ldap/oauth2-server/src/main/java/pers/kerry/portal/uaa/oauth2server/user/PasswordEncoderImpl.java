package pers.kerry.portal.uaa.oauth2server.user;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import pers.kerry.portal.uaa.oauth2server.common.EncoderUtil;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 *      系统默认全局的加密器是在 CustomWebSecurityConfigurerAdapter.java 中通过@Bean passwordEncoder()来实现
 *      这里实现自定义的加密器，用于用户密码的加密方式
 * @author: kerry.wu
 * @since: 2019/9/26 10:11
 * @history: 1.2019/9/26 created by kerry.wu
 */

public class PasswordEncoderImpl implements PasswordEncoder {

    /**
     * 手动调用方法，用于 UserDetailsServiceImpl中手动加密password
     * @param charSequence
     * @return
     */
    @Override
    public String encode(CharSequence charSequence) {
        String pwd = charSequence.toString();
        String md5Pwd= EncoderUtil.encryptByMd5(pwd);
        return md5Pwd;
    }

    /**
     * 后台默认方法，用于验证密码
     * @param charSequence
     * @param s
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        String pwd = charSequence.toString();
        String md5Pwd = EncoderUtil.encryptByMd5(pwd);
        System.out.println("\n begin \n pwd:"+pwd+"\n md5pwd:"+md5Pwd+"\n s:"+s);
        if(md5Pwd.equals(s)){
            return true;
        }
        throw new DisabledException("--密码错误--");
    }



}
