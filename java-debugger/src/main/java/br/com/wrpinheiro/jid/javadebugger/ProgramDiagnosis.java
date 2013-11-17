package br.com.wrpinheiro.jid.javadebugger;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import br.com.wrpinheiro.jgraphlib.familyset.FamilySet;
import br.com.wrpinheiro.jid.j2vbmodel.JavaModelBuilder;
import br.com.wrpinheiro.jid.programdiagnosis.HypothesisValue;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerProgramDiagnosisProblem;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;

public class ProgramDiagnosis {
	/**
	 * Log initialization.
	 */
	static {
		PropertyConfigurator.configure("log4j.properties");
	}

	/**
	 * Class logger.
	 */
	public static Logger LOG = Logger.getLogger(ProgramDiagnosis.class);

	/**
	 * Source code directory.
	 */
	private static String SRC_DIR = "programs/";

	/**
	 * Test case directory.
	 */
	private static String TEST_CASE_DIR = "testcases/";

	/**
	 * Find the hyphotesis set for pProgram using pTestCase.
	 * 
	 * @param pProgram
	 *            the program file.
	 * @param pTestCase
	 *            the test case file.
	 * @return a hyphotesis set.
	 * @throws FileNotFoundException 
	 */
	public final FamilySet<HypothesisValue> findHyphotesisSet(String pProgram,
			String pTestCase) throws FileNotFoundException {
		String program = SRC_DIR + pProgram;
		String testCase = TEST_CASE_DIR + pTestCase;

		LOG.debug("Debugging: " + program + " with testcase: " + testCase);

		JavaModelBuilder jb = new JavaModelBuilder(program, "main");
		IntegerSystem system = jb.getSystem();

		IntegerProgramDiagnosisProblem dp = new IntegerProgramDiagnosisProblem(
				program, system);
		dp.loadObservations(testCase);

		return dp.findHyphotesisSet();
	}
}
