package kompressor.huffman;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HuffmanTreeTest {
    private HuffmanTreeDemo tree;
    
    public HuffmanTreeTest() {
    }
    
    @Before
    public void setUp() {
        HuffmanNode root = new HuffmanNode(null, 10);
        root.setRight(new HuffmanNode('a', 6));

        HuffmanNode left = new HuffmanNode(null, 4);
        left.setLeft(new HuffmanNode('b', 2));
        left.setRight(new HuffmanNode('c', 2));
        
        root.setLeft(left);

        this.tree = new HuffmanTreeDemo(root);
    }
    
    @Test 
    public void searchCharacterTest() {
        assertEquals(new Character('a'), this.tree.searchCharacter("0"));
        assertEquals(new Character('b'), this.tree.searchCharacter("11"));
        assertEquals(new Character('c'), this.tree.searchCharacter("10"));
    }
    
    @Test 
    public void searchCharacterTestEmpty() {
        assertEquals(null, this.tree.searchCharacter(""));
    }
    
    @Test
    public void searchCodeTest() {
        assertEquals("0", this.tree.searchCode('a'));
        assertEquals("11", this.tree.searchCode('b'));
        assertEquals("10", this.tree.searchCode('c'));

    }
    
    @Test
    public void searchCodeTestOneChar() {
        this.tree = new HuffmanTreeDemo(new HuffmanNode('a', 1));
        assertEquals("0", this.tree.searchCode('a'));
    }
    
}
