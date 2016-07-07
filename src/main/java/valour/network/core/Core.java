package valour.network.core;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import valour.network.core.rankManager.RankChecker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Core extends JavaPlugin
{
    private static Core core;

    private Connection _connection;
    private String _host, _database, _username, _password;
    private int _port;

    public static Core getInstance()
    {
        if (core == null)
        {
            core = new Core();
        }
        return core;
    }

    public Plugin getPlugin()
    {
        return this;
    }

    public void onEnable()
    {
        setup();

        _host = getConfig().getString("sql_host");
        _port = getConfig().getInt("sql_port");
        _database = getConfig().getString("sql_database");
        _username = getConfig().getString("sql_username");
        _password = getConfig().getString("sql_password");

        try
        {
            openConnection();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            disable();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            disable();
        }
    }

    public Connection getConnection()
    {
        return _connection;
    }

    private void disable()
    {
        getLogger().severe("UNABLE TO CONENCT TO MYSQL DATABASE");
        getLogger().severe("CORE DISABLED");
    }

    private void openConnection() throws SQLException, ClassNotFoundException
    {
        if (_connection != null && !_connection.isClosed())
        {
            return;
        }

        synchronized (this)
        {
            if (_connection != null && !_connection.isClosed())
            {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            _connection = DriverManager.getConnection("jdbc:mysql://" + this._host + ":" + this._port + "/" + this._database, this._username, this._password);
        }
    }

    private void setup()
    {
        RankChecker.setup();
    }
}
