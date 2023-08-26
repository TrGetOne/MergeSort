import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class Main {

    static ArrayList<String> ArrayList(String pathName) {
        ArrayList<String> list = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathName));
            String str;
            list = new ArrayList<>();
            try {
                while ((str = reader.readLine()) != null) {
                    if (!str.isEmpty()) {
                        list.add(str);
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл не найден!");
        }
        return list;
    }
    static void Merge(int[] targetArray, int[] array1, int[] array2) {
        int array1MinIndex = 0;
        int array2MinIndex = 0;

        int targetArrayMixIndex = 0;

        while (array1MinIndex < array1.length && array2MinIndex < array2.length) {
            if (array1[array1MinIndex] <= array2[array2MinIndex]) {
                targetArray[targetArrayMixIndex] = array1[array1MinIndex];
                array1MinIndex++;
            } else {
                targetArray[targetArrayMixIndex] = array2[array2MinIndex];
                array2MinIndex++;
            }
            targetArrayMixIndex++;
        }
        while (array1MinIndex < array1.length) {
            targetArray[targetArrayMixIndex] = array1[array1MinIndex];
            array1MinIndex++;
            targetArrayMixIndex++;
        }
        while (array2MinIndex < array2.length) {
            targetArray[targetArrayMixIndex] = array2[array2MinIndex];
            array2MinIndex++;
            targetArrayMixIndex++;
        }
    }
    static void MergeSort(int[] intArr) {
        if (intArr.length < 2) {
            return;
        }
        int mid = intArr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[intArr.length-mid];
        for (int i = 0; i < mid;i++) {
            left[i] = intArr[i];
        }
        for (int i = mid; i < intArr.length;i++) {
            right[i-mid] = intArr[i];
        }
        MergeSort(left);
        MergeSort(right);
        Merge(intArr, left, right);
    }
    static void Merge(String[] stringArr, String[] array1, String[] array2) {
        int array1MinIndex = 0;
        int array2MinIndex = 0;

        int targetArrayMixIndex = 0;

        while (array1MinIndex < array1.length && array2MinIndex < array2.length) {
            if (array1[array1MinIndex].compareTo(array2[array2MinIndex]) < 0) {
                stringArr[targetArrayMixIndex] = array1[array1MinIndex];
                array1MinIndex++;
            } else {
                stringArr[targetArrayMixIndex] = array2[array2MinIndex];
                array2MinIndex++;
            }
            targetArrayMixIndex++;
        }
        while (array1MinIndex < array1.length) {
            stringArr[targetArrayMixIndex] = array1[array1MinIndex];
            array1MinIndex++;
            targetArrayMixIndex++;
        }
        while (array2MinIndex < array2.length) {
            stringArr[targetArrayMixIndex] = array2[array2MinIndex];
            array2MinIndex++;
            targetArrayMixIndex++;
        }
    }
    static void MergeSort(String[] stringArr) {
        if (stringArr.length < 2) {
            return;
        }
        int mid = stringArr.length / 2;
        String[] left = new String[mid];
        String[] right = new String[stringArr.length-mid];

        for (int i = 0; i < mid;i++)
            left[i] = stringArr[i];

        for (int i = mid; i < stringArr.length;i++)
            right[i-mid] = stringArr[i];

        MergeSort(left);
        MergeSort(right);
        Merge(stringArr, left, right);
    }
    public static void writeToTxt (int [] intArr, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)));
        for (int i = 0; i < intArr.length; i++) {
                writer.write(String.valueOf(intArr[i]));
                writer.write("\n");
        }
        writer.flush();
    }
    public static void writeToTxt (String[] stringArr, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)));
        for (int i = 0; i < stringArr.length; i++) {
            writer.write(String.valueOf(stringArr[i]));
            writer.write("\n");
        }
        writer.flush();
    }
    public static void writeToTxt (String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName)));
    }

    public static void main(String[] args) throws IOException {
        Scanner Console = new Scanner(System.in);
        String Choice = "";
        String pathName = "";
        String fileName = " ";
        String[] ListInt = {"INTin1.txt", "INTin2.txt", "INTin3.txt"};
        String[] ListString = {"TXTin1.txt", "TXTin2.txt", "TXTin3.txt"};

        System.out.println("Назовите выходной файл");
            fileName = Console.nextLine().replaceAll("\\W","") + ".txt";
        try {
            File file = new File(fileName);
            if (!file.createNewFile()) {
                PrintWriter pw = new PrintWriter(fileName);
                pw.close();
            }
        }
        catch (IOException e) {
            System.exit(0);
        }
        ArrayList<String> list  = ArrayList(fileName);

        System.out.println("Введите тип данных(-i или -s)");
        String dataType = Console.nextLine();
        while (!dataType.equals("-s") && !dataType.equals("-i")) {
            dataType = Console.nextLine();
        }
        System.out.println("Выберите входные файлы(не менее одного(без пробела))");
        if (dataType.contains("-i")) {
            for (int i = 0; i < ListInt.length; i++) {
                System.out.println((i + 1) + ". " + ListInt[i]);
            }
            while (!Choice.contains("1") && !Choice.contains("2")) {
                Choice = Console.nextLine();
            }
                if (Choice.contains("1")) {
                    pathName = "INTin1.txt";
                    list.addAll(ArrayList(pathName));
                }
                if (Choice.contains("2")) {
                    pathName = "INTin2.txt";
                    list.addAll(ArrayList(pathName));
                }
                if (Choice.contains("3")) {
                    pathName = "INTin3.txt";
                    list.addAll(ArrayList(pathName));
                }
            int[] intArr = new int[list.size()];
            for (int i = 0; i < intArr.length; i++) {
                if (!list.get(i).matches("^[a-zA-Z]*$"))
                {
                    intArr[i] = Integer.parseInt(list.get(i));
                }
            }
            MergeSort(intArr);
            writeToTxt(intArr, fileName);
        }

        if (dataType.equals("-s")) {
            for (int i = 0; i < ListString.length; i++) {
                System.out.println((i + 1) + ". " + ListString[i]);
            }
            while (!Choice.contains("1") && !Choice.contains("2")) {
                Choice = Console.nextLine();
            }
            if (Choice.contains("1")) {
                pathName = "TXTin1.txt";
                list.addAll(ArrayList(pathName));
            }
            if (Choice.contains("2")) {
                pathName = "TXTin2.txt";
                list.addAll(ArrayList(pathName));
            }
            if (Choice.contains("3")) {
                pathName = "TXTin3.txt";
                list.addAll(ArrayList(pathName));
            }

            String[] stringArr = new String[list.size()];
            for (int i = 0; i < stringArr.length; i++) {
                stringArr[i] = list.get(i);
            }
            MergeSort(stringArr);
            writeToTxt(stringArr, fileName);
        }
    }
}