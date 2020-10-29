package com.jagrosh.jmusicbot.audio;

import com.github.natanbc.lavadsp.timescale.TimescalePcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.filter.AudioFilter;
import com.sedmelluq.discord.lavaplayer.filter.PcmFilterFactory;
import com.sedmelluq.discord.lavaplayer.filter.UniversalPcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.filter.equalizer.Equalizer;
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.Collections;
import java.util.List;

class FilterFactory implements PcmFilterFactory {
    private double speed = 1;
    private double pitch = 1;
    private boolean bassboosted = false;

    TimescalePcmAudioFilter timescaleFilter;
    Equalizer equalizer;

    @Override
    public List<AudioFilter> buildChain(AudioTrack track, AudioDataFormat format, UniversalPcmAudioFilter output)
    {
        timescaleFilter = new TimescalePcmAudioFilter(output, format.channelCount, format.sampleRate);
        timescaleFilter.setSpeed(speed);
        timescaleFilter.setPitch(pitch);

        if (bassboosted) {
            equalizer = new Equalizer(format.channelCount, output);
            setupEqualizer(equalizer, true);

            return Collections.singletonList(equalizer);
        } else {
            return Collections.singletonList(timescaleFilter);
        }
    }

    public double getPitch()
    {
        return pitch;
    }

    public void setPitch(double pitch)
    {
        this.pitch = pitch;
        if (timescaleFilter != null)
        {
            timescaleFilter.setPitch(pitch);
        }
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
        if (timescaleFilter != null)
        {
            timescaleFilter.setSpeed(speed);
        }
    }

    public boolean isBassboosted()
    {
        return bassboosted;
    }

    public void setBassboosted(boolean bassboosted)
    {
        this.bassboosted = bassboosted;

        if (equalizer != null)
            setupEqualizer(equalizer, bassboosted);
    }

    private static void setupEqualizer(Equalizer equalizer, boolean bassboosted)
    {
        if (bassboosted)
        {
            equalizer.setGain(0, 0.5f);
            equalizer.setGain(1, 0.5f);
            equalizer.setGain(2, 0.5f);
            equalizer.setGain(3, 0.4f);
            equalizer.setGain(4, 0.3f);
        }
        else
        {
            equalizer.setGain(0, 0);
            equalizer.setGain(1, 0);
            equalizer.setGain(2, 0);
            equalizer.setGain(3, 0);
            equalizer.setGain(4, 0);
        }
    }
}
