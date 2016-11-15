package sprms.component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sprms.model.Risk;
import sprms.model.RiskState;
import sprms.model.RiskType;
import sprms.model.User;
import sprms.repository.RiskRepository;
import sprms.repository.RiskStateRepository;
import sprms.service.RiskService;

@Component
public class RiskComponent implements RiskService {

	@Autowired
	RiskRepository riskRepository;
	@Autowired
	RiskStateRepository riskStateRepository;

	@Override
	public Risk submitRisk(Risk risk, User submitter) {
		risk.setSubmitter(submitter);
		return riskRepository.save(risk);
	}

	@Override
	public Risk followRisk(Risk risk, User follower) {
		risk.getFollowers().add(follower);
		return riskRepository.save(risk);
	}

	@Override
	public Iterable<Risk> getAllRisk() {
		return riskRepository.findAll();
	}

	@Override
	public Risk addRiskState(Risk risk, RiskState state) {
		state = riskStateRepository.save(state);
		risk.getStates().add(state);
		return riskRepository.save(risk);
	}

	@Override
	public Map<Risk,Integer> queryMostRecognizedRisk(Optional<Date> from, Optional<Date> to, Optional<Integer> limit) {
		return queryMostShowedRiskType(from, to, limit, RiskType.RISK);
	}

	@Override
	public Map<Risk,Integer> queryMostProblemedRisk(Optional<Date> from, Optional<Date> to, Optional<Integer> limit) {
		return queryMostShowedRiskType(from, to, limit, RiskType.PROBLEM);
	}
	
	private Map<Risk,Integer> queryMostShowedRiskType(Optional<Date> from, Optional<Date> to, Optional<Integer> limit,RiskType type) {
		List<Risk> risks = (List<Risk>) riskRepository.findAll();
		risks.sort((r1, r2) -> countRiskStates(r1, from, to, type)
				- countRiskStates(r2, from, to, type));
		List<Risk> mostShown= limit.isPresent() ? risks.subList(0, Math.min(risks.size(), limit.get().intValue())) : risks;
		HashMap<Risk, Integer> result=new HashMap<>();
		for(Risk risk:mostShown){
			result.put(risk, countRiskStates(risk, from, to, type));
		}
		return result;
	}

	private int countRiskStates(Risk risk, Optional<Date> from, Optional<Date> to, RiskType type) {
		return (int) risk.getStates().stream().filter(state -> dateBetween(state, from, to))
				.filter(state -> state.getRiskType() == type).count();
	}

	private boolean dateBetween(RiskState state, Optional<Date> from, Optional<Date> to) {
		return (from.isPresent() ? state.getCreatedAt().compareTo(from.get()) >= 0 : true)
				&& (to.isPresent() ? state.getCreatedAt().compareTo(to.get()) <= 0 : true);
	}

}
