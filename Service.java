package project;

public class Service {

	private String serviceName;
	private int serviceCost;
	
	Service (String serviceName, int serviceCost) {
		this.setServiceName(serviceName);
		this.setServiceCost(serviceCost);
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public int getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(int serviceCost) {
		this.serviceCost = serviceCost;
	}

}
