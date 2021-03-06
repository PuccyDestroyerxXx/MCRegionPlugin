package com.lupus.commands.sub;


import com.lupus.command.framework.commands.PlayerCommand;
import com.lupus.managers.CacheManager;
import com.lupus.managers.RegionManager;
import com.lupus.messages.GeneralMessages;
import com.lupus.messages.MessageReplaceQuery;
import com.lupus.region.Region;
import com.lupus.command.framework.commands.arguments.ArgumentList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class ThrowOutCMD extends PlayerCommand {
	public ThrowOutCMD(){
		super("wyrzuc", usage("/dzialka wyrzuc", "[Gracz]"),"&6Wyrzucasz z działki gracza",1);
	}
	@Override
	@SuppressWarnings("deprecation")
	public void run(Player executor, ArgumentList args) throws Exception {
		if(args.size() < 1){
			executor.sendMessage(usage());
			return;
		}
		Region r = RegionManager.getRegionOfOwner(executor);
		if(r == null){
			executor.sendMessage(GeneralMessages.NO_PLOT.toString());
			return;
		}
		Player p2 = args.getArg(Player.class,0);
		if(p2 == null){
			//Gotta use this because of player ease of use
			OfflinePlayer offlinePlayer2 = args.getArg(OfflinePlayer.class,0);
			if(offlinePlayer2 == null){
				executor.sendMessage(GeneralMessages.NULL_PLAYER.toString());
				return;
			}
			if(executor.getUniqueId().equals(offlinePlayer2.getUniqueId())){
				executor.sendMessage(GeneralMessages.CANT_USE_ON_MYSELF.toString());
				return;
			}
			RegionManager.removePlayerFromRegion(offlinePlayer2.getUniqueId(),r);
		} else {
			if(p2.getUniqueId().equals(executor.getUniqueId())){
				executor.sendMessage(GeneralMessages.CANT_USE_ON_MYSELF.toString());
				return;
			}
			RegionManager.removePlayerFromRegion(p2.getUniqueId(),r);

			MessageReplaceQuery query = new MessageReplaceQuery().addString("player",p2.getName());


			executor.sendMessage(GeneralMessages.SUCCESSFUL_REMOVE.toString(query));
		}
		return;
	}
}
