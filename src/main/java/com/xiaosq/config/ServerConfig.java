package com.xiaosq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import javax.sql.DataSource;

/**
 * 
 * @author Administrator
 *
 */
@Configuration
@EnableAuthorizationServer // 必须
@EnableResourceServer //必须
public class ServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
    @Qualifier("mysqlDataSource")
	private DataSource dataSource;
	
	@Autowired
    @Qualifier("qiYunClientService")
	private ClientDetailsService clientService;
	
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients()//允许表单登录
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

    	clients.jdbc(dataSource).clients(clientService);

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	
    	//AuthorizationCodeServices inMemoryServices = new InMemoryAuthorizationCodeServices();
    	
        endpoints
                .tokenStore(tokenStore());
                //.authorizationCodeServices(inMemoryServices);
                //.pathMapping("/oauth/token", "/oauth2/token");//自定义授权提交页面
    }

    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore(); //使用内存中的 token store
    }
    
}
