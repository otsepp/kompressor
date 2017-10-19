package kompressor.huffman.structures;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

public class HuffmanTreeTest {
    private HuffmanTree tree;
    
    public HuffmanTreeTest() {
    }

    @Before
    public void setUp() {
        tree = new HuffmanTree(new HuffmanNode(null));
        tree.getRoot().setLeft(new HuffmanNode(null));
        tree.getRoot().getLeft().setLeft(new HuffmanNode('e'));
        tree.getRoot().getLeft().setRight(new HuffmanNode('i'));
        
        tree.getRoot().setRight(new HuffmanNode(null));
        tree.getRoot().getRight().setLeft(new HuffmanNode(null));
        tree.getRoot().getRight().getLeft().setLeft(new HuffmanNode('b'));
        tree.getRoot().getRight().getLeft().setRight(new HuffmanNode('c'));
        tree.getRoot().getRight().setRight(new HuffmanNode('o'));
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
        IntList list = tree.searchCode('e');
        assertEquals(1, list.get(0).intValue());
        assertEquals(1, list.get(1).intValue());
        
        list = tree.searchCode('i');
        assertEquals(1, list.get(0).intValue());
        assertEquals(0, list.get(1).intValue());
        
        list = tree.searchCode('o');
        assertEquals(0, list.get(0).intValue());
        assertEquals(0, list.get(1).intValue());
        
        list = tree.searchCode('b');
        assertEquals(0, list.get(0).intValue());
        assertEquals(1, list.get(1).intValue());
        assertEquals(1, list.get(2).intValue());

        list = tree.searchCode('c');
        assertEquals(0, list.get(0).intValue());
        assertEquals(1, list.get(1).intValue());
        assertEquals(0, list.get(2).intValue());
    }
    
}
