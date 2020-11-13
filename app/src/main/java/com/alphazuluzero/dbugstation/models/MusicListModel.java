package com.alphazuluzero.dbugstation.models;

public class MusicListModel {
    private String musicName;
    private String albumName;
    private double durationInSeconds;
    private String musicImageUrl;
    private int musicImageResInt;
    private int id;

    public MusicListModel(int id, String musicName, String albumName, double durationInSeconds, String musicImageUrl, int musicImageResInt) {
        this.id = id;
        this.musicName = musicName;
        this.albumName = albumName;
        this.durationInSeconds = durationInSeconds;
        this.musicImageUrl = musicImageUrl;
        this.musicImageResInt = musicImageResInt;
    }

    public double getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(double durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusicImageUrl() {
        return musicImageUrl;
    }

    public void setMusicImageUrl(String musicImageUrl) {
        this.musicImageUrl = musicImageUrl;
    }

    public int getMusicImageResInt() {
        return musicImageResInt;
    }

    public void setMusicImageResInt(int musicImageResInt) {
        this.musicImageResInt = musicImageResInt;
    }
}
