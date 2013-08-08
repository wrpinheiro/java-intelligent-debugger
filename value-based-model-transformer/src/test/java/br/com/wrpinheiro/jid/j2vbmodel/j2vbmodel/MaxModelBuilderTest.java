package br.com.wrpinheiro.jid.j2vbmodel.j2vbmodel;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import br.com.wrpinheiro.jid.j2vbmodel.JavaModelBuilder;


/**
 * @author wrp
 * 
 * 22/11/2008
 */
public class MaxModelBuilderTest {
	static {
		PropertyConfigurator.configure("log4j.properties");
	}
	
	private static String SRC_DIR = "resources/examples/programs/";
	private static String PROGRAM_TEST = "Max.java";

	@Test
	public void testModelBuilder() throws FileNotFoundException {
		String program = SRC_DIR + PROGRAM_TEST;

		JavaModelBuilder jb = new JavaModelBuilder(program, "main");

		assertNotNull(jb.getSystem());
	}

	public static void main(String[] args) throws FileNotFoundException {
		String program = SRC_DIR + PROGRAM_TEST;
		new JavaModelBuilder(program, "main");
	}
}
