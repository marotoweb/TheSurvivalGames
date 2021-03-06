/**
 * Name: StopCommand.java
 * Created: 2 January 2014
 *
 * @verson 1.0
 */
package com.communitysurvivalgames.thesurvivalgames.command.subcommands;

import com.communitysurvivalgames.thesurvivalgames.command.SubCommand;
import com.communitysurvivalgames.thesurvivalgames.locale.I18N;
import com.communitysurvivalgames.thesurvivalgames.objects.SGArena;
import com.communitysurvivalgames.thesurvivalgames.managers.ArenaManager;

import org.bukkit.entity.Player;

public class StopCommand implements SubCommand {
    @Override
    public void execute(String cmd, Player p, String[] args) {
        if(cmd.equalsIgnoreCase("stop") && args.length == 1) {
            int i = 0;
            try {
                i = Integer.parseInt(args[0]);
            } catch(NumberFormatException x) {
                p.sendMessage(ArenaManager.getManager().error + I18N.getLocaleString("NOT_NUMBER"));
                return;
            }
            SGArena a = ArenaManager.getManager().getArena(i);
            if(a == null) {
                p.sendMessage(ArenaManager.getManager().error + I18N.getLocaleString("INVALID_ARENA") + a);
                return;
            }
            // a.end();
            p.sendMessage(ArenaManager.getManager().prefix + I18N.getLocaleString("ARENA_END"));
        } else {
            p.sendMessage(ArenaManager.getManager().error + I18N.getLocaleString("INVALID_ARGUMENTS"));
        }
    }
}
