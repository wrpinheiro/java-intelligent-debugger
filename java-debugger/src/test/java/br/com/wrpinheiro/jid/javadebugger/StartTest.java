package br.com.wrpinheiro.jid.javadebugger;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Ignore;
import org.junit.Test;

public class StartTest {

	private void test(final String pathToSuiteFile) {
		try {
			Start st = new Start();

			String fullPath = "testcases/" + pathToSuiteFile;

			TestSuite ts = st.findHyphotesisSet(fullPath);

			for (TestCase tc : ts.getTestCases().values())
				assertEquals("tc=" + tc.getName(), tc.getExpectedResults(), tc
						.getActualResults());
		} catch (FileNotFoundException fex) {
			throw new RuntimeException(fex);
		}
	}

	@Test
	public void testTesteImpl() {
		test("teste-impl.suite");
	}

	@Test
	@Ignore
	public void testNumeroSegmentos() {
		test("numero-segmentos.suite");
	}

	@Test
	public void testMaratona() {
		test("maratona.suite");
	}

	@Test
	public void testMax() {
		test("max.suite");
	}

	@Test
	public void testMediana() {
		test("mediana.suite");
	}

	@Test
	public void testMulta() {
		test("multa.suite");
	}

	@Test
	public void testSomaDigitos() {
		test("soma_digitos.suite");
	}

	@Test
	public void testTriangulo() {
		test("triangulo.suite");
	}

	@Test
	public void testVerificaPar() {
		test("verifica_par.suite");
	}

	@Test
	public void testSomaMultiplos() {
		test("soma_multiplos.suite");
	}

	@Test
	public void testSomaMultiplos4() {
		test("soma_multiplos4.suite");
	}

	@Test
	public void testCalculadora() {
		test("calculadora.suite");
	}

	@Test
	public void testCalculadora2() {
		test("calculadora2.suite");
	}

	@Test
	@Ignore
	public void testSomaNNumerosMain() {
		test("soma_n_numeros_main.suite");
	}

	@Test
	@Ignore
	public void testSomaNNumerosComForMain() {
		test("soma_n_numeros_com_for_main.suite");
	}

	@Test
	@Ignore
	public void testSomaNumeros() {
		test("soma_numeros.suite");
	}

	@Test
	public void testCalculaPremio() {
		test("calcula_premio.suite");
	}

	@Test
	public void testMaxDigitosEmSeq() {
		test("max_digitos_seq.suite");
	}
	
	@Test
	public void testMenorQue10() {
		test("menor_que_10.suite");
	}
  
  @Test
  public void testPalindrome() {
    test("palindrome.suite");
  }
  
  @Test
  public void testMaximiza() {
    test("maximiza.suite");
  }
  
  @Test
  public void testPerfeito() {
    test("perfeito.suite");
  }
}
