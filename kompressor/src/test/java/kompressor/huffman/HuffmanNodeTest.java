package kompressor.huffman;

import java.util.PriorityQueue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HuffmanNodeTest {
    
    public HuffmanNodeTest() {
    }
    
    @Test
    public void compareToTest() {
        PriorityQueue<HuffmanNode> q = new PriorityQueue();
        q.add(new HuffmanNode('a', 2));
        q.add( new HuffmanNode('b', 1));
        q.add(new HuffmanNode('c', 3));
        
        assertEquals(1, q.poll().getFrequency());
        assertEquals(2, q.poll().getFrequency());
        assertEquals(3, q.poll().getFrequency());
    }
    
    
}
