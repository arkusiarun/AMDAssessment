package com.amd.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static void writeToFile(byte[] bytes, String fileName) {
        try {
            Path path = Paths.get(fileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            System.out.println("Exception while Writing to File" + fileName);
            throw new RuntimeException(e);
        }
    }

    public static byte[] readFromFile(Path fileName) {
        try {
            return Files.readAllBytes(fileName);
        } catch (IOException e) {
            System.out.println("Exception while Reading from to FilePath" + fileName);
            throw new RuntimeException(e);
        }
    }
}