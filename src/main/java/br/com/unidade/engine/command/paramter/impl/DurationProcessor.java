package br.com.unidade.engine.command.paramter.impl;

import br.com.unidade.engine.command.duration.Duration;
import br.com.unidade.engine.command.paramter.Processor;
import br.com.unidade.engine.command.utils.CommandReflection;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class DurationProcessor extends Processor<Duration> {

    private final List<String> defaults = Arrays.asList(
            "perm", "5m", "30m", "1h", "2h",
            "1d", "2d", "3d", "4d", "5d", "30d"
    );

    public Duration process(Object sender, String supplied) {
        long duration = parseDuration(supplied);

        if(duration == 0) {
            CommandReflection.sendMessage(sender, "§cValor inválido da duração.");
            return null;
        }

        return new Duration(supplied.toLowerCase(), duration);
    }

    /**
     * Get duration from string
     *
     * @param toParse String to parse
     * @return Duration
     */
    public static long parseDuration(String toParse) {
        try {
            toParse = toParse.toUpperCase();
            if(toParse.equals("FOREVER") || toParse.equals("EVER") || toParse.equals("NEVER") || toParse.equals("PERM") || toParse.equals("PERMANENT"))
                return -1;

            long value = Long.parseLong(toParse.substring(0, toParse.length() - 1));

            if(toParse.endsWith("S")) value = value * 1000;
            else if(toParse.endsWith("M")) value = value * 1000 * 60;
            else if(toParse.endsWith("H")) value = value * 1000 * 60 * 60;
            else if(toParse.endsWith("D")) value = value * 1000 * 60 * 60 * 24;
            else return 0;

            return value;
        } catch(Exception ignored) { return 0; }
    }

    public List<String> tabComplete(CommandSender sender, String supplied) {
        return defaults.stream()
                .filter(name -> name.toLowerCase().startsWith(supplied.toLowerCase()))
                .collect(Collectors.toList());
    }
}
