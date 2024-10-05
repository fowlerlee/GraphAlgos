package leetcode;

import java.util.*;
import java.util.Arrays;

public class ReverseNode {

    /*
     * Input: head = [{2,1}, {4,3}, {5}], k = 2
     * Output: [2,1,4,3,5]
     */

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode " + val + "->" + this.next;
        }
    }

    private ListNode[] reverseList(ListNode head, int k) {
        ListNode curr = head;
        ListNode prev = null, next = null;

        for (int i = 0; i < k; i++) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return new ListNode[] { prev, curr };
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode ptr = dummy;

        while (ptr != null) {
            ListNode tracker = ptr;

            for (int i = 0; i < k; i++) {
                if (tracker == null)
                    break;

                tracker = tracker.next;
            }

            if (tracker == null) {
                break;
            }

            ListNode[] reversedData = reverseList(ptr.next, k);

            ListNode prev = reversedData[0];
            ListNode curr = reversedData[1];

            ListNode lastNodeOfReversedGroup = ptr.next;

            lastNodeOfReversedGroup.next = curr;

            ptr.next = prev;

            ptr = lastNodeOfReversedGroup;

        }
        return dummy.next;
    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        ReverseNode r = new ReverseNode();
        System.out.println(r.reverseKGroup(l1, 2));

    }

}
