package sprms.service;

import java.util.Date;
import java.util.Map;
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
	
	Risk addRiskState(String desc,Long rid,int state);
	
	Risk followRisks(int rid , int[] followers);
	
	Risk getRisk(Long id);
	
	Map<Risk, Integer> queryMostRecognizedRisk(Optional<Date> from,Optional<Date> to,Optional<Integer> limit);
	
	Map<Risk, Integer> queryMostProblemedRisk(Optional<Date> from,Optional<Date> to,Optional<Integer> limit);
}
