package kompressor.huffman;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HuffmanQueueTest {
    
    public HuffmanQueueTest() {
    }

    @Test
    public void AddAndPollTest() {
        HuffmanQueue q = new HuffmanQueue();
        
        HuffmanNode n1 = new HuffmanNode('e', 57);
        q.add(n1);
        HuffmanNode n2 = new HuffmanNode('i', 51);
        q.add(n2);
        HuffmanNode n3 = new HuffmanNode('b', 12);
        q.add(n3);
        HuffmanNode n4 = new HuffmanNode('c', 3);
        q.add(n4);
        HuffmanNode n5 = new HuffmanNode('o', 33);
        q.add(n5);

        assertEquals(q.poll().getCharacter(), n4.getCharacter());
        assertEquals(q.poll().getCharacter(), n3.getCharacter());
        assertEquals(q.poll().getCharacter(), n5.getCharacter());
        assertEquals(q.poll().getCharacter(), n2.getCharacter());
        assertEquals(q.poll().getCharacter(), n1.getCharacter());
    }
        
}
