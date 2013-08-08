package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.CheckParametersInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AExpression;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionAssignment;
import br.com.wrpinheiro.jid.javacompiler.node.ASimpleMethodInvocation;
import br.com.wrpinheiro.jid.javacompiler.node.AStatementBlockStatement;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.javacompiler.node.Start;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAssignment;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.MethodInvocation;

public class SimpleMethodInvocationCommand implements
    ProductionInterpreterCommand {

  /**
   * Possible kinds of invocation.
   * 
   * @author wrp
   */
  private enum InvocationTypeEnum {
    /**
     * invocation of a void method.
     */
    VOID,

    /**
     * Invocation of a non-void method.
     */
    NON_VOID;
  }

  /**
   * Logger of the class.
   */
  private static final Logger LOG = Logger
      .getLogger(SimpleMethodInvocationCommand.class);

  /**
   * Check if the invocation represented by the node must have a return value.
   * This check is done by verifying if the method invocation happens in an
   * expression or in a sentence.
   * 
   * @param node
   *          the node
   * @return InvocationTypeEnum.
   */
  private InvocationTypeEnum isVoidMethodInvocation(
      final ASimpleMethodInvocation node) {
    Node n = node;

    while (!n.getClass().equals(Start.class)) {
      if (n.getClass().equals(AStatementBlockStatement.class))
        return InvocationTypeEnum.VOID;
      if (n.getClass().equals(AExpression.class)
          || n.getClass().equals(AExpressionAssignment.class))
        return InvocationTypeEnum.NON_VOID;

      n = n.parent();
    }

    LOG.debug(node);
    throw new RuntimeException(
        "Could not verify if method being invocated returns void.");
  }

  @Override
  public void inNode(final InterpretationContext context, final Node aNode,
      final BaseInterpreter analysisAdapter) {
    ASimpleMethodInvocation node = (ASimpleMethodInvocation) aNode;

    if (node.getIdentifier().getText().equals("readInt")) {
      IntegerConnection input = context.createInputConnection();
      context.pushConnection(input);
    } else if (node.getIdentifier().getText().equals("writeInt")) {
      String name = context.getNextComponentId();

      IntegerAssignment c = new IntegerAssignment(context.getSystem(), name,
          node.getIdentifier().getLine(), ParserUtils.getInfo(node));
      
      // TODO para que o writeInt nao seja considerado, eh necessario verificar
      //   se ele eh usado simplesmente para mostrar um valor ou se eh usado para
      //   calcular o valor de uma expressao. No caso da expressao, ele nao deve
      //   ser considerado como sempre OK. 
      c.setAlwaysOk(true);

      context.pushComponent(c);
    } else {
      String name = context.getNextComponentId();
      MethodInvocation c = new MethodInvocation(context.getSystem(), name, node
          .getIdentifier().getText(), node.getIdentifier().getLine(),
          ParserUtils.getInfo(node));

      CheckParametersInterpreter interpreter = new CheckParametersInterpreter();
      node.getArgumentList().apply(interpreter);

      LOG.debug("Creating ports for invocation of " + node.getIdentifier());

      for (String param : interpreter.getParams()) {
        c.addInputPort(param);
      }

      if (this.isVoidMethodInvocation(node) == InvocationTypeEnum.NON_VOID) {
        IntegerPort out = c.addOutputPort("return");

        String index = c.getName().substring(1);
        IntegerConnection output = context.createAuxiliaryConnection(index);
        output.addPort(out);

        context.pushConnection(output);
      }

      context.pushComponent(c);
    }
  }

  @Override
  public void outNode(final InterpretationContext context, final Node aNode,
      final BaseInterpreter analysisAdapter) {
    ASimpleMethodInvocation node = (ASimpleMethodInvocation) aNode;

    if (node.getIdentifier().getText().equals("writeInt")) {
      IntegerAssignment c = (IntegerAssignment) context.popComponent();

      IntegerConnection in = context.popConnection();
      in.addPort(c.getIn());

      IntegerConnection out = context.createOutputConnection();
      out.addPort(c.getOut());
    } else if (!node.getIdentifier().getText().equals("readInt")) {

      MethodInvocation c = (MethodInvocation) context.popComponent();

      List<String> methodPorts = new ArrayList<String>(c.getInputPorts().size());
      for (String key : c.getInputPorts().keySet())
        methodPorts.add(key);

      Collections.reverse(methodPorts);

      for (String key : methodPorts) {
        IntegerConnection conn = context.popConnection();
        IntegerPort inputPort = c.getInputPort(key);

        conn.addPort(inputPort);
      }

      IntegerPort port = c.getOutputPort("return");

      if (port != null) {
        String index = c.getName().substring(1);
        IntegerConnection conn = context.createAuxiliaryConnection(index);
        conn.addPort(port);
        context.pushConnection(conn);
      }
    }
  }
}
