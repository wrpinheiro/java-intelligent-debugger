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
 * 17/01/2009
 */
public class MedianaModelTest {
	//@Test
	@SuppressWarnings("unchecked")
	public void testMedianaDiagnosis1() throws ConflictException {
		MedianaModel s = new MedianaModel();

		IntegerProgramDiagnosisProblem dp = new IntegerProgramDiagnosisProblem(
				"Mediana Program", s);

		Map<String, String> obs = new LinkedHashMap<String, String>();
		obs.put("input0", "1");
		obs.put("input1", "1");
		obs.put("input2", "5");
		obs.put("output0", "1");

		FamilySet<HypothesisValue> fs = dp.findHyphotesisSet();

		ArrayFamilySet<LineHyphotesisValue> afs = new ArrayFamilySet<LineHyphotesisValue>(
				new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(9)), 
				new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(8)), 
				new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(5)), 
				new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(14)), 
				new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(7), new LineHyphotesisValue(12)));

		Assert.assertEquals(afs, fs);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testMedianaDiagnosis2() throws ConflictException {
		MedianaModel s = new MedianaModel();

		IntegerProgramDiagnosisProblem dp = new IntegerProgramDiagnosisProblem(
				"Mediana Program", s);

		Map<String, String> obs = new LinkedHashMap<String, String>();
		obs.put("input0", "2");
		obs.put("input1", "1");
		obs.put("input2", "3");
		obs.put("output0", "2");

		FamilySet<HypothesisValue> fs = dp.findHyphotesisSet();

		ArrayFamilySet<LineHyphotesisValue> afs = new ArrayFamilySet<LineHyphotesisValue>(
				new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(9)), 
				new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(8)), 
				new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(5)), 
				new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(14)), 
				new SetMaintainer<LineHyphotesisValue>(new LineHyphotesisValue(7), new LineHyphotesisValue(12)));

		Assert.assertEquals(afs, fs);
	}
}
