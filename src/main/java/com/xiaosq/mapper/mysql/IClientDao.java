package com.xiaosq.mapper.mysql;


import com.xiaosq.entity.Client;

public interface IClientDao {

	Client loadClientByClientId(String clientId);
	
}
