package com.mjc.school.repository.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

public class Utils {
    public Utils() {}

    public static String getRandomContentByFilePath(String fileName) {
        String result;
        Random rn = new Random();
        int lines = 30;
        try{
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            int desiredLine = rn.nextInt(lines);
            int lineCtr = 0;
            while ((result = reader.readLine()) != null){
                if (desiredLine == lineCtr)
                    break;
                lineCtr++;
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


    public static LocalDateTime getRandomDate(){
        Random r = new Random();
        int endDay = 30;
        LocalDate day =LocalDate.now().plusDays(r.nextInt(endDay));
        int hour = r.nextInt(24);
        int minute = r.nextInt(60);
        int second = r.nextInt(60);
        LocalTime time = LocalTime.of(hour, minute, second);
        return LocalDateTime.of(day, time);
    }
}
