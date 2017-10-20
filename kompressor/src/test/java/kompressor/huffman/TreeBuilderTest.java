package kompressor.huffman;

import java.io.IOException;
import kompressor.huffman.structures.HuffmanNode;
import kompressor.huffman.structures.HuffmanTree;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
        
        HuffmanNode p = t.getRoot();
        HuffmanNode l;
        HuffmanNode r;
        
        assertNull(p.getCharacter());
        assertEquals(15, p.getFrequency());
        assertNull(p.getParent());
   
         l = p.getLeft();
         assertNull(l.getCharacter());
         assertEquals(9, l.getFrequency());
         assertEquals(p, l.getParent());
         r = p.getRight();
         assertNull(r.getCharacter());
         assertEquals(6, r.getFrequency());
         assertEquals(p, r.getParent());
         
         p = l;
         l = p.getLeft();
         assertEquals('e', l.getCharacter().charValue());
         assertEquals(5, l.getFrequency());
         assertEquals(p, l.getParent());
         r = p.getRight();
         assertEquals('i', r.getCharacter().charValue());
         assertEquals(4, r.getFrequency());
         assertEquals(p, r.getParent());

         p = t.getRoot().getRight();
         l = p.getLeft();
         assertNull(l.getCharacter());
         assertEquals(3, l.getFrequency());
         assertEquals(p, l.getParent());
         r = p.getRight();
         assertEquals('o', r.getCharacter().charValue());
         assertEquals(3, r.getFrequency());
         assertEquals(p, r.getParent());
         
         p = l;
         l = p.getLeft();
         assertEquals('b', l.getCharacter().charValue());
         assertEquals(2, l.getFrequency());
         assertEquals(p, l.getParent());
         r = p.getRight();
         assertEquals('c', r.getCharacter().charValue());
         assertEquals(1, r.getFrequency());
         assertEquals(p, r.getParent());
    }

    @Test
    public void testCreateTreeFromHeader() throws IOException {
        byte[] header = new byte[]{0x2C, (byte) 0xB6, (byte) 0x92, (byte) 0xC5, 0x63, (byte) 0xB7, (byte) 0x80, 0x00};
        HuffmanTree t = TreeBuilder.createTreeFromHeader(header).getTree();
        
        assertNull(t.getRoot().getCharacter());
        assertNull(t.getRoot().getLeft().getCharacter());
        assertEquals('e', t.getRoot().getLeft().getLeft().getCharacter().charValue());
        assertEquals('i', t.getRoot().getLeft().getRight().getCharacter().charValue());
        assertNull(t.getRoot().getRight().getCharacter());
        
        assertEquals('o', t.getRoot().getRight().getRight().getCharacter().charValue());
        
        assertNull(t.getRoot().getRight().getLeft().getCharacter());
        assertEquals('b', t.getRoot().getRight().getLeft().getLeft().getCharacter().charValue());
        assertEquals('c', t.getRoot().getRight().getLeft().getRight().getCharacter().charValue());
    }
    
}
