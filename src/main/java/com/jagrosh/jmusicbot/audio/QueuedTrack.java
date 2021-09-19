/*
 * Copyright 2016 John Grosh <john.a.grosh@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jagrosh.jmusicbot.audio;

import com.jagrosh.jmusicbot.utils.TimeUtil;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.jagrosh.jmusicbot.queue.Queueable;
import com.jagrosh.jmusicbot.utils.FormatUtil;
import net.dv8tion.jda.api.entities.User;

/**
 *
 * @author John Grosh <john.a.grosh@gmail.com>
 */
public class QueuedTrack implements Queueable
{
    private final AudioTrack track;
    private final long startTimestamp;

    public QueuedTrack(AudioTrack track, User owner)
    {
        this(track, owner.getIdLong(), 0);
    }

    public QueuedTrack(AudioTrack track, long owner)
    {
        this(track, owner, 0);
    }

    public QueuedTrack(AudioTrack track, long owner, long startTimestamp)
    {
        this.track = track;
        this.track.setUserData(owner);
        this.startTimestamp = startTimestamp;
    }
    
    @Override
    public long getIdentifier() 
    {
        return track.getUserData(Long.class);
    }
    
    public AudioTrack getTrack()
    {
        return track;
    }

    public long getStartTimestamp()
    {
        return startTimestamp;
    }

    @Override
    public String toString() 
    {
        return "`[" + TimeUtil.formatTime(track.getDuration()) + "]` **" + track.getInfo().title + "** - <@" + track.getUserData(Long.class) + ">";
    }
}
