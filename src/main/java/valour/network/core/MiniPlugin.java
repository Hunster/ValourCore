package valour.network.core;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class MiniPlugin implements Listener
{
    private String _moduleName = "default";
    private JavaPlugin _plugin;

    public MiniPlugin(String moduleName, JavaPlugin plugin)
    {
        _moduleName = moduleName;
        _plugin = plugin;

        onEnable();
    }

    public String getName()
    {
        return _moduleName;
    }

    private void onEnable()
    {
        registerEvents(this);
    }

    private void registerEvents(Listener listener)
    {
        Bukkit.getPluginManager().registerEvents(listener, _plugin);
    }

    public JavaPlugin getPlugin()
    {
        return _plugin;
    }

    public void log(String message)
    {
        System.out.println(_moduleName + "> " + message);
    }

    public void runAsync(Runnable runnable)
    {
        _plugin.getServer().getScheduler().runTaskAsynchronously(_plugin, runnable);
    }

    public void runSync(Runnable runnable)
    {
        _plugin.getServer().getScheduler().runTask(_plugin, runnable);
    }

    public void runSyncLater(Runnable runnable, long delay)
    {
        _plugin.getServer().getScheduler().runTaskLater(_plugin, runnable, delay);
    }
}
