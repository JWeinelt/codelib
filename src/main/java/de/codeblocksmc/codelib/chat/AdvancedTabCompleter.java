package de.codeblocksmc.codelib.chat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdvancedTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return List.of();
    }


    public void complete(List<String> list, String arg, String... completions) {
        list.clear();
        StringUtil.copyPartialMatches(arg, Arrays.asList(completions), list);
    }
    public void complete(List<String> list, String arg, List<String> completions) {
        list.clear();
        StringUtil.copyPartialMatches(arg, completions, list);
    }

    public void complete(List<String> list, String arg, CommandSender sender, Completer... completions) {
        list.clear();
        List<String> c = new ArrayList<>();
        for (Completer completion : completions) {
            if (!completion.isNeedsPermission() || sender.hasPermission(completion.getPermission())) c.addAll(Arrays.asList(completion.getCommands()));
        }
        StringUtil.copyPartialMatches(arg, c, list);
    }

    public void completeEmpty(List<String> list) {
        list.clear();
    }
}