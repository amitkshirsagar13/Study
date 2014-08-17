package org.abs.controller;

import java.util.List;

import org.abs.bean.SystemUser;
import org.abs.service.SystemUserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: Aug 16, 2014
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
@Controller
public class LoginController {

	/**
	 * Initiate the log4j Logger for debugging purposes.
	 * 
	 */
	private static Logger log4j = Logger.getLogger(LoginController.class);

	/**
	 * Print object values by default. Add required attributes of the object.
	 */

	@Override
	public String toString() {
		return "LoginController: ";
	}

	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public ModelAndView newLoginForm(ModelMap model) {
		return new ModelAndView("login", "command", new SystemUser());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginSystemUser(
			@ModelAttribute("SpringWeb") SystemUser systemUser, ModelMap model) {
		SystemUserService systemUserService = new SystemUserService();
		System.out.println(systemUser);
		systemUser = systemUserService.loginSystemUser(systemUser);
		if (systemUser == null) {
			model.addAttribute("loginMessage", "System User not found...");
		}
		model.addAttribute("systemUser", systemUser);
		return "Welcome";
	}

	@RequestMapping(value = "/getSystemUserList", method = RequestMethod.GET)
	public String getSystemUserList(
			@ModelAttribute("SpringWeb") SystemUser systemUser, ModelMap model) {
		SystemUserService systemUserService = new SystemUserService();
		systemUser.setEmailId("%gmail.com");
		List<SystemUser> systemUserList = systemUserService
				.getSystemUserList(systemUser);
		model.addAttribute("systemUserList", systemUserList);
		return "Welcome";
	}
}
