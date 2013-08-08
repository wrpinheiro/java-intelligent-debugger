package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.assignments;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AMinusAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.javacompiler.node.PAssignmentOperator;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerSubtractor;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class MinusAssignCommand extends AbstractAssignCommand {

  @Override
  protected Class<? extends PAssignmentOperator> getCommandAssignmentOperatorClass() {
    return AMinusAssignAssignmentOperator.class;
  }

  @Override
  protected Class<? extends IntegerComponent> getComponentClass() {
    return IntegerSubtractor.class;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.assignments.AssignmentCommand#getLineOfTheOperator(br.com.wrpinheiro.jid.javacompiler.node.PAssignmentOperator)
   */
  @Override
  public int getLineOfTheOperator(final PAssignmentOperator op) {
    checkType(op);
    return ((AMinusAssignAssignmentOperator) op).getMinusAssign().getLine();
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.assignments.AssignmentCommand#getComponent(br.com.jdebugger.programdiagnosis.IntegerSystem,
   *      java.lang.String,
   *      br.com.wrpinheiro.jid.javacompiler.node.PAssignmentOperator)
   */
  @Override
  public IntegerComponent getComponent(IntegerAbstractComponent system,
      String name, PAssignmentOperator op, Node node) {
    checkType(op);
    return new IntegerSubtractor(system, name, this.getLineOfTheOperator(op),
        ParserUtils.getInfo(node));
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.assignments.AssignmentCommand#connect(br.com.jdebugger.programdiagnosis.components.IntegerComponent,
   *      java.lang.String,
   *      br.com.jdebugger.programdiagnosis.connectors.IntegerConnection)
   */
  @Override
  public void connect(IntegerComponent comp, String leftSideId,
      IntegerConnection rightSideExpr, InterpretationContext ctx) {
    checkType(comp);

    IntegerSubtractor c = (IntegerSubtractor) comp;

    IntegerConnection leftSideConn = ctx.getCurrentConnectionFor(leftSideId);
    leftSideConn.addPort(c.getIn1());
    rightSideExpr.addPort(c.getIn2());

    String index = c.getName().substring(1);

    IntegerConnection out = ctx.createConnection(leftSideId, index);
    out.addPort(c.getResult());
  }
}
