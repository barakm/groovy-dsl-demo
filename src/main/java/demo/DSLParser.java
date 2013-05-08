package demo;

import java.io.File;

import demo.domain.Service;

public interface DSLParser {
	public Service parse(final File dslFile) throws Exception;
}
