import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {
    static String rootDir = "/Users/wawenew/Desktop/Game/";
    static StringBuilder logger = new StringBuilder();

    public static void main(String[] args) {
        // Задание 1
        createFolder("src");
        createFolder("res");
        createFolder("savegames");
        createFolder("temp");
        createFolder("src/main");
        createFolder("src/test");
        createFolder("res/drawables");
        createFolder("res/vectors");
        createFolder("res/icons");
        createFile("src/main/Main.java");
        createFile("src/main/Utils.java");
        createFile("temp/temp.txt");

        try (FileWriter writer = new FileWriter(rootDir + "temp/temp.txt")) {
            writer.write(logger.toString());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        // Задание 2
        GameProgress save1 = new GameProgress(90, 3, 9, 300);
        GameProgress save2 = new GameProgress(80, 2, 6, 200);
        GameProgress save3 = new GameProgress(70, 1, 3, 100);
        String strSave1 = "save1.dat";
        String strSave2 = "save2.dat";
        String strSave3 = "save3.dat";

        saveGame(strSave1, save1);
        saveGame(strSave2, save2);
        saveGame(strSave3, save3);
        List<String> saveList = new ArrayList<>();
        saveList.add(strSave1);
        saveList.add(strSave2);
        saveList.add(strSave3);

        String zip = "/Users/wawenew/Desktop/Game/savegames/SaveData.zip";
        zipFiles(zip, saveList);
        deleteOnlyFiles("/Users/wawenew/Desktop/Game/savegames/");
        // Задание 3
        openZip("/Users/wawenew/Desktop/Game/savegames/SaveData.zip",
                "/Users/wawenew/Desktop/Game/savegames/");
        System.out.println(openProgress("/Users/wawenew/Desktop/Game/savegames/save1.dat"));

    }

    // Задание 1
    private static void createFolder(String folderName) {
        File folder = new File(rootDir + folderName);
        logger
                .append(logResult(folder.mkdir(), folder, folderName))
                .append("\n");
    }

    private static void createFile(String fileName) {
        File file = new File(rootDir + fileName);
        try {
            logger
                    .append(logResult(file.createNewFile(), file, fileName))
                    .append("\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static String logResult(boolean status, File type, String name) {
        if (type.isDirectory()) {
            return status ? "Каталог " + name + " создан" : "Каталог " + name + " не создан";
        } else {
            return status ? "Файл " + name + " создан" : "Файл " + name + " не создан";
        }
    }

    // Задание 2
    private static void saveGame(String filePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
            System.out.println("Файл сохранен");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String filePath, List<String> objects) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(filePath))) {
            for (String l : objects) {
                try (FileInputStream fis = new FileInputStream(l)) {
                    ZipEntry zipE = new ZipEntry(l);
                    zout.putNextEntry(zipE);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            System.out.println("Архив создан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteFile(File fileName) {
        if (fileName.delete()) {
            System.out.println("Файл удален");
        }
    }

    public static void deleteOnlyFiles(String toDeleteFolderPath) {
        File toDelete = new File(toDeleteFolderPath);
        if (toDelete.isDirectory()) {
            try {
                File[] f = toDelete.listFiles();
                if (f != null) {
                    for (File item : f) {
                        String[] n = item.getName().split("\\.");
                        if (!item.isDirectory() && !n[n.length - 1].equals("zip")) {
                            if (item.delete()) {
                                String success = "Файл " + item.getName() + " удален";
                                System.out.println(success);
                            }
                        }
                    }
                }
            } catch (NullPointerException ex) {
                String failed = ex.getMessage();
                System.out.println(failed);
            }
        }
    }

    // Задание 3
    public static void openZip(String zipFilePath, String unzipFolderPath) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry;
            String[] name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName().split("_");
                FileOutputStream fout = new FileOutputStream(unzipFolderPath + name[name.length - 1]);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                String success = "Файл " + entry.getName() + " разархивирован";
                System.out.println(success);
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            String failed = ex.getMessage();
            System.out.println(failed);
        }
    }

    public static GameProgress openProgress(String saveGameFilePath) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(saveGameFilePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
            String success = "Десериализация завершена:";
            System.out.println(success);
        } catch (Exception ex) {
            String failed = ex.getMessage();
            System.out.println(failed);
        }
        return gameProgress;
    }

}