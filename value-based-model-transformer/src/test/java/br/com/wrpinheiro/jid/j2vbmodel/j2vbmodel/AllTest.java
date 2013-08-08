package br.com.wrpinheiro.jid.j2vbmodel.j2vbmodel;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author wrp
 *
 * 22/11/2008
 */
@RunWith(Suite.class)
@SuiteClasses(value={
		AssignmentTest.class,
		ExpressionTest.class
})
public class AllTest {
	// does not need implementation.
}
