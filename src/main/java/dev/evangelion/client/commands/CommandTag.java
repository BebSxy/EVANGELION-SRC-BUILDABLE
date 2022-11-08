package dev.evangelion.client.commands;

import java.util.Iterator;
import dev.evangelion.api.utilities.ChatUtils;
import dev.evangelion.client.modules.client.ModuleCommands;
import dev.evangelion.api.manager.module.Module;
import dev.evangelion.Evangelion;
import dev.evangelion.api.manager.command.RegisterCommand;
import dev.evangelion.api.manager.command.Command;

@RegisterCommand(name = "tag", description = "Let's you customize any module's tag.", syntax = "tag <module> <value>", aliases = { "customname", "modtag", "moduletag", "mark" })
public class CommandTag extends Command
{
    @Override
    public void onCommand(final String[] args) {
        if (args.length == 2) {
            boolean found = false;
            for (final Module module : Evangelion.MODULE_MANAGER.getModules()) {
                if (module.getName().equalsIgnoreCase(args[0])) {
                    found = true;
                    module.setTag(args[1].replace("_", " "));
                    ChatUtils.sendMessage(ModuleCommands.getSecondColor() + module.getName() + ModuleCommands.getFirstColor() + " is now marked as " + ModuleCommands.getSecondColor() + module.getTag() + ModuleCommands.getFirstColor() + ".", "Tag");
                }
            }
            if (!found) {
                ChatUtils.sendMessage("Could not find module.", "Tag");
            }
        }
        else {
            this.sendSyntax();
        }
    }
}
