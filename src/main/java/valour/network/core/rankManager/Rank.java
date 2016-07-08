package valour.network.core.rankmanager;

import org.bukkit.ChatColor;

public enum Rank
{
    OWNER("Owner", ChatColor.DARK_RED, 1000),
    MANAGER("Manager", ChatColor.DARK_RED, 900),
    ADMIN("Admin", ChatColor.RED, 850),
    VET_DEV("Vet.Dev", ChatColor.GOLD, 850),
    DEV("Dev", ChatColor.GOLD, 850),
    VET_BUILDER("Vet.Builder", ChatColor.AQUA, 850),
    BUILDER("Builder", ChatColor.AQUA, 850),
    VET_MOD("Vet.Mod", ChatColor.DARK_GREEN, 800),
    MOD("Mod", ChatColor.DARK_GREEN, 750),
    MENTEE("Mentee", ChatColor.DARK_AQUA, 700),
    YOUTUBE("YouTube", ChatColor.DARK_RED, 500),
    TWITCH("Twitch", ChatColor.DARK_PURPLE, 500),
    YT("YT", ChatColor.RED, 450),
    GFX("GFX", ChatColor.DARK_BLUE, 400),
    VALIANT("Valiant", ChatColor.RED, 40),
    GALLANT("Gallant", ChatColor.AQUA, 30),
    BRAVE("Brave", ChatColor.GREEN, 20),
    DEFAULT("", ChatColor.GRAY, 10);

    private String name;
    private ChatColor _colour;
    private int _priority;

    Rank(String name, ChatColor colour, int priority)
    {
        this.name = name;
        _colour = colour;
        _priority = priority;
    }

    public String getTag(boolean uppercase, boolean bold)
    {
        if (name.equalsIgnoreCase(""))
            return "";

        String tag = name;

        if (uppercase)
            tag = name.toUpperCase();
        if (bold)
            tag = ChatColor.BOLD + tag;

        return _colour + tag;
    }

    public ChatColor getColour()
    {
        return _colour;
    }

    public int getPriority()
    {
        return _priority;
    }
}
