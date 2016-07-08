package valour.network.core.rankmanager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import valour.network.core.util.Chat;

import java.util.UUID;

public class ConsoleUpdateRankCommand implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (cmd.getName().equalsIgnoreCase("consoleupdaterank"))
        {
            if (sender instanceof Player)
            {
                sender.sendMessage(Chat.main("Commands", "Only the console may execute this command"));
            }
            else
            {
                UUID id = UUID.fromString(args[0]);
                Rank rank = Rank.valueOf(args[1].toUpperCase());

                new RankAssigner().updateRank(id, rank, true);
            }
        }
        return true;
    }
}
