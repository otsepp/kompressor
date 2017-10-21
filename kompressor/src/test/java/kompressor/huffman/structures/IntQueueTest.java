package kompressor.huffman.structures;

import org.junit.Test;
import static org.junit.Assert.*;

public class IntQueueTest {
    
    public IntQueueTest() {
    }

    @Test
    public void testPushandPop() {
        IntQueue q = new IntQueue();
        assertNull(q.pop());
        
        q.push(1);
        q.push(0);
        
        assertEquals(0, q.pop().intValue());
        assertEquals(1, q.pop().intValue());
        assertNull(q.pop());
    }

    @Test
    public void testExtend() {
        IntQueue q = new IntQueue();
        for (int i = 0; i < 10; i++) {
            q.push(0);
        }
        q.push(1);  //11. alkio, lista kasvatetaan
        
        assertEquals(1, q.pop().intValue());
    }
}
