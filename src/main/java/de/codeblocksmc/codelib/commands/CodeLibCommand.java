package de.codeblocksmc.codelib.commands;

import de.codeblocksmc.codelib.api.chat.AdvancedTabCompleter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CodeLibCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label,
                             @NotNull String[] args) {
        if (args.length == 1) {

        } else if (args.length == 2) {

        } else if (args.length == 3) {

        }
        return false;
    }


    public static class Completer extends AdvancedTabCompleter implements TabCompleter {

        @Override
        public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd,
                                                    @NotNull String label, @NotNull String[] args) {
            List<String> completions = new ArrayList<>();

            return completions;
        }
    }
}
