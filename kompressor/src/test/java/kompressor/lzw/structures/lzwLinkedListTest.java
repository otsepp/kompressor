package kompressor.lzw.structures;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class lzwLinkedListTest {
    
    public lzwLinkedListTest() {
    }

    @Test
    public void testAddAndSearch() {
        lzwLinkedList<String, Integer> list = new lzwLinkedList();
        list.add("a", 1);
        list.add("b", 2);
        assertEquals(1, list.search("a").intValue());
        assertEquals(2, list.search("b").intValue());
    }
    
    @Test
    public void testSearchNotFound() {
        lzwLinkedList<String, Integer> list = new lzwLinkedList();
        assertNull(list.search("ei löydy"));
    }
    
}
