package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.BlockComponent;

/**
 * Parser for: return <expr>;
 * 
 * @author wrp
 */
public class ExpressionReturnStatementCommand implements
    ProductionInterpreterCommand {

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#inNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
   *      br.com.wrpinheiro.jid.javacompiler.node.Node,
   *      br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
   */
  @Override
  public void inNode(final InterpretationContext context, final Node node,
      final BaseInterpreter analysisAdapter) {
    // does not need implementation.
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#outNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
   *      br.com.wrpinheiro.jid.javacompiler.node.Node,
   *      br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
   */
  @Override
  public void outNode(final InterpretationContext context, final Node node,
      final BaseInterpreter analysisAdapter) {

    IntegerConnection conn = context.popConnection();
    IntegerAbstractComponent absComp = (IntegerAbstractComponent) context
        .getSystem();
    IntegerPort port;
    boolean flag = true;

    // try to connect the result port from the main system.
    while (flag) {
      if (absComp == null)
        flag = false;
      else if (absComp instanceof BlockComponent) {
        if ((port = ((BlockComponent) absComp).getOutputPort("return")) != null) {
          conn.addPort(port);
          flag = false;
        }
      }

      if (absComp != null && flag)
        absComp = absComp.getParent();
    }

    // conn.addPort(block.getOutputPort("return"));
  }
}
