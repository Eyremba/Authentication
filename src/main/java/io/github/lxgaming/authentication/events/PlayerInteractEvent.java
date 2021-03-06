/*
 * Copyright 2017 Alex Thomson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.lxgaming.authentication.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import io.github.lxgaming.authentication.Authentication;
import net.md_5.bungee.api.ChatColor;

public class PlayerInteractEvent implements Listener {
	
	@EventHandler
	public void onPlayerInteract(org.bukkit.event.player.PlayerInteractEvent PI) {
		Player PIP = PI.getPlayer();
		if (!Authentication.config.getBoolean("Authentication.Events.Interact") == true) {
			if (!Authentication.instance.hasPlayerAccepted(PIP)) {
				PI.setCancelled(true);
				if (Authentication.config.getBoolean("Authentication.Events.RulesMessage") == true) {
					PIP.performCommand("serverrules");
				}
				if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage") == true) {
					PIP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
				} else {
					if (Authentication.config.getBoolean("Authentication.Events.EventMessage") == true) {
						PIP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.Interact")));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteractEntity(org.bukkit.event.player.PlayerInteractEntityEvent PIE) {
		Player PIEP = PIE.getPlayer();
		if (!Authentication.config.getBoolean("Authentication.Events.InteractEntities") == true) {
			if (!Authentication.instance.hasPlayerAccepted(PIEP)) {
				PIE.setCancelled(true);
				if (Authentication.config.getBoolean("Authentication.Events.RulesMessage") == true) {
					PIEP.performCommand("serverrules");
				}
				if (Authentication.config.getBoolean("Authentication.Events.OverrideEventMessage") == true) {
					PIEP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.OverrideEventMessage")));
				} else {
					if (Authentication.config.getBoolean("Authentication.Events.EventMessage") == true) {
						PIEP.sendMessage(ChatColor.translateAlternateColorCodes('&', Authentication.messages.getString("Authentication.Events.InteractEntities")));
					}
				}
			}
		}
	}
}
