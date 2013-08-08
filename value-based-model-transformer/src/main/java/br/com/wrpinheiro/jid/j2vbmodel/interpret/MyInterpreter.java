package br.com.wrpinheiro.jid.j2vbmodel.interpret;

import org.apache.log4j.Logger;

import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionBasicForStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionIfThenElseStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionIfThenStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionIifThenStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AExpressionWhileStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AIdentifierIfThenElseStatement;
import br.com.wrpinheiro.jid.javacompiler.node.AMethodDeclaration;
import br.com.wrpinheiro.jid.javacompiler.node.Node;
import br.com.wrpinheiro.jid.programdiagnosis.components.abstractions.IntegerAbstractComponent;

public class MyInterpreter extends BaseInterpreter {

  /**
   * The class logger.
   */
  public static Logger LOG = Logger.getLogger(MyInterpreter.class);

  @SuppressWarnings("all")
  public MyInterpreter(final IntegerAbstractComponent system,
      final InterpretationContext ctx) {
    super(system, ctx);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#defaultIn(br.com.wrpinheiro.jid.javacompiler.node.Node)
   */
  @Override
  public void defaultIn(final Node node) {
    in(node);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#defaultOut(br.com.wrpinheiro.jid.javacompiler.node.Node)
   */
  @Override
  public void defaultOut(final Node node) {
    out(node);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#caseAExpressionIfThenStatement(br.com.wrpinheiro.jid.javacompiler.node.AExpressionIfThenStatement)
   */
  @Override
  public void caseAExpressionIfThenStatement(
      final AExpressionIfThenStatement node) {
    in(node);
    out(node);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#caseAExpressionIifThenStatement(br.com.wrpinheiro.jid.javacompiler.node.AExpressionIifThenStatement)
   */
  @Override
  public void caseAExpressionIifThenStatement(
      final AExpressionIifThenStatement node) {
    in(node);
    out(node);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#caseAExpressionIfThenElseStatement(br.com.wrpinheiro.jid.javacompiler.node.AExpressionIfThenElseStatement)
   */
  @Override
  public void caseAExpressionIfThenElseStatement(
      final AExpressionIfThenElseStatement node) {
    in(node);
    out(node);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#caseAExpressionWhileStatement(br.com.wrpinheiro.jid.javacompiler.node.AExpressionWhileStatement)
   */
  @Override
  public void caseAExpressionWhileStatement(final AExpressionWhileStatement node) {
    in(node);
    out(node);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#caseAExpressionBasicForStatement(br.com.wrpinheiro.jid.javacompiler.node.AExpressionBasicForStatement)
   */
  @Override
  public void caseAExpressionBasicForStatement(AExpressionBasicForStatement node) {
    in(node);
    out(node);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#caseAMethodDeclaration(br.com.wrpinheiro.jid.javacompiler.node.AMethodDeclaration)
   */
  @Override
  public void caseAMethodDeclaration(final AMethodDeclaration node) {
    in(node);
    out(node);
  }

  /**
   * (non-Javadoc)
   * 
   * @see br.com.wrpinheiro.jid.javacompiler.analysis.DepthFirstAdapter#caseAIdentifierIfThenElseStatement(br.com.wrpinheiro.jid.javacompiler.node.AIdentifierIfThenElseStatement)
   */
  @Override
  public void caseAIdentifierIfThenElseStatement(
      AIdentifierIfThenElseStatement node) {
    in(node);
    out(node);
  }
}