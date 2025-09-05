package de.codeblocksmc.codelib.api.worldman;

import de.codeblocksmc.codelib.api.commands.AdvancedTabCompleter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorldManCom extends AdvancedTabCompleter implements TabCompleter {

    private final WorldManager manager;

    public WorldManCom(WorldManager manager) {
        this.manager = manager;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length <= 1) {
            complete(completions, args[0], "teleport", "create", "remove", "list", "delete", "load");
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("teleport"))
                complete(completions, args[1], manager.getLoadedWorlds());
            else if (args[0].equalsIgnoreCase("create")) {
                complete(completions, args[1], "<Name>");
            } else completeEmpty(completions);
        }

        Collections.sort(completions);
        return completions;
    }
}
