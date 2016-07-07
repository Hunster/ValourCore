package valour.network.core.rankManager;

import org.bukkit.Bukkit;
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
            String aPlayer = args[0];
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);

            if (player.hasPlayedBefore())
            {
                UUID id = player.getUniqueId();
                Rank rank = Rank.valueOf(args[1]);

                if (rank == null)
                {
                    p.sendMessage(Chat.main(_module, "That is not a valid rank"));
                }
                else
                {
                    if (RankChecker.getRank(p).getPriority() < rank.getPriority())
                    {
                        p.sendMessage(Chat.main(_module, "You cannot set a player's rank to one above yours!"));
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
}
