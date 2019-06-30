package com.backgom.odyssey.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContext {

	public static String getLoginMember() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			return "Non-members";
		}

		return authentication.getPrincipal().toString();
	}
}
