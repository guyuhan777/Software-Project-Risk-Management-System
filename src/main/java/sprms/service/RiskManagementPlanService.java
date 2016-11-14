package sprms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sprms.model.Risk;
import sprms.model.RiskManagementPlan;

@Service
public interface RiskManagementPlanService {
	public RiskManagementPlan createRiskManagementPlan(RiskManagementPlan plan);
	public RiskManagementPlan addRiskToPlan(RiskManagementPlan plan,Risk risk);
	public RiskManagementPlan addRiskToPlan(RiskManagementPlan plan,List<Risk> risks);
	public RiskManagementPlan removeRiskFromPlan(RiskManagementPlan plan,Risk risk);
	public List<RiskManagementPlan> getAllRiskManagementPlan();
}
