package br.com.wrpinheiro.jid.javadebugger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import br.com.wrpinheiro.jgraphlib.familyset.ArrayFamilySet;
import br.com.wrpinheiro.jgraphlib.familyset.FamilySet;
import br.com.wrpinheiro.jgraphlib.familyset.SetMaintainer;
import br.com.wrpinheiro.jid.j2vbmodel.JavaModelBuilder;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.NullComponent;
import br.com.wrpinheiro.jid.programdiagnosis.HypothesisValue;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerProgramDiagnosisProblem;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;

/**
 * This class is responsible to find the diagnosis for a program using a test
 * suite.
 * 
 * @author wrp
 */
public class Start {

	/**
	 * Log initialization.
	 */
	static {
		PropertyConfigurator.configure("log4j.properties");
	}

	/**
	 * Class logger.
	 */
	public static Logger LOG = Logger.getLogger(Start.class);

	/**
	 * Source code directory.
	 */
	private static String TEST_SUITE_DIR = "testcases/";

	/**
	 * Key of the test suite name.
	 */
	private static final String TEST_SUITE_NAME = "name";

	/**
	 * Key of the resource (a java source file) to be tested.
	 */
	private static final String TEST_SUITE_RESOURCE_NAMES = "resourceName";

	/**
	 * Key of the available test cases.
	 */
	private static final String TEST_CASES = "testCases";

	/**
	 * Suffix key of the test case properties.
	 */
	private static final String TEST_CASE_PROPERTIES = ".properties";

	/**
	 * Suffix key of the method that will be tested.
	 */
	private static final String METHOD_TO_TEST = "method";

	private String getProperty(final Properties properties, final String name,
			boolean errorIfDoesNotExist) {
		String prop = properties.getProperty(name, "");

		if (errorIfDoesNotExist && prop.equals(""))
			throw new RuntimeException("Property '" + name + "' not found.");

		return prop;
	}

	private String getProperty(final Properties properties, final String name) {
		return getProperty(properties, name, true);
	}

	/**
	 * Load the test suite configuration file.
	 * 
	 * @param cfgTestSuite
	 *            the configuration file.
	 * @return the test case.
	 */
	private TestSuite load(final String cfgTestSuite) {
		try {
			LOG.info("loading testsuite: " + cfgTestSuite);

			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream(cfgTestSuite);

			properties.load(fis);

			TestSuite suite = new TestSuite();

			suite.setName(getProperty(properties, TEST_SUITE_NAME));
			LOG.info("Test suite name: " + suite.getName());

			suite.setResouceName(getProperty(properties,
					TEST_SUITE_RESOURCE_NAMES));
			LOG.info("Resource name: " + suite.getResourceName());

			suite.setMethod(getProperty(properties, METHOD_TO_TEST));
			LOG.info("Method to be tested: " + suite.getMethod());

			// set of the strings referring to the test cases for the method
			// given by the var. methodToTest
			Set<String> setOfTestCases = this.getSetOfString(properties,
					TEST_CASES);

			// for each test case used to test the method methodToTest.
			for (String testCaseStr : setOfTestCases) {
				TestCase testCase = loadTestCase(properties, testCaseStr);
				suite.addTestCase(testCaseStr, testCase);
			}

			fis.close();

			return suite;
		} catch (IOException ioex) {
			throw new RuntimeException(ioex);
		}
	}

	/**
	 * Convert a formatted string in a FamilySet. The string must be in the
	 * format {{<int>, <int>, ...}, {<int>, <int>, ...}, ...}
	 * 
	 * @param expectedValuesStr
	 *            the string
	 * @return a FamilySet.
	 */
	private FamilySet<HypothesisValue> strToFamilySet(String expectedValuesStr) {
		char[] values = expectedValuesStr.toCharArray();
		FamilySet<HypothesisValue> f = new ArrayFamilySet<HypothesisValue>();
		SetMaintainer<Integer> currentSet = null;
		int number = -1;
		char c;

		for (int i = 1; i < values.length; i++) {
			c = values[i];

			if (c == ' ')
				;
			else if (c == '{') {
				currentSet = new SetMaintainer<Integer>();
			} else if (Character.isDigit(c)) {
				if (number == -1) {
					number = c - '0';
				} else {
					number *= 10;
					number += (c - '0');
				}
			} else if (c == ',' && currentSet != null) {
				currentSet.add(number);
				number = -1;
			} else if (c == '}') {
				if (number != -1 && currentSet != null) {
					currentSet.add(number);
					number = -1;
					
					NullComponent cp = new NullComponent(null, null, currentSet, null);
					SetMaintainer<HypothesisValue> sm = new SetMaintainer<HypothesisValue>();
					HypothesisValue h = new HypothesisValue(cp);
					sm.add(h);
					
					f.add(sm);
					currentSet = null;
				}
			}
		}

		return f;
	}

	/**
	 * Create the test cases based on the configuration in the property file.
	 * 
	 * @param properties
	 *            the properties.
	 * @return a map of test cases.
	 */
	private TestCase loadTestCase(Properties properties, String name) {
		LOG.info("Loading test case: " + name);

		TestCase tc = new TestCase(new TestSuite());

		tc.setName(name);

		// properties of the test case.
		Set<String> testCaseProperties = getSetOfString(properties, name
				+ TEST_CASE_PROPERTIES);

		Map<String, String> tcProperties = new LinkedHashMap<String, String>();

		for (String testCaseProperty : testCaseProperties) {
			String propKey = name + "." + testCaseProperty;
			String propValue = getProperty(properties, propKey);
			tcProperties.put(testCaseProperty, propValue);
		}

		// TODO separar o que � entrada e o que � sa�da!!!!!
		tc.setInputs(tcProperties);

		String expectedValuesStr = getProperty(properties, name + ".expected",
				false);
		LOG
				.info("expected results available: "
						+ !expectedValuesStr.equals(""));
		if (!expectedValuesStr.equals("")) {
			FamilySet<HypothesisValue> expectedResults = strToFamilySet(expectedValuesStr);
			LOG.info("Expected results: " + name + "=" + expectedResults);
			tc.setExpectedResults(expectedResults);
		}

		LOG.info("Test case loaded: " + tc);

		return tc;
	}

	/**
	 * Return a set o strings given as a property value of a key.
	 * 
	 * @param properties
	 *            the property file.
	 * @param key
	 *            the key.
	 * @return a list of strings.
	 */
	private Set<String> getSetOfString(final Properties properties,
			final String key) {

		String str = getProperty(properties, key);

		StringTokenizer st = new StringTokenizer(str, ",");
		Set<String> setOfStrings = new LinkedHashSet<String>();
		while (st.hasMoreTokens())
			setOfStrings.add(st.nextToken().trim());

		return setOfStrings;
	}

	public TestSuite findHyphotesisSet(final String cfgTestSuite) throws FileNotFoundException {
		TestSuite ts = this.load(cfgTestSuite);

		Date date = new Date();

		String program = ts.getResourceName();
		LOG.info("Creating model for: " + program);
		JavaModelBuilder jb = new JavaModelBuilder(program, ts.getMethod());
		IntegerSystem system = jb.getSystem();

		for (TestCase tc : ts.getTestCases().values()) {
			LOG.info("Debugging with test case: " + tc);

			tc.start();
			IntegerProgramDiagnosisProblem dp = new IntegerProgramDiagnosisProblem(
					program, system);

			Map<String, String> inputsAndOutputs = new LinkedHashMap<String, String>();
			inputsAndOutputs.putAll(tc.getInputs());
			inputsAndOutputs.putAll(tc.getOutputs());

			dp.loadObservations(inputsAndOutputs);
			
			FamilySet<HypothesisValue> h = dp.findHyphotesisSet();
			tc.setActualResults(h);
			tc.stop();

			LOG.info("Elapsed time for test case " + tc + " is " + tc.getElapsedTime() + " ms.") ;
		}

		long ms = (new Date().getTime() - date.getTime());
		long seg = ms / 1000;
		long min = seg / 60;
		LOG.info("Time consumed: " + ms + "ms; " + seg + " seg; " + min
				+ " min ");

		return ts;
	}

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length < 1) {
			System.out.println("Usage: java Start <testeSuite>");
			System.exit(-1);
		}

		Start st = new Start();
		st.findHyphotesisSet(TEST_SUITE_DIR + args[0]);
	}
}
