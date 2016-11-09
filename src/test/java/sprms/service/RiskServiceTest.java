package sprms.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import sprms.model.Risk;
import sprms.model.RiskState;
import sprms.model.User;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RiskServiceTest {

	@Autowired
	RiskService service;
	@Autowired
	UserService userService;
	
	@Test
	public void test() {
		Risk risk=new Risk();
		risk.setContent("risk1");
		User submitter=new User();
		submitter.setName("submitter");
		User follower=new User();
		follower.setName("follower");
		RiskState state=new RiskState();
		state.setDiscription("state");
		
		submitter=userService.addUser(submitter);
		risk=service.submitRisk(risk, submitter);
		assertEquals(risk.getSubmitter().getName(), submitter.getName());
		
		follower=userService.addUser(follower);
		risk=service.followRisk(risk, follower);
		assertEquals(risk.getFollowers().get(0).getName(), follower.getName());
		
		risk=service.addRiskState(risk, state);
		assertEquals(risk.getStates().get(0).getDiscription(), state.getDiscription());
	}

}
