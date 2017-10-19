package kompressor.huffman.structures;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

public class HuffmanQueueTest {
    private HuffmanQueue q;
    
    public HuffmanQueueTest() {
    }

    @Before
    public void setUp() {
        q = new HuffmanQueue();
        q.add(new HuffmanNode('e', 33));
        q.add(new HuffmanNode('i', 51));
        q.add(new HuffmanNode('b', 12));
        q.add(new HuffmanNode('c', 3));
        q.add(new HuffmanNode('o', 57));
    }
    
    @Test
    public void testSize() {
        assertEquals(q.size(), 5);
    }

    @Test
    public void testPoll() {
        assertEquals('c', q.poll().getCharacter().charValue());
        assertEquals('b', q.poll().getCharacter().charValue());
        assertEquals('e', q.poll().getCharacter().charValue());
        assertEquals('i', q.poll().getCharacter().charValue());
        assertEquals('o', q.poll().getCharacter().charValue());
        assertEquals(null, q.poll());
    }
    
    @Test 
    public void testAdd() {
        q.add(new HuffmanNode('i', 1));
        assertEquals('i', q.poll().getCharacter().charValue());
    }
    
}
