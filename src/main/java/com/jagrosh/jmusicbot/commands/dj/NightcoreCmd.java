package com.jagrosh.jmusicbot.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.commands.DJCommand;

public class NightcoreCmd extends DJCommand {
    public NightcoreCmd(Bot bot) {
        super(bot);
        this.name = "nightcore";
        this.help = "toggles nightcore mode";
        this.aliases = bot.getConfig().getAliases(this.name);
    }

    @Override
    public void doCommand(CommandEvent event)
    {
        if(event.getArgs().isEmpty())
        {
            event.replyError("Please specify on/off switch");
            return;
        }

        AudioHandler handler = (AudioHandler)event.getGuild().getAudioManager().getSendingHandler();

        if (event.getArgs().equals("on"))
        {
            handler.setPlaybackSpeed(1.4);
            handler.setPlaybackPitch(1.2);

            event.reply("Enabled nightcore mode");
        }
        else if (event.getArgs().equals("off"))
        {
            handler.setPlaybackSpeed(1);
            handler.setPlaybackPitch(1);

            event.reply("Disabled nightcore mode");
        }
    }
}
