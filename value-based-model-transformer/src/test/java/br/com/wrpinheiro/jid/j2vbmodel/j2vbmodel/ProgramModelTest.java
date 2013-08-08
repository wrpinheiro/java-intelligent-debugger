package br.com.wrpinheiro.jid.j2vbmodel.j2vbmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import br.com.wrpinheiro.jid.j2vbmodel.JavaModelBuilder;
import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAdder;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAssignment;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerMultiplier;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * @author wrp
 * 
 * 22/11/2008
 */
public class ProgramModelTest {
	private String SRC_DIR = "resources/examples/";

	protected IntegerSystem getProgramModel(String sourceFile) {
		
		try {
			JavaModelBuilder analyzer = new JavaModelBuilder(SRC_DIR + sourceFile, "main");
			IntegerSystem system = analyzer.getSystem();
			return system;
		} catch (FileNotFoundException ex) {
			throw new RuntimeException(ex);
		}
	}

	protected IntegerConnection checkConnValues(IntegerSystem system,
			String connName, int numberOfConnectedPorts) {
		IntegerConnection conn = system.getConnection(connName);
		assertNotNull(conn);
		assertNotNull(conn.getConnectedPorts());
		assertEquals(numberOfConnectedPorts, conn.getConnectedPorts().size());
		return conn;
	}
	
	protected IntegerAssignment checkIntegerAssignment(IntegerSystem system,
			String name) {
		IntegerComponent c0 = system.getComponent(name);
		assertNotNull(c0);
		assertTrue(c0 instanceof IntegerAssignment);
		IntegerAssignment a0 = (IntegerAssignment) c0;
		return a0;
	}

	protected IntegerAdder checkIntegerAdder(IntegerSystem system, String name) {
		IntegerComponent c0 = system.getComponent(name);
		assertNotNull(c0);
		assertTrue(c0 instanceof IntegerAdder);
		IntegerAdder a0 = (IntegerAdder) c0;
		return a0;
	}

	protected IntegerMultiplier checkIntegerMultiplier(IntegerSystem system, String name) {
		IntegerComponent c0 = system.getComponent(name);
		assertNotNull(c0);
		assertTrue(c0 instanceof IntegerMultiplier);
		IntegerMultiplier m0 = (IntegerMultiplier) c0;
		return m0;
	}
}
