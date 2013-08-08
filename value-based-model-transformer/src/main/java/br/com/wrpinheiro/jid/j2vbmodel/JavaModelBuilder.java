package br.com.wrpinheiro.jid.j2vbmodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jid.j2vbmodel.interpret.MethodFilteringInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.lexer.Lexer;
import br.com.wrpinheiro.jid.javacompiler.node.Start;
import br.com.wrpinheiro.jid.javacompiler.parser.Parser;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponentAdapter;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;

/**
 * A model builder for Java programs.
 * 
 * @author wrp
 */
public class JavaModelBuilder {
	/**
	 * The logger of the class.
	 */
	private Logger LOG = Logger.getLogger(JavaModelBuilder.class);

	/**
	 * The model constructed for the program.
	 */
	private IntegerSystem system;

	/**
	 * The context used during the parsing of the main system.
	 */
	private InterpretationContext mainContext;

	/**
	 * Create the instance of the model generator by reading the program in the
	 * reader.
	 * 
	 * @param name
	 *            the name of the main system.
	 * @param reader
	 *            the reader.
	 * @param methodName
	 *            the entry point method.
	 */
	private JavaModelBuilder(final String name, final Reader reader,
			final String methodName) {
		LOG.debug("Debugging method: " + methodName);
		try {
			/* Form our AST */
			Lexer lexer;
			lexer = new Lexer(new PushbackReader(reader, 1024));

			Parser parser = new Parser(lexer);
			Start ast = parser.parse();

			IntegerComponentAdapter.clearComponentList();
			IntegerConnection.clearConnectionList();
			IntegerPort.clearPortList();

			this.system = new IntegerSystem(name, null);
			MethodFilteringInterpreter interp = new MethodFilteringInterpreter(
					this.system, methodName);

			this.mainContext = interp.getContext();

			ast.apply(interp);
		} catch (FileNotFoundException ex) {
			LOG.error("Could not load file: " + name);
			LOG.error("Current path: " + new File(".").getAbsolutePath());
			throw new RuntimeException(ex);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Creates the instance of the model generator e construct the model.
	 * 
	 * @param filename
	 *            the Java source file used to construct the model.
	 * @param methodName
	 *            the entry point of the model constructor. Defaults to "main".
	 * @throws FileNotFoundException
	 */
	public JavaModelBuilder(String filename, String methodName)
			throws FileNotFoundException {
		this(filename, new FileReader(filename), methodName);
	}

	/**
	 * Creates the instance of the model generator with a given name, for an
	 * specific program. The entry point is given by methodName parameter.
	 * 
	 * @param name
	 *            the name for the main system.
	 * @param program
	 *            the program.
	 * @param methodName
	 *            the entry point.
	 */
	public JavaModelBuilder(final String name, final String program,
			final String methodName) {
		this(name, new StringReader(program), methodName);
	}

	/**
	 * Return the model constructed.
	 * 
	 * @return the model.
	 */
	public IntegerSystem getSystem() {
		return this.system;
	}

	/**
	 * Return the interpretation that was used to parse the main context.
	 * 
	 * @return the interpretation context.
	 */
	public InterpretationContext getMainContext() {
		return this.mainContext;
	}
}