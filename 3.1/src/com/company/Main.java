package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
    static int partition(int[] arr, int low, int high) { //quicksort
        int pivot = arr[high];
        int i = low - 1;//begins b4 arr
        for (int j = low; j < high; j++) { //n iterations
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        return i;
    }

    static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int p = partition(arr, start, end);
            quickSort(arr, start, p - 1);
            quickSort(arr, p + 1, end);
        }
    }

    static boolean containsPairWithSumX (int[] arr,int sum){ //0 nlogn
        quickSort(arr,0,arr.length-1);//nlogn
        int l=0,r=arr.length-1;
        while(l<r){ //n
            int temp=arr[l]+arr[r];
            if(temp==sum) return true;
            else if(temp<sum) l++;
            else r--;
        }
        return false;
    }
    //diff bwin largest and smallest isnt too big
    static boolean containsPairWithSumXRange (int[] arr,int sum){

        return false;
    }

}