package com.cjw.shorturl.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	ADMIN("ROLE_ADMIN"), MEMBER("ROLE_MEMBER");
	private final String value;
}
