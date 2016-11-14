package sprms.service;

import java.util.Date;
import java.util.Optional;

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
	
	Iterable<Risk> queryMostRecognizedRisk(Optional<Date> from,Optional<Date> to,Optional<Integer> limit);
	
	Iterable<Risk> queryMostProblemedRisk(Optional<Date> from,Optional<Date> to,Optional<Integer> limit);
}
