package sprms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sprms.model.User;
import sprms.service.UserService;

@Controller    
@RequestMapping("/Home")   
public class HomeController {
	
	@Autowired
	private UserService userService = null;
	
	@RequestMapping("/welcome")
	public String helloHtml(Map<String,Object> map){  
		return "Welcome";  
	}  
	
	@RequestMapping(value="/addUser", method= RequestMethod.GET)
	public @ResponseBody  Map<String,Object> addUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String name = request.getParameter("name");
		String role = request.getParameter("role");
		Map<String,Object> map = new HashMap<String,Object>();
		User u = new User();
		u.setName(name);
		u.setRole(role);
		int flag = 1;
		User ret = userService.addUser(u);
		if(ret == null){
			flag = 0;
		}
		map.put("flag", flag);
		return map;
	}
	
	@RequestMapping(value="/getTable", method= RequestMethod.GET)
	public @ResponseBody  Map<String,Object> getTable(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		Iterable<User> users = userService.getAllUser();
		map.put("table", users);
		return map;
	}
	
}
