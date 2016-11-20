package sprms.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sprms.auxiliary.CollectionHelper;
import sprms.component.RiskComponent;
import sprms.component.RiskManagementPlanComponent;
import sprms.model.Risk;
import sprms.model.RiskManagementPlan;
import sprms.model.User;
import sprms.service.UserService;

@Controller    
@RequestMapping("/Home")   
public class HomeController {
	
	@Autowired
	private UserService userService = null;
	
	@Autowired
	private RiskComponent riskService = null;
	
	@Autowired
	private RiskManagementPlanComponent riskManagementService = null;
	
	@RequestMapping(value="/getPlan",method = RequestMethod.GET)
	public ModelAndView getPlan(@RequestParam("id") int id,Model model){
		ModelAndView mav = new ModelAndView("RiskPlanDetail");
		RiskManagementPlan plan = riskManagementService.getRiskPlan((long)id);
		model.addAttribute("plan",plan);
		return mav;
	}
	
	@RequestMapping(value="/getDetail", method= RequestMethod.GET)
	public ModelAndView getDetail(@RequestParam("id") int id,Model model){
		ModelAndView mav = new ModelAndView("RiskDetail");
		Risk risk = riskService.getRisk((long) id);
		Iterable<User> users = userService.getAllUser();
		model.addAttribute("risk", risk);
		model.addAttribute("followers",users);
		mav.addObject(model);
		return mav;
	}
	
	@RequestMapping("/account")
	public String account(Map<String,Object> map){  
		return "Analysis";  
	} 
	
	@RequestMapping("/addPlan")
	public String addPlan(Map<String,Object> map){  
		return "RiskPlanAdd";  
	}  
	
	@RequestMapping("/welcome")
	public String helloHtml(Map<String,Object> map){  
		return "Welcome";  
	}  
	
	@RequestMapping("/check")
	public ModelAndView checkPlan(Model model){
		ModelAndView mav = new ModelAndView("RiskPlanManagement");
		Iterable<RiskManagementPlan> plans = riskManagementService.getAllRiskManagementPlan();
		model.addAttribute("RiskPlan",plans);
		mav.addObject(model);
		return mav;
	}
	
	@RequestMapping("/add")
	public ModelAndView addRisk(Model model){
		ModelAndView mav = new ModelAndView("RiskAdd");
		Iterable<User> users = userService.getAllUser();
		model.addAttribute("users",users);
		mav.addObject(model);
		return mav;
	}
	
	
	@RequestMapping("/risk")
	public String riskMangement(Map<String,Object> map){  
		return "RiskManagement";  
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
		int count = CollectionHelper.getLength(users);
		map.put("table", users);
		map.put("count", count);
		return map;
	}
	
}
