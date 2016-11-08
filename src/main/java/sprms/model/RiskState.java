package sprms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RiskState {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	@Column
	RiskType riskType;
	@Column
	String discription;
	@Column(updatable=false)
	Date createdAt=new Date();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RiskType getRiskType() {
		return riskType;
	}
	public void setRiskType(RiskType riskType) {
		this.riskType = riskType;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	
}
