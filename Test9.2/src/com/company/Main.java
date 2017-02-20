package com.company;

import java.security.InvalidParameterException;

public class Main {
    /*Sarah Raitan 326852290*/
    public static void main(String[] args) {
        int[] arr = {2,4,5,6,12,13,50,55,60,70,71};
        System.out.println(ex1(arr,7));

        int[][] maaatriiix=new int[3][3];
        for (int i = 0; i < maaatriiix.length; i++) {
            for (int j = 0; j < maaatriiix.length; j++) {
                maaatriiix[i][j]=i*10+ j;
            }
        }
        printMatrix(maaatriiix);
        if(ex2(maaatriiix,-6))
            System.out.println("yes");
        else System.out.println("no");
    }
    static void printMatrix(int[][] mat){
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(i+","+j+":" +mat[i][j]+"  ");
            }
            System.out.println();
        }
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
            int p = partition(arr, start, end-1);
            quickSort(arr, start, p - 1);
            quickSort(arr, p + 1, end);
        }
    }

    static int sum(int[] arr, int from, int to) {
        int sum = 0;
        for (int i = from; i < to; i++) {
            sum += arr[i];
        }
        return sum;
    }

    static int min(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int item : arr)
            if (item < min)
                min = item;
        return min;
    }

    static int ex1(int[] chocolates, int students) {
        if(chocolates.length==0 || students==0) throw new InvalidParameterException();

        quickSort(chocolates, 0, chocolates.length);// 0nlogn

        int[] diff = new int[chocolates.length - 1];//array that will hold differences between packs
        int[] results = new int[chocolates.length - students-1]; //array that will hold best diffs

        for (int i = 1; i < chocolates.length; i++) // 0n
            diff[i-1] = chocolates[i] - chocolates[i - 1];

        for (int i = 0; i < results.length; i++) // 0n
            results[i] = sum(diff, i, i + students - 1);

        return min(results); // 0n
        //overall nlogn
    }
/*    static int ex1Teacher(int[] arr, int m){
        if(arr.length==0 || m==0) return 0;
        if(arr.length<m) return -1;
        quickSort(arr, 0, arr.length-1);// 0nlogn
        int first=0, last=0;
        int minDif=Integer.MAX_VALUE;
        for (int i = 0; i+m-1 < arr.length; i++) {
            int diff=arr[i+m-1]-arr[i];
            if()
        }
    }*/
    /**
     * Function iterates down the last column of the given matrix
     * and checks which row the given number should be in.
     * Once it reached the specific row, searches for the number in it
     * @param theMatrix--as name implies
     * @param balonzo--number to search for
     * @return
     */
    static boolean ex2(int[][] theMatrix, int balonzo)
    {
        int n=theMatrix[0].length;// rows==columns --> n

        for (int i = 0; i < n; i++) { //0n
            if (balonzo <= theMatrix[i][n-1]) {//Last item of every row. if num is smaller than last item-->This is his row
                int j = n-1;
                while (j >= 0) {// search in current row 0n
                    if (theMatrix[i][j] == balonzo)
                        return true;
                    j--; // keep going down the row
                }
                return false; //number was not found in matching row
            }
        }
        return false; // number was too big for matrix
        // overall 0n --> matching row is only found once, and iterated through once
    }
    static boolean ex2Teacher (int [][]mat, int x)
    {
        int n=mat.length;
        int i=0,j=n-1; //set index to top right element
        while(i<n && j>=0){
            if(mat[i].length!=n) throw new InvalidParameterException("ROW "+i);
            if(mat[i][j]==x) return true;
            if(mat[i][j]>x) j--;
            else i++;
        } return false;
    }
}

