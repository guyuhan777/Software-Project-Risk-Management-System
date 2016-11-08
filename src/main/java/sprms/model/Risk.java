package sprms.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Risk {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	@Column
	String content;
	@Column
	RiskPosibility posibility;
	@Column
	RiskInfluence influence;
	@Column
	String riskTrigger;
	@ManyToOne
	User submitter;
	@ManyToMany
	List<User> Followers;	
	@OneToMany
	List<RiskState> states;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public RiskPosibility getPosibility() {
		return posibility;
	}
	public void setPosibility(RiskPosibility posibility) {
		this.posibility = posibility;
	}
	public RiskInfluence getInfluence() {
		return influence;
	}
	public void setInfluence(RiskInfluence influence) {
		this.influence = influence;
	}
	public String getRiskTrigger() {
		return riskTrigger;
	}
	public void setRiskTrigger(String riskTrigger) {
		this.riskTrigger = riskTrigger;
	}
	public User getSubmitter() {
		return submitter;
	}
	public void setSubmitter(User submitter) {
		this.submitter = submitter;
	}
	public List<User> getFollowers() {
		return Followers;
	}
	public void setFollowers(List<User> followers) {
		Followers = followers;
	}
	public List<RiskState> getStates() {
		return states;
	}
	public void setStates(List<RiskState> states) {
		this.states = states;
	}
	
}
