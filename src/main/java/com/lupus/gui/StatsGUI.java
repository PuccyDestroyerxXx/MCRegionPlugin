package com.lupus.gui;

import com.lupus.region.Region;
import com.lupus.gui.utils.TextUtility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class StatsGUI extends GUI {
	Region region;
	public StatsGUI(Region region) {
		super(TextUtility.color("&b&lStaty"), 9);
		this.region = region;
		ItemStack icon = getIcon();
		for (int i=0;i<4;i++)
			this.addItemStack(new NonSelectableItem(new ItemStack(Material.BLUE_STAINED_GLASS_PANE)));
		this.addItemStack(new NonSelectableItem(icon));
		for (int i=0;i<4;i++)
			this.addItemStack(new NonSelectableItem(new ItemStack(Material.BLUE_STAINED_GLASS_PANE)));
		List<SelectableItem> selectableItems = super.items;
		for (int i = 0; i < 9; i++) {
			ItemStack item = selectableItems.get(i).getItem();
			inv.setItem(i,item);
		}
	}

	public ItemStack getIcon(){
		ItemStack icon = new ItemStack(region.getIcon());
		List<String> message = new ArrayList<>();
		message.add(TextUtility.color("&e----------Działka----------"));
		String regionName = region.getName();
		if(regionName != null){
			message.add(TextUtility.color("&6Nazwa: &b"+regionName));
		}
		String ownerName = region.getOwnerName();
		if(ownerName != null){
			message.add(TextUtility.color("&6Wlasciciel: &b"+ownerName));
		}
		int size = region.getMembers().size();
		message.add(TextUtility.color("&6Ilość członków: &b"+size));
		int regionSquared = region.getArea();
		message.add(TextUtility.color("&6Teren w m2: &b"+regionSquared));
		message.add(TextUtility.color("&6Poziom działki: &b"+region.getLevel()+"/9"));
		ItemMeta meta = icon.getItemMeta();
		if (meta == null)
			return null;
		meta.setLore(message);
		icon.setItemMeta(meta);
		return icon;
	}

	@Override
	public void onClose(Player player) {

	}
}
