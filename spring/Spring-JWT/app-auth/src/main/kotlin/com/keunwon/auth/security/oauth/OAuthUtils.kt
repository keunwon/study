package com.keunwon.auth.security.oauth

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest

fun OAuth2UserRequest.registrationId(): String = this.clientRegistration.registrationId

fun OAuth2UserRequest.userNameAttributeName(): String =
    this.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName
