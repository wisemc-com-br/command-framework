package br.com.unidade.engine.command.paramter.impl;

import br.com.unidade.engine.command.paramter.Processor;
import br.com.unidade.engine.command.utils.CommandReflection;

public final class DoubleProcessor extends Processor<Double> {

    public Double process(Object sender, String supplied) {
        try {
            return Double.parseDouble(supplied);
        } catch(Exception ex) {
            CommandReflection.sendMessage(sender, "§cValor double '" + supplied + "' inválido.");
            return 0D;
        }
    }
}
