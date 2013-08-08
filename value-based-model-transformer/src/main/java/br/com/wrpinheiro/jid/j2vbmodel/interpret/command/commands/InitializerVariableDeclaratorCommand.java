package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AInitializerVariableDeclarator;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAssignment;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

/**
 * Parser for sentences like int <id> = <expression>
 * 
 * @author wrp
 * 
 */
public class InitializerVariableDeclaratorCommand implements
    ProductionInterpreterCommand {

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand#inNode(br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext,
   *      br.com.wrpinheiro.jid.javacompiler.node.Node,
   *      br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter)
   */
  @Override
  public void inNode(InterpretationContext context, Node aNode,
      BaseInterpreter analysisAdapter) {

    AInitializerVariableDeclarator node = (AInitializerVariableDeclarator) aNode;

    String name = context.getNextComponentId();
    IntegerAssignment c = new IntegerAssignment(context.getSystem(), name, node
        .getAssign().getLine(), ParserUtils.getInfo(aNode));

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
  public void outNode(InterpretationContext context, Node aNode,
      BaseInterpreter analysisAdapter) {

    AInitializerVariableDeclarator node = (AInitializerVariableDeclarator) aNode;

    IntegerAssignment c = (IntegerAssignment) context.popComponent();

    IntegerConnection in = context.popConnection();
    in.addPort(c.getIn());

    String id = node.getIdentifier().getText();
    String index = c.getName().substring(1);

    IntegerConnection out = context.createConnection(id, index);
    out.addPort(c.getOut());
  }
}
