package com.algo.ds.tree;

/**
 * 
 * Video link - https://youtu.be/ZBHKZF5w4YU.
 * 
 * to find the range: A simple solution is to run a loop from l to r and calculate sum of elements
 * in given range. To update a value, simply do arr[i] = x. The first operation takes O(n) time and
 * second operation takes O(1) time.
 * 
 * Another solution is to create another array and store sum from start to i at the ith index in
 * this array. Sum of a given range can now be calculated in O(1) time, but update operation takes
 * O(n) time now. This works well if the number of query operations are large and very few updates.
 * 
 * What if the number of query and updates are equal? Can we perform both the operations in O(log n)
 * time once given the array? We can use a Segment Tree to do both operations in O(Logn) time.
 * 
 * A segment tree is a tree data structure for storing intervals, or segments. It allows for faster
 * querying (e.g sum or min) in these intervals. Write a program to support these operations for sum
 * createSegmentTree(int arr[]) - create segment tree query(int segment[], int startRange, int
 * endRange) - query in this range
 * 
 * Similar segment trees can be created for min or max.
 * 
 * Representation of Segment trees 1. Leaf Nodes are the elements of the input array. 2. Each
 * internal node represents some merging of the leaf nodes. The merging may be different for
 * different problems. For this problem, merging is sum of leaves under a node.
 * 
 * An array representation of tree is used to represent Segment Trees. For each node at index i, the
 * left child is at index 2*i+1, right child at 2*i+2 and the parent is at (i-1)/2
 * 
 * Time complexity to create segment tree is O(nlogn) Space complexity to create segment tree is
 * O(nlogn) Time complexity to search in segment tree is O(logn)
 * 
 * References http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
 * http://www.geeksforgeeks.org/segment-tree-set-1-range-minimum-query/
 */
public class SegmentTree {

    public int[] createTree(int input[], Operation operation){
        int height = (int)Math.ceil(Math.log(input.length)/Math.log(2));
        int segmentTree[] = new int[(int)(Math.pow(2, height+1)-1)];
        constructTree(segmentTree,input,0,input.length-1,0, operation);
        return segmentTree;
    }
    
    private void constructTree(int segmentTree[], int input[], int low, int high,int pos, Operation operation){
        if(low == high){
            segmentTree[pos] = input[low];
            return;
        }
        int mid = (low + high)/2;
        constructTree(segmentTree,input,low,mid,2*pos+1, operation);
        constructTree(segmentTree,input,mid+1,high,2*pos+2, operation);
        segmentTree[pos] = operation.perform(segmentTree[2*pos+1], segmentTree[2*pos+2]);
    }
    
    public int rangeQuery(int []segmentTree,int qlow,int qhigh,int len, Operation operation){
        return rangeQuery(segmentTree,0,len-1,qlow,qhigh,0, operation);
    }
    
    private int rangeQuery(int segmentTree[],int low,int high,int qlow,int qhigh,int pos, Operation operation){
        if(qlow <= low && qhigh >= high){
            return segmentTree[pos];
        }
        if(qlow > high || qhigh < low){
            return 0;
        }
        int mid = (low+high)/2;
        return operation.perform(rangeQuery(segmentTree,low,mid,qlow,qhigh,2*pos+1, operation),
                rangeQuery(segmentTree,mid+1,high,qlow,qhigh,2*pos+2, operation));
    }
    
    public void updateValueForSumOperation(int input[],int segmentTree[],int newVal,int index){
        int diff = newVal - input[index];
        input[index] = newVal;
        updateVal(segmentTree,0,input.length-1,diff,index,0);
    }
    
    private void updateVal(int segmentTree[],int low,int high,int diff,int index, int pos){
        if(index < low || index > high){
            return;
        }
        segmentTree[pos] += diff;
        if(low >= high){
            return;
        }
        int mid = (low + high)/2;
        updateVal(segmentTree,low,mid,diff,index,2*pos+1);
        updateVal(segmentTree,mid+1,high,diff,index,2*pos+2);
    }
    
    public static void main(String args[]){
        int input[] = {1,3,5,7,9,11};
        SegmentTree st = new SegmentTree();
        Operation sumOp = new SumOperation();
        Operation minOp = new MinOperation();
        int result [] = st.createTree(input, sumOp);
        for(int i=0; i < result.length; i++){
            System.out.print(result[i] + " ");
        }
        int input1[] = {3,4,2,1,6,-1};
        int result1[] = st.createTree(input1, minOp);
        for(int i=0; i < result1.length; i++){
            System.out.print(result1[i] + " ");
        }
        
        st.updateValueForSumOperation(input, result,0 , 0);
        System.out.println();
        for(int i=0; i < result.length; i++){
            System.out.print(result[i] + " ");
        }
        
    }
}

/**
 * Provides interface to perform operations on range queue like sum or min 
 */
interface Operation{
    int perform(int a, int b);
}

class SumOperation implements Operation{

    @Override
    public int perform(int a, int b) {
        return a+b;
    }
}

class MinOperation implements Operation{
    @Override
    public int perform(int a, int b){
        return Math.min(a,b);
    }
}

