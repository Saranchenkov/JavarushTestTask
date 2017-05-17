package com.testtask.springmvc.controller;

import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import com.testtask.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.testtask.springmvc.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	UserService service;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing users with pagination.
	 */
	@RequestMapping(value = {"/{type}","/"}, method = RequestMethod.GET)
	public ModelAndView all(@PathVariable Map<String, String> map) {
		String type = map.get("type");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("allusers");
		mv.getModel().put("userList", type == null ? service.getCurrentPageList(1) : service.getCurrentPageList(Integer.parseInt(type)));
		mv.getModel().put("pageCount", service.getPageCount());
		mv.getModel().put("currentPage", type == null ? 1 : Integer.valueOf(type));
		return  mv;
	}

	/*
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
						   ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [name] should be implementing custom @Unique annotation
		 * and applying it on field [name] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!service.isUserNameUnique(user.getId(), user.getName())){
			FieldError nameError = new FieldError("user","name",messageSource.getMessage("non.unique.name", new String[]{user.getName()}, Locale.getDefault()));
		    result.addError(nameError);
			return "registration";
		}

		service.saveUser(user);
		model.addAttribute("success", "User \"" + user.getName() + "\" registered successfully");
		return "success";
	}

	/*
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-{name}-user" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String name, ModelMap model) {
		User user = service.findUserByName(name);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{name}-user"}, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
							 ModelMap model, @PathVariable String name) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!service.isUserNameUnique(user.getId(), user.getName())){
			FieldError ssnError = new FieldError("user","name",messageSource.getMessage("non.unique.name", new String[]{user.getName()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "registration";
		}

		service.updateUser(user);
		model.addAttribute("success", "User \"" + user.getName()	+ "\" updated successfully");
		return "success";
	}

	/*
	 * This method will delete an user by it's NAME value.
	 */
	@RequestMapping(value = { "/delete-{name}-user" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String name) {
		service.deleteUserByName(name);
		return "redirect:/";
	}

	@RequestMapping(value = "/search")
	public String Search(@RequestParam("searchString") String searchString, Model model) {

			User searchResult = service.findUserByName(searchString);
			model.addAttribute("find", searchResult != null);
			if (searchString.equals(null)){
				model.addAttribute("stringForSearching", "Name of user is empty. Please try again.");
			} else {
				model.addAttribute("stringForSearching", "User with name \"" + searchString + "\" is not found!");
			}
			model.addAttribute("searchResult", searchResult);

		return "searchresult";
	}
}
