package valour.network.core.rankManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import valour.network.core.Core;
import valour.network.core.MiniPlugin;
import valour.network.core.util.Chat;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class RankAssigner extends MiniPlugin
{
    public RankAssigner()
    {
        super("Ranks", Core.getInstance());
    }

    @EventHandler
    public void on(PlayerLoginEvent e)
    {
        if (RankChecker.getRank(e.getPlayer()) == null)
        {
            // Assign rank
            updateRank(e.getPlayer().getUniqueId(), Rank.DEFAULT, false);
        }
        else
        {
            e.getPlayer().setPlayerListName(RankChecker.getRank(e.getPlayer()).getTag(true, true) + " §e" + e.getPlayer().getName());
        }
    }

    protected void updateRank(UUID playerID, Rank rank, boolean inform)
    {
        try
        {
            Statement statement = Core.getInstance().getConnection().createStatement();
            statement.executeUpdate("INSERT INTO Ranks (UUID, rank) VALUES ('" + playerID + "', DEFAULT);");

            if (inform && Bukkit.getPlayer(playerID) != null)
            {
                Player p = Bukkit.getPlayer(playerID);
                p.sendMessage(Chat.main(getName(), "Your rank has been updated to §9" + rank.getTag(true, false)));
                p.sendMessage(Chat.main(getName(), "You may need to relog for changes to take effect!"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
