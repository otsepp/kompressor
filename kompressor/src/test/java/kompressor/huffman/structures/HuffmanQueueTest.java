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
        assertEquals(q.poll().getCharacter() == 'c', true);
        assertEquals(q.poll().getCharacter() == 'b', true);
        assertEquals(q.poll().getCharacter() == 'e', true);
        assertEquals(q.poll().getCharacter() == 'i', true);
        assertEquals(q.poll().getCharacter() == 'o', true);
        assertEquals(q.poll(), null);
    }
    
    @Test 
    public void testAdd() {
        q.add(new HuffmanNode('i', 1));
        assertEquals(q.poll().getCharacter() == 'i', true);
    }
    
}
