package valour.network.core.util;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import valour.network.core.Core;
import valour.network.core.MiniPlugin;
import valour.network.core.rankmanager.Rank;
import valour.network.core.rankmanager.RankChecker;

public class ChatFormat extends MiniPlugin
{
    public ChatFormat()
    {
        super("Chat", Core.getInstance());
    }

    @EventHandler
    public void on(AsyncPlayerChatEvent e)
    {
        Rank rank = RankChecker.getRank(e.getPlayer().getUniqueId());
        String prefix = rank.getTag(false, true);

        if (rank != Rank.DEFAULT)
            prefix = rank.getColour() + "[" + prefix + rank.getColour() + "]";

        e.setFormat(prefix + ChatColor.YELLOW + " %s " + ChatColor.WHITE + "%s");
    }
}
