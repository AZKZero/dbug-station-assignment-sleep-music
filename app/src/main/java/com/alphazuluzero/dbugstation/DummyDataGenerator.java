package com.alphazuluzero.dbugstation;

import com.alphazuluzero.dbugstation.models.MusicListModel;

import java.util.ArrayList;
import java.util.Random;

public class DummyDataGenerator {
    public static ArrayList<MusicListModel> musicListModels;

    public static ArrayList<MusicListModel> generateModels() {
        ArrayList<MusicListModel> results = new ArrayList<>();
        int id = 0;
        results.add(new MusicListModel(++id, "Night Island", "Sleep Music", 90 * 60, "musicImageUrl", R.drawable.nightisland));
        results.add(new MusicListModel(++id, "Sweet Sleep", "Sleep Music", 45 * 60, "musicImageUrl", R.drawable.sweetsleep));
        results.add(new MusicListModel(++id, "Good Night", "Sleep Music", 50 * 60, "musicImageUrl", R.drawable.goodnight));
        results.add(new MusicListModel(++id, "Moon Clouds", "Sleep Music", 70 * 60, "musicImageUrl", R.drawable.mooncloud));
        results.add(new MusicListModel(++id, "Sweet Sleep", "Sleep Music", 80 * 60, "musicImageUrl", R.drawable.sweetsleep));
        results.add(new MusicListModel(++id, "Moon Clouds", "Sleep Music", 45 * 60, "musicImageUrl", R.drawable.mooncloud));
        results.add(new MusicListModel(++id, "Good Night", "Sleep Music", 45 * 60, "musicImageUrl", R.drawable.goodnight));
        results.add(new MusicListModel(++id, "Sweet Sleep", "Sleep Music", 45 * 60, "musicImageUrl", R.drawable.sweetsleep));
        results.add(new MusicListModel(++id, "Moon Clouds", "Sleep Music", 45 * 60, "musicImageUrl", R.drawable.mooncloud));
        results.add(new MusicListModel(++id, "Sweet Sleep", "Sleep Music", 45 * 60, "musicImageUrl", R.drawable.sweetsleep));
        results.add(new MusicListModel(++id, "Good Night", "Sleep Music", 45 * 60, "musicImageUrl", R.drawable.goodnight));
        results.add(new MusicListModel(++id, "Night Island", "Sleep Music", 45 * 60, "musicImageUrl", R.drawable.nightisland));

        return musicListModels = results;
    }

    public static int getRandomRatings() {
        Random random = new Random();
        double d = random.nextDouble();
        return (int) d * 10000;
    }

    public static MusicListModel getMusicModel(int id) {
        if (musicListModels == null) generateModels();
        for (MusicListModel musicListModel :
                musicListModels) {
            if (musicListModel.getId() == id) return musicListModel;
        }
        return null;
    }
}
