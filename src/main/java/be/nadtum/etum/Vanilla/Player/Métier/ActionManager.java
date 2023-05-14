package be.nadtum.etum.Vanilla.Player.Métier;


import be.nadtum.etum.Main;
import be.nadtum.etum.Utility.Modules.FichierGestion;
import be.nadtum.etum.Utility.Modules.HashMapGestion;
import be.nadtum.etum.Utility.Modules.PlayerGestion;
import be.nadtum.etum.Vanilla.City.Fonctionnalité.Claim;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class ActionManager implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        if (!Claim.canBuild(event.getPlayer(), event.getBlock().getX(), event.getBlock().getZ())) {
            return;
        }

        if (HashMapGestion.blockPlaced.containsKey(event.getBlock())) {
            HashMapGestion.blockPlaced.remove(event.getBlock());
            return;
        }

        if (event.getBlock().getState().getBlockData() instanceof Ageable ageable) {
            if (ageable.getAge() != 7) {
                return;
            }
        }

        selector(String.valueOf(event.getBlock().getType()), event.getPlayer());
    }

    @EventHandler
    public void BlockPlaced(BlockPlaceEvent event){

        YamlConfiguration cfg = FichierGestion.getCfgJobs();

        if (!cfg.contains("Jobs." + PlayerGestion.getPlayerJobName(event.getPlayer().getName()) + "." + event.getBlock().getType())) {
            return;
        }
        HashMapGestion.blockPlaced.put(event.getBlockPlaced(), event.getBlockPlaced());
    }

    @EventHandler
    public void onEntityKill(@NotNull EntityDeathEvent event) {
        if(event.getEntity().getKiller() == null) return;
        selector(String.valueOf(event.getEntity().getType()), Objects.requireNonNull(event.getEntity().getKiller()));
    }

    @EventHandler
    public void onFish(@NotNull PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            selector(Objects.requireNonNull(event.getCaught()).getName(), event.getPlayer());
        }
    }

    private final HashMap<UUID, BossBar> bossBarHashMap = new HashMap<>();

    public void selector(String entity, @NotNull Player player) {

        String playerName = player.getName();
        String playerJob = PlayerGestion.getPlayerJobName(playerName);

        player.sendMessage(Component.text(entity));

        YamlConfiguration cfg = FichierGestion.getCfgJobs();

        // Check if the player gains xp from this entity type
        if (!cfg.contains("Jobs." + playerJob + "." + entity + ".gain_xp")) {
            return;
        }

        int gain_xp = cfg.getInt("Jobs." + playerJob + "." + entity + ".gain_xp");


        int xp_for_next_level = PlayerGestion.getPlayerJobNiveau(playerName) * 500;

        // Check if the player should level up
        if (PlayerGestion.getPlayerJobXp(playerName) + gain_xp >= xp_for_next_level) {
            PlayerGestion.setPlayerJobXp(playerName, 0);
            PlayerGestion.addPlayerJobNiveau(playerName, 1);
            player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 1);
        }

        // Add the xp gain to the player's total;
        PlayerGestion.addPlayerJobXp(playerName, gain_xp);

        // Update the player's boss bar
        BossBar bossBar = bossBarHashMap.getOrDefault(player.getUniqueId(), Bukkit.createBossBar("", BarColor.BLUE, BarStyle.SOLID));
        bossBar.setTitle(PlayerGestion.getPlayerJobName(player.getName()) + " §e: §e[§6" + PlayerGestion.getPlayerJobXp(player.getName()) + " §b/ §6" + xp_for_next_level + "§e]");
        bossBar.setProgress((double) PlayerGestion.getPlayerJobXp(player.getName()) / xp_for_next_level);
        bossBar.addPlayer(player);
        bossBarHashMap.put(player.getUniqueId(), bossBar);

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), bossBar::removeAll, 100L);

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(("§7vous avez gagné §f" + gain_xp + " §7expérience")));


    }


}
