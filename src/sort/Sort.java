package sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

public class Sort {

    public static void main(String[] args){
//        int[] arr = {2,4,1,5,2,5,24,0};
//        nonRec_MergeSort(arr);
//        for(int i:arr){
//            System.out.print(i+",");
//        }



        ListNode head = new ListNode(2);
        ListNode dummy = head;

        head.next = new ListNode(1);
        head = head.next;
        head.next = new ListNode(3);
        head = head.next;
        head.next = new ListNode(5);
        head = head.next;
        head.next = new ListNode(4);
        head = head.next;
        head.next = null;
        head = InsertionList(dummy);


        while(head != null){
            System.out.println(head.val);
            head = head.next;
        }

        HashMap<String,String> map = new HashMap<>();

////        Collections
//        System.out.print("321" .compareTo( "32"));
//        Arrays.copyOf(arr,arr.length);

    }


    /**
     * 定义一个标志位，可以使冒泡排序最好是O（n）（数组已经有序的情况下）
     * @param arr
     */
    public static void BubbleSort(int[] arr){
        boolean didSwap = false;
        for(int i=arr.length-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                    didSwap = true;
                }
            }
            if(didSwap){
                return;
            }
        }
    }

    public static void SelectionSort(int[] arr){
        int min;
        for(int i=0;i<arr.length-1;i++){
            min = i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            swap(arr,min,i);
        }
    }

    public static void InsertionSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int value = arr[i];
            int position = i;
            while(position > 0 && arr[position-1] > value){
                arr[position] = arr[position-1];
                position--;
            }
            arr[position] = value;
        }
    }

    //递归归并
    public static void MergeSort(int[] arr){
        sort(arr,0,arr.length-1);
    }

    public static void sort(int[] arr,int left,int right){
        if(left < right){
            int middle = left + (right-left)/2;
            sort(arr,left,middle);
            sort(arr,middle+1,right);
            merge(arr,left,middle,right);
        }
    }

    public static void merge(int[] arr,int left,int middle,int right){
        int[] tmp = new int[right-left+1];
        int i=left,j = middle+1,k=0;
        while(i <= middle && j<=right){
            if(arr[i] > arr[j]){
                tmp[k++] = arr[j++];
            }else{
                tmp[k++] = arr[i++];
            }
        }
        while(i <= middle){
            tmp[k++] = arr[i++];
        }
        while(j <= right){
            tmp[k++] = arr[j++];
        }

        for(i=0;i<k;i++){
            arr[left+i] = tmp[i];
        }
    }


    //非递归归并
    public static void nonRec_MergeSort(int[] arr){
        int len = 1;
        while(len < arr.length){
            for(int i=0;i<arr.length;i+=2*len){
                nonRec_merge(arr,i,len);
            }
            len *=2 ;
        }
    }

    public static void nonRec_merge(int[] arr,int left,int len){
        int i=left;
        int i_len = i+len;
        int j = i+len;
        int j_len = j+len;
        int[] tmp = new int[2*len];
        int k = 0;
        while(i<i_len && j<j_len && i<arr.length){
            if(arr[i] > arr[j]){
                tmp[k++] = arr[j++];
            }else{
                tmp[k++] = arr[i++];
            }
        }
        while(i < i_len && i<arr.length){
            tmp[k++] = arr[i++];
        }
        while(j< j_len && j<arr.length){
            tmp[k++] = arr[j++];
        }
        k = 0;
        while(left<arr.length && left < j){
            arr[left++] = tmp[k++];
        }
    }


    //递归快速排序
    public static void QuickSort(int[] arr){
        qsort(arr,0,arr.length-1);
    }

    public static void qsort(int []arr,int left,int right){
        if(left < right){
            int pivot = partition(arr,left,right);
            qsort(arr,left,pivot-1);
            qsort(arr,pivot+1,right);
        }
    }

    //非递归快速排序
    public static void nonRec_QuickSort(int[] arr){
        nonRec_qsort(arr,0,arr.length-1);
    }

    public static void nonRec_qsort(int[] arr,int left,int right){
        Stack<Integer> stack = new Stack<>();
        stack.push(right);
        stack.push(left);
        while (!stack.isEmpty()){
            int l = stack.pop();
            int r = stack.pop();
            int index = partition(arr,l,r);
            if(l < index-1){
                stack.push(index-1);
                stack.push(l);
            }
            if(r > index+1){
                stack.push(r);
                stack.push(index+1);
            }
        }
    }

    public static int partition(int[] arr,int left,int right){
        int pivot = arr[left];
        while(left < right){
            while (left < right && arr[right] >= pivot) right--;
            arr[left] = arr[right];
            while(left < right && arr[left] <= pivot) left++;
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }


    //堆排序：建堆，交换堆顶和最后的元素，调整堆
    public static void HeapSort(int[] arr){
        for(int i=arr.length/2;i>=0;i--){
            HeapAdjust(arr,i,arr.length);
        }

        for(int i=arr.length-1;i>=0;i--){
            swap(arr,0,i);
            HeapAdjust(arr,0,i);
        }
    }

    public static void HeapAdjust(int[] arr,int i,int n){
        int child;
        int father;
        for(father = arr[i];leftChild(i) < n;i=child){
            child = leftChild(i);
            if(child < n-1 && arr[child] < arr[child+1]){
                child += 1;
            }
            if(father < arr[child]){
                arr[i] = arr[child];
            }else{
                break;
            }
        }
        arr[i] = father;
    }

    public static int leftChild(int i){
        return 2*i+1;
    }



    public static void swap(int[]arr,int i ,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


//-----------------------------链表排序----------------------------------


    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }


    /**
     * 单链表快排
     * @param head
     * @return
     */
    public static ListNode QuickSortList(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode left = new ListNode(-1),leftHead = left;
        ListNode middle = new ListNode(-1),middleHead  =middle;
        ListNode right = new ListNode(-1),rightHead = right;
        int val = head.val;
        while(head != null){
            if(head.val < val){
                left.next = head;
                left = left.next;
            }else if(head.val == val){
                middle.next = head;
                middle = middle.next;
            }else{
                right.next = head;
                right = right.next;
            }
            head = head.next;
        }
        left.next = null;
        middle.next = null;
        right.next = null;
        return concat(QuickSortList(leftHead.next),middleHead.next,QuickSortList(rightHead.next));
    }

    public static ListNode concat(ListNode left,ListNode middle,ListNode right){
        ListNode leftTail = getTail(left);
        ListNode middleTail = getTail(middle);
        middleTail.next = right;
        if(leftTail != null){
            leftTail.next = middle;
            return left;
        }else{
            return middle;
        }
    }

    public static ListNode getTail(ListNode node){
        if(node == null) return null;
        while(node.next != null){
            node = node.next;
        }
        return node;
    }


    /**
     * 单链表归并
     * @param head
     * @return
     */
    public static ListNode MergeSortList(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode fast = head,slow = head,pre = null;
        while(fast != null && fast.next != null){
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        pre.next = null;
        return mergeList(MergeSortList(head),MergeSortList(slow));
    }

    public static ListNode mergeList(ListNode left,ListNode right){
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        while (left != null && right != null){
            if(left.val > right.val){
                node.next = right;
                right = right.next;
            }else{
                node.next = left;
                left = left.next;
            }
            node = node.next;
        }
        while(left != null){
            node.next = left;
            left = left.next;
            node = node.next;
        }

        while(right != null){
            node.next = right;
            right = right.next;
            node = node.next;
        }
        return dummy.next;
    }


    /**
     * 单链表插入排序
     * @param head
     * @return
     */
    public static ListNode InsertionList(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);

        while(head != null){
            ListNode pre = dummy;
            ListNode cur = dummy.next;
            while(cur != null && cur.val < head.val){
                pre = pre.next;
                cur = cur.next;
            }
            ListNode tmp = new ListNode(head.val);
            pre.next = tmp;
            tmp.next = cur;

            head = head.next;

        }
        return dummy.next;
    }

}
