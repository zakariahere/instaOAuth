package com.elzakaria.instaOAuth.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.elzakaria.instaOAuth.services.impl.InstaHttpCurl;

@Controller
public class HomeController {

	private static final String CODE_INSTA = "codeInsta";

	@Inject
	private InstaHttpCurl instaHttpCurlService;

	@RequestMapping(value = "hello")
	public ModelAndView hello(@RequestParam(required = false, defaultValue = "World") String name,
			HttpSession httpSession) {
		ModelAndView ret = new ModelAndView("home");
		// Adds an objet to be used in home.jsp
		ret.addObject("name", name);

		final String accessToken = (String) httpSession.getAttribute(CODE_INSTA);

		if (!StringUtils.isEmpty(accessToken)) {
			try {
				ret.addObject("self", instaHttpCurlService.getUsersSelf(accessToken));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	@RequestMapping(value = "redirect_uri")
	public ModelAndView redirectURI(@RequestParam(required = true, name = "code") String code,
			HttpSession httpSession) {

		// Post the curl to obtain the token
		String authorizationCode = null;
		try {
			authorizationCode = instaHttpCurlService.curlForToken(code);
		} catch (Exception e) {
			e.printStackTrace();
		}

		httpSession.setAttribute(CODE_INSTA, authorizationCode);
		ModelAndView ret = new ModelAndView("home");
		// Adds an objet to be used in home.jsp
		ret.addObject("name", code);
		ret.addObject(CODE_INSTA, authorizationCode);

		return ret;
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
