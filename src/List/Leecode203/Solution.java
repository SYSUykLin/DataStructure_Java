package List.Leecode203;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val){
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if (head == null){
            return null;
        }

        ListNode pre = head;
        while (pre.next != null){
            if (pre.next.val == val){
                ListNode delNode = pre.next;
                pre.next = delNode.next;
                delNode.next = null;
            }else {
                pre = pre.next;
            }
        }
        return head;
    }

    public ListNode removeElements_version2(ListNode head, int val){
        if (head == null){
            return null;
        }
        ListNode res = removeElements(head.next, val);
        if (head.val == val){
            return res;
        }else {
            head.next = res;
            return head;
        }
    }
}
