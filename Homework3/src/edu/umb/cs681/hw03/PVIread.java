package edu.umb.cs681.hw03;

import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.lang.Math;

public class PVIread {
    private static List<List<String>> MA_counties_PVI_data;

    public static void PVIaverage() {
        // Compute the mean of PVI, infection ratee and vax rate in MA counties.
        float[] PVI_mean = MA_counties_PVI_data.stream().map((List<String> county) -> new String[] {county.get(0), county.get(5), county.get(6), county.get(9)}).reduce(
                new float[5],
                (result, pvi) -> {
                    result[1] = (result[0] * result[1] + Float.parseFloat(pvi[0])) / (result[0] + 1);
                    result[2] = (result[0] * result[2] + Float.parseFloat(pvi[1])) / (result[0] + 1);
                    result[3] = (result[0] * result[3] + Float.parseFloat(pvi[2])) / (result[0] + 1);
                    result[4] = (result[0] * result[4] + Float.parseFloat(pvi[3])) / (result[0] + 1);
                    result[0]++;
                    return result;
                },
                (finalResult, intermediateResult) -> finalResult
        );
        System.out.println("Based on all counties in Massachusetts: ");
        System.out.println("Average PVI: " + PVI_mean[1]);
        System.out.println("Average Infection Rate(Transmissible Cases): " + PVI_mean[2]);
        System.out.println("Average Infection Rate(Disease Spread): " + PVI_mean[3]);
        System.out.println("Average Intervention(Vaccines): " + PVI_mean[4]);
    }

    public static void PVIstd() {
        // Compute the mean of PVI values in MA counties.
        float PVI_mean = MA_counties_PVI_data.stream().map((List<String> county) -> county.get(0)).reduce(
                new float[2],
                (result, pvi) -> {
                    result[1] = (result[0] * result[1] + Float.parseFloat(pvi)) / ++result[0];
                    return result;
                },
                (finalResult, intermediateResult) -> finalResult
        )[1];

        // Compute the standard deviation and variance.
        float PVI_variance = MA_counties_PVI_data.stream().map((List<String> county) -> county.get(0)).reduce(
                new float[2],
                (result, pvi) -> {
                    result[1] = (result[0] * result[1] + (Float.parseFloat(pvi) - PVI_mean) * (Float.parseFloat(pvi) - PVI_mean)) / ++result[0];
                    return result;
                },
                (finalResult, intermediateResult) -> finalResult
        )[1];
        System.out.println("Variance of PVI: " + PVI_variance);
        System.out.println("Standard deviation of PVI: " + Math.sqrt(PVI_variance));
    }

    public static void main(String[] args) {
        // Get PVI data of all Massachusets counties on 2/14/2022 with the following URL.
        String url = "https://raw.githubusercontent.com/COVID19PVI/data/master/Model12.4/Model_12.4_20220214_results.csv";
        MA_counties_PVI_data = new ArrayList<List<String>>();
        try {
            URL rowdata = new URL(url);
            URLConnection data = rowdata.openConnection();
            Scanner input = new Scanner(data.getInputStream());
            if (input.hasNext()) // remove
                input.nextLine();  //remove

            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] curline = line.substring(1, line.length() - 1).split("\",\"", 0);
                List<String> curlineToList = Arrays.asList(curline);

                if (curline[3].length() >= 13 && curline[3].substring(0, 13).equals("Massachusetts")) {
                    // System.out.println(line);
                    MA_counties_PVI_data.add(curlineToList);
                }
            }

            PVIaverage();
            PVIstd();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
