package demo;

import org.apache.commons.beanutils.BeanUtils;

import demo.domain.Service;
import demo.domain.ServiceLifecyle;
import groovy.lang.Closure;
import groovy.lang.Script;

public abstract class BaseDslScript extends Script {

	Service service;
	ServiceLifecyle lifecycle;
	Object activeObject = null;
	public BaseDslScript() {
		
		lifecycle = new ServiceLifecyle();
		service = new Service();
		service.setLifecycle(lifecycle);
		 
	}
	
		  
	
	
	public Object service(Closure<Object> closure) {
		activeObject = service;
		closure.call();
		return service;
	}
	
	public Object lifecycle(Closure<Object> closure) {
		activeObject = lifecycle;
		closure.call();
		return lifecycle;
	}
	

	
	@Override
	public Object invokeMethod(final String name, final Object arg) {
		if(arg instanceof Object[]) {
			Object[] paramArray = (Object[])arg;
			if(paramArray.length == 1) {
				final Object param = paramArray[0];
				try {
					BeanUtils.setProperty(this.activeObject, name, param);
				} catch (Exception e) {
					throw new IllegalArgumentException(e);
				} 
				return activeObject;
			}
		}
		
		return super.invokeMethod(name, arg);
	}
}
