package valour.network.core.rankManager;

import org.bukkit.ChatColor;

public enum Rank
{
    OWNER("Owner", ChatColor.RED),
    ADMIN("Admin", ChatColor.RED),
    VET_DEV("Vet.Dev", ChatColor.GOLD),
    DEV("Dev", ChatColor.GOLD),
    VET_MOD("Vet.Mod", ChatColor.DARK_PURPLE),
    MOD("Mod", ChatColor.GREEN),
    MENTEE("Mentee", ChatColor.DARK_AQUA),
    BUILDER("Builder", ChatColor.BLUE),
    // Staff ^^

    YOUTUBE("Youtube", ChatColor.RED),
    TWITCH("Twitch", ChatColor.DARK_PURPLE),
    // Special ^^

    VALIANT("Valiant", ChatColor.YELLOW),
    GALLANT("Gallant", ChatColor.YELLOW),
    BRAVE("Brave", ChatColor.YELLOW),
    DEFAULT("", ChatColor.WHITE);
    // Donators ^^

    private ChatColor _colour;
    public String name;

    Rank(ChatColor colour, String name)
    {
        _colour = colour;
        this.name = name;
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
}
