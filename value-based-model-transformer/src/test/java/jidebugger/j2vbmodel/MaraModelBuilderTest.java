package jidebugger.j2vbmodel;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;

import org.junit.Test;

import br.com.wrpinheiro.jid.j2vbmodel.JavaModelBuilder;


/**
 * @author wrp
 * 
 * 22/11/2008
 */
public class MaraModelBuilderTest {
	private String SRC_DIR = "resources/examples/programs/";
	private String PROGRAM_TEST = "Maratona.java";

	@Test
	public void testModelBuilder() throws FileNotFoundException {
		String program = SRC_DIR + PROGRAM_TEST;

		JavaModelBuilder jb = new JavaModelBuilder(program, "main");

		assertNotNull(jb.getSystem());
	}
}
