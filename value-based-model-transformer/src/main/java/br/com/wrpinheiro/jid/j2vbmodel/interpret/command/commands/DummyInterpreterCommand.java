package br.com.wrpinheiro.jid.j2vbmodel.interpret.command.commands;

import br.com.wrpinheiro.jid.j2vbmodel.interpret.BaseInterpreter;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.command.ProductionInterpreterCommand;
import br.com.wrpinheiro.jid.j2vbmodel.interpret.context.InterpretationContext;
import br.com.wrpinheiro.jid.javacompiler.node.Node;

public class DummyInterpreterCommand implements ProductionInterpreterCommand {

    @Override
    public void inNode(InterpretationContext context, Node aNode, BaseInterpreter analysisAdapter) {
        // this node does not need interpretation.
    }

    @Override
    public void outNode(InterpretationContext context, Node aNode, BaseInterpreter analysisAdapter) {
        // this node does not need interpretation.
    }
}