package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.assignments;


import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ParserUtils;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AAssignAssignmentOperator;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.javacompiler.node.PAssignmentOperator;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerAssignment;
import br.com.wrpinheiro.jid.programdiagnosis.components.IntegerComponent;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;
import br.com.wrpinheiro.jid.programdiagnosis.connectors.IntegerConnection;

public class AssignCommand extends AbstractAssignCommand {

  @Override
  protected Class<? extends PAssignmentOperator> getCommandAssignmentOperatorClass() {
    return AAssignAssignmentOperator.class;
  }

  @Override
  protected Class<? extends IntegerComponent> getComponentClass() {
    return IntegerAssignment.class;
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.assignments.AssignmentCommand#getLineOfTheOperator(br.com.wrpinheiro.jid.javacompiler.node.PAssignmentOperator)
   */
  @Override
  public int getLineOfTheOperator(final PAssignmentOperator op) {
    checkType(op);
    return ((AAssignAssignmentOperator) op).getAssign().getLine();
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands.assignments.AssignmentCommand#getComponent(br.com.jdebugger.programdiagnosis.IntegerSystem,
   *      java.lang.String)
   */
  @Override
  public IntegerComponent getComponent(final IntegerAbstractComponent system,
      final String name, PAssignmentOperator op, Node node) {
    return new IntegerAssignment(system, name, this.getLineOfTheOperator(op),
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

    IntegerAssignment c = (IntegerAssignment) comp;

    rightSideExpr.addPort(c.getIn());

    String index = c.getName().substring(1);

    IntegerConnection out = ctx.createConnection(leftSideId, index);
    out.setIsBoolean(rightSideExpr.isBoolean());
    out.addPort(c.getOut());
  }
}
