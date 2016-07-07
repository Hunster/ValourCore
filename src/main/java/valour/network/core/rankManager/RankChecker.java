package valour.network.core.rankManager;

import org.bukkit.entity.Player;
import valour.network.core.Core;
import valour.network.core.util.Chat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class RankChecker
{
    private static HashMap<Rank, Integer> _priority = new HashMap<Rank, Integer>();

    private static Rank[] _values = {
        Rank.OWNER, Rank.VET_DEV, Rank.ADMIN,
        Rank.DEV, Rank.VET_MOD, Rank.MOD, Rank.MENTEE, Rank.BUILDER,
        Rank.YOUTUBE, Rank.TWITCH,
        Rank.VALIANT, Rank.GALLANT, Rank.BRAVE, Rank.DEFAULT
    };

    public static void setup()
    {
        try
        {
            Statement statement = Core.getInstance().getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Ranks(UUID varchar(36), rank varchar(7));");
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        _priority.put(Rank.OWNER, 999);
        _priority.put(Rank.ADMIN, 100);
        _priority.put(Rank.VET_DEV, 100);

        _priority.put(Rank.DEV, 99);
        _priority.put(Rank.VET_MOD, 90);
        _priority.put(Rank.MOD, 80);
        _priority.put(Rank.MENTEE, 70);
        _priority.put(Rank.BUILDER, 60);

        _priority.put(Rank.YOUTUBE, 50);
        _priority.put(Rank.TWITCH, 50);

        _priority.put(Rank.VALIANT, 40);
        _priority.put(Rank.GALLANT, 30);
        _priority.put(Rank.BRAVE, 20);
        _priority.put(Rank.DEFAULT, 10);
    }

    protected static Rank getRank(Player player)
    {
        try
        {
            Statement statement = Core.getInstance().getConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Ranks (UUID, rank) WHERE UUID = '" + player.getUniqueId() + "';");

            if (results.next())
            {
                String rank = results.getString("rank");

                for (Rank r : _values)
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
        Rank curRank = getRank(player);
        int curPriority = _priority.get(curRank);

        if (curPriority < _priority.get(rank))
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
