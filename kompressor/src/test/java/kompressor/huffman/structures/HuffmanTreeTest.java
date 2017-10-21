package kompressor.huffman.structures;

import kompressor.huffman.TreeBuilder;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HuffmanTreeTest {
    private HuffmanTree tree;
    
    public HuffmanTreeTest() {
    }

    @Test
    public void testSearchCode() {
         tree = TreeBuilder.createTreeFromUnencodedBytes("cbboooiiiieeeee".getBytes()); 
        
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
