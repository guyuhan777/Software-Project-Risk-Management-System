package sprms.service;

import org.springframework.stereotype.Service;

import sprms.model.Risk;
import sprms.model.RiskState;
import sprms.model.User;

@Service
public interface RiskService {
	Risk submitRisk(Risk risk,User submitter);
	Risk followRisk(Risk risk,User follower);
	Iterable<Risk> getAllRisk();
	Risk addRiskState(Risk risk,RiskState state);
}
