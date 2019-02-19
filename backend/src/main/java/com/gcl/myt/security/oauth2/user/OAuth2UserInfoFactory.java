package com.gcl.myt.security.oauth2.user;

import java.util.Map;

import com.gcl.myt.exception.OAuth2AuthenticationProcessingException;
import com.gcl.myt.model.enums.AuthProvider;

public class OAuth2UserInfoFactory {

	private OAuth2UserInfoFactory() {
	}

	public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
		if (registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
			return new GoogleOAuth2UserInfo(attributes);
		} else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
			return new FacebookOAuth2UserInfo(attributes);
		} else if (registrationId.equalsIgnoreCase(AuthProvider.github.toString())) {
			return new GithubOAuth2UserInfo(attributes);
		} else {
			throw new OAuth2AuthenticationProcessingException(
					String.format("Sorry! Login with %s is not supported yet.", registrationId));
		}

	}

}
