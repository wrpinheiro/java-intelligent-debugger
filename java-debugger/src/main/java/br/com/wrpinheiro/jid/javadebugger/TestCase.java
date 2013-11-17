package br.com.wrpinheiro.jid.javadebugger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.wrpinheiro.jgraphlib.familyset.FamilySet;
import br.com.wrpinheiro.jid.programdiagnosis.HypothesisValue;

/**
 * A test case used to test a determined method.
 * 
 * @author wrp
 */
public class TestCase {
  private TestSuite testSuite;
  
	/**
	 * Name of the test case.
	 */
	private String name;

	/**
	 * A map of testCases inputs.
	 */
	private Map<String, String> inputs = new LinkedHashMap<String, String>();

	/**
	 * A map of testCases inputs.
	 */
	private Map<String, String> outputs = new LinkedHashMap<String, String>();
	
	/**
	 * A map of values generated in the execution of this test case.
	 */
	private Map<String, String> actualOutputs = new LinkedHashMap<String, String>();
	
	/**
	 * Flag to check if this test case was executed;
	 */
	private boolean actualOutputWasSet = false;

	/**
	 * A map of expected results for the test case. This information is used to
	 * check if the expected results of the diagnosis system is correct.
	 */
	private FamilySet<HypothesisValue> expectedResults;

	/**
	 * The diagnosis obtained by the execution of the program diagnosis with the
	 * test cases.
	 */
	private FamilySet<HypothesisValue> actualResults;

	/**
	 * Mask the starting time of the execution. This value is used to calculated
	 * the elapsed time with the method start() and stop().
	 */
	private long start;

	/**
	 * The elapsed time of this last test case execution.
	 */
	private long elapsedTime;

	public TestCase(TestSuite ts) {
	  this.testSuite = ts;
  }

  /**
	 * Return the testcase inputs.
	 * 
	 * @return the testCases.
	 */
	public Map<String, String> getInputs() {
		return inputs;
	}
	
	public Map<String, String> getNamedInputs() {
	  List<String> namedInputs = this.testSuite.getInputs();
	  if (namedInputs == null || namedInputs.size() == 0)
	    return this.getInputs();
	  else {
	    Map<String, String> mapOfNamedInputs = new LinkedHashMap<String, String>();
	    int idx = 0;
	    for (Entry<String, String> entry : this.getInputs().entrySet()) {
	      if (entry.getKey().startsWith("argIn") && idx < namedInputs.size()) {
	        mapOfNamedInputs.put(namedInputs.get(idx), entry.getValue());
	        idx++;;
	      } else mapOfNamedInputs.put(entry.getKey(), entry.getValue());
	    }
	    return mapOfNamedInputs;
	  }
	}

	/**
	 * Return the testcase output.
	 * 
	 * @return the testCases.
	 */
	public Map<String, String> getOutputs() {
		return outputs;
	}
	
	public List<String> getOutputsAsList() {
	  return new ArrayList<String>(this.outputs.values());
	}

	/**
	 * Return the testcase results of execution.
	 * @return
	 */
	public Map<String, String> getActualOutputs() {
		return actualOutputs;
	}
	
	 public List<String> getActualOutputsAsList() {
	    return new ArrayList<String>(this.actualOutputs.values());
	  }

	/**
	 * Set of input for this test case.
	 * 
	 * @param input
	 *            the inputs for this testcase.
	 */
	public void setInputs(final Map<String, String> inputs) {
		this.inputs = inputs;
	}

	/**
	 * Set of input for this test case.
	 * 
	 * @param input
	 *            the inputs for this testcase.
	 */
	public void setOutputs(final Map<String, String> outputs) {
		this.outputs = outputs;
	}
	
	public void setActualOutputs(final Map<String, String> actualOutputs) {
		this.actualOutputs = actualOutputs;
		this.actualOutputWasSet = true;
	}
	
	public void clearActualOutputs() {
		this.actualOutputs = new LinkedHashMap<String, String>();
		this.actualOutputWasSet = false;
	}
	
	/**
	 * Return the expected results for a test case.
	 * 
	 * @return the expected results.
	 */
	public FamilySet<HypothesisValue> getExpectedResults() {
		return this.expectedResults;
	}

	/**
	 * Set the expected results to this test case.
	 * 
	 * @param expectedResults
	 *            the expected results.
	 */
	public void setExpectedResults(final FamilySet<HypothesisValue> expectedResults) {
		this.expectedResults = expectedResults;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("[name:").append(name).append("]");
		sb.append("[inputs: ").append(inputs).append("]");
		sb.append("[exp. outputs: ").append(outputs).append("]");
		sb.append("[actual outputs]").append(actualOutputs).append("]");

		return sb.toString();
	}

	/**
	 * Set the actual results. These are the diagnoses obtained from the
	 * debugging system.
	 * 
	 * @name the name of the test case
	 * @param familySet
	 *            the actualResults to set
	 */
	public void setActualResults(FamilySet<HypothesisValue> familySet) {
		this.actualResults = familySet;
	}

	/**
	 * Return the actual results.
	 * 
	 * @return the actualResults
	 */
	public FamilySet<HypothesisValue> getActualResults() {
		return actualResults;
	}

	/**
	 * Get the test case name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the test case name.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Return the elapsed time of the last execution of this case.
	 * @return the elapsed time of the last execution of this case.
	 */
	public long getElapsedTime() {
		return this.elapsedTime;
	}

	/**
	 * Mark the starting time of this test case execution.
	 */
	public void start() {
		this.start = System.currentTimeMillis();
	}

	/**
	 * Mark the end of this test case execution and return the elapsed time.
	 * @return the elapsed time of this test case execution.
	 */
	public long stop() {
		this.elapsedTime = System.currentTimeMillis() - this.start;
		return elapsedTime;
	}

	/**
	 * Check if this test case has already run.
	 * @return TRUE if the test case has run of FALSE otherwise.
	 */
	public boolean isActualOutputSet() {
		return this.actualOutputWasSet;
	}

  public TestSuite getTestSuite() {
    return testSuite;
  }
}
