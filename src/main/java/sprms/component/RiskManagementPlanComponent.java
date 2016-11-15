package sprms.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sprms.model.Risk;
import sprms.model.RiskManagementPlan;
import sprms.repository.RiskManagementPlanRepository;
import sprms.service.RiskManagementPlanService;

@Component
public class RiskManagementPlanComponent implements RiskManagementPlanService{
	
	@Autowired
	RiskManagementPlanRepository repository;

	@Override
	public RiskManagementPlan createRiskManagementPlan(RiskManagementPlan plan) {
		return repository.save(plan);
	}

	@Override
	public RiskManagementPlan addRiskToPlan(RiskManagementPlan plan, Risk risk) {
		plan.getRiskList().add(risk);
		return repository.save(plan);
	}

	@Override
	public RiskManagementPlan addRiskToPlan(RiskManagementPlan plan, List<Risk> risks) {
		plan.getRiskList().addAll(risks);
		return repository.save(plan);
	}

	@Override
	public RiskManagementPlan removeRiskFromPlan(RiskManagementPlan plan, Risk risk) {
		plan=repository.findOne(plan.getId());
		plan.getRiskList().remove(risk);
		return plan;
	}

	@Override
	public List<RiskManagementPlan> getAllRiskManagementPlan() {
		return (List<RiskManagementPlan>) repository.findAll();
	}

}
