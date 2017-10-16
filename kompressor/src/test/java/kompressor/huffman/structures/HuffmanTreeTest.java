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
        assertEquals(tree.searchCharacter(list) == 'e', true);
        
        list = new IntList();
        list.add(1);
        list.add(0);
        assertEquals(tree.searchCharacter(list) == 'i', true);

        list = new IntList();
        list.add(0);
        list.add(0);
        assertEquals(tree.searchCharacter(list) == 'o', true);
        
        list = new IntList();
        list.add(0);
        list.add(1);
        list.add(1);
        assertEquals(tree.searchCharacter(list) == 'b', true);
        
        list = new IntList();
        list.add(0);
        list.add(1);
        list.add(0);
        assertEquals(tree.searchCharacter(list) == 'c', true);
    }
    
    @Test
    public void testSearchCode() {
        IntList list = tree.searchCode('e');
        assertEquals(list.get(0) == 1, true);
        assertEquals(list.get(1) == 1, true);
        
        list = tree.searchCode('i');
        assertEquals(list.get(0) == 1, true);
        assertEquals(list.get(1) == 0, true);
        
        list = tree.searchCode('o');
        assertEquals(list.get(0) == 0, true);
        assertEquals(list.get(1) == 0, true);
        
        list = tree.searchCode('b');
        assertEquals(list.get(0) == 0, true);
        assertEquals(list.get(1) == 1, true);
        assertEquals(list.get(2) == 1, true);

        list = tree.searchCode('c');
        assertEquals(list.get(0) == 0, true);
        assertEquals(list.get(1) == 1, true);
        assertEquals(list.get(2) == 0, true);
    }
    
}
