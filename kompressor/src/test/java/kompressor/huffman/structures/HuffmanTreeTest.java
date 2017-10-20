package kompressor.huffman.structures;

import kompressor.huffman.TreeBuilder;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

public class HuffmanTreeTest {
    private HuffmanTree tree;
    
    public HuffmanTreeTest() {
    }

    @Before
    public void setUp() {
        tree = TreeBuilder.createTreeFromUnencodedBytes("cbboooiiiieeeee".getBytes());
//        	 null
//            /       \
//        null      null
//         / \       /   \
//       e	i  null   o
//	   /  \
//	 b    c
    }
    
    @Test
    public void testSearchCharacter() {
        IntList list = new IntList();
        list.add(1);
        list.add(1);
        assertEquals('e', tree.searchCharacter(list).charValue());
        
        list = new IntList();
        list.add(1);
        list.add(0);
        assertEquals('i', tree.searchCharacter(list).charValue());

        list = new IntList();
        list.add(0);
        list.add(0);
        assertEquals('o', tree.searchCharacter(list).charValue());
        
        list = new IntList();
        list.add(0);
        list.add(1);
        list.add(1);
        assertEquals('b', tree.searchCharacter(list).charValue());
        
        list = new IntList();
        list.add(0);
        list.add(1);
        list.add(0);
        assertEquals('c', tree.searchCharacter(list).charValue());
    }
    
    @Test
    public void testSearchCode() {
        IntQueue list = tree.searchCode('e');
        assertEquals(1, list.pop().intValue());
        assertEquals(1, list.pop().intValue());
        
        list = tree.searchCode('i');
        assertEquals(1, list.pop().intValue());
        assertEquals(0, list.pop().intValue());
        
        list = tree.searchCode('o');
        assertEquals(0, list.pop().intValue());
        assertEquals(0, list.pop().intValue());
        
        list = tree.searchCode('b');
        assertEquals(0, list.pop().intValue());
        assertEquals(1, list.pop().intValue());
        assertEquals(1, list.pop().intValue());

        list = tree.searchCode('c');
        assertEquals(0, list.pop().intValue());
        assertEquals(1, list.pop().intValue());
        assertEquals(0, list.pop().intValue());
    }
    
}
