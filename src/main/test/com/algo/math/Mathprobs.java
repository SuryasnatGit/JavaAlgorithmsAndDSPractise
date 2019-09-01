package com.algo.math;

import java.util.Scanner;

public class Mathprobs {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int arr[] = new int[n];
        for(int arr_i=0; arr_i < n; arr_i++){
            arr[arr_i] = in.nextInt();
        }
        in.close();
        int negc = 0;
        int posc = 0;
        int zeroc = 0;
        for(int i=0;i<n;i++){
            if(arr[i] < 0) negc++;
            else if(arr[i] > 0) posc++;
            else zeroc++;
        }
        float a = (float)posc/n;
//        Double d = new Double(a);
//        d.
        
        System.out.println(a);
        System.out.println(negc/n);
        System.out.println(zeroc/n);
        
    }

}
