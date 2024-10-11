package br.com.unidade.engine.command.paramter.impl;

import br.com.unidade.engine.command.paramter.Processor;
import br.com.unidade.engine.command.utils.CommandReflection;

public final class IntegerProcessor extends Processor<Integer> {

    public Integer process(Object sender, String supplied) {
        try {
            return Integer.parseInt(supplied);
        } catch(Exception ex) {
            CommandReflection.sendMessage(sender, "§cO valor do inteiro '" + supplied + "' é inválido.");
            return 0;
        }
    }
}
