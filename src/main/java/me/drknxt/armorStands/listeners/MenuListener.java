package me.drknxt.armorStands.listeners;

import me.drknxt.armorStands.ArmorStands;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class MenuListener implements Listener {

    // Menu list
    final String AS_MENU = ChatColor.DARK_BLUE + "Armor Stand Menu";
        final String AS_CREATE = ChatColor.GREEN + "Create";
        final String AS_RESUME = ChatColor.YELLOW + "Resume";
        final String AS_CLOSE = ChatColor.RED + "Close";

    final String CREATE_MENU = ChatColor.DARK_BLUE + "Edit Armor Stand";
        final String CREATE_COMPLETE = ChatColor.GREEN + "Complete";
        final String CREATE_POSITION = ChatColor.YELLOW + "Position And Rotation";
        final String CREATE_SETTINGS = ChatColor.YELLOW + "Settings";
        final String CREATE_MAIN_HAND = ChatColor.YELLOW + "Main Hand";
        final String CREATE_OFF_HAND = ChatColor.YELLOW + "Off Hand";
        final String CREATE_ARMOR = ChatColor.YELLOW + "Armor";
        final String CREATE_DELETE = ChatColor.RED + "Delete";

        final String SETTINGS_GRAVITY = ChatColor.YELLOW + "Gravity";
        final String SETTINGS_INVULNERABLE = ChatColor.YELLOW + "Invulnerable";
        final String SETTINGS_BASE_PLATE = ChatColor.YELLOW + "Base Plate";
        final String SETTINGS_LOCKED = ChatColor.YELLOW + "Locked";
        final String SETTINGS_ARMS = ChatColor.YELLOW + "Arms";
        final String SETTINGS_MARKER = ChatColor.YELLOW + "Marker";
        final String SETTINGS_GLOWING = ChatColor.YELLOW + "Glowing";
        final String SETTINGS_COLLIDABLE = ChatColor.YELLOW + "Collidable";
        final String SETTINGS_INVISIBLE = ChatColor.YELLOW + "Invisible";
        final String SETTINGS_SMALL = ChatColor.YELLOW + "Small";

        final String POSITION_POSITION = ChatColor.YELLOW + "Position";
        final String POSITION_HEAD = ChatColor.YELLOW + "Head";
        final String POSITION_ROTATION = ChatColor.YELLOW + "Rotation";
        final String POSITION_LEFT_ARM = ChatColor.YELLOW + "Left Arm";
        final String POSITION_BODY = ChatColor.YELLOW + "Body";
        final String POSITION_RIGHT_ARM = ChatColor.YELLOW + "Right Arm";
        final String POSITION_LEFT_LEG = ChatColor.YELLOW + "Left Leg";
        final String POSITION_RIGHT_LEG = ChatColor.YELLOW + "Right Leg";
        final String POSITION_X_PLUS = ChatColor.GREEN + "X+";
        final String POSITION_X_MINUS = ChatColor.RED + "X-";
        final String POSITION_Y_PLUS = ChatColor.GREEN + "Y+";
        final String POSITION_Y_MINUS = ChatColor.RED + "Y-";
        final String POSITION_Z_PLUS = ChatColor.GREEN + "Z+";
        final String POSITION_Z_MINUS = ChatColor.RED + "Z-";
        final String POSITION_RESET = ChatColor.YELLOW + "Reset";
        final String POSITION_TELEPORT = ChatColor.YELLOW + "Teleport";

        final String ARMOR_NO_HELMET = ChatColor.YELLOW + "No Helmet";
        final String ARMOR_LEATHER_HELMET = ChatColor.YELLOW + "Leather Helmet";
        final String ARMOR_CHAIN_HELMET = ChatColor.YELLOW + "Chainmail Helmet";
        final String ARMOR_GOLD_HELMET = ChatColor.YELLOW + "Golden Helmet";
        final String ARMOR_IRON_HELMET = ChatColor.YELLOW + "Iron Helmet";
        final String ARMOR_DIAMOND_HELMET = ChatColor.YELLOW + "Diamond Helmet";
        final String ARMOR_NETHERITE_HELMET = ChatColor.YELLOW + "Netherite Helmet";

        final String ARMOR_NO_CHESTPLATE = ChatColor.YELLOW + "No Chestplate";
        final String ARMOR_LEATHER_CHESTPLATE = ChatColor.YELLOW + "Leather Chestplate";
        final String ARMOR_CHAIN_CHESTPLATE = ChatColor.YELLOW + "Chainmail Chestplate";
        final String ARMOR_GOLD_CHESTPLATE = ChatColor.YELLOW + "Golden Chestplate";
        final String ARMOR_IRON_CHESTPLATE = ChatColor.YELLOW + "Iron Chestplate";
        final String ARMOR_DIAMOND_CHESTPLATE = ChatColor.YELLOW + "Diamond Chestplate";
        final String ARMOR_NETHERITE_CHESTPLATE = ChatColor.YELLOW + "Netherite Chestplate";

        final String ARMOR_NO_LEGGINGS = ChatColor.YELLOW + "No Leggings";
        final String ARMOR_LEATHER_LEGGINGS = ChatColor.YELLOW + "Leather Leggings";
        final String ARMOR_CHAIN_LEGGINGS = ChatColor.YELLOW + "Chainmail Leggings";
        final String ARMOR_GOLD_LEGGINGS = ChatColor.YELLOW + "Golden Leggings";
        final String ARMOR_IRON_LEGGINGS = ChatColor.YELLOW + "Iron Leggings";
        final String ARMOR_DIAMOND_LEGGINGS = ChatColor.YELLOW + "Diamond Leggings";
        final String ARMOR_NETHERITE_LEGGINGS = ChatColor.YELLOW + "Netherite Leggings";

        final String ARMOR_NO_BOOTS = ChatColor.YELLOW + "No Boots";
        final String ARMOR_LEATHER_BOOTS = ChatColor.YELLOW + "Leather Boots";
        final String ARMOR_CHAIN_BOOTS = ChatColor.YELLOW + "Chainmail Boots";
        final String ARMOR_GOLD_BOOTS = ChatColor.YELLOW + "Golden Boots";
        final String ARMOR_IRON_BOOTS = ChatColor.YELLOW + "Iron Boots";
        final String ARMOR_DIAMOND_BOOTS = ChatColor.YELLOW + "Diamond Boots";
        final String ARMOR_NETHERITE_BOOTS = ChatColor.YELLOW + "Netherite Boots";

    final String DELETE_MENU = ChatColor.DARK_BLUE + "Confirm Deletion";
        final String DELETE_YES = ChatColor.GREEN + "YES";
        final String DELETE_NO = ChatColor.RED + "NO";

    final String COMPLETE_MENU = ChatColor.DARK_BLUE + "Confirm Completion";
        final String COMPLETE_YES = ChatColor.GREEN + "YES";
        final String COMPLETE_NO = ChatColor.RED + "NO";

    private final ArmorStands plugin;

    public MenuListener(ArmorStands plugin) {
        this.plugin = plugin;
    }

    public void itemClick(Player p) {
        p.closeInventory();
        playClickSound(p);
    }

    public void createNewArmorStand(Player p) {
        ArmorStand as = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
        as.setVisible(false);
        as.setInvulnerable(true);
        as.setCollidable(false);
        as.setGravity(false);
        as.setMarker(true);
        plugin.armorStands.put(p, as);
        p.sendMessage(ChatColor.GREEN + "Created a new armor stand!");
    }

    public void deleteArmorStand(Player p) {
        plugin.armorStands.get(p).remove();
        plugin.armorStands.remove(p);
        p.sendMessage(ChatColor.RED + "Armor stand deleted!");
    }

    public void playClickSound(Player p){
        p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 1, 1);
    }

    public void changeSelectedPosition(Player p, Inventory inventory, Vector amount) {

        playClickSound(p);
        ArmorStand as = plugin.armorStands.get(p);

        String pos = "";
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 4; j++){
                ItemStack position = inventory.getItem(i + 9*j + 18);
                ItemMeta positionMeta = position.getItemMeta();
                if (positionMeta.hasEnchantmentGlintOverride() && positionMeta.getEnchantmentGlintOverride()) {
                    pos = positionMeta.getDisplayName();
                }
            }
        }

        if (amount.isZero()) {
            if (pos.equals(POSITION_HEAD)) {
                as.setHeadPose(new EulerAngle(0, 0, 0));
            } else if (pos.equals(POSITION_ROTATION)) {
                as.setRotation(0, 0);
            } else if (pos.equals(POSITION_LEFT_ARM)) {
                as.setLeftArmPose(new EulerAngle(0, 0, 0));
            } else if (pos.equals(POSITION_BODY)) {
                as.setBodyPose(new EulerAngle(0, 0, 0));
            } else if (pos.equals(POSITION_RIGHT_ARM)) {
                as.setRightArmPose(new EulerAngle(0, 0, 0));
            } else if (pos.equals(POSITION_LEFT_LEG)) {
                as.setLeftLegPose(new EulerAngle(0, 0, 0));
            } else if (pos.equals(POSITION_RIGHT_LEG)) {
                as.setRightLegPose(new EulerAngle(0, 0, 0));
            }
        }

        if (pos.equals(POSITION_POSITION)) {
            Location location = as.getLocation();
            location.add(amount.divide(new Vector(100, 100, 100)));
            as.teleport(location);
        } else if (pos.equals(POSITION_HEAD)) {
            EulerAngle euler = as.getHeadPose();
            as.setHeadPose(new EulerAngle(Math.toRadians(amount.getX()) + euler.getX(),
                    Math.toRadians(amount.getY()) + euler.getY(), Math.toRadians(amount.getZ()) + euler.getZ()));
        } else if (pos.equals(POSITION_ROTATION)) {
            float yaw = as.getLocation().getYaw();
            float pitch = as.getLocation().getPitch();
            as.setRotation(yaw + (float) amount.getX(),  pitch);
        } else if (pos.equals(POSITION_LEFT_ARM)) {
            EulerAngle euler = as.getLeftArmPose();
            as.setLeftArmPose(new EulerAngle(Math.toRadians(amount.getX()) + euler.getX(),
                    Math.toRadians(amount.getY()) + euler.getY(), Math.toRadians(amount.getZ()) + euler.getZ()));
        } else if (pos.equals(POSITION_BODY)) {
            EulerAngle euler = as.getBodyPose();
            as.setBodyPose(new EulerAngle(Math.toRadians(amount.getX()) + euler.getX(),
                    Math.toRadians(amount.getY()) + euler.getY(), Math.toRadians(amount.getZ()) + euler.getZ()));
        } else if (pos.equals(POSITION_RIGHT_ARM)) {
            EulerAngle euler = as.getRightArmPose();
            as.setRightArmPose(new EulerAngle(Math.toRadians(amount.getX()) + euler.getX(),
                    Math.toRadians(amount.getY()) + euler.getY(), Math.toRadians(amount.getZ()) + euler.getZ()));
        } else if (pos.equals(POSITION_LEFT_LEG)) {
            EulerAngle euler = as.getLeftLegPose();
            as.setLeftLegPose(new EulerAngle(Math.toRadians(amount.getX()) + euler.getX(),
                    Math.toRadians(amount.getY()) + euler.getY(), Math.toRadians(amount.getZ()) + euler.getZ()));
        } else if (pos.equals(POSITION_RIGHT_LEG)) {
            EulerAngle euler = as.getRightLegPose();
            as.setRightLegPose(new EulerAngle(Math.toRadians(amount.getX()) + euler.getX(),
                    Math.toRadians(amount.getY()) + euler.getY(), Math.toRadians(amount.getZ()) + euler.getZ()));
        }
    }

    public int getSelectedSlot(Player p, Inventory inventory) {

        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 4; j++){
                ItemStack tab = inventory.getItem(i + 9*j + 18);
                ItemMeta tabMeta = tab.getItemMeta();
                if (tabMeta.hasEnchantmentGlintOverride()) {
                    if (tabMeta.getEnchantmentGlintOverride()){
                        return i + 9*j + 18;
                    }
                }
                inventory.setItem(i + 9*j + 18, tab);
            }
        }
        return 19;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null){
            return;
        }
        String clickedItem = e.getCurrentItem().getItemMeta().getDisplayName();


        // check which menu was clicked
        if (e.getView().getTitle().equals(AS_MENU)) { // clicked in main menu

            e.setCancelled(true);

            if(!e.isLeftClick()){
                return;
            }

            // check which item was clicked
            if (clickedItem.equals(AS_CREATE)) { // selected create

                // create new armor stand and store in hash map, if player doesn't already have one
                if (!plugin.armorStands.containsKey(p)) {
                    itemClick(p);
                    createNewArmorStand(p);
                    plugin.openCreateMenu(p);
                }else {
                    itemClick(p);
                    plugin.openDeleteMenu(p, "duplicate");
                }


            } else if (clickedItem.equals(AS_RESUME)) {
                itemClick(p);
                if (plugin.armorStands.containsKey(p)) {
                    plugin.openCreateMenu(p);
                }else {
                    p.sendMessage(ChatColor.RED + "You don't have an active Armor Stand!");
                }
            } else if (clickedItem.equals(AS_CLOSE)) { // selected close
                itemClick(p);
            }


        } else if (e.getView().getTitle().equals(CREATE_MENU)) { // clicked in create menu

            e.setCancelled(true);
            ArmorStand as = plugin.armorStands.get(p);

            if (!e.isRightClick() && !e.isLeftClick()) {
                return;
            } else if (e.isRightClick()){
                if (clickedItem.equals(POSITION_X_PLUS)) {
                    changeSelectedPosition(p, e.getInventory(), new Vector(10,0,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(p, e.getInventory()));

                } else if (clickedItem.equals(POSITION_X_MINUS)) {
                    changeSelectedPosition(p, e.getInventory(), new Vector(-10,0,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(p, e.getInventory()));

                } else if (clickedItem.equals(POSITION_Y_PLUS)) {
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,10,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(p, e.getInventory()));

                } else if (clickedItem.equals(POSITION_Y_MINUS)) {
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,-10,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(p, e.getInventory()));

                } else if (clickedItem.equals(POSITION_Z_PLUS)) {
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,0,10));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(p, e.getInventory()));

                } else if (clickedItem.equals(POSITION_Z_MINUS)) {
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,0,-10));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(p, e.getInventory()));
                }
            } else if(e.isLeftClick()){
                if (clickedItem.equals(CREATE_COMPLETE)){
                    playClickSound(p);
                    plugin.openCompleteMenu(p);
                } else if (clickedItem.equals(CREATE_SETTINGS)) {
                    playClickSound(p);
                    plugin.openCreateSettings(p, e.getInventory());
                }  else if (clickedItem.equals(CREATE_POSITION)) {
                    playClickSound(p);
                    plugin.openCreatePosition(p, e.getInventory());
                }  else if (clickedItem.equals(CREATE_MAIN_HAND)) {
                    playClickSound(p);
                    plugin.openCreateMainHand(e.getInventory());
                }  else if (clickedItem.equals(CREATE_OFF_HAND)) {
                    playClickSound(p);
                    plugin.openCreateOffHand(e.getInventory());
                }  else if (clickedItem.equals(CREATE_ARMOR)) {
                    playClickSound(p);
                    plugin.openCreateArmor(p, e.getInventory());
                }  else if (clickedItem.equals(CREATE_DELETE)) {
                    itemClick(p);
                    plugin.openDeleteMenu(p, "delete");
                } else if (clickedItem.equals(SETTINGS_GRAVITY)) {
                    playClickSound(p);
                    plugin.armorStands.get(p).setGravity(!plugin.armorStands.get(p).hasGravity());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_INVULNERABLE)) {
                    playClickSound(p);
                    plugin.armorStands.get(p).setInvulnerable(!plugin.armorStands.get(p).isInvulnerable());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_BASE_PLATE)) {
                    playClickSound(p);
                    plugin.armorStands.get(p).setBasePlate(!plugin.armorStands.get(p).hasBasePlate());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_LOCKED)) {
                    playClickSound(p);
                    if (as.hasEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.REMOVING_OR_CHANGING)) {
                        as.removeEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
                        as.removeEquipmentLock(EquipmentSlot.BODY, ArmorStand.LockType.REMOVING_OR_CHANGING);
                        as.removeEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.REMOVING_OR_CHANGING);
                        as.removeEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.REMOVING_OR_CHANGING);
                        as.removeEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.REMOVING_OR_CHANGING);
                        as.removeEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
                        as.removeEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    }else {
                        as.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
                        as.addEquipmentLock(EquipmentSlot.BODY, ArmorStand.LockType.REMOVING_OR_CHANGING);
                        as.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.REMOVING_OR_CHANGING);
                        as.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.REMOVING_OR_CHANGING);
                        as.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.REMOVING_OR_CHANGING);
                        as.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
                        as.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    }
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_ARMS)) {
                    playClickSound(p);
                    plugin.armorStands.get(p).setArms(!plugin.armorStands.get(p).hasArms());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_MARKER)) {
                    playClickSound(p);
                    plugin.armorStands.get(p).setMarker(!plugin.armorStands.get(p).isMarker());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_GLOWING)) {
                    playClickSound(p);
                    plugin.armorStands.get(p).setGlowing(!plugin.armorStands.get(p).isGlowing());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_COLLIDABLE)) {
                    playClickSound(p);
                    plugin.armorStands.get(p).setCollidable(!plugin.armorStands.get(p).isCollidable());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_INVISIBLE)) {
                    playClickSound(p);
                    plugin.armorStands.get(p).setInvisible(!plugin.armorStands.get(p).isInvisible());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_SMALL)) {
                    playClickSound(p);
                    plugin.armorStands.get(p).setSmall(!plugin.armorStands.get(p).isSmall());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(POSITION_POSITION)) {
                    playClickSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 19);

                } else if (clickedItem.equals(POSITION_HEAD)) {
                    playClickSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 20);

                } else if (clickedItem.equals(POSITION_ROTATION)) {
                    playClickSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 21);

                } else if (clickedItem.equals(POSITION_LEFT_ARM)) {
                    playClickSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 28);

                } else if (clickedItem.equals(POSITION_BODY)) {
                    playClickSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 29);

                } else if (clickedItem.equals(POSITION_RIGHT_ARM)) {
                    playClickSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 30);

                } else if (clickedItem.equals(POSITION_LEFT_LEG)) {
                    playClickSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 37);

                } else if (clickedItem.equals(POSITION_RIGHT_LEG)) {
                    playClickSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 39);

                } else if (clickedItem.equals(POSITION_X_PLUS)) {
                    changeSelectedPosition(p, e.getInventory(), new Vector(1,0,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(p, e.getInventory()));

                } else if (clickedItem.equals(POSITION_X_MINUS)) {
                    changeSelectedPosition(p, e.getInventory(), new Vector(-1,0,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(p, e.getInventory()));

                } else if (clickedItem.equals(POSITION_Y_PLUS)) {
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,1,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(p, e.getInventory()));

                } else if (clickedItem.equals(POSITION_Y_MINUS)) {
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,-1,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(p, e.getInventory()));

                } else if (clickedItem.equals(POSITION_Z_PLUS)) {
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,0,1));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(p, e.getInventory()));

                } else if (clickedItem.equals(POSITION_Z_MINUS)) {
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,0,-1));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(p, e.getInventory()));

                } else if (clickedItem.equals(POSITION_RESET)) {
                    changeSelectedPosition(p, e.getInventory(), new Vector());
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(p, e.getInventory()));

                } else if (clickedItem.equals(POSITION_TELEPORT)) {
                    playClickSound(p);
                    as.teleport(p);
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(p, e.getInventory()));
                } else if (clickedItem.equals(ARMOR_NO_HELMET)) {
                    as.getEquipment().setHelmet(new ItemStack(Material.AIR));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_LEATHER_HELMET)) {
                    as.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_CHAIN_HELMET)) {
                    as.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_GOLD_HELMET)) {
                    as.getEquipment().setHelmet(new ItemStack(Material.GOLDEN_HELMET));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_IRON_HELMET)) {
                    as.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_DIAMOND_HELMET)) {
                    as.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_NETHERITE_HELMET)) {
                    as.getEquipment().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_NO_CHESTPLATE)) {
                    as.getEquipment().setChestplate(new ItemStack(Material.AIR));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_LEATHER_CHESTPLATE)) {
                    as.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_CHAIN_CHESTPLATE)) {
                    as.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_GOLD_CHESTPLATE)) {
                    as.getEquipment().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_IRON_CHESTPLATE)) {
                    as.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_DIAMOND_CHESTPLATE)) {
                    as.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_NETHERITE_CHESTPLATE)) {
                    as.getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_NO_LEGGINGS)) {
                    as.getEquipment().setLeggings(new ItemStack(Material.AIR));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_LEATHER_LEGGINGS)) {
                    as.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_CHAIN_LEGGINGS)) {
                    as.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_GOLD_LEGGINGS)) {
                    as.getEquipment().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_IRON_LEGGINGS)) {
                    as.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_DIAMOND_LEGGINGS)) {
                    as.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_NETHERITE_LEGGINGS)) {
                    as.getEquipment().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_NO_BOOTS)) {
                    as.getEquipment().setBoots(new ItemStack(Material.AIR));
                    plugin.updateCreateArmor(p, e.getInventory());

                }else if (clickedItem.equals(ARMOR_LEATHER_BOOTS)) {
                    as.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_CHAIN_BOOTS)) {
                    as.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_GOLD_BOOTS)) {
                    as.getEquipment().setBoots(new ItemStack(Material.GOLDEN_BOOTS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_IRON_BOOTS)) {
                    as.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_DIAMOND_BOOTS)) {
                    as.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_NETHERITE_BOOTS)) {
                    as.getEquipment().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
                    plugin.updateCreateArmor(p, e.getInventory());

                }
            }

        } else if (e.getView().getTitle().equals(DELETE_MENU)) { // clicked in delete menu

            e.setCancelled(true);

            if(!e.isLeftClick()){
                return;
            }

            if (clickedItem.equals(DELETE_YES)) { // selected yes
                itemClick(p);
                deleteArmorStand(p);
                plugin.openASMenu(p);
            } else if (clickedItem.equals(DELETE_NO)) { // selected no
                itemClick(p);
                plugin.openASMenu(p);
            }
        } else if (e.getView().getTitle().equals(COMPLETE_MENU)) { // clicked in complete menu

            e.setCancelled(true);

            if(!e.isLeftClick()){
                return;
            }

            if (clickedItem.equals(COMPLETE_YES)) { // selected yes
                itemClick(p);
                plugin.armorStands.remove(p);
                p.sendMessage(ChatColor.GREEN + "Armor stand creation completed!");
                p.closeInventory();
            } else if (clickedItem.equals(COMPLETE_NO)) { // selected no
                itemClick(p);
                plugin.openCreateMenu(p);
            }
        }
    }
}
