package sprms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class RiskManagementPlan {
	@Id
	@GeneratedValue
	Long id;
	@Column
	String name;
	@ManyToMany
	List<Risk> riskList=new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Risk> getRiskList() {
		return riskList;
	}
	public void setRiskList(List<Risk> riskList) {
		this.riskList = riskList;
	}
	
}
