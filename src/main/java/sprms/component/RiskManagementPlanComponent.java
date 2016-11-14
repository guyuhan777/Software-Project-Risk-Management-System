package sprms.component;

import java.util.List;

import org.springframework.stereotype.Component;

import sprms.model.Risk;
import sprms.model.RiskManagementPlan;
import sprms.service.RiskManagementPlanService;

@Component
public class RiskManagementPlanComponent implements RiskManagementPlanService{

	@Override
	public RiskManagementPlan createRiskManagementPlan(RiskManagementPlan plan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RiskManagementPlan addRiskToPlan(RiskManagementPlan plan, Risk risk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RiskManagementPlan addRiskToPlan(RiskManagementPlan plan, List<Risk> risks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RiskManagementPlan removeRiskFromPlan(RiskManagementPlan plan, Risk risk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RiskManagementPlan> getAllRiskManagementPlan() {
		// TODO Auto-generated method stub
		return null;
	}

}
