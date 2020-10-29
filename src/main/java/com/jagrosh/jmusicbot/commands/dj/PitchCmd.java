package com.jagrosh.jmusicbot.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.commands.DJCommand;

public class PitchCmd extends DJCommand {
    public PitchCmd(Bot bot) {
        super(bot);
        this.name = "pitch";
        this.help = "sets player pitch";
        this.aliases = bot.getConfig().getAliases(this.name);
    }

    @Override
    public void doCommand(CommandEvent event)
    {
        if(event.getArgs().isEmpty())
        {
            event.replyError("Please specify pitch.");
            return;
        }

        AudioHandler handler = (AudioHandler)event.getGuild().getAudioManager().getSendingHandler();
        handler.setPlaybackPitch(Double.parseDouble(event.getArgs()));

        event.reply("Changed playback pitch to " + event.getArgs());
    }
}
