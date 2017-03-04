package main.main;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.item.Item;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;


public class Main extends PluginBase implements Listener{

	public Map<String, String> Language;
	public Config c;
	public int useb;


	@Override
	public void onEnable(){
		this.getServer().getPluginManager().registerEvents(this, this);

		this.saveDefaultConfig();
		this.reloadConfig();

		this.getDataFolder().mkdirs();
		this.c = new Config(this.getDataFolder() + "/config.yml", Config.YAML);

		this.useb = this.getConfig().getInt("Cooking_block");

		this.lang();

		this.getLogger().info(this.Language.get("start"));


	}


	@EventHandler
	public void onTap(PlayerInteractEvent event){
		Player player = event.getPlayer();

		int item = player.getInventory().getItemInHand().getId();
		int b = event.getBlock().getId();

		if(b == useb){

		switch(item){

			case 319:
				player.sendMessage(this.Language.get("did"));
				player.getInventory().addItem(Item.get(320, 0, 1));
				player.getInventory().removeItem(Item.get(319, 0, 1));
				break;

			case 349:
				player.sendMessage(this.Language.get("did"));
				player.getInventory().addItem(Item.get(350, 0, 1));
				player.getInventory().removeItem(Item.get(349, 0, 1));
				break;

			case 363:
				player.sendMessage(this.Language.get("did"));
				player.getInventory().addItem(Item.get(364, 0, 1));
				player.getInventory().removeItem(Item.get(363, 0, 1));
				break;

			case 365:
				player.sendMessage(this.Language.get("did"));
				player.getInventory().addItem(Item.get(366, 0, 1));
				player.getInventory().removeItem(Item.get(365, 0, 1));
				break;

			case 392:
				player.sendMessage(this.Language.get("did"));
				player.getInventory().addItem(Item.get(393, 0, 1));
				player.getInventory().removeItem(Item.get(392, 0, 1));
				break;

			case 411:
				player.sendMessage(this.Language.get("did"));
				player.getInventory().addItem(Item.get(412, 0, 1));
				player.getInventory().removeItem(Item.get(411, 0, 1));
				break;

			case 423:
				player.sendMessage(this.Language.get("did"));
				player.getInventory().addItem(Item.get(424, 0, 1));
				player.getInventory().removeItem(Item.get(423, 0, 1));
				break;

		}

	}
}



	public void lang(){
		final int READ_TIMEOUT = 10 * 1000;
		final int CONNECT_TIMEOUT = 10 * 1000;

		String lang = this.getConfig().getString("Use_language");
		try{
			URL url = new URL("https://raw.githubusercontent.com/PMpluginMaker-Team/ToCookQuickly/master/lang/" + lang + ".json");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(CONNECT_TIMEOUT);
			con.setReadTimeout(READ_TIMEOUT);
			con.connect();

			Gson gson = new GsonBuilder().serializeNulls().create();

			final int status = con.getResponseCode();
			if(status == HttpURLConnection.HTTP_OK){
				StringBuilder resultBuilder = new StringBuilder();
				String line = "";

				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

				while ((line = br.readLine()) != null){
					resultBuilder.append(String.format("%s%s", line, "\r\n"));
				}
				this.Language = gson.fromJson(resultBuilder.toString(), new TypeToken<Map<String, String>>(){}.getType());
			}
			con.disconnect();

		}catch (Exception e){
			e.printStackTrace();
			this.getLogger().error("language file not exists.");
			return;
		}
	}
}
