package kompressor.huffman;

import kompressor.huffman.structures.HuffmanNode;
import kompressor.huffman.structures.HuffmanTree;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TreeBuilderTest {
    
    public TreeBuilderTest() {
    }
    
    @Test
    public void testCreateTreeFromUnencodedBytes() {
        //"cbboooiiiieeeee"
        byte[] bytes = new byte[]{
            (byte) 'c',
            (byte) 'b', (byte) 'b',
            (byte) 'o', (byte) 'o', (byte) 'o',
            (byte) 'i', (byte) 'i', (byte) 'i', (byte) 'i',
            (byte) 'e', (byte) 'e', (byte) 'e', (byte) 'e', (byte) 'e',};
            
        HuffmanTree t = TreeBuilder.createTreeFromUnencodedBytes(bytes);
        
        HuffmanNode n = t.getRoot();
        
        assertNull(n.getCharacter());
        assertEquals(n.getFrequency(), 15);
        
        n = n.getLeft();
        assertNull(n.getCharacter());
        assertEquals(n.getFrequency(), 9);
        
        n = n.getLeft();
        assertTrue(n.getCharacter() == 'e');
        assertEquals(n.getFrequency(), 5);

        n = t.getRoot().getLeft().getRight();
        assertTrue(n.getCharacter() == 'i');
        assertEquals(n.getFrequency(), 4);
        
        n = t.getRoot().getRight();
        assertNull(n.getCharacter());
        assertEquals(n.getFrequency(), 6);
        
        n = n.getRight();
        assertTrue(n.getCharacter() == 'o');
        assertEquals(n.getFrequency(), 3);
        
        n = t.getRoot().getRight().getLeft();
        assertNull(n.getCharacter());
        assertEquals(n.getFrequency(), 3);
        
        n = n.getLeft();
        assertTrue(n.getCharacter() == 'b');
        assertEquals(n.getFrequency(), 2);
        
        n = t.getRoot().getRight().getLeft().getRight();
        assertTrue(n.getCharacter() == 'c');
        assertEquals(n.getFrequency(), 1);
    }

    @Test
    public void testCreateTreeFromHeader() {
        byte[] header = new byte[]{0x2C, (byte) 0xB6, (byte) 0x92, (byte) 0xC5, 0x63, (byte) 0xB7, (byte) 0x80};
        HuffmanTree t = TreeBuilder.createTreeFromHeader(header).getTree();
        
        assertNull(t.getRoot().getCharacter());
        
        assertNull(t.getRoot().getLeft().getCharacter());
        assertTrue(t.getRoot().getLeft().getLeft().getCharacter() == 'e');
        assertTrue(t.getRoot().getLeft().getRight().getCharacter() == 'i');
        
        assertNull(t.getRoot().getRight().getCharacter());
        assertTrue(t.getRoot().getRight().getRight().getCharacter() == 'o');
        
        assertNull(t.getRoot().getRight().getLeft().getCharacter());
        assertTrue(t.getRoot().getRight().getLeft().getLeft().getCharacter() == 'b');
        assertTrue(t.getRoot().getRight().getLeft().getRight().getCharacter() == 'c');
    }
    
}
