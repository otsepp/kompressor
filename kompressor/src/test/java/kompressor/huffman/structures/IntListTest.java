package kompressor.huffman.structures;

import static org.junit.Assert.assertEquals;
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
        assertEquals(list.get(0), null);
        list.add(1);
        list.add(0);
        assertEquals(list.get(-1), null);
        assertEquals(list.get(0) == 1, true);
        assertEquals(list.get(1) == 0, true);
        assertEquals(list.get(2), null);
    }

    @Test
    public void testAdd() {
        for (int i = 0; i <= 5; i++) {
            list.add(i);
        }
        assertEquals(list.get(5) == 5, true);
    }

    @Test
    public void testIterator() {
        for (int i = 0; i < 5; i++) {
            list.add(1);
        }
        for (int i : list) {
            assertEquals(i, 1);
        }
    }
    
    @Test
    public void testSecondConstructor() {
        list.add(1);
        IntList listNew = new IntList(list);
        assertEquals(listNew.get(0) == 1, true);
        assertEquals(listNew.get(1), null);
    }
    
}
