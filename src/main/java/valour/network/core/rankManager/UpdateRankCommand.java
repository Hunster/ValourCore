package valour.network.core.rankmanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import valour.network.core.cmds.CommandInfo;
import valour.network.core.cmds.CoreCommand;
import valour.network.core.util.Chat;

import java.util.UUID;

@CommandInfo(rank = Rank.ADMIN, aliases = {"updaterank", "setrank", "addrank"})
public class UpdateRankCommand extends CoreCommand
{
    private String _module = "Rank";

    @Override
    public void onCommand(Player p, String[] args)
    {
        if (args.length != 2)
        {
            p.sendMessage(Chat.main(_module, "Usage: /updaterank <player> <rank>"));
        }
        else
        {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);

            if (player.hasPlayedBefore())
            {
                UUID id = player.getUniqueId();
                Rank rank;

                try
                {
                    rank = Rank.valueOf(args[1].toUpperCase());
                }
                catch (Exception ex)
                {
                    p.sendMessage(Chat.main(_module, "The specified rank does not exist!"));
                    p.sendMessage(Chat.main(_module, "Registered Ranks: " + allRanks()));
                    return;
                }

                if (RankChecker.getRank(p.getUniqueId()).getPriority() <= rank.getPriority())
                {
                    p.sendMessage(Chat.main(_module, "You cannot set your rank to one higher than your current one!"));
                }
                else
                {
                    if (RankChecker.getRank(id).getPriority() >= RankChecker.getRank(p.getUniqueId()).getPriority() && RankChecker.getRank(p.getUniqueId()) != Rank.OWNER)
                    {
                        p.sendMessage(Chat.main(_module, "You cannot set another player's rank if they are higher in the hierarchy!"));
                    }
                    else
                    {
                        new RankAssigner().updateRank(id, rank, true);
                        p.sendMessage(Chat.main(_module, "You updated " + player.getName() + "'s rank to " + rank.getTag(true, false)));
                    }
                }
            }
            else
            {
                p.sendMessage(Chat.main(_module, "The specified player has never joined before!"));
            }
        }
    }

    private String allRanks()
    {
        StringBuilder sb = new StringBuilder();

        for (Rank rank : Rank.values())
        {
            sb.append(rank.toString() + " ");
        }
        return sb.toString().trim();
    }
}
