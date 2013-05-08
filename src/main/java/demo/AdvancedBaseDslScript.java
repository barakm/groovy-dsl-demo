package demo;

import org.apache.commons.beanutils.BeanUtils;

import demo.domain.Service;
import demo.domain.ServiceLifecyle;
import groovy.lang.Closure;
import groovy.lang.Script;

public abstract class AdvancedBaseDslScript extends Script {

	Service service;
	ServiceLifecyle lifecycle;
	Object activeObject = null;
	
	private final StringBuilder printBuilder = new StringBuilder();
	private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AdvancedBaseDslScript.class.getName());
	
	public AdvancedBaseDslScript() {
		
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
	public void print(final Object obj) {
		if (obj != null) {
			printBuilder.append(obj.toString());
		}
	}


	@Override
	public void println(final Object obj) {
		if (obj != null) {
			printBuilder.append(obj.toString());
		}
		logger.info(printBuilder.toString());
		// probably performs as well as allocating a new one
		printBuilder.setLength(0);

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
