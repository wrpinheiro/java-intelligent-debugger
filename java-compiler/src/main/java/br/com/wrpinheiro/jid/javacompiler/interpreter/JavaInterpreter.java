package br.com.wrpinheiro.jid.javacompiler.interpreter;

/* Create an AST, then invoke our interpreter. */

import java.io.FileReader;
import java.io.PushbackReader;


import br.com.wrpinheiro.jid.javacompiler.lexer.Lexer;
import br.com.wrpinheiro.jid.javacompiler.node.Start;
import br.com.wrpinheiro.jid.javacompiler.parser.Parser;

public class JavaInterpreter {
	public JavaInterpreter(String filename) {
		try {
			/* Form our AST */
			Lexer lexer = new Lexer(new PushbackReader(
					new FileReader(filename), 1024));
			Parser parser = new Parser(lexer);
			Start ast = parser.parse();

			PrintTreeInterpreter interp = new PrintTreeInterpreter();
			ast.apply(interp);

			// this.system = interp.getSystem();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static void main(String[] args) {
		if (args.length > 0) {
			new JavaInterpreter(args[0]);
		} else {
			System.err.println("usage: java JavaAnalizer inputFile");
			System.exit(1);
		}
	}
}