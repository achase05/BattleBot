package com.achase.BattleBot;

import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Author:	achase05
 * Date:	April 26, 2018
 * Project:	BattleBot Discord Bot
 * Desc:	This project is a Discord bot that I am creating to have fun with my friends in Discord.
 * 			It is a text based battle game that allows subscribed users to open chests to obtain random
 * 			items that they can use to battle their friends.   
 *
 */
public class App extends ListenerAdapter
{
	List<String> playerNames = new ArrayList<String>();
	List<Player> players = new ArrayList<Player>(); 
	
    public static void main( String[] args ) throws Exception
    {
        JDA jda = new JDABuilder(AccountType.BOT).setToken(Ref.token).buildBlocking();
        jda.addEventListener(new App());
    }
    
    @Override
    public void onMessageReceived(MessageReceivedEvent evt) {
    	// Objects
    	User objUsr = evt.getAuthor();
    	MessageChannel objMsgCh = evt.getChannel();
    	Message objMsg = evt.getMessage();
    	
    	// TODO: Get message string and search for first instance of "!". Then, collect all chars up to the
    	//		 next whitespace. Use that collection of chars as the command word.
    	
    	// Commands
    	if (objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix+"BattleBegin")) {
    		Player newPlayer = new Player(objUsr, 100);
    		
    		// TODO: Instead of adding users names to a separate list, just add the players to a players list and compare with
    		//		 User.getName() on each player in the list
    		if(playerNames.contains(newPlayer.retrievePlayerName()) == true) {
    			objMsgCh.sendMessage(objUsr.getAsMention() + ", you're already playing!").queue();
    		} else {
    			
    			// Instead of adding player names, just add players
    			playerNames.add(objUsr.getName());
	    		players.add(newPlayer);
	    		objMsgCh.sendMessage("Welcome to the game, " + newPlayer.getPlayer().getAsMention() + "!").queue();
    		}
    		
    	} else if (objMsg.getContentRaw().equalsIgnoreCase(Ref.prefix+"Open") && playerNames.contains(objUsr.getName())) {
    		int playerIndex = playerNames.indexOf(objUsr.getName());
    		players.get(playerIndex).openChest(objMsgCh);
    		
    	// For now, this command MUST be typed as !use (all lower case), but will fix casing issue soon
    	// Also it doesn't take into account any words that come BEFORE !use yet
    	} else if (objMsg.getContentRaw().contains(Ref.prefix+"use") && playerNames.contains(objUsr.getName())) {
    		String msg = objMsg.getContentRaw();
    		String[] words = msg.split("\\s+");
    		int playerIndex = playerNames.indexOf(objUsr.getName());
    		int tgtIndex = playerNames.indexOf(words[2]);
    		players.get(playerIndex).use(words[1], players.get(tgtIndex), objMsgCh);
    	}
    }
}
