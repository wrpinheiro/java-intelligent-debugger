package br.com.wrpinheiro.jid.javadebugger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Test suite class providing information about the class and its methods to
 * execute and the test cases.
 * 
 * @author wrp
 */
public class TestSuite {
	/**
	 * Name of the test suite.
	 */
	private String name;

	/**
	 * The full qualified names of the classes to be tested.
	 */
	private String resourceName;

	/**
	 * The method that will be tested.
	 */
	private String method;

	/**
	 * The test cases for a class.
	 */
	private Map<String, TestCase> testCases = new LinkedHashMap<String, TestCase>();

	/**
	 * A list of inputs.
	 */
  private List<String> inputs;

	/**
	 * Get then name of this test suite.
	 * 
	 * @return name of the test suite.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the test suite.
	 * 
	 * @param name
	 *            the name of the test suite.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Get the name of the resource to be tested.
	 * 
	 * @return the resource name.
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * Set the names of the classes to be tested.
	 * 
	 * @param resourceName
	 *            the classes names.
	 */
	public void setResouceName(final String resourceName) {
		this.resourceName = resourceName;
	}
	
	/**
	 * Return the method to be tested.
	 * @return the method.
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Set the method to be tested.
	 * @param method the method to set.
	 */
	public void setMethod(final String method) {
		this.method = method;
	}

	/**
	 * Return the class test cases.
	 * 
	 * @return the testCases
	 */
	public Map<String, TestCase> getTestCases() {
		return testCases;
	}

	/**
	 * Add a new test case to this test suite.
	 * 
	 * @param testCaseName
	 *            the name of the test case.
	 * @param testCases
	 *            the testCases to set.
	 */
	public void addTestCase(final String testCaseName, final TestCase testCase) {
		this.testCases.put(testCaseName, testCase);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("[name: ").append(name).append("]");
		sb.append("[resourceName: ").append(resourceName).append("]");
		sb.append("[testMethod: ]").append(method).append("]");
		sb.append("[testCases: ").append(testCases).append("]");

		return sb.toString();
	}

	/**
	 * Reset the actual outputs of all test cases.
	 */
	public void resetTestCaseExecution() {
		for (TestCase tc : this.testCases.values()) {
			tc.clearActualOutputs();
		}
	}

  public void setInputs(List<String> inputs) {
    this.inputs = inputs;
  }
  
  public List<String> getInputs() {
    return this.inputs;
  }
}
