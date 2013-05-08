package demo.domain;

public class Service {

	private String name;
	private ServiceLifecyle lifecycle;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ServiceLifecyle getLifecycle() {
		return lifecycle;
	}
	public void setLifecycle(ServiceLifecyle lifecycle) {
		this.lifecycle = lifecycle;
	}
	@Override
	public String toString() {
		return "Service [name=" + name + ", lifecycle=" + lifecycle + "]";
	}
	
	
	
	
	
	
}
