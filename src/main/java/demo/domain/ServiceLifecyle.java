package demo.domain;

public class ServiceLifecyle {

	private String install;
	private String start;
	
	public String getInstall() {
		return install;
	}
	public void setInstall(String install) {
		this.install = install;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	@Override
	public String toString() {
		return "ServiceLifecyle [install=" + install + ", start=" + start + "]";
	}
	
	
}
