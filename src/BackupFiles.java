import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * 1. Написать функцию, создающую резервную копию всех файлов
 * в директории(без поддиректорий) во вновь созданную папку ./backup
 */

public class BackupFiles {
    public static void main(String[] args) {
        String sourceDir = "./source"; // Исходная директория
        String backupDir = "./backup"; // Директория для резервной копии

        File sourceDirFile = new File(sourceDir);
        File backupDirFile = new File(backupDir);

        if (!sourceDirFile.isDirectory()) {
            System.out.println("Ошибка: исходная директория не существует или не является директорией");
            return;
        }

        if (!backupDirFile.exists()) {
            if (!backupDirFile.mkdir()) {
                System.out.println("Ошибка: не удалось создать директорию для резервной копии");
                return;
            }
        }
        File[] files = sourceDirFile.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try {
                        Files.copy(file.toPath(), new File(backupDir + "/" + file.getName()).toPath(),
                                StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Создана резервная копия файла: " + file.getName());
                    } catch (IOException e) {
                        System.out.println("Ошибка при создании резервной копии файла: " + file.getName());
                    }
                }
            }
        }
    }
}





