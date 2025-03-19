package me.drknxt.armorStandGenerator;

import me.drknxt.armorStandGenerator.commands.ArmorStandCommand;
import me.drknxt.armorStandGenerator.listeners.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public final class ArmorStandGenerator extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        Objects.requireNonNull(getCommand("asg")).setExecutor(new ArmorStandCommand(this));
        getServer().getPluginManager().registerEvents(new MenuListener(this), this);
    }

    // create hash map to store player and armor stand together
    public HashMap<Player, ArmorStand> armorStands = new HashMap<>();

    public ItemStack getFiller() {
        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();
        assert fillerMeta != null;
        fillerMeta.setHideTooltip(true);
        filler.setItemMeta(fillerMeta);

        return filler;
    }

    public ItemStack getItem(Material material, String displayName, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public void setSelectedTab(Inventory inventory, int selectedSlot) {

        // remove enchantment effect from tab items
        for (int i = 2; i < 7; i++) {
            ItemStack tab = inventory.getItem(i);
            assert tab != null;
            ItemMeta tabMeta = tab.getItemMeta();
            assert tabMeta != null;
            tabMeta.setEnchantmentGlintOverride(false);
            List<String> list = tabMeta.getLore();
            assert list != null;
            list.remove(ChatColor.GREEN + "Selected");
            if (!list.contains(ChatColor.DARK_PURPLE + "Click to select")){
                list.add(ChatColor.DARK_PURPLE + "Click to select");
            }
            tabMeta.setLore(list);
            tab.setItemMeta(tabMeta);
            inventory.setItem(i, tab);
        }

        // set current tab item to have enchantment effect
        ItemStack current = inventory.getItem(selectedSlot);
        assert current != null;
        ItemMeta currentMeta = current.getItemMeta();
        assert currentMeta != null;
        currentMeta.setEnchantmentGlintOverride(true);
        List<String> list = currentMeta.getLore();
        assert list != null;
        list.remove(ChatColor.DARK_PURPLE + "Click to select");
        list.add(ChatColor.GREEN + "Selected");
        currentMeta.setLore(list);
        current.setItemMeta(currentMeta);
        inventory.setItem(selectedSlot, current);

        // replace slots with filler
        ItemStack filler = getFiller();
        for (int i = 18; i< 54; i++) {
            inventory.setItem(i, filler);
        }
    }

    public void getCurrentRotation(Player p, Inventory inventory, int selectedSlot) {
        ArmorStand as = armorStands.get(p);

        ItemStack currentX = getFiller();
        ItemStack currentY = getFiller();
        ItemStack currentZ = getFiller();
        switch (selectedSlot){
            case 19:
                currentX = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "X",
                        List.of(ChatColor.GOLD + "Current X position: " + ChatColor.DARK_PURPLE + Math.round(as.getLocation().getX() * 100) / 100.0));
                currentY = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "Y",
                        List.of(ChatColor.GOLD + "Current Y position: " + ChatColor.DARK_PURPLE + Math.round(as.getLocation().getY() * 100) / 100.0));
                currentZ = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "Z",
                        List.of(ChatColor.GOLD + "Current Z position: " + ChatColor.DARK_PURPLE + Math.round(as.getLocation().getZ() * 100) / 100.0));
                break;
            case 20:
                currentX = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "X",
                        List.of(ChatColor.GOLD + "Current X rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getHeadPose().getX())) + "°"));
                currentY = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "Y",
                        List.of(ChatColor.GOLD + "Current Y rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getHeadPose().getY())) + "°"));
                currentZ = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "Z",
                        List.of(ChatColor.GOLD + "Current Z rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getHeadPose().getZ())) + "°"));
                break;
            case 21:
                currentX = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "X",
                        List.of(ChatColor.GOLD + "Current X rotation: " + ChatColor.DARK_PURPLE + Math.round(as.getLocation().getYaw()) + "°"));
                break;
            case 28:
                currentX = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "X",
                        List.of(ChatColor.GOLD + "Current X rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getLeftArmPose().getX())) + "°"));
                currentY = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "Y",
                        List.of(ChatColor.GOLD + "Current Y rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getLeftArmPose().getY())) + "°"));
                currentZ = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "Z",
                        List.of(ChatColor.GOLD + "Current Z rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getLeftArmPose().getZ())) + "°"));
                break;
            case 29:
                currentX = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "X",
                        List.of(ChatColor.GOLD + "Current X rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getBodyPose().getX())) + "°"));
                currentY = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "Y",
                        List.of(ChatColor.GOLD + "Current Y rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getBodyPose().getY())) + "°"));
                currentZ = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "Z",
                        List.of(ChatColor.GOLD + "Current Z rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getBodyPose().getZ())) + "°"));
                break;
            case 30:
                currentX = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "X",
                        List.of(ChatColor.GOLD + "Current X rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getRightArmPose().getX())) + "°"));
                currentY = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "Y",
                        List.of(ChatColor.GOLD + "Current Y rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getRightArmPose().getY())) + "°"));
                currentZ = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "Z",
                        List.of(ChatColor.GOLD + "Current Z rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getRightArmPose().getZ())) + "°"));
                break;
            case 37:
                currentX = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "X",
                        List.of(ChatColor.GOLD + "Current X rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getLeftLegPose().getX())) + "°"));
                currentY = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "Y",
                        List.of(ChatColor.GOLD + "Current Y rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getLeftLegPose().getY())) + "°"));
                currentZ = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "Z",
                        List.of(ChatColor.GOLD + "Current Z rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getLeftLegPose().getZ())) + "°"));
                break;
            case 39:
                currentX = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "X",
                        List.of(ChatColor.GOLD + "Current X rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getRightLegPose().getX())) + "°"));
                currentY = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "Y",
                        List.of(ChatColor.GOLD + "Current Y rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getRightLegPose().getY())) + "°"));
                currentZ = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "Z",
                        List.of(ChatColor.GOLD + "Current Z rotation: " + ChatColor.DARK_PURPLE + Math.round(Math.toDegrees(as.getRightLegPose().getZ())) + "°"));
                break;
        }
        if (selectedSlot == 21) {
            inventory.setItem(33, currentX);
        }else {
            inventory.setItem(32, currentX);
            inventory.setItem(33, currentY);
            inventory.setItem(34, currentZ);
        }
    }

    public void setArmorEnchantmentOverride(Inventory inventory, int row, int selectedSlot){

        for (int i = 1; i<8; i++) {
            ItemStack item = inventory.getItem(i + row*9);
            assert item != null;
            ItemMeta itemMeta = item.getItemMeta();
            assert itemMeta != null;
            itemMeta.setLore(List.of(ChatColor.DARK_PURPLE + "Click to select"));
            itemMeta.setEnchantmentGlintOverride(false);
            item.setItemMeta(itemMeta);
            inventory.setItem(i + row*9, item);
        }
        ItemStack item = inventory.getItem(selectedSlot + row * 9);
        assert item != null;
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setLore(List.of(ChatColor.GREEN + "Selected"));
        itemMeta.setEnchantmentGlintOverride(true);
        item.setItemMeta(itemMeta);
        inventory.setItem(selectedSlot + row*9, item);
    }

    public void setHandEnchantmentOverride(Inventory inventory, int selectedSlot) {
        ItemStack item = inventory.getItem(selectedSlot);
        assert item != null;
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setLore(List.of(ChatColor.GREEN + "Selected"));
        itemMeta.setEnchantmentGlintOverride(true);
        item.setItemMeta(itemMeta);
        inventory.setItem(selectedSlot, item);
    }

    // create menu tabs
    // open tab settings
    public void openCreateSettings(Player p, Inventory inventory) {

        setSelectedTab(inventory, 2);

        // create items
        ItemStack on = getItem(Material.GREEN_CONCRETE, ChatColor.GREEN + "ON", List.of());
        ItemStack off = getItem(Material.RED_CONCRETE, ChatColor.RED + "OFF", List.of());

        ItemStack gravity = getItem(Material.FEATHER, ChatColor.YELLOW + "Gravity", List.of(ChatColor.DARK_PURPLE + "Click to toggle"));
        ItemStack invulnerable = getItem(Material.BEDROCK, ChatColor.YELLOW + "Invulnerable", List.of(ChatColor.DARK_PURPLE + "Click to toggle"));
        ItemStack basePlate = getItem(Material.SMOOTH_STONE_SLAB, ChatColor.YELLOW + "Base Plate", List.of(ChatColor.DARK_PURPLE + "Click to toggle"));
        ItemStack locked = getItem(Material.TRIAL_KEY, ChatColor.YELLOW + "Locked", List.of(ChatColor.DARK_PURPLE + "Click to toggle"));
        ItemStack arms = getItem(Material.STICK, ChatColor.YELLOW + "Arms", List.of(ChatColor.DARK_PURPLE + "Click to toggle"));
        ItemStack marker = getItem(Material.DRAGON_BREATH, ChatColor.YELLOW + "Marker", List.of(ChatColor.DARK_PURPLE + "Click to toggle"));
        ItemStack glowing = getItem(Material.GLOW_INK_SAC, ChatColor.YELLOW + "Glowing", List.of(ChatColor.DARK_PURPLE + "Click to toggle"));
        ItemStack customName = getItem(Material.NAME_TAG, ChatColor.YELLOW + "Custom Name Visible", List.of(ChatColor.DARK_PURPLE + "Click to toggle"));
        ItemStack invisible = getItem(Material.GLASS, ChatColor.YELLOW + "Invisible", List.of(ChatColor.DARK_PURPLE + "Click to toggle"));
        ItemStack small = getItem(Material.ARMOR_STAND, ChatColor.YELLOW + "Small", List.of(ChatColor.DARK_PURPLE + "Click to toggle"));

        ItemStack setCustomName = getItem(Material.KNOWLEDGE_BOOK, ChatColor.YELLOW + "Set Custom Name",
                List.of(ChatColor.GOLD + "Use " + ChatColor.DARK_PURPLE + "/asg setname <name>" + ChatColor.GOLD + " to set the" , ChatColor.GOLD + "custom name for this armor stand"));


        // set option items
        inventory.setItem(20, gravity);
        inventory.setItem(21, invulnerable);
        inventory.setItem(22, basePlate);
        inventory.setItem(23, locked);
        inventory.setItem(24, arms);
        inventory.setItem(38, marker);
        inventory.setItem(39, glowing);
        inventory.setItem(40, customName);
        inventory.setItem(41, invisible);
        inventory.setItem(42, small);

        inventory.setItem(35, setCustomName);

        // set on/off items
        ArmorStand as = armorStands.get(p);

        if (as.hasGravity()) {
            inventory.setItem(29, on);
        }else {
            inventory.setItem(29, off);
        }

        if (as.isInvulnerable()) {
            inventory.setItem(30, on);
        }else {
            inventory.setItem(30, off);
        }

        if (as.hasBasePlate()) {
            inventory.setItem(31, on);
        }else {
            inventory.setItem(31, off);
        }

        if (as.hasEquipmentLock(EquipmentSlot.BODY, ArmorStand.LockType.REMOVING_OR_CHANGING)) {
            inventory.setItem(32, on);
        }else {
            inventory.setItem(32, off);
        }

        if (as.hasArms()) {
            inventory.setItem(33, on);
        }else {
            inventory.setItem(33, off);
        }

        if (as.isMarker()) {
            inventory.setItem(47, on);
        }else {
            inventory.setItem(47, off);
        }

        if (as.isGlowing()) {
            inventory.setItem(48, on);
        }else {
            inventory.setItem(48, off);
        }

        if (as.isCustomNameVisible()) {
            inventory.setItem(49, on);
        }else {
            inventory.setItem(49, off);
        }

        if (as.isInvisible()) {
            inventory.setItem(50, on);
        }else {
            inventory.setItem(50, off);
        }

        if (as.isSmall()) {
            inventory.setItem(51, on);
        }else {
            inventory.setItem(51, off);
        }

    }

    // open tab position
    public void getCreatePositon(Player p, Inventory inventory, int selectedSlot) {

        // remove enchantment effect from items
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 4; j++){
                ItemStack tab = inventory.getItem(i + 9*j + 18);
                assert tab != null;
                ItemMeta tabMeta = tab.getItemMeta();
                assert tabMeta != null;
                tabMeta.setEnchantmentGlintOverride(false);
                tabMeta.setLore(List.of(ChatColor.DARK_PURPLE + "Click to select"));
                tab.setItemMeta(tabMeta);
                inventory.setItem(i + 9*j + 18, tab);
            }
        }

        // set enchantment effect to selected item
        ItemStack item = inventory.getItem(selectedSlot);
        assert item != null;
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setEnchantmentGlintOverride(true);
        itemMeta.setLore(List.of(ChatColor.GREEN + "Selected"));
        item.setItemMeta(itemMeta);
        inventory.setItem(selectedSlot, item);

        // create x,y,z +- and reset items
        ItemStack xPlus = getItem(Material.GREEN_CONCRETE, ChatColor.GREEN + "X+",
                List.of(ChatColor.GOLD + "Left Click to increase by 1", ChatColor.GOLD + "Right click to increase by 10"));
        ItemStack xMinus = getItem(Material.RED_CONCRETE, ChatColor.RED + "X-",
                List.of(ChatColor.GOLD + "Left Click to decrease by 1", ChatColor.GOLD + "Right click to decrease by 10"));
        ItemStack yPlus = getItem(Material.GREEN_CONCRETE, ChatColor.GREEN + "Y+",
                List.of(ChatColor.GOLD + "Left Click to increase by 1", ChatColor.GOLD + "Right click to increase by 10"));
        ItemStack yMinus = getItem(Material.RED_CONCRETE, ChatColor.RED + "Y-",
                List.of(ChatColor.GOLD + "Left Click to decrease by 1", ChatColor.GOLD + "Right click to decrease by 10"));
        ItemStack zPlus = getItem(Material.GREEN_CONCRETE, ChatColor.GREEN + "Z+",
                List.of(ChatColor.GOLD + "Left Click to increase by 1", ChatColor.GOLD + "Right click to increase by 10"));
        ItemStack zMinus = getItem(Material.RED_CONCRETE, ChatColor.RED + "Z-",
                List.of(ChatColor.GOLD + "Left Click to decrease by 1", ChatColor.GOLD + "Right click to decrease by 10"));
        ItemStack resetPosition = getItem(Material.COMPARATOR, ChatColor.YELLOW + "Reset",
                List.of(ChatColor.GOLD + "Reset the rotation of the currently", ChatColor.GOLD + "selected part"));
        ItemStack teleport = getItem(Material.ENDER_PEARL, ChatColor.YELLOW + "Teleport",
                List.of(ChatColor.GOLD + "Teleports the armor stand to your", ChatColor.GOLD + "current location"));

        // set items
        if (selectedSlot == 21){
            ItemStack filler = getFiller();
            for (int i = 5; i < 9; i++) {
                for (int j = 0; j < 4; j++){
                    inventory.setItem(i + 9*j + 18, filler);
                }
            }

            inventory.setItem(24, xPlus);
            inventory.setItem(34, resetPosition);
            inventory.setItem(42, xMinus);
        }else {
            inventory.setItem(23, xPlus);
            inventory.setItem(24, yPlus);
            inventory.setItem(25, zPlus);
            if (selectedSlot != 19) {
                inventory.setItem(35, resetPosition);
            }else {
                inventory.setItem(35, teleport);
            }
            inventory.setItem(41, xMinus);
            inventory.setItem(42, yMinus);
            inventory.setItem(43, zMinus);
        }
        getCurrentRotation(p, inventory, selectedSlot);
    }

    public void openCreatePosition(Player p, Inventory inventory) {

        setSelectedTab(inventory, 3);

        // create selectable items
        ItemStack position = getItem(Material.RECOVERY_COMPASS, ChatColor.YELLOW + "Position",
                List.of());
        ItemStack head = getItem(Material.LEATHER_HELMET, ChatColor.YELLOW + "Head",
                List.of());
        ItemStack rotation = getItem(Material.ARMOR_STAND, ChatColor.YELLOW + "Rotation",
                List.of());
        ItemStack leftArm = getItem(Material.SHIELD, ChatColor.YELLOW + "Left Arm",
                List.of());
        ItemStack body = getItem(Material.LEATHER_CHESTPLATE, ChatColor.YELLOW + "Body",
                List.of());
        ItemStack rightArm = getItem(Material.WOODEN_SWORD, ChatColor.YELLOW + "Right Arm",
                List.of());
        ItemStack leftLeg = getItem(Material.LEATHER_BOOTS, ChatColor.YELLOW + "Left Leg",
                List.of());
        ItemStack rightLeg = getItem(Material.LEATHER_BOOTS, ChatColor.YELLOW + "Right Leg",
                List.of());

        inventory.setItem(19, position);
        inventory.setItem(20, head);
        inventory.setItem(21, rotation);
        inventory.setItem(28, leftArm);
        inventory.setItem(29, body);
        inventory.setItem(30, rightArm);
        inventory.setItem(37, leftLeg);
        inventory.setItem(39, rightLeg);

        getCreatePositon(p, inventory, 19);
    }

    // open tab main hand
    public void updateCreateHand(Player p, Inventory inventory) {
        for (int i = 1; i<8; i++) {
            for (int j = 2; j<5; j++){
                ItemStack item = inventory.getItem(i + j*9);
                assert item != null;
                ItemMeta itemMeta = item.getItemMeta();
                assert itemMeta != null;
                itemMeta.setLore(List.of(ChatColor.DARK_PURPLE + "Click to select"));
                itemMeta.setEnchantmentGlintOverride(false);
                item.setItemMeta(itemMeta);
                inventory.setItem(i + j*9, item);
            }
        }
        ItemStack item = inventory.getItem(49);
        assert item != null;
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setLore(List.of(ChatColor.DARK_PURPLE + "Click to select"));
        itemMeta.setEnchantmentGlintOverride(false);
        item.setItemMeta(itemMeta);
        inventory.setItem(49, item);

        Material current;
        boolean isMainHand = Objects.requireNonNull(Objects.requireNonNull(inventory.getItem(4)).getItemMeta()).getEnchantmentGlintOverride();

        if (isMainHand) {
            current = Objects.requireNonNull(armorStands.get(p).getEquipment()).getItemInMainHand().getType();
        }else {
            current = Objects.requireNonNull(armorStands.get(p).getEquipment()).getItemInOffHand().getType();
        }

        if (Objects.requireNonNull(inventory.getItem(46)).getType() != Material.ARROW) {
            switch (current) {
                case AIR: setHandEnchantmentOverride(inventory, 49); break;
                case WOODEN_SWORD: setHandEnchantmentOverride(inventory, 19); break;
                case STONE_SWORD: setHandEnchantmentOverride(inventory, 20); break;
                case IRON_SWORD: setHandEnchantmentOverride(inventory, 21); break;
                case GOLDEN_SWORD: setHandEnchantmentOverride(inventory, 22); break;
                case DIAMOND_SWORD: setHandEnchantmentOverride(inventory, 23); break;
                case NETHERITE_SWORD: setHandEnchantmentOverride(inventory, 24); break;
                case WOODEN_AXE: setHandEnchantmentOverride(inventory, 28); break;
                case STONE_AXE: setHandEnchantmentOverride(inventory, 29); break;
                case IRON_AXE: setHandEnchantmentOverride(inventory, 30); break;
                case GOLDEN_AXE: setHandEnchantmentOverride(inventory, 31); break;
                case DIAMOND_AXE: setHandEnchantmentOverride(inventory, 32); break;
                case NETHERITE_AXE: setHandEnchantmentOverride(inventory, 33); break;
                case WOODEN_PICKAXE: setHandEnchantmentOverride(inventory, 37); break;
                case STONE_PICKAXE: setHandEnchantmentOverride(inventory, 38); break;
                case IRON_PICKAXE: setHandEnchantmentOverride(inventory, 39); break;
                case GOLDEN_PICKAXE: setHandEnchantmentOverride(inventory, 40); break;
                case DIAMOND_PICKAXE: setHandEnchantmentOverride(inventory, 41); break;
                case NETHERITE_PICKAXE: setHandEnchantmentOverride(inventory, 42); break;
                case SHIELD: setHandEnchantmentOverride(inventory, 25); break;
                case BOW: setHandEnchantmentOverride(inventory, 34); break;
                case CROSSBOW: setHandEnchantmentOverride(inventory, 43); break;
            }
        }else {
            switch (current) {
                case AIR: setHandEnchantmentOverride(inventory, 49); break;
                case WOODEN_SHOVEL: setHandEnchantmentOverride(inventory, 19); break;
                case STONE_SHOVEL: setHandEnchantmentOverride(inventory, 20); break;
                case IRON_SHOVEL: setHandEnchantmentOverride(inventory, 21); break;
                case GOLDEN_SHOVEL: setHandEnchantmentOverride(inventory, 22); break;
                case DIAMOND_SHOVEL: setHandEnchantmentOverride(inventory, 23); break;
                case NETHERITE_SHOVEL: setHandEnchantmentOverride(inventory, 24); break;
                case WOODEN_HOE: setHandEnchantmentOverride(inventory, 28); break;
                case STONE_HOE: setHandEnchantmentOverride(inventory, 29); break;
                case IRON_HOE: setHandEnchantmentOverride(inventory, 30); break;
                case GOLDEN_HOE: setHandEnchantmentOverride(inventory, 31); break;
                case DIAMOND_HOE: setHandEnchantmentOverride(inventory, 32); break;
                case NETHERITE_HOE: setHandEnchantmentOverride(inventory, 33); break;
                case FISHING_ROD: setHandEnchantmentOverride(inventory, 37); break;
                case FLINT_AND_STEEL: setHandEnchantmentOverride(inventory, 38); break;
                case SHEARS: setHandEnchantmentOverride(inventory, 39); break;
                case SPYGLASS: setHandEnchantmentOverride(inventory, 40); break;
                case BRUSH: setHandEnchantmentOverride(inventory, 41); break;
                case COMPASS: setHandEnchantmentOverride(inventory, 42); break;
                case TRIDENT: setHandEnchantmentOverride(inventory, 25); break;
                case MACE: setHandEnchantmentOverride(inventory, 34); break;
                case CLOCK: setHandEnchantmentOverride(inventory, 43); break;
            }
        }

    }

    public void openCreateHandPage(Inventory inventory, int page) {


        if (page == 1) {
            // set page 1 items
            ItemStack woodenSword = getItem(Material.WOODEN_SWORD, ChatColor.YELLOW + "Wooden Sword", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack stoneSword = getItem(Material.STONE_SWORD, ChatColor.YELLOW + "Stone Sword", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack ironSword = getItem(Material.IRON_SWORD, ChatColor.YELLOW + "Iron Sword", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack goldSword = getItem(Material.GOLDEN_SWORD, ChatColor.YELLOW + "Golden Sword", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack diamondSword = getItem(Material.DIAMOND_SWORD, ChatColor.YELLOW + "Diamond Sword", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack netheriteSword = getItem(Material.NETHERITE_SWORD, ChatColor.YELLOW + "Netherite Sword", List.of(ChatColor.DARK_PURPLE + "Click to select"));

            ItemStack woodenAxe = getItem(Material.WOODEN_AXE, ChatColor.YELLOW + "Wooden Axe", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack stoneAxe = getItem(Material.STONE_AXE, ChatColor.YELLOW + "Stone Axe", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack ironAxe = getItem(Material.IRON_AXE, ChatColor.YELLOW + "Iron Axe", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack goldAxe = getItem(Material.GOLDEN_AXE, ChatColor.YELLOW + "Golden Axe", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack diamondAxe = getItem(Material.DIAMOND_AXE, ChatColor.YELLOW + "Diamond Axe", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack netheriteAxe = getItem(Material.NETHERITE_AXE, ChatColor.YELLOW + "Netherite Axe", List.of(ChatColor.DARK_PURPLE + "Click to select"));

            ItemStack woodenPickaxe = getItem(Material.WOODEN_PICKAXE, ChatColor.YELLOW + "Wooden Pickaxe", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack stonePickaxe = getItem(Material.STONE_PICKAXE, ChatColor.YELLOW + "Stone Pickaxe", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack ironPickaxe = getItem(Material.IRON_PICKAXE, ChatColor.YELLOW + "Iron Pickaxe", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack goldPickaxe = getItem(Material.GOLDEN_PICKAXE, ChatColor.YELLOW + "Golden Pickaxe", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack diamondPickaxe = getItem(Material.DIAMOND_PICKAXE, ChatColor.YELLOW + "Diamond Pickaxe", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack netheritePickaxe = getItem(Material.NETHERITE_PICKAXE, ChatColor.YELLOW + "Netherite Pickaxe", List.of(ChatColor.DARK_PURPLE + "Click to select"));

            ItemStack shield = getItem(Material.SHIELD, ChatColor.YELLOW + "Shield", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack bow = getItem(Material.BOW, ChatColor.YELLOW + "Bow", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack crossbow = getItem(Material.CROSSBOW, ChatColor.YELLOW + "Crossbow", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack nextPage = getItem(Material.ARROW, ChatColor.YELLOW + "Next Page", List.of(ChatColor.DARK_PURPLE + "Click to go to next page"));

            inventory.setItem(19, woodenSword);
            inventory.setItem(20, stoneSword);
            inventory.setItem(21, ironSword);
            inventory.setItem(22, goldSword);
            inventory.setItem(23, diamondSword);
            inventory.setItem(24, netheriteSword);

            inventory.setItem(28, woodenAxe);
            inventory.setItem(29, stoneAxe);
            inventory.setItem(30, ironAxe);
            inventory.setItem(31, goldAxe);
            inventory.setItem(32, diamondAxe);
            inventory.setItem(33, netheriteAxe);

            inventory.setItem(37, woodenPickaxe);
            inventory.setItem(38, stonePickaxe);
            inventory.setItem(39, ironPickaxe);
            inventory.setItem(40, goldPickaxe);
            inventory.setItem(41, diamondPickaxe);
            inventory.setItem(42, netheritePickaxe);

            inventory.setItem(25, shield);
            inventory.setItem(34, bow);
            inventory.setItem(43, crossbow);
            inventory.setItem(52, nextPage);
            inventory.setItem(46, getFiller());

        } else {
            // set page 2 items
            ItemStack woodenShovel = getItem(Material.WOODEN_SHOVEL, ChatColor.YELLOW + "Wooden Shovel", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack stoneShovel = getItem(Material.STONE_SHOVEL, ChatColor.YELLOW + "Stone Shovel", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack ironShovel = getItem(Material.IRON_SHOVEL, ChatColor.YELLOW + "Iron Shovel", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack goldShovel = getItem(Material.GOLDEN_SHOVEL, ChatColor.YELLOW + "Golden Shovel", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack diamondShovel = getItem(Material.DIAMOND_SHOVEL, ChatColor.YELLOW + "Diamond Shovel", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack netheriteShovel = getItem(Material.NETHERITE_SHOVEL, ChatColor.YELLOW + "Netherite Shovel", List.of(ChatColor.DARK_PURPLE + "Click to select"));

            ItemStack woodenHoe = getItem(Material.WOODEN_HOE, ChatColor.YELLOW + "Wooden Hoe", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack stoneHoe = getItem(Material.STONE_HOE, ChatColor.YELLOW + "Stone Hoe", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack ironHoe = getItem(Material.IRON_HOE, ChatColor.YELLOW + "Iron Hoe", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack goldHoe = getItem(Material.GOLDEN_HOE, ChatColor.YELLOW + "Golden Hoe", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack diamondHoe = getItem(Material.DIAMOND_HOE, ChatColor.YELLOW + "Diamond Hoe", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack netheriteHoe = getItem(Material.NETHERITE_HOE, ChatColor.YELLOW + "Netherite Hoe", List.of(ChatColor.DARK_PURPLE + "Click to select"));

            ItemStack fishingRod = getItem(Material.FISHING_ROD, ChatColor.YELLOW + "Fishing Rod", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack flintAndSteel = getItem(Material.FLINT_AND_STEEL, ChatColor.YELLOW + "Flint And Steel", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack shears = getItem(Material.SHEARS, ChatColor.YELLOW + "Shears", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack spyglass = getItem(Material.SPYGLASS, ChatColor.YELLOW + "Spyglass", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack brush = getItem(Material.BRUSH, ChatColor.YELLOW + "Brush", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack compass = getItem(Material.COMPASS, ChatColor.YELLOW + "Compass", List.of(ChatColor.DARK_PURPLE + "Click to select"));

            ItemStack trident = getItem(Material.TRIDENT, ChatColor.YELLOW + "Trident", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack mace = getItem(Material.MACE, ChatColor.YELLOW + "Mace", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack clock = getItem(Material.CLOCK, ChatColor.YELLOW + "Clock", List.of(ChatColor.DARK_PURPLE + "Click to select"));
            ItemStack prevPage = getItem(Material.ARROW, ChatColor.YELLOW + "Previous Page", List.of(ChatColor.DARK_PURPLE + "Click to go to previous page"));

            inventory.setItem(19, woodenShovel);
            inventory.setItem(20, stoneShovel);
            inventory.setItem(21, ironShovel);
            inventory.setItem(22, goldShovel);
            inventory.setItem(23, diamondShovel);
            inventory.setItem(24, netheriteShovel);

            inventory.setItem(28, woodenHoe);
            inventory.setItem(29, stoneHoe);
            inventory.setItem(30, ironHoe);
            inventory.setItem(31, goldHoe);
            inventory.setItem(32, diamondHoe);
            inventory.setItem(33, netheriteHoe);

            inventory.setItem(37, fishingRod);
            inventory.setItem(38, flintAndSteel);
            inventory.setItem(39, shears);
            inventory.setItem(40, spyglass);
            inventory.setItem(41, brush);
            inventory.setItem(42, compass);

            inventory.setItem(25, trident);
            inventory.setItem(34, mace);
            inventory.setItem(43, clock);
            inventory.setItem(46, prevPage);
            inventory.setItem(52, getFiller());
        }

        ItemStack otherItem = getItem(Material.KNOWLEDGE_BOOK, ChatColor.YELLOW + "Use Other Item",
                List.of(ChatColor.GOLD + "Use " + ChatColor.DARK_PURPLE + "/asg mainhand | offhand" + ChatColor.GOLD + " to set the" , ChatColor.GOLD + "held item to your currently held item"));
        ItemStack noItem = getItem(Material.STRUCTURE_VOID, ChatColor.YELLOW + "No Item", List.of(ChatColor.DARK_PURPLE + "Click to select"));


        inventory.setItem(49, noItem);
        inventory.setItem(35, otherItem);
    }

    public void openCreateMainHand(Player p, Inventory inventory) {

        setSelectedTab(inventory, 4);

        openCreateHandPage(inventory, 1);

        updateCreateHand(p, inventory);
    }

    // open tab off hand
    public void openCreateOffHand(Player p, Inventory inventory) {

        setSelectedTab(inventory, 5);

        openCreateHandPage(inventory, 1);

        updateCreateHand(p, inventory);
    }

    // update tab armor
    public void updateCreateArmor(Player p, Inventory inventory) {
        ArmorStand as = armorStands.get(p);
        Material helmet = Objects.requireNonNull(Objects.requireNonNull(as.getEquipment()).getHelmet()).getType();
        Material chestplate = Objects.requireNonNull(as.getEquipment().getChestplate()).getType();
        Material leggings = Objects.requireNonNull(as.getEquipment().getLeggings()).getType();
        Material boots = Objects.requireNonNull(as.getEquipment().getBoots()).getType();

        switch (helmet){
            case Material.LEATHER_HELMET:
                setArmorEnchantmentOverride(inventory, 2, 2);
                break;
            case Material.CHAINMAIL_HELMET:
                setArmorEnchantmentOverride(inventory, 2, 3);
                break;
            case Material.GOLDEN_HELMET:
                setArmorEnchantmentOverride(inventory, 2, 4);
                break;
            case Material.IRON_HELMET:
                setArmorEnchantmentOverride(inventory, 2, 5);
                break;
            case Material.DIAMOND_HELMET:
                setArmorEnchantmentOverride(inventory, 2, 6);
                break;
            case Material.NETHERITE_HELMET:
                setArmorEnchantmentOverride(inventory, 2, 7);
                break;
            default:
                setArmorEnchantmentOverride(inventory, 2, 1);
                break;
        }

        switch (chestplate){
            case Material.LEATHER_CHESTPLATE:
                setArmorEnchantmentOverride(inventory, 3, 2);
                break;
            case Material.CHAINMAIL_CHESTPLATE:
                setArmorEnchantmentOverride(inventory, 3, 3);
                break;
            case Material.GOLDEN_CHESTPLATE:
                setArmorEnchantmentOverride(inventory, 3, 4);
                break;
            case Material.IRON_CHESTPLATE:
                setArmorEnchantmentOverride(inventory, 3, 5);
                break;
            case Material.DIAMOND_CHESTPLATE:
                setArmorEnchantmentOverride(inventory, 3, 6);
                break;
            case Material.NETHERITE_CHESTPLATE:
                setArmorEnchantmentOverride(inventory, 3, 7);
                break;
            default:
                setArmorEnchantmentOverride(inventory, 3, 1);
                break;
        }

        switch (leggings){
            case Material.LEATHER_LEGGINGS:
                setArmorEnchantmentOverride(inventory, 4, 2);
                break;
            case Material.CHAINMAIL_LEGGINGS:
                setArmorEnchantmentOverride(inventory, 4, 3);
                break;
            case Material.GOLDEN_LEGGINGS:
                setArmorEnchantmentOverride(inventory, 4, 4);
                break;
            case Material.IRON_LEGGINGS:
                setArmorEnchantmentOverride(inventory, 4, 5);
                break;
            case Material.DIAMOND_LEGGINGS:
                setArmorEnchantmentOverride(inventory, 4, 6);
                break;
            case Material.NETHERITE_LEGGINGS:
                setArmorEnchantmentOverride(inventory, 4, 7);
                break;
            default:
                setArmorEnchantmentOverride(inventory, 4, 1);
                break;
        }

        switch (boots){
            case Material.LEATHER_BOOTS:
                setArmorEnchantmentOverride(inventory, 5, 2);
                break;
            case Material.CHAINMAIL_BOOTS:
                setArmorEnchantmentOverride(inventory, 5, 3);
                break;
            case Material.GOLDEN_BOOTS:
                setArmorEnchantmentOverride(inventory, 5, 4);
                break;
            case Material.IRON_BOOTS:
                setArmorEnchantmentOverride(inventory, 5, 5);
                break;
            case Material.DIAMOND_BOOTS:
                setArmorEnchantmentOverride(inventory, 5, 6);
                break;
            case Material.NETHERITE_BOOTS:
                setArmorEnchantmentOverride(inventory, 5, 7);
                break;
            default:
                setArmorEnchantmentOverride(inventory, 5, 1);
                break;
        }

    }

    // open tab armo
    public void openCreateArmor(Player p, Inventory inventory) {

        setSelectedTab(inventory, 6);

        // create armor items
        ItemStack noHelmet = getItem(Material.STRUCTURE_VOID, ChatColor.YELLOW + "No Helmet", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack noChestplate = getItem(Material.STRUCTURE_VOID, ChatColor.YELLOW + "No Chestplate", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack noLeggings = getItem(Material.STRUCTURE_VOID, ChatColor.YELLOW + "No Leggings", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack noBoots = getItem(Material.STRUCTURE_VOID, ChatColor.YELLOW + "No Boots", List.of(ChatColor.DARK_PURPLE + "Click to select"));

        ItemStack leatherHelmet = getItem(Material.LEATHER_HELMET, ChatColor.YELLOW + "Leather Helmet", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack leatherChestplate = getItem(Material.LEATHER_CHESTPLATE, ChatColor.YELLOW + "Leather Chestplate", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack leatherLeggings = getItem(Material.LEATHER_LEGGINGS, ChatColor.YELLOW + "Leather Leggings", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack leatherBoots = getItem(Material.LEATHER_BOOTS, ChatColor.YELLOW + "Leather Boots", List.of(ChatColor.DARK_PURPLE + "Click to select"));

        ItemStack chainHelmet = getItem(Material.CHAINMAIL_HELMET, ChatColor.YELLOW + "Chainmail Helmet", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack chainChestplate = getItem(Material.CHAINMAIL_CHESTPLATE, ChatColor.YELLOW + "Chainmail Chestplate", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack chainLeggings = getItem(Material.CHAINMAIL_LEGGINGS, ChatColor.YELLOW + "Chainmail Leggings", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack chainBoots = getItem(Material.CHAINMAIL_BOOTS, ChatColor.YELLOW + "Chainmail Boots", List.of(ChatColor.DARK_PURPLE + "Click to select"));

        ItemStack goldHelmet = getItem(Material.GOLDEN_HELMET, ChatColor.YELLOW + "Golden Helmet", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack goldChestplate = getItem(Material.GOLDEN_CHESTPLATE, ChatColor.YELLOW + "Golden Chestplate", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack goldLeggings = getItem(Material.GOLDEN_LEGGINGS, ChatColor.YELLOW + "Golden Leggings", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack goldBoots = getItem(Material.GOLDEN_BOOTS, ChatColor.YELLOW + "Golden Boots", List.of(ChatColor.DARK_PURPLE + "Click to select"));

        ItemStack ironHelmet = getItem(Material.IRON_HELMET, ChatColor.YELLOW + "Iron Helmet", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack ironChestplate = getItem(Material.IRON_CHESTPLATE, ChatColor.YELLOW + "Iron Chestplate", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack ironLeggings = getItem(Material.IRON_LEGGINGS, ChatColor.YELLOW + "Iron Leggings", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack ironBoots = getItem(Material.IRON_BOOTS, ChatColor.YELLOW + "Iron Boots", List.of(ChatColor.DARK_PURPLE + "Click to select"));

        ItemStack diamondHelmet = getItem(Material.DIAMOND_HELMET, ChatColor.YELLOW + "Diamond Helmet", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack diamondChestplate = getItem(Material.DIAMOND_CHESTPLATE, ChatColor.YELLOW + "Diamond Chestplate", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack diamondLeggings = getItem(Material.DIAMOND_LEGGINGS, ChatColor.YELLOW + "Diamond Leggings", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack diamondBoots = getItem(Material.DIAMOND_BOOTS, ChatColor.YELLOW + "Diamond Boots", List.of(ChatColor.DARK_PURPLE + "Click to select"));

        ItemStack netheriteHelmet = getItem(Material.NETHERITE_HELMET, ChatColor.YELLOW + "Netherite Helmet", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack netheriteChestplate = getItem(Material.NETHERITE_CHESTPLATE, ChatColor.YELLOW + "Netherite Chestplate", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack netheriteLeggings = getItem(Material.NETHERITE_LEGGINGS, ChatColor.YELLOW + "Netherite Leggings", List.of(ChatColor.DARK_PURPLE + "Click to select"));
        ItemStack netheriteBoots = getItem(Material.NETHERITE_BOOTS, ChatColor.YELLOW + "Netherite Boots", List.of(ChatColor.DARK_PURPLE + "Click to select"));

        ItemStack otherItem = getItem(Material.KNOWLEDGE_BOOK, ChatColor.YELLOW + "Use Other Item",
                List.of(ChatColor.GOLD + "Use " + ChatColor.DARK_PURPLE + "/asg head | chest | legs | feet" + ChatColor.GOLD + " to set the" , ChatColor.GOLD + "worn item to your currently held item"));

        inventory.setItem(19, noHelmet);
        inventory.setItem(20, leatherHelmet);
        inventory.setItem(21, chainHelmet);
        inventory.setItem(22, goldHelmet);
        inventory.setItem(23, ironHelmet);
        inventory.setItem(24, diamondHelmet);
        inventory.setItem(25, netheriteHelmet);

        inventory.setItem(28, noChestplate);
        inventory.setItem(29, leatherChestplate);
        inventory.setItem(30, chainChestplate);
        inventory.setItem(31, goldChestplate);
        inventory.setItem(32, ironChestplate);
        inventory.setItem(33, diamondChestplate);
        inventory.setItem(34, netheriteChestplate);

        inventory.setItem(37, noLeggings);
        inventory.setItem(38, leatherLeggings);
        inventory.setItem(39, chainLeggings);
        inventory.setItem(40, goldLeggings);
        inventory.setItem(41, ironLeggings);
        inventory.setItem(42, diamondLeggings);
        inventory.setItem(43, netheriteLeggings);

        inventory.setItem(46, noBoots);
        inventory.setItem(47, leatherBoots);
        inventory.setItem(48, chainBoots);
        inventory.setItem(49, goldBoots);
        inventory.setItem(50, ironBoots);
        inventory.setItem(51, diamondBoots);
        inventory.setItem(52, netheriteBoots);

        inventory.setItem(35, otherItem);

        updateCreateArmor(p, inventory);

    }

    // create and open main menu
    public void openASMenu(Player p) {

        Inventory asmenu = Bukkit.createInventory(p, 27, ChatColor.DARK_BLUE + "Armor Stand Menu");

        // create items
        ItemStack create = getItem(Material.ARMOR_STAND, ChatColor.GREEN + "Create",
                List.of(ChatColor.GOLD + "Create a new armor stand"));

        ItemStack resume = getItem(Material.SMITHING_TABLE, ChatColor.YELLOW + "Resume",
                List.of(ChatColor.GOLD + "Resume editing last armor stand"));

        ItemStack close = getItem(Material.BARRIER, ChatColor.RED + "Close",
                List.of(ChatColor.GOLD + "Close armor stand menu"));

        // fill slots with filler
        ItemStack filler = getFiller();
        for (int i = 0; i < 27; i++) {
            asmenu.setItem(i, filler);
        }

        // set items and open inventory
        asmenu.setItem(11, create);
        asmenu.setItem(13, resume);
        asmenu.setItem(15, close);
        p.openInventory(asmenu);
    }

    // create and open armor stand create menu
    public void openCreateMenu(Player p) {

        Inventory createMenu = Bukkit.createInventory(p, 54, ChatColor.DARK_BLUE + "Edit Armor Stand");

        // create menu items
        ItemStack complete = getItem(Material.GREEN_CONCRETE, ChatColor.GREEN + "Complete",
                List.of(ChatColor.GOLD + "Finish editing and spawn armor stand"));

        ItemStack settings = getItem(Material.REPEATER, ChatColor.YELLOW + "Settings",
                List.of(ChatColor.GOLD + "Change the general settings of the", ChatColor.GOLD + "armor stand"));

        ItemStack position = getItem(Material.COMPASS, ChatColor.YELLOW + "Position And Rotation",
                List.of(ChatColor.GOLD + "Change the position and rotation of", ChatColor.GOLD + "the armor stand and it's parts"));

        ItemStack mainHand = getItem(Material.NETHERITE_SWORD, ChatColor.YELLOW + "Main Hand",
                List.of(ChatColor.GOLD + "Change the item held in the armor", ChatColor.GOLD + "stands main hand"));

        ItemStack offHand = getItem(Material.SHIELD, ChatColor.YELLOW + "Off Hand",
                List.of(ChatColor.GOLD + "Change the item held in the armor", ChatColor.GOLD + "stands off hand"));

        ItemStack armor = getItem(Material.NETHERITE_CHESTPLATE, ChatColor.YELLOW + "Armor",
                List.of(ChatColor.GOLD + "Change the armor worn by the armor stand"));

        ItemStack delete = getItem(Material.BARRIER, ChatColor.RED + "Delete",
                List.of(ChatColor.GOLD + "Delete this armor stand"));

        // fill slots with filler
        ItemStack filler = getFiller();

        for (int i = 0; i < 54; i++) {
            createMenu.setItem(i, filler);
        }

        // replace with correct items
        createMenu.setItem(0, complete);
        createMenu.setItem(2, settings);
        createMenu.setItem(3, position);
        createMenu.setItem(4, mainHand);
        createMenu.setItem(5, offHand);
        createMenu.setItem(6, armor);
        createMenu.setItem(8, delete);

        openCreateSettings(p, createMenu);
        p.openInventory(createMenu);
    }

    public void openChoiceMenu(Player p, Inventory menu, ItemStack choice) {

        // Create yes and no item
        ItemStack yes = getItem(Material.GREEN_CONCRETE, ChatColor.GREEN + "YES", List.of());
        ItemStack no = getItem(Material.RED_CONCRETE, ChatColor.RED + "NO", List.of());

        // set items
        ItemStack filler = getFiller();
        for (int i = 0; i < 36; i++){
            menu.setItem(i, filler);
        }
        menu.setItem(13, choice);
        menu.setItem(21, yes);
        menu.setItem(23, no);

        p.openInventory(menu);
    }

    public void openDeleteMenu(Player p, String choice) {

        Inventory deleteMenu = Bukkit.createInventory(p, 36, ChatColor.DARK_BLUE + "Confirm Deletion");

        // Create center item based on choice to make
        ItemStack c = new ItemStack(Material.BARRIER);
        c = switch (choice) {
            case "duplicate" -> getItem(Material.ARMOR_STAND, ChatColor.RED + "You already have an active armor stand!",
                    List.of(ChatColor.GOLD + "Do you want to delete your last edited", ChatColor.GOLD + "Armor stand and create a new one?"));
            case "delete" -> getItem(Material.ARMOR_STAND, ChatColor.RED + "Delete",
                    List.of(ChatColor.GOLD + "Are you sure you want to delete your", ChatColor.GOLD + "current armor stand?"));
            default -> c;
        };

        openChoiceMenu(p, deleteMenu, c);
    }

    public void openCompleteMenu(Player p) {

        Inventory completeMenu = Bukkit.createInventory(p, 36, ChatColor.DARK_BLUE + "Confirm Completion");

        ItemStack c = getItem(Material.ARMOR_STAND, ChatColor.GREEN + "Complete",
                List.of(ChatColor.GOLD + "Are you sure you want to complete this", ChatColor.GOLD + "armor stand?",
                ChatColor.RED + "You will NOT be able to edit this armor", ChatColor.RED + "stand again!"));

        openChoiceMenu(p, completeMenu, c);
    }
}
