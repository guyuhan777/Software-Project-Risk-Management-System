package sprms.model;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class RiskState {
	@Id
	@GeneratedValue
	Long id;
	@Column
	RiskType riskType;
	@Column
	String discription;
	@Column
	@CreationTimestamp
	Date createdAt;
	
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
