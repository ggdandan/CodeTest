import java.util.*;

public class ProrityQueueTest {
    public static void main(String args[])
    {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        pq.add(3);
        pq.add(5);
        pq.add(7);
        pq.add(1);
        while(!pq.isEmpty())
        {
            System.out.println(pq.remove());
        }
    }
}
