package com.jearias.hackercup2014.qual;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Tennison {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner in = null;
        // in = new Scanner(System.in);
        try {
            in = new Scanner(new FileInputStream("/Users/jose/Downloads/tennisonExample-input.txt"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int t = in.nextInt();
        for (int indexT = 1; indexT <= t; indexT++) {
            // sets
            int k = in.nextInt();
            // probability of winning when it's sunny
            float ps = in.nextFloat();
            // probability of winning when it's raining
            float pr = in.nextFloat();
            // The chance that there will be sun for the first set
            float pi = in.nextFloat();
            // if the set is won, the probability that there will be sun increases by pu
            float pu = in.nextFloat();
            // pu with probability pw
            float pw = in.nextFloat();
            // if the set is lost, the probability that there will be sun decreases by pu
            float pd = in.nextFloat();
            // pu with probability pw
            float pl = in.nextFloat();

            // first set
            float previousProbabilty = 0;
            float probabilty = 0;
            float probabiltySunny = 0;
            float probabiltyRainning = 0;
            for (int i = 1; i <= k; i++) {
                if (i == 1) {
                    probabiltySunny = ps * pi;
                    probabiltyRainning = pr * (1 - pi);
                    probabilty = probabiltySunny + probabiltyRainning;
                    previousProbabilty = probabilty;
                } else {
                    pi = (pi + pu) * pw;
                    probabiltySunny = ps * pi;
                    probabiltyRainning = pr * (1 - pi);
                    probabilty = probabiltySunny + probabiltyRainning;
                    probabilty = previousProbabilty * probabilty * i;
                }
            }
            System.out.format("Case #%d: %.6f\n", indexT, probabilty);

        }
    }
}
