package com.demo.arithmetic.recursion;

import java.util.Stack;

/**
 * Created by louis on 2014/10/29.
 */

/*
    线性递归，数组倒置
 */
public class Demo2 {
    //递归调用
    public static void reverseArray(int[] arr, int low, int high) {
        if (low < high) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            reverseArray(arr, low + 1, high - 1);
        }
    }
    //一般调用
    public static void IterativeReverseArray(int[] arr,int low,int high) {
        while (low < high) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--;
        }
    }
    private static void print(int[] arr) {
        for (int i : arr) {
            System.out.printf("%d ", i);
        }
    }
    public static void main(String[] args) {
        int arr[] = {2, 3, 6, 1};
        reverseArray(arr, 0, arr.length - 1);
        print(arr);
    }
}
