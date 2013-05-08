package demo;

import groovy.lang.GroovyShell;

import java.io.File;
import demo.domain.Service;

public class UnmodifiedGroovyParser implements DSLParser{

	@Override
	public Service parse(File dslFile) throws Exception{

		// Create the groovy shell
		GroovyShell shell = new GroovyShell();
		
		// parse the dsl file
		final Object result = shell.evaluate(dslFile);
		return (Service)result;
		
	}
	

}
