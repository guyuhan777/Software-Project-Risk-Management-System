package sprms.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sprms.model.Risk;
import sprms.model.RiskManagementPlan;
import sprms.repository.RiskManagementPlanRepository;
import sprms.repository.RiskRepository;
import sprms.service.RiskManagementPlanService;

@Component
public class RiskManagementPlanComponent implements RiskManagementPlanService{
	
	@Autowired
	RiskManagementPlanRepository repository;
	
	@Autowired
	RiskRepository riskRepository;
	
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
		return repository.save(plan);
	}

	@Override
	public List<RiskManagementPlan> getAllRiskManagementPlan() {
		return (List<RiskManagementPlan>) repository.findAll();
	}

	@Override
	public RiskManagementPlan getRiskPlan(long id) {
		return repository.findOne(id);
	}

	@Override
	public RiskManagementPlan removeRiskFromPlan(int pid, int[] risks) {
		RiskManagementPlan plan = repository.findOne((long)pid);
		List<Risk> risk = new ArrayList<Risk>();
		for(int i=0;i<risks.length;i++){
			risk.add(riskRepository.findOne((long)risks[i]));
		}
		plan.getRiskList().removeAll(risk);
		return repository.save(plan);
	}

	@Override
	public RiskManagementPlan addRiskToPlan(int pid, int[] risks) {
		RiskManagementPlan plan = repository.findOne((long)pid);
		List<Risk> risk = new ArrayList<Risk>();
		for(int i=0;i<risks.length;i++){
			risk.add(riskRepository.findOne((long)risks[i]));
		}
		plan.getRiskList().addAll(risk);
		return repository.save(plan);
	}

}
