import demo.domain.Service;
import demo.domain.ServiceLifecyle;

Service service = new Service();
service.setName("MyService");

ServiceLifecyle lifecycle = new ServiceLifecyle()
lifecycle.setInstall("myInstall.sh");
lifecycle.setStart("myStart.sh");

service.setLifecycle(lifecycle);
return service;