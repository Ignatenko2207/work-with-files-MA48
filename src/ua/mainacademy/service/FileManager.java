package ua.mainacademy.service;

import ua.mainacademy.AppRunner;
import ua.mainacademy.exception.MyOwnException;
import ua.mainacademy.model.ConnectionInfo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FileManager {

    private static final Logger LOGGER = Logger.getLogger(FileManager.class.getName());

    private static final String MAIN_DIR = System.getProperty("user.dir"); // D:\intelij-workspace\lesson5-work-with-files
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String FILES_DIR = MAIN_DIR + SEPARATOR + "files";

    public static synchronized void writeConnectionInfoToFile(ConnectionInfo connectionInfo,
                                                 String fileName,
                                                 boolean append) {
        checkFilesDir();
        LOGGER.info("Try to write object to file " + fileName);
        String filePath = FILES_DIR + SEPARATOR + fileName;
        try (FileWriter fileWriter = new FileWriter(filePath, append)) {
            fileWriter.write(connectionInfo.toString() + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void filterLines(String fileName, long timeFrom, long timeTo) {
        List<ConnectionInfo> connectionInfoList = readConnectionInfoFromFile(fileName);
        boolean append = false;
        for (ConnectionInfo connectionInfo : connectionInfoList) {
            if (connectionInfo.getTime() >= timeFrom && connectionInfo.getTime() <= timeTo) {
                writeConnectionInfoToFile(connectionInfo, fileName, append);
                append = true;
            }
        }
    }

    private static void checkFilesDir() {
        File file = new File(FILES_DIR);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public synchronized static List<ConnectionInfo> readConnectionInfoFromFile(String fileName) {
        List<ConnectionInfo> result = new ArrayList<>();
        String filePath = FILES_DIR + SEPARATOR + fileName;
        if (isNotExist(filePath)) {
            throw new MyOwnException("Sorry, can not handle reading");
        }
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] elements = line.split(" ");
                ConnectionInfo connectionInfo = new ConnectionInfo(
                        Integer.valueOf(elements[0]),
                        Long.valueOf(elements[1]),
                        elements[2]
                );
                result.add(connectionInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static boolean isNotExist(String filePath) {
        File file = new File(filePath);
        return !file.exists();
    }

    public static void writeBytesToFile(byte[] bytes, String fileName) {
        checkFilesDir();
        String filePath = FILES_DIR + SEPARATOR + fileName;

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] readBytesFromFile(String fileName) {
        String filePath = FILES_DIR + SEPARATOR + fileName;
        if (isNotExist(filePath)) {
            throw new RuntimeException("Sorry, can not handle reading");
        }
        File file = new File(filePath);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static void copyFile(String sourceFile, String targetFile) {
        byte[] bytes = readBytesFromFile(sourceFile);
        writeBytesToFile(bytes, targetFile);
    }

    public static void moveFile(String sourceFile, String targetFile) {
        String filePath = FILES_DIR + SEPARATOR + sourceFile;
        byte[] bytes = readBytesFromFile(sourceFile);
        writeBytesToFile(bytes, targetFile);
        File file = new File(filePath);
        file.delete();
    }

    public static int getSum(int num1, int num2) {
        return num1 + num2;
    }
}
