package sprms.model;

public class ExtraRisk {
	
	public Risk getRisk() {
		return risk;
	}

	public void setRisk(Risk risk) {
		this.risk = risk;
	}


	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	Risk risk;
	
	int count;
	
}
