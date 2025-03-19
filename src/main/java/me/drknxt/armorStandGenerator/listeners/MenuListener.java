package me.drknxt.armorStandGenerator.listeners;

import me.drknxt.armorStandGenerator.ArmorStandGenerator;
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

import java.util.Objects;

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
        final String SETTINGS_CUSTOM_NAME = ChatColor.YELLOW + "Custom Name Visible";
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

        final String HAND_NEXT_PAGE = ChatColor.YELLOW + "Next Page";
        final String HAND_PREVIOUS_PAGE = ChatColor.YELLOW + "Previous Page";
        final String HAND_NO_ITEM = ChatColor.YELLOW + "No Item";

        final String HAND_WOODEN_SWORD = ChatColor.YELLOW + "Wooden Sword";
        final String HAND_STONE_SWORD = ChatColor.YELLOW + "Stone Sword";
        final String HAND_IRON_SWORD = ChatColor.YELLOW + "Iron Sword";
        final String HAND_GOLD_SWORD = ChatColor.YELLOW + "Golden Sword";
        final String HAND_DIAMOND_SWORD = ChatColor.YELLOW + "Diamond Sword";
        final String HAND_NETHERITE_SWORD = ChatColor.YELLOW + "Netherite Sword";

        final String HAND_WOODEN_AXE = ChatColor.YELLOW + "Wooden Axe";
        final String HAND_STONE_AXE = ChatColor.YELLOW + "Stone Axe";
        final String HAND_IRON_AXE = ChatColor.YELLOW + "Iron Axe";
        final String HAND_GOLD_AXE = ChatColor.YELLOW + "Golden Axe";
        final String HAND_DIAMOND_AXE = ChatColor.YELLOW + "Diamond Axe";
        final String HAND_NETHERITE_AXE = ChatColor.YELLOW + "Netherite Axe";

        final String HAND_WOODEN_PICKAXE = ChatColor.YELLOW + "Wooden Pickaxe";
        final String HAND_STONE_PICKAXE = ChatColor.YELLOW + "Stone Pickaxe";
        final String HAND_IRON_PICKAXE = ChatColor.YELLOW + "Iron Pickaxe";
        final String HAND_GOLD_PICKAXE = ChatColor.YELLOW + "Golden Pickaxe";
        final String HAND_DIAMOND_PICKAXE = ChatColor.YELLOW + "Diamond Pickaxe";
        final String HAND_NETHERITE_PICKAXE = ChatColor.YELLOW + "Netherite Pickaxe";

        final String HAND_WOODEN_SHOVEL = ChatColor.YELLOW + "Wooden Shovel";
        final String HAND_STONE_SHOVEL = ChatColor.YELLOW + "Stone Shovel";
        final String HAND_IRON_SHOVEL = ChatColor.YELLOW + "Iron Shovel";
        final String HAND_GOLD_SHOVEL = ChatColor.YELLOW + "Golden Shovel";
        final String HAND_DIAMOND_SHOVEL = ChatColor.YELLOW + "Diamond Shovel";
        final String HAND_NETHERITE_SHOVEL = ChatColor.YELLOW + "Netherite Shovel";

        final String HAND_WOODEN_HOE = ChatColor.YELLOW + "Wooden Hoe";
        final String HAND_STONE_HOE = ChatColor.YELLOW + "Stone Hoe";
        final String HAND_IRON_HOE = ChatColor.YELLOW + "Iron Hoe";
        final String HAND_GOLD_HOE = ChatColor.YELLOW + "Golden Hoe";
        final String HAND_DIAMOND_HOE = ChatColor.YELLOW + "Diamond Hoe";
        final String HAND_NETHERITE_HOE = ChatColor.YELLOW + "Netherite Hoe";

        final String HAND_SHIELD = ChatColor.YELLOW + "Shield";
        final String HAND_BOW = ChatColor.YELLOW + "Bow";
        final String HAND_CROSSBOW = ChatColor.YELLOW + "Crossbow";
        final String HAND_TRIDENT = ChatColor.YELLOW + "Trident";
        final String HAND_MACE = ChatColor.YELLOW + "Mace";
        final String HAND_CLOCK = ChatColor.YELLOW + "Clock";

        final String HAND_FISHING_ROD = ChatColor.YELLOW + "Fishing Rod";
        final String HAND_FLINT_AND_STEEL = ChatColor.YELLOW + "Flint And Steel";
        final String HAND_SHEARS = ChatColor.YELLOW + "Shears";
        final String HAND_SPYGLASS = ChatColor.YELLOW + "Spyglass";
        final String HAND_BRUSH = ChatColor.YELLOW + "Brush";
        final String HAND_COMPASS = ChatColor.YELLOW + "Compass";

    final String DELETE_MENU = ChatColor.DARK_BLUE + "Confirm Deletion";
        final String DELETE_YES = ChatColor.GREEN + "YES";
        final String DELETE_NO = ChatColor.RED + "NO";

    final String COMPLETE_MENU = ChatColor.DARK_BLUE + "Confirm Completion";
        final String COMPLETE_YES = ChatColor.GREEN + "YES";
        final String COMPLETE_NO = ChatColor.RED + "NO";

    private final ArmorStandGenerator plugin;

    public MenuListener(ArmorStandGenerator plugin) {
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
        p.playSound(p.getLocation(), Sound.BLOCK_LEVER_CLICK, .5f, 1f);
    }

    public void playSettingsChangeSound(Player p){
        p.playSound(p.getLocation(), Sound.BLOCK_LEVER_CLICK, .5f, 1f);
    }

    public void playPositionSelectSound(Player p){
        p.playSound(p.getLocation(), Sound.BLOCK_LEVER_CLICK, .5f, 1f);
    }

    public void playPositionChangeSmallSound(Player p){
        p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, .7f, 1f);
    }

    public void playPositionChangeBigSound(Player p){
        p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_PLACE, .7f, 1f);
    }

    public void playPositionTeleportSound(Player p){
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_TELEPORT, .3f, 1f);
    }

    public void playPositionResetSound(Player p){
        p.playSound(p.getLocation(), Sound.BLOCK_LEVER_CLICK, .5f, 1f);
    }

    public void playArmorChangeSound(Player p){
        p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 1f, 1f);
    }

    public void playHandChangeSound(Player p){
        p.playSound(p.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 1f, 1f);
    }

    public void changeSelectedPosition(Player p, Inventory inventory, Vector amount) {

        ArmorStand as = plugin.armorStands.get(p);

        String pos = "";
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 4; j++){
                ItemStack position = inventory.getItem(i + 9*j + 18);
                assert position != null;
                ItemMeta positionMeta = position.getItemMeta();
                assert positionMeta != null;
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

    public int getSelectedSlot(Inventory inventory) {

        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 4; j++){
                ItemStack tab = inventory.getItem(i + 9*j + 18);
                assert tab != null;
                ItemMeta tabMeta = tab.getItemMeta();
                assert tabMeta != null;
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

    public void setHandItem(Player p, Inventory inventory, ItemStack item) {
        ArmorStand as = plugin.armorStands.get(p);
        boolean isMainHand = Objects.requireNonNull(Objects.requireNonNull(inventory.getItem(4)).getItemMeta()).getEnchantmentGlintOverride();
        if (isMainHand) {
            Objects.requireNonNull(as.getEquipment()).setItemInMainHand(item);
        }else {
            Objects.requireNonNull(as.getEquipment()).setItemInOffHand(item);
        }
        plugin.updateCreateHand(p, inventory);
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

            if (e.isRightClick()){
                if (clickedItem.equals(POSITION_X_PLUS)) {
                    playPositionChangeBigSound(p);
                    changeSelectedPosition(p, e.getInventory(), new Vector(10,0,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(e.getInventory()));

                } else if (clickedItem.equals(POSITION_X_MINUS)) {
                    playPositionChangeBigSound(p);
                    changeSelectedPosition(p, e.getInventory(), new Vector(-10,0,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(e.getInventory()));

                } else if (clickedItem.equals(POSITION_Y_PLUS)) {
                    playPositionChangeBigSound(p);
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,10,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(e.getInventory()));

                } else if (clickedItem.equals(POSITION_Y_MINUS)) {
                    playPositionChangeBigSound(p);
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,-10,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(e.getInventory()));

                } else if (clickedItem.equals(POSITION_Z_PLUS)) {
                    playPositionChangeBigSound(p);
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,0,10));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(e.getInventory()));

                } else if (clickedItem.equals(POSITION_Z_MINUS)) {
                    playPositionChangeBigSound(p);
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,0,-10));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(e.getInventory()));
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
                    plugin.openCreateMainHand(p, e.getInventory());
                }  else if (clickedItem.equals(CREATE_OFF_HAND)) {
                    playClickSound(p);
                    plugin.openCreateOffHand(p, e.getInventory());
                }  else if (clickedItem.equals(CREATE_ARMOR)) {
                    playClickSound(p);
                    plugin.openCreateArmor(p, e.getInventory());
                }  else if (clickedItem.equals(CREATE_DELETE)) {
                    itemClick(p);
                    plugin.openDeleteMenu(p, "delete");
                } else if (clickedItem.equals(SETTINGS_GRAVITY)) {
                    playSettingsChangeSound(p);
                    plugin.armorStands.get(p).setGravity(!plugin.armorStands.get(p).hasGravity());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_INVULNERABLE)) {
                    playSettingsChangeSound(p);
                    plugin.armorStands.get(p).setInvulnerable(!plugin.armorStands.get(p).isInvulnerable());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_BASE_PLATE)) {
                    playSettingsChangeSound(p);
                    plugin.armorStands.get(p).setBasePlate(!plugin.armorStands.get(p).hasBasePlate());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_LOCKED)) {
                    playSettingsChangeSound(p);
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
                    playSettingsChangeSound(p);
                    plugin.armorStands.get(p).setArms(!plugin.armorStands.get(p).hasArms());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_MARKER)) {
                    playSettingsChangeSound(p);
                    plugin.armorStands.get(p).setMarker(!plugin.armorStands.get(p).isMarker());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_GLOWING)) {
                    playSettingsChangeSound(p);
                    plugin.armorStands.get(p).setGlowing(!plugin.armorStands.get(p).isGlowing());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_CUSTOM_NAME)) {
                    playSettingsChangeSound(p);
                    plugin.armorStands.get(p).setCustomNameVisible(!plugin.armorStands.get(p).isCustomNameVisible());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_INVISIBLE)) {
                    playSettingsChangeSound(p);
                    plugin.armorStands.get(p).setInvisible(!plugin.armorStands.get(p).isInvisible());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(SETTINGS_SMALL)) {
                    playSettingsChangeSound(p);
                    plugin.armorStands.get(p).setSmall(!plugin.armorStands.get(p).isSmall());
                    plugin.openCreateSettings(p, e.getInventory());
                } else if (clickedItem.equals(POSITION_POSITION)) {
                    playPositionSelectSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 19);

                } else if (clickedItem.equals(POSITION_HEAD)) {
                    playPositionSelectSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 20);

                } else if (clickedItem.equals(POSITION_ROTATION)) {
                    playPositionSelectSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 21);

                } else if (clickedItem.equals(POSITION_LEFT_ARM)) {
                    playPositionSelectSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 28);

                } else if (clickedItem.equals(POSITION_BODY)) {
                    playPositionSelectSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 29);

                } else if (clickedItem.equals(POSITION_RIGHT_ARM)) {
                    playPositionSelectSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 30);

                } else if (clickedItem.equals(POSITION_LEFT_LEG)) {
                    playPositionSelectSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 37);

                } else if (clickedItem.equals(POSITION_RIGHT_LEG)) {
                    playPositionSelectSound(p);
                    plugin.getCreatePositon(p, e.getInventory(), 39);

                } else if (clickedItem.equals(POSITION_X_PLUS)) {
                    playPositionChangeSmallSound(p);
                    changeSelectedPosition(p, e.getInventory(), new Vector(1,0,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(e.getInventory()));

                } else if (clickedItem.equals(POSITION_X_MINUS)) {
                    playPositionChangeSmallSound(p);
                    changeSelectedPosition(p, e.getInventory(), new Vector(-1,0,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(e.getInventory()));

                } else if (clickedItem.equals(POSITION_Y_PLUS)) {
                    playPositionChangeSmallSound(p);
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,1,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(e.getInventory()));

                } else if (clickedItem.equals(POSITION_Y_MINUS)) {
                    playPositionChangeSmallSound(p);
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,-1,0));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(e.getInventory()));

                } else if (clickedItem.equals(POSITION_Z_PLUS)) {
                    playPositionChangeSmallSound(p);
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,0,1));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(e.getInventory()));

                } else if (clickedItem.equals(POSITION_Z_MINUS)) {
                    playPositionChangeSmallSound(p);
                    changeSelectedPosition(p, e.getInventory(), new Vector(0,0,-1));
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(e.getInventory()));

                } else if (clickedItem.equals(POSITION_RESET)) {
                    playPositionResetSound(p);
                    changeSelectedPosition(p, e.getInventory(), new Vector());
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(e.getInventory()));

                } else if (clickedItem.equals(POSITION_TELEPORT)) {
                    playPositionTeleportSound(p);
                    as.teleport(p);
                    plugin.getCurrentRotation(p, e.getInventory(), getSelectedSlot(e.getInventory()));
                } else if (clickedItem.equals(ARMOR_NO_HELMET)) {
                    playArmorChangeSound(p);
                    Objects.requireNonNull(as.getEquipment()).setHelmet(new ItemStack(Material.AIR));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_LEATHER_HELMET)) {
                    Objects.requireNonNull(as.getEquipment()).setHelmet(new ItemStack(Material.LEATHER_HELMET));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_CHAIN_HELMET)) {
                    Objects.requireNonNull(as.getEquipment()).setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_GOLD_HELMET)) {
                    Objects.requireNonNull(as.getEquipment()).setHelmet(new ItemStack(Material.GOLDEN_HELMET));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_IRON_HELMET)) {
                    Objects.requireNonNull(as.getEquipment()).setHelmet(new ItemStack(Material.IRON_HELMET));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_DIAMOND_HELMET)) {
                    Objects.requireNonNull(as.getEquipment()).setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_NETHERITE_HELMET)) {
                    Objects.requireNonNull(as.getEquipment()).setHelmet(new ItemStack(Material.NETHERITE_HELMET));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_NO_CHESTPLATE)) {
                    playArmorChangeSound(p);
                    Objects.requireNonNull(as.getEquipment()).setChestplate(new ItemStack(Material.AIR));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_LEATHER_CHESTPLATE)) {
                    Objects.requireNonNull(as.getEquipment()).setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_CHAIN_CHESTPLATE)) {
                    Objects.requireNonNull(as.getEquipment()).setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_GOLD_CHESTPLATE)) {
                    Objects.requireNonNull(as.getEquipment()).setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_IRON_CHESTPLATE)) {
                    Objects.requireNonNull(as.getEquipment()).setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_DIAMOND_CHESTPLATE)) {
                    Objects.requireNonNull(as.getEquipment()).setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_NETHERITE_CHESTPLATE)) {
                    Objects.requireNonNull(as.getEquipment()).setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_NO_LEGGINGS)) {
                    playArmorChangeSound(p);
                    Objects.requireNonNull(as.getEquipment()).setLeggings(new ItemStack(Material.AIR));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_LEATHER_LEGGINGS)) {
                    Objects.requireNonNull(as.getEquipment()).setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_CHAIN_LEGGINGS)) {
                    Objects.requireNonNull(as.getEquipment()).setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_GOLD_LEGGINGS)) {
                    Objects.requireNonNull(as.getEquipment()).setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_IRON_LEGGINGS)) {
                    Objects.requireNonNull(as.getEquipment()).setLeggings(new ItemStack(Material.IRON_LEGGINGS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_DIAMOND_LEGGINGS)) {
                    Objects.requireNonNull(as.getEquipment()).setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_NETHERITE_LEGGINGS)) {
                    Objects.requireNonNull(as.getEquipment()).setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_NO_BOOTS)) {
                    playArmorChangeSound(p);
                    Objects.requireNonNull(as.getEquipment()).setBoots(new ItemStack(Material.AIR));
                    plugin.updateCreateArmor(p, e.getInventory());

                }else if (clickedItem.equals(ARMOR_LEATHER_BOOTS)) {
                    Objects.requireNonNull(as.getEquipment()).setBoots(new ItemStack(Material.LEATHER_BOOTS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_CHAIN_BOOTS)) {
                    Objects.requireNonNull(as.getEquipment()).setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_GOLD_BOOTS)) {
                    Objects.requireNonNull(as.getEquipment()).setBoots(new ItemStack(Material.GOLDEN_BOOTS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_IRON_BOOTS)) {
                    Objects.requireNonNull(as.getEquipment()).setBoots(new ItemStack(Material.IRON_BOOTS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_DIAMOND_BOOTS)) {
                    Objects.requireNonNull(as.getEquipment()).setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(ARMOR_NETHERITE_BOOTS)) {
                    Objects.requireNonNull(as.getEquipment()).setBoots(new ItemStack(Material.NETHERITE_BOOTS));
                    plugin.updateCreateArmor(p, e.getInventory());

                } else if (clickedItem.equals(HAND_NEXT_PAGE)) {
                    playClickSound(p);
                    plugin.openCreateHandPage(e.getInventory(), 2);
                    plugin.updateCreateHand(p, e.getInventory());

                } else if (clickedItem.equals(HAND_PREVIOUS_PAGE)) {
                    playClickSound(p);
                    plugin.openCreateHandPage(e.getInventory(), 1);
                    plugin.updateCreateHand(p, e.getInventory());

                } else if (clickedItem.equals(HAND_NO_ITEM)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.AIR));

                } else if (clickedItem.equals(HAND_WOODEN_SWORD)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.WOODEN_SWORD));

                }  else if (clickedItem.equals(HAND_STONE_SWORD)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.STONE_SWORD));

                }  else if (clickedItem.equals(HAND_IRON_SWORD)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.IRON_SWORD));

                }  else if (clickedItem.equals(HAND_GOLD_SWORD)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.GOLDEN_SWORD));

                } else if (clickedItem.equals(HAND_DIAMOND_SWORD)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.DIAMOND_SWORD));

                } else if (clickedItem.equals(HAND_NETHERITE_SWORD)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.NETHERITE_SWORD));

                } else if (clickedItem.equals(HAND_WOODEN_AXE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.WOODEN_AXE));

                }  else if (clickedItem.equals(HAND_STONE_AXE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.STONE_AXE));

                }  else if (clickedItem.equals(HAND_IRON_AXE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.IRON_AXE));

                }  else if (clickedItem.equals(HAND_GOLD_AXE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.GOLDEN_AXE));

                } else if (clickedItem.equals(HAND_DIAMOND_AXE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.DIAMOND_AXE));

                } else if (clickedItem.equals(HAND_NETHERITE_AXE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.NETHERITE_AXE));

                } else if (clickedItem.equals(HAND_WOODEN_PICKAXE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.WOODEN_PICKAXE));

                }  else if (clickedItem.equals(HAND_STONE_PICKAXE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.STONE_PICKAXE));

                }  else if (clickedItem.equals(HAND_IRON_PICKAXE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.IRON_PICKAXE));

                }  else if (clickedItem.equals(HAND_GOLD_PICKAXE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.GOLDEN_PICKAXE));

                } else if (clickedItem.equals(HAND_DIAMOND_PICKAXE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.DIAMOND_PICKAXE));

                } else if (clickedItem.equals(HAND_NETHERITE_PICKAXE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.NETHERITE_PICKAXE));

                }  else if (clickedItem.equals(HAND_SHIELD)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.SHIELD));

                } else if (clickedItem.equals(HAND_BOW)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.BOW));

                } else if (clickedItem.equals(HAND_CROSSBOW)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.CROSSBOW));

                } else if (clickedItem.equals(HAND_WOODEN_SHOVEL)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.WOODEN_SHOVEL));

                }  else if (clickedItem.equals(HAND_STONE_SHOVEL)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.STONE_SHOVEL));

                }  else if (clickedItem.equals(HAND_IRON_SHOVEL)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.IRON_SHOVEL));

                }  else if (clickedItem.equals(HAND_GOLD_SHOVEL)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.GOLDEN_SHOVEL));

                } else if (clickedItem.equals(HAND_DIAMOND_SHOVEL)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.DIAMOND_SHOVEL));

                } else if (clickedItem.equals(HAND_NETHERITE_SHOVEL)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.NETHERITE_SHOVEL));

                }  else if (clickedItem.equals(HAND_WOODEN_HOE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.WOODEN_HOE));

                }  else if (clickedItem.equals(HAND_STONE_HOE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.STONE_HOE));

                }  else if (clickedItem.equals(HAND_IRON_HOE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.IRON_HOE));

                }  else if (clickedItem.equals(HAND_GOLD_HOE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.GOLDEN_HOE));

                } else if (clickedItem.equals(HAND_DIAMOND_HOE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.DIAMOND_HOE));

                } else if (clickedItem.equals(HAND_NETHERITE_HOE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.NETHERITE_HOE));

                }  else if (clickedItem.equals(HAND_FISHING_ROD)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.FISHING_ROD));

                }  else if (clickedItem.equals(HAND_FLINT_AND_STEEL)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.FLINT_AND_STEEL));

                }  else if (clickedItem.equals(HAND_SHEARS)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.SHEARS));

                }  else if (clickedItem.equals(HAND_SPYGLASS)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.SPYGLASS));

                } else if (clickedItem.equals(HAND_BRUSH)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.BRUSH));

                } else if (clickedItem.equals(HAND_COMPASS)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.COMPASS));

                } else if (clickedItem.equals(HAND_TRIDENT)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.TRIDENT));

                }  else if (clickedItem.equals(HAND_MACE)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.MACE));

                } else if (clickedItem.equals(HAND_CLOCK)) {
                    playHandChangeSound(p);
                    setHandItem(p, e.getInventory(), new ItemStack(Material.CLOCK));

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
