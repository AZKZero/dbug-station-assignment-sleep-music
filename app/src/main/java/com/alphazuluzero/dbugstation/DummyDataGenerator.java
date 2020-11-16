package com.alphazuluzero.dbugstation;

import com.alphazuluzero.dbugstation.models.MusicListModel;

import java.util.ArrayList;
import java.util.Random;

public class DummyDataGenerator {
    public static ArrayList<MusicListModel> musicListModels;

    public static ArrayList<MusicListModel> generateModels() {
        if (musicListModels != null) return musicListModels;
        ArrayList<MusicListModel> results = new ArrayList<>();
        int id = 0;
        results.add(new MusicListModel(++id, "Night Island", "Sleep Music", 90 * 60, "musicImageUrl", R.drawable.nightisland));
        results.add(new MusicListModel(++id, "Sweet Sleep", "Sleep Music", 45 * 60, "musicImageUrl", R.drawable.sweetsleep));
        results.add(new MusicListModel(++id, "Good Night", "Sleep Music", 50 * 60, "musicImageUrl", R.drawable.goodnight));
        results.add(new MusicListModel(++id, "Moon Clouds", "Sleep Music", 70 * 60, "musicImageUrl", R.drawable.mooncloud));
        results.add(new MusicListModel(++id, "Sweet Sleep", "Sleep Music", 80 * 60, "musicImageUrl", R.drawable.sweetsleep));
        results.add(new MusicListModel(++id, "Moon Clouds", "Sleep Music", 5 * 60, "musicImageUrl", R.drawable.mooncloud));
        results.add(new MusicListModel(++id, "Good Night", "Sleep Music", 20 * 60, "musicImageUrl", R.drawable.goodnight));
        results.add(new MusicListModel(++id, "Sweet Sleep", "Sleep Music", 15 * 60, "musicImageUrl", R.drawable.sweetsleep));
        results.add(new MusicListModel(++id, "Moon Clouds", "Sleep Music", 2 * 60, "musicImageUrl", R.drawable.mooncloud));
        results.add(new MusicListModel(++id, "Sweet Sleep", "Sleep Music", 45 * 60, "musicImageUrl", R.drawable.sweetsleep));
        results.add(new MusicListModel(++id, "Good Night", "Sleep Music", 45 * 60, "musicImageUrl", R.drawable.goodnight));
        results.add(new MusicListModel(++id, "Night Island", "Sleep Music", 45 * 60, "musicImageUrl", R.drawable.nightisland));

        return musicListModels = results;
    }

    public static int getRandomRatings() {
        Random random = new Random();
        int d = random.nextInt(100000);
        return d < 10000 ? d * 10000 : d;
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
