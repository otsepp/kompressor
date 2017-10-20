package kompressor.huffman.structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.Before;

public class IntListTest {
    private IntList list;
    
    public IntListTest() {
    }
    
    @Before
    public void setUp() {
        list = new IntList();
    }
    
    @Test
    public void testGet() {
        assertNull(list.get(0));
        list.add(1);
        list.add(0);
        assertNull(list.get(-1));
        assertEquals(1, list.get(0).intValue());
        assertEquals(0, list.get(1).intValue());
        assertNull(list.get(2));    //pienempi kuin taulun oikea koko (5), mutta suurempi index-kentän arvo
        assertNull(list.get(6));
    }

    @Test
    public void testAdd() {
        for (int i = 0; i <= 5; i++) {
            list.add(i);
            assertEquals(i, list.get(i).intValue());
        }
    }

    @Test
    public void testIterator() {
        for (int i = 0; i < 5; i++) {
            list.add(1);
        }
        for (int i : list) {
            assertEquals(1, i);
        }
    }
    
}
