package com.company;

public class Main {

    public static void main(String[] args) {
        //region linked list
        Node<Integer> n1=new Node<>(4);
        Node<Integer> n2=new Node<>(7);
        Node<Integer> n3=new Node<>(10);
        Node<Integer> n4=new Node<>(19);
        Node<Integer> n5=new Node<>(45);
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        Node<Node<Integer>> head1=new Node<>(n1);

        Node<Integer> n6=new Node<>(2);
        Node<Integer> n7=new Node<>(8);
        Node<Integer> n8=new Node<>(11);
        Node<Integer> n9=new Node<>(25);
        Node<Integer> n10=new Node<>(56);
        n6.next=n7;
        n7.next=n8;
        n8.next=n9;
        n9.next=n10;
        Node<Node<Integer>> head2=new Node<>(n6);

        Node<Integer> n11=new Node<>(12);
        Node<Integer> n12=new Node<>(14);
        Node<Integer> n13=new Node<>(16);
        Node<Integer> n14=new Node<>(18);
        Node<Integer> n15=new Node<>(21);
        n11.next=n12;
        n12.next=n13;
        n13.next=n14;
        n14.next=n15;
        Node<Node<Integer>> head3=new Node<>(n11);

        Node<Integer> n16=new Node<>(7);
        Node<Integer> n17=new Node<>(13);
        Node<Integer> n18=new Node<>(17);
        Node<Integer> n19=new Node<>(21);
        Node<Integer> n20=new Node<>(23);
        n16.next=n17;
        n17.next=n18;
        n18.next=n19;
        n19.next=n20;
        Node<Node<Integer>> head4=new Node<>(n16);

        head1.next=head2;
        head2.next=head3;
        head3.next=head4;
        //printLinkedList(sortKlists2(head1));
        //endregion
        int [] arr1 ={1,2,3};
        int [] arr2 ={11,13,18};
        int [] arr3 ={12,15,24};
        int [] arr4 ={32,42,53};
        int [][] arr={arr1,arr2, arr3, arr4};
        int[] toPrint=sortKlists3(arr);
        for (int i = 0; i < toPrint.length; i++) {
            if(i<toPrint.length-1)
            System.out.print(toPrint[i]+ " , ");
            else System.out.print(toPrint[i]);
        }
        System.out.println();
        System.out.println("WE ARE SO AWESOME");
    }
    public static int[] sortKlists3 ( int[][] arr ){
        int counter = arr.length * arr[0].length;
        int [] result=new int[counter];
        int j=0;
        MinHeap minHeap=new MinHeap();
        for (int i = 0; i < arr.length; i++)
            minHeap.insert(new CoolNode(arr[i][0], i, 0));

        while(counter>0) {
            CoolNode temp = (CoolNode) minHeap.extractMin();
            counter--;
            result[j++] = temp.value;
            if(temp.col+1<arr[0].length && temp.row<arr.length)
                minHeap.insert(new CoolNode(arr[temp.row][temp.col + 1], temp.row, temp.col+1));
        }
        return result;
    }
    public static void printList(Node<Integer> head){


    }
    public static void printLinkedList(Node node){
        while (node!=null){
            System.out.print(node.value+" ");
            node=node.next;
        }
        System.out.println();

    }
    public static Node<Integer> mergeLinkedListOfLinkedList(Node<Node<Integer>> head){
        if(head==null) return null;
        while(head.next!=null){
            head.value=merge(head.value,head.next.value);
            head.next=head.next.next;
        }
        return head.value;
    }
    //teacher
    static Node<Integer> mergeLinkedListOfLinkedList2 (Node<Node<Integer>> head){
        if(head==null) return null;
        if(head.next==null)
            return head.value;
        Node<Node<Integer>> temp=new Node<>(null);
        Node<Node<Integer>>headresult=temp;
       while(head!=null && head.next!=null) {
            headresult.next=new Node<>(merge(head.value, head.next.value));
            head=head.next.next;
            headresult=headresult.next;
        }
        if(head!=null){
           temp.next = head;
        }
        return mergeLinkedListOfLinkedList2(temp.next); // bc first node is fictitious node
    }
    static Node<Integer> sortKlists2 (Node<Node<Integer>> head){
        if(head == null) return null;
        if(head.next == null)
            return head.value;
        Node<Node<Integer>> twoNext=head.next.next;
        if(twoNext!=null && twoNext.next!=null ) {
            twoNext = new Node<> (merge(merge(head.value, head.next.value),merge(twoNext.value, twoNext.next.value)));
            if(twoNext.next != null) //after merging
                twoNext.next = twoNext.next.next;
            return sortKlists2(twoNext);
        }
        if(twoNext != null) // if we got here twoNext.next == null
        {
            head = new Node<>(merge(merge(head.value, head.next.value),twoNext.value));
            head.next = twoNext.next;
            return sortKlists2(twoNext);
        }// if we got here head is one b4 last (twonext=null)
        head = new Node<>(merge(head.value ,head.next.value)); // merge me with next
        head.next = null; // prevent redundancy of lists
        return head.value;
    }
    static <T extends Comparable<T>> Node<T> merge(Node<T> a, Node<T> b){
        //TODO: func returns Node<T> but we also have previous information about T type (is comparable)
        Node<T> anchor = new Node<>(null);
        Node<T> tail = anchor;
        while(true){
            if(a == null) {
                //if either list runs out, use the other one.
                tail.next = b;
                break;
            }else if (b == null){
                tail.next = a;
                break;
            }
            if(b.value.compareTo(a.value)>=0){
                tail.next = a;
                a = a.next;
            }
            else{
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        return anchor.next;
    }
}
