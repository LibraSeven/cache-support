package com.algorithm.sort;

/**
 * Created by louis on 2015/3/5.
 */
public class Sort {
    public static void selectionSort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int min = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[min] > data[j]) {
                    min = j;
                }
            }
            swap(data, i, min);
        }
    }

    public static void insertionSort(int[] data) {
        if (data.length == 0)
            return;

        for (int i = 1; i < data.length; i++) {
            int key = data[i];
            int j = i - 1;
            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
    }

    public static void bubbleSort(int[] data) {
        if (data.length == 0)
            return;
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 1; j < data.length - i; j++) {
                if (data[j - 1] > data[j]) {
                    swap(data, j - 1, j);
                }
            }
        }
    }

    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }


    public static void main(String[] args) {
        int[] data = {5, 2, 4, 6, 1, 3};
//        Sort.insertionSort(data);
//        Sort.bubbleSort(data);
        Sort.selectionSort(data);


        Sort.print(data);
    }

    private static void print(int[] data) {
        for (int i : data) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
