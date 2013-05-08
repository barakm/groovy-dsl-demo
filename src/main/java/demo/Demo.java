package demo;

import java.io.File;

import demo.domain.Service;

public class Demo {

	private enum DemoModes {
		UNMODIFIED1("src/main/resources/BasicJava.groovy"), 
		UNMODIFIED2("src/main/resources/BasicGroovy.groovy"), 
		NO_IMPORT("src/main/resources/GroovyNoImport.groovy"),
		CLOSURES("src/main/resources/GroovyClosure.groovy"), 
		SECURE("src/main/resources/GroovySecure.groovy"), 
		ADVANCED("src/main/resources/GroovyAdvanced.groovy");

		private String path;

		private DemoModes(final String path) {
			this.path = path;
		}

		public String getPath() {
			return this.path;
		}
	}

	private static DSLParser getParser(DemoModes mode) {
		switch (mode) {
		case UNMODIFIED1:
		case UNMODIFIED2:
			return new UnmodifiedGroovyParser();
		case NO_IMPORT:
			return new CustomizedImportParser();
		case CLOSURES:
			return new ClosureParser();
		case ADVANCED:
		case SECURE:
			return new AdvancedParser();
		default:
			throw new IllegalStateException("Unsupported mode: " + mode);
		}
	}

	public static void main(String[] args) throws Exception {
		final DemoModes mode = DemoModes.ADVANCED;
		runDemo(mode);

	}

	private static void runDemo(final DemoModes mode) throws Exception {
		final DSLParser parser = getParser(mode);
		final File dslFile = new File(mode.getPath());
		System.out.println("Running DSL Parser for mode: " + mode.toString()
				+ " from File: " + dslFile);
		final Service service = parser.parse(dslFile);
		System.out.println(service);
	}
}
