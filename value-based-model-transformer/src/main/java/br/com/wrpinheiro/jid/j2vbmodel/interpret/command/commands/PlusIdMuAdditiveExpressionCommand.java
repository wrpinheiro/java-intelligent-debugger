package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.APlusIdMuAdditiveExpression;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAdder;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * Parser for sentences like: <id> + <literal>
 * 
 * @author wrp
 */
public class PlusIdMuAdditiveExpressionCommand implements
    ProductionInterpreterCommand {

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.sablecc.grammars.java_1_5.interpret.command.ProductionInterpreterCommand
   * #
   * inNode(org.sablecc.grammars.java_1_5.interpret.context.InterpretationContext
   * , org.sablecc.grammars.java_1_5.node.Node,
   * org.sablecc.grammars.java_1_5.analysis.AnalysisAdapter)
   */
  @Override
  public void inNode(InterpretationContext context, Node aNode,
      BaseInterpreter analysisAdapter) {

    APlusIdMuAdditiveExpression node = (APlusIdMuAdditiveExpression) aNode;
    String name = context.getNextComponentId();
    IntegerAdder c = new IntegerAdder(context.getSystem(), name, node
        .getIdentifier().getLine(), ParserUtils.getInfo(node));

    context.pushComponent(c);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.sablecc.grammars.java_1_5.interpret.command.ProductionInterpreterCommand
   * #
   * outNode(org.sablecc.grammars.java_1_5.interpret.context.InterpretationContext
   * , org.sablecc.grammars.java_1_5.node.Node,
   * org.sablecc.grammars.java_1_5.analysis.AnalysisAdapter)
   */
  @Override
  public void outNode(InterpretationContext context, Node aNode,
      BaseInterpreter analysisAdapter) {

    APlusIdMuAdditiveExpression node = (APlusIdMuAdditiveExpression) aNode;

    IntegerAdder adderComponent = (IntegerAdder) context.popComponent();

    IntegerConnection conn1 = context.getCurrentConnectionFor(node
        .getIdentifier().getText());
    IntegerConnection conn2 = context.popConnection();

    conn1.addPort(adderComponent.getIn1());
    conn2.addPort(adderComponent.getIn2());

    String index = adderComponent.getName().substring(1);
    IntegerConnection outConn = context.createAuxiliaryConnection(index);

    outConn.addPort(adderComponent.getResult());
    context.pushConnection(outConn);
  }
}
