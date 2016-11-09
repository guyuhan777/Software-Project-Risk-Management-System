package sprms.service;

import java.util.List;

import sprms.model.Risk;
import sprms.model.RiskState;
import sprms.model.User;

public interface RiskService {
	Risk submitRisk(Risk risk,User submitter);
	Risk followRisk(Risk risk,User follower);
	List<Risk> getAllRisk();
	Risk addRiskStage(Risk risk,RiskState stage);
}
