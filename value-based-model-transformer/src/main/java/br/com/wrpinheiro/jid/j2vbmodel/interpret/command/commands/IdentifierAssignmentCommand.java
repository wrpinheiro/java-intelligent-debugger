package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierAssignment;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAssignment;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * Faz o parser de uma atribui��o do tipo <id> = <id>.
 * 
 * @author wrp
 */
public class IdentifierAssignmentCommand implements
    ProductionInterpreterCommand {

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#inNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
   *      br.com.wrpinheiro.jid.javacompiler.node.Node,
   *      org.sablecc.grammars.java_1_5.analysis.AnalysisAdapter)
   */
  @Override
  public void inNode(final InterpretationContext context, final Node aNode,
      final BaseInterpreter analysisAdapter) {

    AIdentifierAssignment node = (AIdentifierAssignment) aNode;

    String name = context.getNextComponentId();

    int line = ParserUtils.getLineOfTheAssignment(node.getAssignmentOperator());

    IntegerAssignment c = new IntegerAssignment(context.getSystem(), name, line, ParserUtils.getInfo(aNode));
    // c.setAlwaysOk(true);
    context.pushComponent(c);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#outNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
   *      br.com.wrpinheiro.jid.javacompiler.node.Node,
   *      br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
   */
  @Override
  public void outNode(final InterpretationContext context, final Node aNode,
      final BaseInterpreter analysisAdapter) {

    AIdentifierAssignment node = (AIdentifierAssignment) aNode;

    IntegerAssignment c = (IntegerAssignment) context.popComponent();

    IntegerConnection in = context.getCurrentConnectionFor(node.getIdentifier()
        .getText());
    in.addPort(c.getIn());

    String id = context.popIdentifier();
    String index = c.getName().substring(1);

    IntegerConnection out = context.createConnection(id, index);
    out.addPort(c.getOut());
  }
}
