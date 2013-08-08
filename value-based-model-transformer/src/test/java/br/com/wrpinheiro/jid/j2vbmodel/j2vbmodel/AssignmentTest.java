package br.com.wrpinheiro.jid.j2vbmodel.j2vbmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.wrpinheiro.jid.programdiagnosis.IntegerSystem;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAssignment;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * @author wrp
 * 
 * 22/11/2008
 * 
 */
public class AssignmentTest extends ProgramModelTest {
	@Test
	public void testP1Model() {
		IntegerSystem system = getProgramModel("Assign1.java");

		assertEquals(system.getNumberOfComponents(), 1);

		IntegerAssignment a0 = checkIntegerAssignment(system, "C0");

		assertEquals(2, system.getNumberOfConnections());

		IntegerConnection const5 = checkConnValues(system, "5", 1);
		IntegerConnection connX = checkConnValues(system, "x0", 1);

		assertTrue(const5.getConnectedPorts().contains(a0.getIn()));
		assertTrue(connX.getConnectedPorts().contains(a0.getOut()));
	}

	@Test
	public void testP2Model() {
		System.out.println();
		System.out.println();
		
		IntegerSystem system = getProgramModel("Assign2.java");

		assertEquals(2, system.getNumberOfComponents());

		IntegerAssignment a0 = super.checkIntegerAssignment(system, "C0");
		IntegerAssignment a1 = super.checkIntegerAssignment(system, "C1");

		assertEquals(4, system.getNumberOfConnections());

		IntegerConnection const5 = checkConnValues(system, "5", 1);
		IntegerConnection const8 = checkConnValues(system, "8", 1);
		IntegerConnection connX = checkConnValues(system, "x0", 1);
		IntegerConnection connY = checkConnValues(system, "y1", 1);

		assertTrue(const5.getConnectedPorts().contains(a0.getIn()));
		assertTrue(connX.getConnectedPorts().contains(a0.getOut()));
		assertTrue(const8.getConnectedPorts().contains(a1.getIn()));
		assertTrue(connY.getConnectedPorts().contains(a1.getOut()));
	}

	//@Test
	public void testReassignmentOfAVariable() {
		IntegerSystem system = getProgramModel("Assign3.java");

		assertEquals(2, system.getNumberOfComponents());

		IntegerAssignment a0 = super.checkIntegerAssignment(system, "C?0");
		IntegerAssignment a1 = super.checkIntegerAssignment(system, "C?1");

		assertEquals(4, system.getNumberOfConnections());

		IntegerConnection const5 = checkConnValues(system, "CONST5?0", 1);
		IntegerConnection const8 = checkConnValues(system, "CONST6?0", 1);
		IntegerConnection connX0 = checkConnValues(system, "x?0", 1);
		IntegerConnection connX1 = checkConnValues(system, "x?1", 1);

		assertTrue(const5.getConnectedPorts().contains(a0.getIn()));
		assertTrue(connX0.getConnectedPorts().contains(a0.getOut()));

		assertTrue(const8.getConnectedPorts().contains(a1.getIn()));
		assertTrue(connX1.getConnectedPorts().contains(a1.getOut()));
	}

	//@Test
	public void testReassignmentOfTheSameVariable() {
		IntegerSystem system = getProgramModel("Assign4.java");

		assertEquals(2, system.getNumberOfComponents());

		IntegerAssignment a0 = super.checkIntegerAssignment(system, "C?0");
		IntegerAssignment a1 = super.checkIntegerAssignment(system, "C?1");

		assertEquals(3, system.getNumberOfConnections());

		IntegerConnection const5 = checkConnValues(system, "CONST5?0", 1);
		IntegerConnection connX0 = checkConnValues(system, "x?0", 2);
		IntegerConnection connX1 = checkConnValues(system, "x?1", 1);

		assertTrue(const5.getConnectedPorts().contains(a0.getIn()));
		assertTrue(connX0.getConnectedPorts().contains(a0.getOut()));
		assertTrue(connX0.getConnectedPorts().contains(a1.getIn()));
		assertTrue(connX1.getConnectedPorts().contains(a1.getOut()));
	}
}
