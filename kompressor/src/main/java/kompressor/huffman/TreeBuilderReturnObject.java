package kompressor.huffman;

import kompressor.huffman.structures.HuffmanTree;

 public class TreeBuilderReturnObject {
        private HuffmanTree tree;
        private byte[] leftoverBytes;
        TreeBuilderReturnObject(HuffmanTree tree, byte[] leftoverBytes) {
            this.tree = tree;
            this.leftoverBytes = leftoverBytes;
        }
        public HuffmanTree getTree() {
            return this.tree;
        }
        public byte[] getLeftoverBytes() {
            return this.leftoverBytes;
        }
    }
