package dev.makashi.titles.titles;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.IOException;

public final class Title extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic

        System.out.println("|-------------------------------------------|");
        System.out.println("|  Starting Plugin | Title Is Starting      |");
        System.out.println("|-------------------------------------------|");


        getServer().getPluginManager().registerEvents(this, this);


    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        // Set user varible to user who joined username
        String user = event.getPlayer().getName();
        System.out.printf("%s has joined the server%n", user);
        event.getPlayer().sendTitle(ChatColor.RED + "Welcome!",  ChatColor.AQUA + "Enjoy ur stay!", 20, 100, 20);
        event.setJoinMessage(String.format("%n %s &e&lHas Join All of Us", user));


    }

    @EventHandler
    public void  onMessage(AsyncPlayerChatEvent event) throws IOException {
        String username = event.getPlayer().getName();
        String message = event.getMessage();

        System.out.println(username + ": " + message);
        // Check if username is equal to makashi
        if (username.equals("MakashiDev")) {
            // If username is equal to makashi
            // Set username to red
        username = ChatColor.GOLD + "Owner " + ChatColor.RED + "Makashi" + ChatColor.RESET;
        } else {
            // If username is not equal to makashi
            // Set username to white
            username = "&f" + username;
        }


        event.setFormat(String.format("%s: %s", username, message));






        // If user sends message with no command
        if (!message.startsWith("/")) {
            event.setFormat(String.format("%s: %s", username, message));
        }



    }

    @EventHandler
    public void onWoodBreak(BlockBreakEvent event){
        // Check if block is wood
        if (event.getBlock().getType().toString().contains("LOG")) {
            // If block is wood
            // Set block to air
            event.getBlock().setType(org.bukkit.Material.AIR);
            // Send message to user
            event.getPlayer().sendMessage(ChatColor.GREEN + "You have broken wood!");
            // Spawn explosion
            event.getBlock().getWorld().createExplosion(event.getBlock().getLocation(), 5);
            event.getPlayer().sendMessage(ChatColor.RED + "You have been damaged by the explosion hehehehe!");
        }

    }

    @EventHandler
    public void onBowShoot(EntityShootBowEvent event){
        // Check if entity is player
        if (event.getEntity() instanceof org.bukkit.entity.Player) {

            if (event.getBow().getItemMeta().getDisplayName().equals("Super Bow")){
                ItemStack bow = event.getBow();
                ItemMeta bowMeta = bow.getItemMeta();
                event.getEntity().sendMessage(ChatColor.GREEN + "Your bow has been Upgraded!");
                bowMeta.setDisplayName("&#ff0000S&#ff0e00u&#ff1c00p&#ff2a00e&#ff3800r &#ff4700B&#ff5500o&#ff6300w&#ff7100+&#ff7f00+");
                bow.setItemMeta(bowMeta);




            }else {
                event.getEntity().sendMessage(ChatColor.GREEN + "You have shot a bow!");
            }

        }

    }

    @EventHandler
    public void onAvil(PrepareAnvilEvent e){
        ItemStack item = e.getResult();
        ItemMeta meta = item.getItemMeta();
        if (meta.getDisplayName().equals("Super Bow")){
            meta.setDisplayName("§x§f§f§0§0§0§0S§x§f§f§0§e§0§0u§x§f§f§1§c§0§0p§x§f§f§2§a§0§0e§x§f§f§3§8§0§0r §x§f§f§4§7§0§0B§x§f§f§5§5§0§0o§x§f§f§6§3§0§0w§x§f§f§7§1§0§0+§x§f§f§7§f§0§0+");
            item.setItemMeta(meta);


        }
    }






}




