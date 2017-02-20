package com.company;
import java.util.Random;
public class Main {

    public static void main(String[] args) {
        //  Random r = new Random(9);
        //for (int i = 0; i < 4; i++)
        // System.out.println(r.nextInt() + ',');
        // int [] arr ={34,55,6,7,12,35,78,49,3,4,9};
        // System.out.println(quickSelect(arr,0,arr.length,4));
        // drawRect(4,5,3,3);
        // drawX(5);

        //drawMatrix(3, 2, 2, 7);
        // drawMatrix(0, 0, 3, 3);

        /*Canvas canvas= new Canvas (60,50);
        canvas.drawRectangle(3,4,6,7);
        canvas.drawRectangle(6,5,6,7);
        canvas.render();*/

        int[] arr = {1,2,3,7,11};
        // 2,3,4,5,6,7,1,9,20
        //printMoreThanNd4(arr);
        System.out.println(amIpivot(arr));
    }

    public static int[] moreThanFourth(int[] arr) {
        //radius*^2=(x-a)^2 + (y-b)^2
        int num = arr.length / 4;
        int[] counter = new int[10];
        int[] res = new int[3];
        for (int i = 0; i < arr.length; i++) {
            counter[arr[i]]++;
        }
        int j = 0;
        for (int i = 0; i < 10; i++) {
            if (counter[i] > num)
                res[j++] = i;
        }
        return res;
    }

    /*public static int [] moreThanFourthGood(int[] arr){
        //radius*^2=(x-a)^2 + (y-b)^2
       int num=arr.length/4;
        int [] counter=new int[10];
        int [] res=new int[num-1];
        for (int i = 0; i < arr.length; i++)
            counter[arr[i]]++;
        int j=0;
        for (int i = 0; i < 10; i++) {
            if(counter[i]>num)
                res[j++]=i;
        }
        return res;
     myMap[] myMap = new myMap();
        for (int i = 0; i < arr.length; i++) {
            if(isIn(arr[i], myMap)
        }
    }*/
    public static int[][] midcount(int arr[], int from, int to, int num) {
        int inforCounter = 0;
        int[][] info = new int[num][2];
        for (int i = from; i < to; i++) {
            if (isIn(arr[i], info[0]) != -1)
                info[isIn(arr[i], info[0])][2]++;
            else {
                info[inforCounter][0] = arr[i];
                info[inforCounter][2]++;
                inforCounter++;
            }
        }
        return info;
    }

    public static int isIn(int x, int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) return i;
        }
        return -1;
    }

    static void anySort(int[] arr) {
        int upTo = arr.length - 1;
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < upTo; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = temp;
                    isSorted = false;
                }
            }
            upTo--;
        }
    }

    static void merge(int[] arr, int l, int m, int r) {
        int n1 = m + l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int i = 0; i < n2; i++) {
            R[i] = arr[m + 1 + i];
        }
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i++];
            } else {
                arr[k] = R[j++];
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i++];
            k++;
        }
        while (j < n2) {
            arr[k] = R[j++];
            k++;
        }

    }

    static void insertionSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    static int randomized_partition(int[] arr, int low, int high) {
        if (high == low)
            return low;
        Random r = new Random(10);
        int andw = low + r.nextInt(high - low);
        return andw;
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

    static int kth(int[] arr, int k) {
        int min = 0;
        for (int i = 0; i < k; i++) {
            for (int j = i; j < arr.length; i++) {
                if (arr[i] < arr[min])
                    min = i;
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        return arr[k];// runtime: 0(n*k)
    }

    static int quickSelect(int arr[], int r, int l, int k) {
        if (k > 0 && k <= r - l + 1) {
            int pos = randomized_partition(arr, l, r);
            if (pos - l == k - 1)
                return arr[pos];
            if (pos - l > k - 1)
                return quickSelect(arr, l, pos - 1, k);
            return quickSelect(arr, pos + 1, r, k - pos + l - 1);//bc we are still dealing with the original array
        }
        return Integer.MAX_VALUE; //err ^_^
    }

    static void drawRectangleGood(int x, int y, int width, int height) {
        for (int i = 0; i < y; i++)
            System.out.println();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < x; j++)
                System.out.print(" ");
            for (int j = 0; j < width; j++) {
                System.out.print(i == 0 || i == height - 1 || j == 0 ||
                        j == width - 1 ? "*" : " ");
            }
            System.out.println();
        }
    }

    static void drawX(int x) {
        char[][] arr = new char[x][x];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                if (j == i || i + j + 1 == x)
                    arr[i][j] = '*';
                else
                    arr[i][j] = ' ';
            }
        }
        for (int r = 0; r < x; r++) {
            for (int c = 0; c < x; c++) {
                System.out.print(arr[r][c]);
            }
            System.out.println("");
        }
    }

    static void drawMatrix(int x, int y, int width, int height) {
        int rows = (y + height);
        int cols = (x + width);
        boolean[][] arr = new boolean[100][100];
        for (int i = x - 1; i < rows; i++) {
            for (int j = y - 1; j < cols; j++) {
                if (i == x - 1 || i == rows - 1 || j == y - 1 || j == cols - width + 1)
                    arr[i][j] = true;
                else arr[i][j] = false;
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (arr[r][c])
                    System.out.print('*');
                else
                    System.out.print(" ");
            }
            System.out.println("");
        }

    }

    static void printarray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] != 0 ? arr[i] : " ");
            System.out.print("  ");
        }
    }

    static void printMoreThanNd4(int[] arr) {
        int k = 4 - 1;
        //init array
        myMap[] elementCounts = new myMap[k];
        for (int i = 0; i < elementCounts.length; i++) {
            elementCounts[i] = new myMap(0, 0);
        }
        //Step 1 process all elemts in array
        for (int i = 0; i < elementCounts.length; i++) { //n
            int j;//when i exit for loop i want to know where I stopped
            for (j = 0; j < k; j++) {
                if (arr[i] == elementCounts[j].key) {
                    elementCounts[j].val++;
                    break;
                }
            }
            if (j == k)// which means we ended the loop bc it was not in our Map
            {
                int l;
                for (l = 0; l < k; l++) {
                    if (elementCounts[l].val == 0) {
                        elementCounts[l].key = arr[i];
                        elementCounts[l].val = 1;
                        break;
                    }
                }
                if (l == k) {
                    for (l = 0; l < k; l++) {
                        elementCounts[l].val--;
                    }
                }
            }
        }
        for (int i = 0; i < k; i++) {
            int actual = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == elementCounts[i].key) actual++;
            }
            if (actual > arr.length / (k + 1))
                System.out.println(elementCounts[i].key);
        }
    }

    static int amIpivot(int[] arr) {
        int max = Integer.MIN_VALUE;
        int next = 0;
        for (int i = 0; i < arr.length; i++) {
            if(i<arr.length-1)
                next = arr[i + 1];
            if (arr[i] > max) {
                max = arr[i];
                //next=arr[i+1];
            }
            if (next <= arr[i]) {
                i++; //for will add another 1 to i
                //next=arr[i];
            } else {
                int j = i;
                while (next > max && j < arr.length - 1) {
                    j++;
                    next = arr[j];
                }
                if (j == arr.length-1 && (next > max))
                    return i;
                else i=j;
            }
        }
        return -1;
    }

    static void findSmallLarge(int[] arr) {
        /*1 create two arrays leftmax, rightmin
        2 trasverse the input array from left to right and fill leftmax so that leftmax[i] will contain
        the max element up to index i in arr
        3 do the same with rightmin but from right to left
        4 trasverse the aray if an elemtn is in index i if freater than leftmax[i] and also smaller than rightmin[i]
        we found such an element
        */
        int [] leftMax=new int[arr.length];
        leftMax[0]=Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            leftMax[i]=7;
        }
    }
}
//  2,3,4,5,6,7,1,8,9