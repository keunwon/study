package com.keunwon.object.chapter10.playlist.step01;

public class PersonalPlaylist extends Playlist {
    public void remove(Song song) {
        getTracks().remove(song);
    }
}
