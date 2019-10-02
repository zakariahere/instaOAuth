package com.elzakaria.instaOAuth.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.elzakaria.instaOAuth.beans.json.oauth.User;
import com.elzakaria.instaOAuth.beans.json.self.RootObject;
import com.elzakaria.instaOAuth.http.util.NameValuePairBuilder;
import com.elzakaria.instaOAuth.services.impl.InstaHttpCurl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

	//@TODO junit
	public static void main(String[] args) throws Exception {
		InstaHttpCurl httpCurl = new InstaHttpCurl();
		httpCurl.setNvpBuilder(new NameValuePairBuilder());
		String usersSelf = "{\"data\": {\"id\": \"12159557754\", \"username\": \"zakariafromtropoja\", \"profile_picture\": \"https://scontent.cdninstagram.com/vp/7736fa58f51647e67c6c8f288e1f874e/5E2BD008/t51.2885-19/s150x150/65855616_446299512766618_5442787766733635584_n.jpg?_nc_ht=scontent.cdninstagram.com\", \"full_name\": \"Zakaria\", \"bio\": \"The only thing that scares me is Keyser sose\", \"website\": \"\", \"is_business\": false, \"counts\": {\"media\": 7, \"follows\": 42, \"followed_by\": 43}}, \"meta\": {\"code\": 200}}"; //httpCurl.getUsersSelf("12159557754.93126b0.4d0ab42555104edeb1b5100d74c9e1a8");
		
		Gson gson = new GsonBuilder().create();
		RootObject fromJson = gson.fromJson(usersSelf, RootObject.class);
		System.out.println(fromJson);
		
		
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
