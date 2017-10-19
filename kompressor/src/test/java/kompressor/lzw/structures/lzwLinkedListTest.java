package kompressor.lzw.structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

public class lzwLinkedListTest {
    
    public lzwLinkedListTest() {
    }

    @Test
    public void addAndSearchTest() {
        lzwLinkedList<String, Integer> l1 = new lzwLinkedList();
        l1.add("a", 1);
        l1.add("b", 2);
        assertEquals(1, l1.search("a").intValue());
        assertEquals(2, l1.search("b").intValue());
    }
    
    @Test
    public void SearchNotFoundTest() {
        lzwLinkedList<String, Integer> l1 = new lzwLinkedList();
        assertNull(l1.search("ei löydy"));
    }
    
}
