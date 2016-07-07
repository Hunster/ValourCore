package valour.network.core.rankManager;

import org.bukkit.ChatColor;

import java.util.HashMap;

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

    private HashMap<Rank, Integer> _priority = new HashMap<Rank, Integer>();

    Rank(ChatColor colour, String name)
    {
        _colour = colour;
        this.name = name;

        setup();
    }

    private void setup()
    {
        _priority.put(Rank.OWNER, 999);
        _priority.put(Rank.ADMIN, 100);
        _priority.put(Rank.VET_DEV, 100);

        _priority.put(Rank.DEV, 99);
        _priority.put(Rank.VET_MOD, 90);
        _priority.put(Rank.MOD, 80);
        _priority.put(Rank.MENTEE, 70);
        _priority.put(Rank.BUILDER, 60);

        _priority.put(Rank.YOUTUBE, 50);
        _priority.put(Rank.TWITCH, 50);

        _priority.put(Rank.VALIANT, 40);
        _priority.put(Rank.GALLANT, 30);
        _priority.put(Rank.BRAVE, 20);
        _priority.put(Rank.DEFAULT, 10);
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
        return _priority.get(this);
    }
}
