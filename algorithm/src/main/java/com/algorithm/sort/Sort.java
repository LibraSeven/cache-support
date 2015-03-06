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

    /**
     * Divide , Conquer , Combine
     *
     * @param data array
     * @param p    1
     * @param r    array length
     */
    public static void mergeSort(int[] data, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(data, p, q);
            mergeSort(data, q + 1, r);
            merge(data, p, q, r);
        }
    }

    private static void merge(int[] data, int p, int q, int r) {
        int n1 = q - p + 1;   //length of the sub array data[p..q]
        int n2 = r - q;     //length of the sub array data[q+1,r]
        //create arrays L and R
        int[] left = new int[n1 + 1];
        int[] right = new int[n2 + 1];
        int i, j;
        for (i = 0; i < n1; i++) {
            left[i] = data[p - 1 + i];
        }
        for (j = 0; j < n2; j++) {
            right[j] = data[q + j];
        }
        left[n1] = Integer.MAX_VALUE; //sentinel
        right[n2] = Integer.MAX_VALUE;
        // merge two sorted sub array. think playing cards game.
        i = j = 0;
        for (int k = p - 1; k < r; k++) {
            if (left[i] <= right[j]) {
                data[k] = left[i];
                i++;
            } else {
                data[k] = right[j];
                j++;
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
//        Sort.selectionSort(data);
        Sort.mergeSort(data, 1, data.length);

        Sort.print(data);
    }

    private static void print(int[] data) {
        for (int i : data) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
