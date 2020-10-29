package com.jagrosh.jmusicbot.commands.dj;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.commands.DJCommand;

public class BassboostCmd extends DJCommand {
    public BassboostCmd(Bot bot) {
        super(bot);
        this.name = "bassboost";
        this.help = "toggles bassboost mode";
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
            handler.setBassboost(true);

            event.reply("Enabled bassboost mode");
        }
        else if (event.getArgs().equals("off"))
        {
            handler.setBassboost(false);

            event.reply("Disabled bassboost mode");
        }
    }
}
