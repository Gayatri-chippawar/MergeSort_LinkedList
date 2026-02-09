package Linked_List;

public class MergeSort {
    public static class Node{
        int data;
        Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    public static Node head;
    public static Node tail;

    private Node getMid(Node head){
        Node slow = head;
        Node fast = head.next;

        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private Node merge(Node lhHead,Node rtHead){
        Node mergedLL = new Node(-1);
        Node temp = mergedLL;

        while (rtHead!=null && lhHead!=null){
            if(lhHead.data > rtHead.data){
                temp.next = rtHead;
                rtHead = rtHead.next;
                temp = temp.next;
            }
            else {
                temp.next = lhHead;
                lhHead = lhHead.next;
                temp = temp.next;
            }
        }
        while (lhHead != null){
            temp.next = lhHead;
            lhHead = lhHead.next;
            temp = temp.next;
        }
        while (rtHead != null){
            temp.next = rtHead;
            rtHead = rtHead.next;
            temp = temp.next;
        }
        return mergedLL.next;
    }

    public Node mergeSort(Node head){
        //base case
        if(head==null || head.next==null){
            return head;
        }
        //step 1 - find mid
        Node mid = getMid(head);
        Node rtHead = mid.next;
        mid.next = null;

        // lh - rh ms
       Node newLeft =   mergeSort(head);
       Node newRight = mergeSort(rtHead);

        //merge
        return merge(newLeft,newRight);
    }
    public void addFirst(int data){

        //step1:create new node
        Node newNode = new Node(data);


        //Empty linkedList
        if(head == null){
            head = tail = newNode;
            return;
        }
        //step2:next node poiting to head
        newNode.next = head;

        //step3:head = newNode
        head = newNode;
    }
    public  void printList(){//O(n)
        if(head == null){
            System.out.println("Linked List is empty");
            return;
        }
        Node temp = head;

        while (temp!=null){
            System.out.print(temp.data+"->");
            temp = temp.next;
        }
        System.out.print("null");
        System.out.println();
    }

    public static void main(String[] args) {
        MergeSort mm = new MergeSort();
        mm.addFirst(1);
        mm.addFirst(3);
        mm.addFirst(2);
        mm.addFirst(6);

        System.out.print("Not sorted : ");
        mm.printList();
        mm.head = mm.mergeSort(mm.head);
        System.out.print("Sorted : ");
        mm.printList();
        //O(nlogn)
    }
}
