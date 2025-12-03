package de.codeblocksmc.codelib.chat;

import lombok.Getter;

public class Completer {
    @Getter
    private final String[] commands;

    @Getter
    private boolean needsPermission = false;
    @Getter
    private String permission;

    public Completer(String... commands) {
        this.commands = commands;
    }

    public Completer withPermission(String permission) {
        this.permission = permission;
        needsPermission = true;
        return this;
    }
}