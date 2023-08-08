import java.io.*;

/**
 * 2. Предположить, что числа в исходном массиве из 9 элементов имеют диапазон[0, 3],
 * и представляют собой, например, состояния ячеек поля для игры в крестикинолики,
 * где 0 – это пустое поле, 1 – это поле с крестиком, 2 – это поле с ноликом,
 * 3 – резервное значение.
 * Такое предположение позволит хранить в одном числе типа int всё поле 3х3.
 * Реализовать функционал с записью в файл и обратно игрового поля.
 * Выводить в консоль игровое поле после импорта, заменяя числа символами X, O, •(пусто)
 */
public class TicTacToe {
    private static final int SIZE = 3; // Размер игрового поля

    public static void main(String[] args) {
        int[][] gameField = new int[SIZE][SIZE]; // Игровое поле
        gameField[0][0]=1;
        gameField[0][2]=2;
        gameField[1][2]=3;

        // Запись игрового поля в файл
        writeGameFieldToFile(gameField, "game_field.txt");

        // Чтение игрового поля из файла
        int[][] importedGameField = readGameFieldFromFile("game_field.txt");

        // Вывод игрового поля в консоль
        printGameField(importedGameField);
    }

    // Запись игрового поля в файл
    private static void writeGameFieldToFile(int[][] gameField, String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    writer.print(gameField[i][j]);
                }
                writer.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при записи игрового поля в файл");
        }
    }

    // Чтение игрового поля из файла
    private static int[][] readGameFieldFromFile(String fileName) {
        int[][] gameField = new int[SIZE][SIZE];

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < SIZE) {
                for (int col = 0; col < SIZE; col++) {
                    gameField[row][col] = Character.getNumericValue(line.charAt(col));
                }
                row++;
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении игрового поля из файла");
        }

        return gameField;
    }

    // Вывод игрового поля в консоль
    private static void printGameField(int[][] gameField) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                switch (gameField[i][j]) {
                    case 0:
                        System.out.print("   •  ");
                        break;
                    case 1:
                        System.out.print("   X  ");
                        break;
                    case 2:
                        System.out.print("   O  ");
                        break;
                    case 3:
                        System.out.print("reserve");
                        break;
                }
            }
            System.out.println();
        }
    }
}
