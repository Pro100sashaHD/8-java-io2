package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        String ffprobe = "F:\\iidea\\test\\8-java-io2\\task01\\src\\main\\resources\\ffmpeg\\bin\\ffprobe.exe";
        ProcessBuilder processBuilder = new ProcessBuilder(ffprobe, "-v", "error", "-of", "flat", "-show_format", file.getAbsolutePath());
        Process process = processBuilder.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())))
        {
            String str = "";
            while (!str.contains("format.tags.title"))
                str = reader.readLine();

            return str.substring(19).replace("\"","");
        }
    }
}
