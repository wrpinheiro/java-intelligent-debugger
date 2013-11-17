package br.com.wrpinheiro.jid.j2vbmodel.j2vbmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAdder;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAssignment;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerMultiplier;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * @author wrp
 * 
 *         22/11/2008
 */
@Ignore
public class ExpressionTest extends ProgramModelTest {
	@Test
	public void testAProgramThatSumsTwoNumbers() {
		IntegerSystem system = getProgramModel("Expr1.java");

		assertEquals(1, system.getNumberOfComponents());

		IntegerAdder a0 = checkIntegerAdder(system, "C?0");

		assertEquals(system.getNumberOfConnections(), 3);

		IntegerConnection const5 = checkConnValues(system, "CONST5?0", 1);
		IntegerConnection const3 = checkConnValues(system, "CONST3?0", 1);
		IntegerConnection temp0 = checkConnValues(system, "TEMP?0", 1);

		assertTrue(const5.getConnectedPorts().contains(a0.getIn1()));
		assertTrue(const3.getConnectedPorts().contains(a0.getIn2()));
		assertTrue(temp0.getConnectedPorts().contains(a0.getResult()));
	}

	@Test
	public void testAProgramThatSumsTwoNumbersAndAssignToAnotherVariable() {
		IntegerSystem system = getProgramModel("Expr2.java");

		assertEquals(2, system.getNumberOfComponents());

		IntegerAssignment a0 = checkIntegerAssignment(system, "C?0");
		IntegerAdder a1 = checkIntegerAdder(system, "C?1");

		assertEquals(system.getNumberOfConnections(), 4);

		IntegerConnection const5 = checkConnValues(system, "CONST5?0", 1);
		IntegerConnection const3 = checkConnValues(system, "CONST3?0", 1);
		IntegerConnection z0 = checkConnValues(system, "z?0", 1);
		IntegerConnection temp0 = checkConnValues(system, "TEMP?0", 2);

		assertTrue(const5.getConnectedPorts().contains(a1.getIn1()));
		assertTrue(const3.getConnectedPorts().contains(a1.getIn2()));

		assertTrue(temp0.getConnectedPorts().contains(a1.getResult()));
		assertTrue(temp0.getConnectedPorts().contains(a0.getIn()));

		assertTrue(z0.getConnectedPorts().contains(a0.getOut()));
	}

	@Test
	public void testAProgramThatSumsTwoVariablesAndAssignToAnotherVariable() {
		IntegerSystem system = getProgramModel("Expr3.java");

		assertEquals(4, system.getNumberOfComponents());

		IntegerAssignment a0 = checkIntegerAssignment(system, "C?0");
		IntegerAssignment a1 = checkIntegerAssignment(system, "C?1");
		IntegerAssignment a2 = checkIntegerAssignment(system, "C?2");
		IntegerAdder a3 = checkIntegerAdder(system, "C?3");

		assertEquals(system.getNumberOfConnections(), 6);

		IntegerConnection const5 = checkConnValues(system, "CONST5?0", 1);
		IntegerConnection const3 = checkConnValues(system, "CONST3?0", 1);
		IntegerConnection x0 = checkConnValues(system, "x?0", 2);
		IntegerConnection y0 = checkConnValues(system, "y?0", 2);
		IntegerConnection z0 = checkConnValues(system, "z?0", 1);
		IntegerConnection temp0 = checkConnValues(system, "TEMP?0", 2);

		assertTrue(const5.getConnectedPorts().contains(a0.getIn()));

		assertTrue(const3.getConnectedPorts().contains(a1.getIn()));

		assertTrue(x0.getConnectedPorts().contains(a0.getOut()));
		assertTrue(x0.getConnectedPorts().contains(a3.getIn1()));

		assertTrue(y0.getConnectedPorts().contains(a1.getOut()));
		assertTrue(y0.getConnectedPorts().contains(a3.getIn2()));

		assertTrue(temp0.getConnectedPorts().contains(a3.getResult()));
		assertTrue(temp0.getConnectedPorts().contains(a2.getIn()));

		assertTrue(z0.getConnectedPorts().contains(a2.getOut()));
	}

	@Test
	public void testAProgramThatMultipliesTwoNumbers() {
		IntegerSystem system = getProgramModel("Expr4.java");

		assertEquals(1, system.getNumberOfComponents());

		IntegerMultiplier a0 = checkIntegerMultiplier(system, "C?0");

		assertEquals(system.getNumberOfConnections(), 3);

		IntegerConnection const5 = checkConnValues(system, "CONST5?0", 1);
		IntegerConnection const3 = checkConnValues(system, "CONST3?0", 1);
		IntegerConnection temp0 = checkConnValues(system, "TEMP?0", 1);

		assertTrue(const5.getConnectedPorts().contains(a0.getIn1()));
		assertTrue(const3.getConnectedPorts().contains(a0.getIn2()));
		assertTrue(temp0.getConnectedPorts().contains(a0.getResult()));
	}

	@Test
	public void testAProgramThatMultipliesTwoNumbersAndAssignToAnotherVariable() {
		IntegerSystem system = getProgramModel("Expr5.java");

		assertEquals(2, system.getNumberOfComponents());

		IntegerAssignment a0 = checkIntegerAssignment(system, "C?0");
		IntegerMultiplier a1 = checkIntegerMultiplier(system, "C?1");

		assertEquals(system.getNumberOfConnections(), 4);

		IntegerConnection const5 = checkConnValues(system, "CONST5?0", 1);
		IntegerConnection const3 = checkConnValues(system, "CONST3?0", 1);
		IntegerConnection z0 = checkConnValues(system, "z?0", 1);
		IntegerConnection temp0 = checkConnValues(system, "TEMP?0", 2);

		assertTrue(const5.getConnectedPorts().contains(a1.getIn1()));
		assertTrue(const3.getConnectedPorts().contains(a1.getIn2()));

		assertTrue(temp0.getConnectedPorts().contains(a1.getResult()));
		assertTrue(temp0.getConnectedPorts().contains(a0.getIn()));

		assertTrue(z0.getConnectedPorts().contains(a0.getOut()));
	}

	@Test
	public void testAProgramThatSumsMultipliesVariablesAndAssignToAnotherVariable() {
		IntegerSystem system = getProgramModel("Expr6.java");

		assertEquals(4, system.getNumberOfComponents());

		IntegerAssignment a0 = checkIntegerAssignment(system, "C?0");
		IntegerAssignment a1 = checkIntegerAssignment(system, "C?1");
		IntegerAssignment a2 = checkIntegerAssignment(system, "C?2");
		IntegerMultiplier a3 = checkIntegerMultiplier(system, "C?3");

		assertEquals(system.getNumberOfConnections(), 6);

		IntegerConnection const5 = checkConnValues(system, "CONST5?0", 1);
		IntegerConnection const3 = checkConnValues(system, "CONST3?0", 1);
		IntegerConnection x0 = checkConnValues(system, "x?0", 2);
		IntegerConnection y0 = checkConnValues(system, "y?0", 2);
		IntegerConnection z0 = checkConnValues(system, "z?0", 1);
		IntegerConnection temp0 = checkConnValues(system, "TEMP?0", 2);

		assertTrue(const5.getConnectedPorts().contains(a0.getIn()));

		assertTrue(const3.getConnectedPorts().contains(a1.getIn()));

		assertTrue(x0.getConnectedPorts().contains(a0.getOut()));
		assertTrue(x0.getConnectedPorts().contains(a3.getIn1()));

		assertTrue(y0.getConnectedPorts().contains(a1.getOut()));
		assertTrue(y0.getConnectedPorts().contains(a3.getIn2()));

		assertTrue(temp0.getConnectedPorts().contains(a3.getResult()));
		assertTrue(temp0.getConnectedPorts().contains(a2.getIn()));

		assertTrue(z0.getConnectedPorts().contains(a2.getOut()));
	}
}
