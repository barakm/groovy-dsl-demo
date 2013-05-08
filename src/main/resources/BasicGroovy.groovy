
import demo.domain.Service;
import demo.domain.ServiceLifecyle;

new Service(
	name:"MyService", 
	lifecycle: new ServiceLifecyle(install:"myInstall.sh", start:"myStart.sh")
	)
