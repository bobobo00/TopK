package Heap;

import com.sun.scenario.effect.impl.HeapImage;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @ClassName MyPriorityQueuen
 * @Description TODO
 * @Auther danni
 * @Date 2019/9/22 14:18]
 * @Version 1.0
 **/

public class MyPriorityQueuen {
    private static  int[] array;
    private static int size;
    private static int top;

    public MyPriorityQueuen() {
     array = new int[8];
    }
    public MyPriorityQueuen(int length) {array = new int[length];}
    private  int[] ensureCapicity(){
        int newlen=size*2;
        int[] temp=new int[newlen];
        System.arraycopy(array,0,temp,0,array.length);
        array=temp;
        return array;
    }
    private void checkRange(int index){
        if(index<0){
            throw new RuntimeException("队列为空");
        }
    }
    //小堆
    public void putSmall(int element){
        if(size>=array.length){
            array=ensureCapicity();
        }
        array[size++]=element;
        shiftUpSmall(size-1);
        top=this.peek();
    }
    //大堆
    public void putMax(int element){
        if(size>=array.length){
            array=ensureCapicity();
        }
        array[size++]=element;
       shiftupMax(size-1);
       top=this.peek();
    }

    public int peek(){
        checkRange(0);
        return array[0];
    }
    //小堆
    public int pollSmall(){
        checkRange(0);
        int element=array[0];
        array[0]=array[size-1];
        size--;
        TestHeap.shiftDownSmall(array,0,size);
        top=this.peek();
        return element;
    }
    //大堆
    public int pollMax(){
        checkRange(0);
        int element=array[0];
        array[0]=array[size-1];
        size--;
        TestHeap.shiftDownBig(array,0,size);
        top=this.peek();
        return element;
    }
    public static void shiftUpSmall(int index) {
        int parent = (index - 1) / 2;
        while (parent >= 0) {
            if (array[parent] > array[index]) {
                swamp(array, index, parent);
                index=parent;
                parent = (parent - 1) / 2;
            } else {
                break;
            }
        }
    }
    public static void shiftupMax(int index){
        int parent = (index - 1) / 2;
        while (parent>=0) {
            if (array[parent] < array[index]) {
                swamp(array, index, parent);
                index=parent;
                parent = (parent - 1) / 2;
            } else {
                break;
            }
        }
    }
    //TopK问题（求前K个小数，建立大堆）
    public  void topkSamll(int[] array,int k,int size){
        for (int i = 0; i <k ; i++) {
            this.putMax(array[i]);
        }
        for (int i = k; i <size ; i++) {
            int temp=this.peek();
            if(array[i]<temp){
                swamp(array,i,0);
                TestHeap.shiftDownBig(array,0,k);
            }
        }
        for (int i = 0; i <k ; i++) {
            System.out.print(array[i]+",");
        }
    }
    //Topk问题（求前k个大数，建立小堆）
    public  void topkMax(int[] array,int k,int size){
        for (int i = 0; i <k ; i++) {
            this.putSmall(array[i]);
        }
        for (int i = k; i <size ; i++) {
            int temp=this.peek();
            if(array[i]>temp){
                swamp(array,i,0);
                TestHeap.shiftDownSmall(array,0,k);
            }
        }
        for (int i = 0; i <k ; i++) {
            System.out.print(array[i]+",");
        }
    }
    private static void swamp(int[] array, int index, int parent) {
        int temp=array[index];
        array[index]=array[parent];
        array[parent]=temp;
    }

    public static void main(String[] args) {
        MyPriorityQueuen queuen=new MyPriorityQueuen();
       /* queuen.putSmall(1);
        queuen.putSmall(1);
        queuen.putSmall(1);
        queuen.putSmall(1);
        queuen.putSmall(1);
        for (int i = 0; i <size; i++) {
            System.out.print(array[i]+",");
        }*/
       array[0]=1;
       array[1]=4;
       array[2]=3;
       array[3]=5;
       array[4]=2;
        queuen.topkMax(array,4,5);


    }
}
