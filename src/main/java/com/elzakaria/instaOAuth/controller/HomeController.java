package com.elzakaria.instaOAuth.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.elzakaria.instaOAuth.beans.json.self.RootObject;
import com.elzakaria.instaOAuth.services.impl.InstaHttpCurl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class HomeController {

	private static final String CODE_INSTA = "codeInsta";
	private static final String  COOKIE_ACCESS_TOKEN  = "acess_token_ig";

	@Autowired
	private InstaHttpCurl instaHttpCurlService;

	@RequestMapping(value = "hello")
	public ModelAndView hello(@RequestParam(required = false, defaultValue = "World") final String pName,
			final HttpSession pHttpSession, final HttpServletRequest pRequest) {
		ModelAndView ret = new ModelAndView("home");
		// Adds an objet to be used in home.jsp
		ret.addObject("name", pName);

		String accessToken = null;
		
		//We check if we have the access token, from the session first, and if not we go check the cookies, if not found self will be
		// null, and then home.jsp rendering will give link to ig authorize
		if (pHttpSession.getAttribute(CODE_INSTA) != null) {
			accessToken = (String) pHttpSession.getAttribute(CODE_INSTA);
		}else {
			Cookie cookieAccessToken = WebUtils.getCookie(pRequest,COOKIE_ACCESS_TOKEN);
			accessToken = cookieAccessToken != null ? cookieAccessToken.getValue() : null;			
		}
		
		if (!StringUtils.isEmpty(accessToken)) {
			try {
				Gson gson = new GsonBuilder().create();
				RootObject fromJson = gson.fromJson(instaHttpCurlService.getUsersSelf(accessToken), RootObject.class);
				ret.addObject("self", fromJson);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	@RequestMapping(value = "redirect_uri")
	public ModelAndView redirectURI(@RequestParam(required = true, name = "code") final  String code,
			final  HttpSession pHttpSession, final  HttpServletResponse pResponse) {

		// Post the curl to obtain the token
		String authorizationCode = null;
		ModelAndView ret = null;
		try {
			authorizationCode = instaHttpCurlService.curlForToken(code);
			pHttpSession.setAttribute(CODE_INSTA, authorizationCode);
			
			ret = new ModelAndView("home");
			// Adds an objet to be used in home.jsp
			ret.addObject("name", code);
			ret.addObject(CODE_INSTA, authorizationCode);
			
			storeAccessTokenAsCookie(pResponse, authorizationCode);
			
		} catch (Exception e) {
			//TODO handle errors with better management
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * We store the access token in the browser so we avoid reconnecting every time
	 * @param pResponse
	 * @param authorizationCode
	 */
	private void storeAccessTokenAsCookie(final HttpServletResponse pResponse, String authorizationCode) {
		//Keep that cookie so we dont redirect each time
		Cookie cookieAccessToken = new Cookie(COOKIE_ACCESS_TOKEN, authorizationCode);
		cookieAccessToken.setMaxAge(Integer.MAX_VALUE);
		pResponse.addCookie(cookieAccessToken);
	}

	/**
	 * @return the instaHttpCurlService
	 */
	public InstaHttpCurl getInstaHttpCurlService() {
		return instaHttpCurlService;
	}

	/**
	 * @param instaHttpCurlService
	 *            the instaHttpCurlService to set
	 */
	public void setInstaHttpCurlService(InstaHttpCurl instaHttpCurlService) {
		this.instaHttpCurlService = instaHttpCurlService;
	}

}
