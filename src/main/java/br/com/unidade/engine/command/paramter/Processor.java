package br.com.unidade.engine.command.paramter;

import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Processor<T> {

    private final Class<?> type;

    @SneakyThrows
    public Processor() {
        Type type = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.type = Class.forName(type.getTypeName());
        ParamProcessor.createProcessor(this);
    }

    /**
     * Process the object
     */
    public abstract T process(Object sender, String supplied);

    /**
     * Processes the tab completion
     */
    public List<String> tabComplete(Object sender, String supplied) {
        return new ArrayList<>();
    }

}
