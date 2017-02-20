/**
 * Created by hackeru on 2/2/2017.
 */
public class MyPriorityQueue {
    private int [] arr;
    private int size; //the actual size
    //private int indexMax;

    public MyPriorityQueue (){
        arr=new int[10];
        size=0;
    }
    private int leftChild(int i){
        return 2*i+1;
    }
    private int rightChild(int i){
        return 2*i+2;
    }
    private int parent(int i){
        return ((i-1)/2);
    }
    public void insert(int x){
        if(size==arr.length){
            int[] temp= new int[size*2];
            for (int i = 0; i < size; i++) {
                temp[i]=arr[i];
            }
            arr=temp;
        }
        //if(x>arr[indexMax])
           // indexMax=size;
        arr[size++]=x;
    }
    public int getSize() {return size;}
    public int getMax(){
        if(size==0)
            throw new IndexOutOfBoundsException();
        int result=arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>result)
                result=arr[i];
        }
        return result;
    }
    private void heapify (int i){
        int largest=i;
        int l=leftChild(i);
        int r=rightChild(i);
        if(l<size && arr[l]>arr[largest])
            largest=l;
        if(r<size && arr[r] > arr[largest])
            largest=r; //now we found who is the largest out of 3-- father & 2 sons
        if(largest!=i){
            int temp=arr[i];
            arr[i]=arr[largest];
            arr[largest]=temp;
            heapify(largest);
        }
    }
    public int extractMax(){
        if(size==0)
            throw new IndexOutOfBoundsException();
        int result=arr[0];
        int maxIndex=0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>result) {
                result = arr[i];
                maxIndex=i;
            }
        }
        for (int i = maxIndex; i <size-1 ; i++)
            arr[i] = arr[i + 1];
        size--;
        return result;
    }
}
