package com.xiaosq.service;

import com.xiaosq.entity.Client;
import com.xiaosq.mapper.mysql.IClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service("qiYunClientService")
public class ClientService implements ClientDetailsService {

    @Autowired
    private IClientDao clientDao;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = clientDao.loadClientByClientId(clientId);
        return client;
    }

}
