package demo;

import groovy.lang.GroovyShell;

import java.io.File;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;

import demo.domain.Service;
import demo.domain.ServiceLifecyle;

public class CustomizedImportParser implements DSLParser{

	@Override
	public Service parse(File dslFile) throws Exception{

		final CompilerConfiguration cc = new CompilerConfiguration();
		
		// Set up default imports
		final ImportCustomizer ic = new ImportCustomizer();
		ic.addImports(Service.class.getName());
		ic.addImports(ServiceLifecyle.class.getName());
		cc.addCompilationCustomizers(ic);
		
		// Create the groovy shell
		GroovyShell shell = new GroovyShell(cc);
		
		// parse the dsl file
		final Object result = shell.evaluate(dslFile);
		return (Service)result;
		
	}
	

}
