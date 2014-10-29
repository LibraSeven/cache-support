package com.demo.arithmetic.recursion;

/**
 * Created by louis on 2014/10/29.
 */

/*
  线性递归，对数组求和
 */
public class Demo1 {
    //求arr中前N个元素和
    public static int linearSum(int[] arr,int n){
        if(n==1)
            return arr[0];  //递归基
        else
            return linearSum(arr, n - 1) + arr[n - 1];
    }

    public static void main(String[] args) {
        int arr[] = {2, 3, 6, 1};
        System.out.printf("Result=%d\n", Demo1.linearSum(arr, 4));
    }
}
