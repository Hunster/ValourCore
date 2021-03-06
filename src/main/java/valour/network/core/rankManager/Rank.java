package valour.network.core.rankmanager;

import org.bukkit.ChatColor;

public enum Rank
{
    OWNER("Owner", ChatColor.RED, 999),
    MANAGER("Manager", ChatColor.RED, 200),
    ADMIN("Admin", ChatColor.RED, 100),
    VET_DEV("Vet.Dev", ChatColor.GOLD, 100),
    DEV("Dev", ChatColor.GOLD, 99),
    VET_MOD("Vet.Mod", ChatColor.DARK_GREEN, 90),
    MOD("Mod", ChatColor.GREEN, 80),
    MENTEE("Mentee", ChatColor.DARK_AQUA, 70),
    VET_BUILDER("Vet.Builder", ChatColor.AQUA, 61),
    BUILDER("Builder", ChatColor.AQUA, 60),
    // Staff ^^

    YOUTUBE("YouTube", ChatColor.DARK_RED, 50),
    TWITCH("Twitch", ChatColor.DARK_PURPLE, 50),
    YT("YT", ChatColor.RED, 50),
    GFX("GFX", ChatColor.DARK_BLUE, 49),
    // Special ^^

    VALIANT("Valiant", ChatColor.RED, 40),
    GALLANT("Gallant", ChatColor.AQUA, 30),
    BRAVE("Brave", ChatColor.GREEN, 20),
    DEFAULT("", ChatColor.WHITE, 10);
    // Donators ^^

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
        if (name.equalsIgnoreCase("ALL"))
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
