package kompressor.lzw.structures;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class lzwLinkedListTest {
    
    public lzwLinkedListTest() {
    }

    @Test
    public void addAndSearchTest() {
        lzwLinkedList<String, Integer> l1 = new lzwLinkedList();
        l1.add("a", 1);
        l1.add("b", 2);
        assertEquals(l1.search("a") == 1, true);
        assertEquals(l1.search("b") == 2, true);
    }
    
}
