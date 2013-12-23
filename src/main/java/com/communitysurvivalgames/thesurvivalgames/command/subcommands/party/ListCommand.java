/**
 * Name: ListCommand.java Created: 8 December 2013
 *
 * @version 1.0.0
 */
package com.communitysurvivalgames.thesurvivalgames.command.subcommands.party;

import com.communitysurvivalgames.thesurvivalgames.command.SubCommand;
import com.communitysurvivalgames.thesurvivalgames.managers.PartyManager;
import com.communitysurvivalgames.thesurvivalgames.objects.Party;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ListCommand implements SubCommand {

    /**
     * Lists the current members of your party
     *
     * @param player The player executing the command
     */
    public void execute(String cmd, Player sender, String[] args) {
        if ((args.length == 1) && (args[0].equalsIgnoreCase("list")) && (sender.hasPermission("party.admin.list"))) {
            executeAdmin(sender, args[0]);
        }
        if ((cmd.equalsIgnoreCase("list"))) {

            UUID id = (UUID) PartyManager.getPartyManager().getPlayers().get(sender.getName());
            if (id != null) {
                Party party = (Party) PartyManager.getPartyManager().getParties().get(id);
                String list = org.bukkit.ChatColor.GOLD + party.getLeader() + " ";
                for (String member : party.getMembers()) {
                    if (member != null) {
                        Player player = Bukkit.getServer().getPlayer(member);
                        if (player == null) {
                            list = list + org.bukkit.ChatColor.DARK_GRAY + member + " ";
                        } else {
                            list = list + org.bukkit.ChatColor.WHITE + member + " ";
                        }
                    }
                }

                sender.sendMessage(list);
            } else {
                sender.sendMessage(org.bukkit.ChatColor.YELLOW + "You are not in a party");
            }
        }
    }

    /**
     * Lists the members of another player's party
     *
     * @param sender The player executing the command
     * @param args   The player's username of the party who you want to list the
     *               members of
     */
    public static void executeAdmin(Player sender, String args) {
        Player p = Bukkit.getServer().getPlayer(args);
        if (p != null) {
            UUID id = (UUID) PartyManager.getPartyManager().getPlayers().get(p.getName());
            if (id != null) {
                Party party = (Party) PartyManager.getPartyManager().getParties().get(id);
                String list = org.bukkit.ChatColor.GOLD + party.getLeader() + " ";
                for (String member : party.getMembers()) {
                    if (member != null) {
                        Player player = Bukkit.getServer().getPlayer(member);
                        if (player == null) {
                            list = list + org.bukkit.ChatColor.DARK_GRAY + member + " ";
                        } else {
                            list = list + org.bukkit.ChatColor.WHITE + member + " ";
                        }
                    }
                }

                sender.sendMessage(list);
            } else {
                sender.sendMessage(org.bukkit.ChatColor.YELLOW + args + " is not in a party");
            }
        } else {
            sender.sendMessage(org.bukkit.ChatColor.YELLOW + args + " is not online");
        }
    }
}