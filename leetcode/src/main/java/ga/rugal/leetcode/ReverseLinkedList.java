package ga.rugal.leetcode;

/**
 *
 * @author Rugal Bernstein
 */
public class ReverseLinkedList
{

    public ListNode reverseList(ListNode head)
    {
        if (null == head || null == head.next)
        {
            return head;
        }
        ListNode last = head;
        ListNode current = last.next;
        head.next = null;
        while (null != current)
        {
            ListNode temp = current.next;
            current.next = last;
            last = current;
            current = temp;
        }
        return last;
    }
}
