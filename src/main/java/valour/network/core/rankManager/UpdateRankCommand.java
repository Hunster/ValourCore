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
    private String _module = "Ranks";

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
                    p.sendMessage(Chat.main(_module, "That is not a valid rank"));
                    p.sendMessage(Chat.main(_module, "Possible values: §9" + allRanks()));
                    return;
                }

                if (RankChecker.getRank(p.getUniqueId()).getPriority() <= rank.getPriority())
                {
                    p.sendMessage(Chat.main(_module, "Insufficient permissions!"));
                }
                else
                {
                    if (RankChecker.getRank(id).getPriority() >= RankChecker.getRank(p.getUniqueId()).getPriority() && RankChecker.getRank(p.getUniqueId()) != Rank.OWNER)
                    {
                        p.sendMessage(Chat.main(_module, "Insufficient permissions!"));
                    }
                    else
                    {
                        new RankAssigner().updateRank(id, rank, true);
                        p.sendMessage(Chat.main(_module, "You updated §b" + player.getName() + "§7's rank to §b" + rank.getTag(true, false)));
                    }
                }
            }
            else
            {
                p.sendMessage(Chat.main(_module, "That player has never joined before"));
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
