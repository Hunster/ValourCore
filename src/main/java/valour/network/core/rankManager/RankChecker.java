package valour.network.core.rankManager;

import org.bukkit.entity.Player;
import valour.network.core.Core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RankChecker
{
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

    public static boolean has(Rank rank, Rank[] any, boolean inform)
    {
        return true;
        // stuff
    }
}
