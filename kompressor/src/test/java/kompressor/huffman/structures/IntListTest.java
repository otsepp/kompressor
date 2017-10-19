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
        assertNull(list.get(2));
        assertNull(list.get(6));
    }

    @Test
    public void testAdd() {
        for (int i = 0; i <= 5; i++) {
            list.add(i);
        }
        assertEquals(5, list.get(5).intValue());
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
    
    @Test
    public void testSecondConstructor() {
        list.add(1);
        IntList listNew = new IntList(list);
        assertEquals(1, listNew.get(0).intValue());
        assertNull(listNew.get(1));
    }
    
}
