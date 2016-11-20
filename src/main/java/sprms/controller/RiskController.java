package sprms.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sprms.auxiliary.CollectionHelper;
import sprms.auxiliary.DateHelper;
import sprms.component.RiskComponent;
import sprms.component.RiskManagementPlanComponent;
import sprms.component.UserComponent;
import sprms.model.ExtraRisk;
import sprms.model.Risk;
import sprms.model.RiskInfluence;
import sprms.model.RiskManagementPlan;
import sprms.model.RiskPosibility;
import sprms.model.RiskType;
import sprms.model.User;

@Controller
@RequestMapping("Home/Risk")
public class RiskController {
	
	@Autowired
	private RiskComponent riskService;
	
	@Autowired
	private RiskManagementPlanComponent riskPlanService;
	
	@Autowired
	private UserComponent userService;
	
	@RequestMapping(value="/addPlan",method = RequestMethod.POST)
	public @ResponseBody  Map<String,Object> addPlan(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		String name = request.getParameter("name");
		
		RiskManagementPlan plan = new RiskManagementPlan();
		plan.setName(name);
		
		plan = riskPlanService.createRiskManagementPlan(plan);
		
		int flag = 0;
		if(plan!=null){
			flag = 1;
		}
		map.put("flag", flag);
		return map;
	}
	
	@RequestMapping(value="/addFollowers",method = RequestMethod.POST)
	public @ResponseBody  Map<String,Object> addFollowers(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		int rid = Integer.parseInt(request.getParameter("rid"));
		String followerStr = request.getParameter("followers");
		int followers[] = CollectionHelper.arrayParse(followerStr);
		
		Risk risk = riskService.followRisks(rid,followers);
		int flag = 0;
		if(risk!=null){
			flag = 1;
		}
		map.put("flag", flag);
		return map;
	}
	
	@RequestMapping(value="/deleteRisks",method = RequestMethod.POST)
	public @ResponseBody  Map<String,Object> deleteRisks(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		int pid = Integer.parseInt(request.getParameter("pid"));
		String riskStr = request.getParameter("risks");
		int risks[] = CollectionHelper.arrayParse(riskStr);
		RiskManagementPlan plan = riskPlanService.removeRiskFromPlan(pid, risks);
		int flag = 0;
		if(plan!=null){
			flag = 1;
		}
		map.put("flag", flag);
		return map;
	}
	
	@RequestMapping(value="/addRisks",method = RequestMethod.POST)
	public @ResponseBody  Map<String,Object> addRisks(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		
		int pid = Integer.parseInt(request.getParameter("pid"));
		
		String riskStr = request.getParameter("risks");
		int risks[] = CollectionHelper.arrayParse(riskStr);
		
		RiskManagementPlan plan = riskPlanService.addRiskToPlan(pid, risks);
		
		int flag = 0;
		if(plan!=null){
			flag = 1;
		}
		map.put("flag", flag);
		
		return map;
	}
	
	@RequestMapping(value="/addRisk",method = RequestMethod.POST)
	public @ResponseBody  Map<String,Object> addRisk(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		int influence = Integer.parseInt(request.getParameter("influence"));
		int possibility = Integer.parseInt(request.getParameter("possibility"));
		
		User u = userService.getUser((long)uid);
		
		Risk risk = new Risk();
		risk.setContent(request.getParameter("description"));
		risk.setRiskTrigger(request.getParameter("trigger"));
		risk.setPosibility(RiskPosibility.values()[possibility]);
		risk.setInfluence(RiskInfluence.values()[influence]);
		
		risk = riskService.submitRisk(risk, u);
		
		int flag = 0;
		if(risk!=null){
			flag = 1;
		}
		map.put("flag", flag);
		
		return map;
	}
	
	
	@RequestMapping(value="/getRisks",method = RequestMethod.POST)
	public @ResponseBody  Map<String,Object> getRisks(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		int typeIndex = Integer.parseInt(request.getParameter("type"));
		
		String fromDateStr = request.getParameter("fromDate");
		String toDateStr = request.getParameter("toDate");
		
		Date fromDate = DateHelper.parseDate(fromDateStr);
		Date toDate = DateHelper.parseDate(toDateStr);
		
		Optional<Date> fromDateOption = Optional.ofNullable(fromDate);
		Optional<Date> toDateOption = Optional.ofNullable(toDate);
		Optional<Integer> limitOption = Optional.ofNullable(null);
		RiskType type = RiskType.values()[typeIndex];
		
		Map<Risk,Integer> retMap = null;
		if(type.equals(RiskType.RISK)){
			retMap = riskService.queryMostRecognizedRisk(fromDateOption, toDateOption, limitOption);
		}else{
			retMap = riskService.queryMostProblemedRisk(fromDateOption, toDateOption, limitOption);
		}
		
		List<ExtraRisk> list = CollectionHelper.parseMap(retMap);
		map.put("list",list );
		
		return map;
	}
	
	@RequestMapping(value="/addState",method = RequestMethod.POST)
	public @ResponseBody  Map<String,Object> addState(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		int rid = Integer.parseInt(request.getParameter("rid"));
		int state = Integer.parseInt(request.getParameter("state"));
		String desc = request.getParameter("desc");
		
		Risk risk = riskService.addRiskState(desc, (long) rid, state);
		int flag = 0;
		if(risk!=null){
			flag = 1;
		}
		map.put("flag", flag);
		return map;
	}
	
	@RequestMapping(value="/getTable", method= RequestMethod.GET)
	public @ResponseBody  Map<String,Object> getTable(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		Iterable<Risk> risks = riskService.getAllRisk();
		int count = CollectionHelper.getLength(risks);
		map.put("table", risks);
		map.put("count", count);
		return map;
	}
}
