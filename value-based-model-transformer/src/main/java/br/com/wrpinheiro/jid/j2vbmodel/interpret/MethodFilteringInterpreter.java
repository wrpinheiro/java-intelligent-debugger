/*
 * Copyright 2006-2013 Wellington Ricardo Pinheiro.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.wrpinheiro.jid.j2vbmodel.interpret;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter;
import br.com.wrpinheiro.jid.javacompiler.interpreter.PrintTreeInterpreter;
import br.com.wrpinheiro.jid.javacompiler.node.AMethodDeclarator;
import br.com.wrpinheiro.jid.javacompiler.node.Start;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.MethodSystem;

/**
 * Class responsible to create the "entry point of the system" based on the
 * entry point of the program.
 * 
 * @author wrp
 */
public class MethodFilteringInterpreter extends DepthFirstAdapter {
	/**
	 * The current system.
	 */
	private IntegerSystem system;

	/**
	 * The name of the method used as entry point.
	 */
	private String methodName;

	/**
	 * A reference used to check if the entry program can be found in the
	 * program.
	 */
	private AMethodDeclarator methodDeclarator;
	
	/**
	 * The interpretation context used to interpret the program from the entry point.
	 */
	private InterpretationContext context;
	
	/**
	 * Create an instance of the method interpreter with the current system and
	 * look for a method named by methodName.
	 * 
	 * @param s
	 *            the current system.
	 * @param methodName
	 *            the method name.
	 */
	public MethodFilteringInterpreter(final IntegerSystem s,
			final String methodName) {
		this.system = s;
		this.methodName = methodName;
	}

	/**
	 * Return the interpretation context.
	 * @return the interpretation context.
	 */
	public InterpretationContext getContext() {
		return this.context;
	}

	/**
	 * Check if the entry point method can be found in the program.
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inAMethodDeclarator(br.com.wrpinheiro.jid.javacompiler.node.AMethodDeclarator)
	 */
	@Override
	public void inAMethodDeclarator(final AMethodDeclarator aNode) {
		if (aNode.getIdentifier().getText().equals(this.methodName))
			this.methodDeclarator = aNode;
	}

	/**
	 * Print the AST of the program. This is useful as debugging information.
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#inStart(br.com.wrpinheiro.jid.javacompiler.node.Start)
	 */
	@Override
	public void inStart(final Start aNode) {
		PrintTreeInterpreter p = new PrintTreeInterpreter();
		aNode.apply(p);
	}

	/**
	 * Create the entry point in the model and its current input/output
	 * connections.
	 * 
	 * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#outStart(br.com.wrpinheiro.jid.javacompiler.node.Start)
	 */
	@Override
	public void outStart(final Start aNode) {
		if (this.methodDeclarator == null)
			throw new RuntimeException("Method " + methodName + " not found!");

		// creates the parser with a new interpretation context.
		MyInterpreter interpreter = new MyInterpreter(this.system, null);
		aNode.apply(interpreter);

		MethodSystem method = (MethodSystem) interpreter.getSystem()
				.getProperty(this.methodName);

		// we must assume that the entry point method is not abstrat.
		method.refine();

		int inputs = method.getInputPorts().size();
		int outputs = method.getOutputPorts().size();

		for (int i = 0; i < inputs; i++) {
			IntegerConnection conn = interpreter.getContext().createConnection(
					"argIn", i + "");
			conn.addPort(method.getInputPorts().get("arg" + i));
		}

		if (outputs > 0) {
			IntegerConnection conn = interpreter.getContext().createConnection(
					"argOut", "");
			conn.addPort(method.getOutputPorts().get("return"));
		}

		this.context = interpreter.getContext();
	}
}
