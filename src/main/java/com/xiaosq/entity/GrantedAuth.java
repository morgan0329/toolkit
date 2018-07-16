package com.xiaosq.entity;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class GrantedAuth implements GrantedAuthority {
	
	private String authority;
	
	public GrantedAuth(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}

}
