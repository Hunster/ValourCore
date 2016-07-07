package valour.network.core.util;

import org.bukkit.ChatColor;

public class Chat
{
    public static String main(String module, String message)
    {
        return ChatColor.BLUE + module + " §8> " + ChatColor.GRAY + message;
    }
}
