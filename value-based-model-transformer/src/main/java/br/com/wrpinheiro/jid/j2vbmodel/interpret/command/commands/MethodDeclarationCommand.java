package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;

import java.util.Set;

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.CheckParametersInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.MyInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AMethodDeclaration;
import br.com.wrpinheiro.jid.javacompiler.node.AMethodDeclarator;
import br.com.wrpinheiro.jid.javacompiler.node.APrimitiveMethodHeader;
import br.com.wrpinheiro.jid.javacompiler.node.AReferenceMethodHeader;
import br.com.wrpinheiro.jid.javacompiler.node.AVoidMethodHeader;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.javacompiler.node.PMethodHeader;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerPort;
import br.com.wrpinheiro.jid.programdiagnosis.programcomponents.MethodSystem;

public class MethodDeclarationCommand implements ProductionInterpreterCommand {

  /**
   * Logger of the class.
   */
  private Logger LOG = Logger.getLogger(MethodDeclarationCommand.class);

  /**
   * Index of the method's name.
   */
  private static final int NAME = 0;

  /**
   * Index of the method's beginning line.
   */
  private static final int LINE = 1;

  /**
   * Index of the method's type.
   */
  private static final int TYPE = 2;

  /**
   * Index of the method's declarator.
   */
  private static final int METHOD_DECLARATOR = 3;

  /**
   * Get the name and the line that begin the method.
   * 
   * @param header
   * @return
   */
  private Object[] getNameAndLine(PMethodHeader header) {
    Object[] result = new Object[4];

    if (header instanceof AVoidMethodHeader) {
      AVoidMethodHeader h = (AVoidMethodHeader) header;
      result[NAME] = ((AMethodDeclarator) h.getMethodDeclarator())
          .getIdentifier().getText();
      result[LINE] = ((AMethodDeclarator) h.getMethodDeclarator())
          .getIdentifier().getLine();
      result[TYPE] = MethodSystem.TYPE_VOID;
      result[METHOD_DECLARATOR] = h.getMethodDeclarator();
    } else if (header instanceof AReferenceMethodHeader) {
      AReferenceMethodHeader h = (AReferenceMethodHeader) header;
      result[NAME] = ((AMethodDeclarator) h.getMethodDeclarator())
          .getIdentifier().getText();
      result[LINE] = ((AMethodDeclarator) h.getMethodDeclarator())
          .getIdentifier().getLine();
      result[TYPE] = MethodSystem.TYPE_OTHER;
      result[METHOD_DECLARATOR] = h.getMethodDeclarator();
    } else if (header instanceof APrimitiveMethodHeader) {
      APrimitiveMethodHeader h = (APrimitiveMethodHeader) header;
      result[NAME] = ((AMethodDeclarator) h.getMethodDeclarator())
          .getIdentifier().getText();
      result[LINE] = ((AMethodDeclarator) h.getMethodDeclarator())
          .getIdentifier().getLine();
      result[TYPE] = MethodSystem.TYPE_OTHER;
      result[METHOD_DECLARATOR] = h.getMethodDeclarator();
    }

    return result;
  }

  @Override
  public void inNode(final InterpretationContext context, final Node aNode,
      final BaseInterpreter analysisAdapter) {

    AMethodDeclaration node = (AMethodDeclaration) aNode;

    Object[] props = this.getNameAndLine(node.getMethodHeader());

    String name = context.getNextComponentId();
    int line = (Integer) props[LINE];

    MethodSystem method = new MethodSystem(name, context.getSystem(), line,
        (String) props[NAME], (Integer) props[TYPE], ParserUtils.getInfo(aNode));

    MyInterpreter methodInterpreter = new MyInterpreter(method, context);
    methodInterpreter.getContext().pushComponent(method);

    // node.getMethodHeader().apply(methodInterpreter);
    this.inAMethodDeclarator((AMethodDeclarator) props[METHOD_DECLARATOR],
        methodInterpreter.getContext());

    node.getMethodBody().apply(methodInterpreter);
  }

  private void inAMethodDeclarator(final AMethodDeclarator node,
      final InterpretationContext context) {
    MethodSystem c = (MethodSystem) context.getSystem();

    if (node.getFormalParameterList() != null) {
      CheckParametersInterpreter parametersInterpreter = new CheckParametersInterpreter();
      node.getFormalParameterList().apply(parametersInterpreter);

      Set<String> arguments = parametersInterpreter.getParams();
      LOG.debug("method arguments: " + arguments);

      int i = 0;
      for (String var : arguments) {
        String portName = "arg" + i;
        i++;
        IntegerPort p = c.addInputPort(portName);

        //IntegerConnection conn = context.createConnection(var, "(i)_"
            //+ context.getSystem().getName());
            
          IntegerConnection conn = context.createConnection(var, "(i)_"
              + context.getSystem().getName());
          conn.setAssignable(false);

        // IntegerConnection conn = context.createConnection(var,
        // index);
        conn.addPort(p);
      }
    }

    // if the method returns sth different from void, then there must be
    // created an output.
    if (!(node.parent() instanceof AVoidMethodHeader)) {
      c.addOutputPort("return");
    }

    context.pushComponent(c);
  }

  @Override
  public void outNode(final InterpretationContext context, final Node aNode,
      final BaseInterpreter analysisAdapter) {
    // does not need implementation.
  }
}
