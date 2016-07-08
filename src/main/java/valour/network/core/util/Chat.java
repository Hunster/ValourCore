package valour.network.core.util;

import org.bukkit.ChatColor;

public class Chat
{
    public static String main(String module, String message)
    {
        return ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + module + ChatColor.DARK_GRAY + "] " +  ChatColor.RED + message;
    }
}
