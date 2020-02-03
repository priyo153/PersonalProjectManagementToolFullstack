package com.demoProject.security;

public class SecurityConstants {
	
	public static final String SIGN_UP_URLS="/users/**";
	public static final String SECRET="SecretKeyToGenerateTokens";
	public static final String TOKEN_PREFIX="Bearer ";
	public static final String HEADER_STRING="Authorization";
	public static final Long EXPIRATION_TIME=300000L;
	
	

}
