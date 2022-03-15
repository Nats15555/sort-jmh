package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SortTemperatures {

    private final float[] temperatures;
    public static final float LEFT_LIMIT = -272F;
    public static final float RIGHT_LIMIT = 499;

    public SortTemperatures(float[] temperatures) {
        this.temperatures = temperatures.clone();
    }

    public List<Float> sortTemperaturesCollectionSort() {
        List<Float> listTemperatures = new ArrayList<>();

        for (Float it : temperatures) {
            listTemperatures.add(it);
        }

        Collections.sort(listTemperatures);
        return listTemperatures;
    }

    public List<Float> mySortTemperatures() {
        List<Float> listTemperatures = new ArrayList<>();
        int[] neg = new int[2731];
        int[] pos = new int[5001];

        for (float it : temperatures) {
            it *= 10;
            if (it >= 0) {
                pos[(int) it]++;
            } else {
                neg[(int) Math.abs(it)]++;
            }
        }

        for (int i = 2730; i > 0; i--) {
            int buff = neg[i];
            while (buff > 0) {
                listTemperatures.add((float) i / 10 * -1);
                buff--;
            }
        }

        for (int i = 0; i <= 5000; i++) {
            int buff = pos[i];
            while (buff > 0) {
                listTemperatures.add((float) i / 10);
                buff--;
            }
        }

        return listTemperatures;
    }

    public List<Float> sortTemperaturesBobbleSort() {
        List<Float> listTemperatures = new ArrayList<>();

        boolean chek = false;
        while (!chek) {
            chek = true;
            for (int i = 0; i < temperatures.length - 1; i++) {
                if (temperatures[i] > temperatures[i + 1]) {
                    chek = false;

                    float buff = temperatures[i];
                    temperatures[i] = temperatures[i + 1];
                    temperatures[i + 1] = buff;
                }
            }
        }

        for (Float it : temperatures) {
            listTemperatures.add(it);
        }

        return listTemperatures;
    }

}

