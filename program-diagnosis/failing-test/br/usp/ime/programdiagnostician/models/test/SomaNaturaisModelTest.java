package br.usp.ime.programdiagnostician.models.test;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import br.com.jdebugger.graphlib.hittingset.ArrayFamilySet;
import br.com.jdebugger.graphlib.hittingset.FamilySet;
import br.com.jdebugger.graphlib.set.SetMaintainer;
import br.com.jdebugger.programdiagnosis.ConflictException;
import br.com.jdebugger.programdiagnosis.HypothesisValue;
import br.com.jdebugger.programdiagnosis.IntegerProgramDiagnosisProblem;
import br.com.jdebugger.programdiagnosis.LineHyphotesisValue;

/**
 * @author wrp
 *
 * 01/02/2009
 */
public class SomaNaturaisModelTest {
	@Test
	@SuppressWarnings("unchecked")
	public void testSomaNaturais() throws ConflictException {
		SomaNaturaisModel s = new SomaNaturaisModel();

		IntegerProgramDiagnosisProblem dp = new IntegerProgramDiagnosisProblem(
		        "Soma naturais", s);

		Map<String, String> obs = new LinkedHashMap<String, String>();
		obs.put("const0", "0");
		obs.put("const1", "1");
		obs.put("input0", "1");
		obs.put("output0", "2");

		dp.loadObservations(obs);
		FamilySet<HypothesisValue> fs = dp.findHyphotesisSet();

		ArrayFamilySet<LineHyphotesisValue> afs = new ArrayFamilySet<LineHyphotesisValue>(
		        new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(9)), new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(8)),
		        new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(5)), new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(14)),
		        new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(7), new LineHyphotesisValue(12)));

		Assert.assertEquals(afs, fs);
	}
}
