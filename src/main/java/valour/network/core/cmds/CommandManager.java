package valour.network.core.cmds;

import com.google.common.collect.Lists;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import valour.network.core.Core;
import valour.network.core.MiniPlugin;
import valour.network.core.rankmanager.Rank;
import valour.network.core.rankmanager.RankChecker;
import valour.network.core.rankmanager.UpdateRankCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager extends MiniPlugin
{
    public CommandManager()
    {
        super("Commands", Core.getInstance());
    }

    private List<CoreCommand> _commands = new ArrayList<CoreCommand>();

    public void setup()
    {
        _commands.add(new UpdateRankCommand());
    }

    @EventHandler
    public void on(PlayerCommandPreprocessEvent e)
    {
        for (CoreCommand cmd : _commands)
        {
            CommandInfo info = cmd.getClass().getAnnotation(CommandInfo.class);
            String command = e.getMessage().split(" ")[0].substring(1);

            for (String string : info.aliases())
            {
                if (command.equalsIgnoreCase(string))
                {
                    e.setCancelled(true);

                    Rank needed = info.rank();
                    if (RankChecker.has(e.getPlayer(), needed, true))
                    {
                        ArrayList<String> message = new ArrayList(Arrays.asList(e.getMessage().split(" ")));
                        message.remove(0);

                        String[] args = message.toArray(new String[message.size()]);
                        cmd.onCommand(e.getPlayer(), args);
                    }
                }
            }
        }
    }
}
