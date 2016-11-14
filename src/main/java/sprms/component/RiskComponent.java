package sprms.component;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sprms.model.Risk;
import sprms.model.RiskState;
import sprms.model.User;
import sprms.repository.RiskRepository;
import sprms.repository.RiskStateRepository;
import sprms.service.RiskService;

@Component
public class RiskComponent implements RiskService{

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
		state=riskStateRepository.save(state);
		risk.getStates().add(state);
		return riskRepository.save(risk);
	}

	@Override
	public List<Risk> queryMostRecognizedRisk() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Risk> queryMostRecognizedRisk(Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Risk> queryMostProblemedRisk() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Risk> queryMostProblemedRisk(Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

}
