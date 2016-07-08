package valour.network.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import valour.network.core.cmds.CommandManager;
import valour.network.core.rankmanager.ConsoleUpdateRankCommand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Core extends JavaPlugin
{
    private static Core instance;

    private Connection _connection;
    private String _host, _database, _username, _password;
    private int _port;

    public void onEnable()
    {
        instance = this;

        setup();

        saveDefaultConfig();
        reloadConfig();

        getCommand("consoleupdaterank").setExecutor(new ConsoleUpdateRankCommand());

        _host = getConfig().getString("sql_host");
        _port = getConfig().getInt("sql_port");
        _database = getConfig().getString("sql_database");
        _username = getConfig().getString("sql_username");
        _password = getConfig().getString("sql_password");

        try
        {
            openConnection();
            Statement statement = _connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Ranks(UUID varchar(36), rank varchar(11));");
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

    public static Core getInstance()
    {
        return instance;
    }

    public Connection getConnection()
    {
        return _connection;
    }

    private void disable()
    {
        getLogger().severe("UNABLE TO CONENCT TO MYSQL DATABASE");
        getLogger().severe("CORE DISABLED");
        Bukkit.getPluginManager().disablePlugin(this);
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
        new CommandManager().setup();
    }
}
