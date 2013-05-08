package demo;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;
import org.codehaus.groovy.control.customizers.SecureASTCustomizer;

import demo.domain.Service;
import demo.domain.ServiceLifecyle;

public class AdvancedParser implements DSLParser {

	@Override
	public Service parse(File dslFile) throws Exception {

		final CompilerConfiguration cc = new CompilerConfiguration();

		// Set up default imports
		final ImportCustomizer ic = new ImportCustomizer();
		ic.addImports(Service.class.getName());
		ic.addImports(ServiceLifecyle.class.getName());

		// Adding the base class for all scripts
		cc.setScriptBaseClass(AdvancedBaseDslScript.class.getName());

		// Add expression checker to prevent a dynamically generated service name 
		SecureASTCustomizer secure = new SecureASTCustomizer();
		secure.addExpressionCheckers(new SecureASTCustomizer.ExpressionChecker() {

			@Override
			public boolean isAuthorized(Expression expression) {
				if (expression instanceof MethodCallExpression) {
					MethodCallExpression mce = (MethodCallExpression) expression;
					String text = mce.getMethod().getText();
					if (text.equals("name")) {
						ArgumentListExpression args = (ArgumentListExpression) mce
								.getArguments();
						Expression arg = args.getExpression(0);
						if (!(arg instanceof ConstantExpression)) {
							return false;
						}

					}

				}
				return true;
			}

		});

		cc.addCompilationCustomizers(ic, secure);

		// Load properties file and inject all properties to the DSL bindings.
		Binding binding = new Binding();
		Properties props = new Properties();
		
		loadProperties(dslFile, props);
		
		Set<Entry<Object, Object>> entries = props.entrySet();
		for (Entry<Object, Object> entry : entries) {
			binding.setProperty(entry.getKey().toString(), entry.getValue().toString());
		}
		// Create the groovy shell
		GroovyShell shell = new GroovyShell(binding, cc);

		// parse the dsl file
		final Object result = shell.evaluate(dslFile);
		return (Service) result;

	}

	private void loadProperties(File dslFile, Properties props)
			throws FileNotFoundException, IOException {
		File propertiesFile = new File(dslFile.getAbsolutePath()
				+ ".properties");
		if (propertiesFile.exists()) {
			FileReader reader = null;
			try {
				reader = new FileReader(propertiesFile);
				
				props.load(reader);
			} finally {
				if (reader != null) {
					reader.close();
				}

			}

		}
	}

}
