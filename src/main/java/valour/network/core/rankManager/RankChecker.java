package valour.network.core.rankmanager;

import org.bukkit.entity.Player;
import valour.network.core.Core;
import valour.network.core.util.Chat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class RankChecker
{
    public static Rank getRank(UUID id)
    {
        try
        {
            Statement statement = Core.getInstance().getConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Ranks WHERE UUID = '" + id + "';");

            if (results.next())
            {
                String rank = results.getString("rank");

                for (Rank r : Rank.values())
                {
                    if (r.toString().equalsIgnoreCase(rank))
                    {
                        return r;
                    }

                    return null;
                }
            }
            else
            {
                return null;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public static boolean has(Player player, Rank rank, boolean inform)
    {
        Rank curRank = getRank(player.getUniqueId());
        int curPriority = curRank.getPriority();

        if (curPriority < rank.getPriority())
        {
            // No permission
            if (inform)
                player.sendMessage(Chat.main("Ranks", "This requires permission rank §b" + rank.getTag(true, false)));

            return false;
        }
        else
        {
            // Permission
            return true;
        }
    }
}
