package kompressor.huffman;

public class HuffmanDemo {
    private String s;
    private String sEncoded;
    private HuffmanTreeDemo tree;
    
    public HuffmanDemo(String s) {
        this.setString(s);
    }
    
    public String getString() {
        return this.s;
    }
    
    public String getEncodedString() {
        return this.sEncoded;
    }
    
    public final void setString(String s) {
        this.s = s;
        this.sEncoded = "";
        this.tree = this.buildTree(this.s);
    }
    
    public String encode() {
        StringBuilder code = new StringBuilder();
        for (char c : this.s.toCharArray()) {
            code.append(this.tree.searchCode(c));
        }
        this.sEncoded = code.toString();
        return this.sEncoded;
    }
    
    //merkkijono täytyy pakata ensin
    public String decode() {
        StringBuilder sDecoded = new StringBuilder();
        StringBuilder code = new StringBuilder();
        
        for (char c : this.sEncoded.toCharArray()) {
            code.append(c);
            Character cFound = tree.searchCharacter(code.toString());
            
            if (cFound != null) {
                sDecoded.append(cFound);
                code = new StringBuilder();
            } 
        }
        return sDecoded.toString();
    }
    
    private HuffmanTreeDemo buildTree(String s) {
        int[] freqs = new int[256];
        for (char c : s.toCharArray()) { 
            freqs[c]++;
        }
        HuffmanQueue q = new HuffmanQueue();

        for (int i = 0; i < freqs.length; i++) {   
            if (freqs[i] > 0) {
                q.add(new HuffmanNode((char) i, freqs[i])); 
            }
        }
        HuffmanTreeDemo t = null;
        while (q.size() > 0) {
            //jonosta poistetaan solmut (lehdet), joiden kirjaimia esiintyy vähiten
            HuffmanNode n0 = q.poll(); 
            HuffmanNode n1 = q.poll();  

            if (n1 != null) {
                //kun löytyy kaksi solmua, ne yhdistetään uudeksi
                HuffmanNode nu = new HuffmanNode(null, n0.getFrequency() + n1.getFrequency());
                nu.setRight(n0);
                nu.setLeft(n1);
                q.add(nu);  
            } else {
                //viimeinen solmu loydetään, asetetaan puun juureksi
                t = new HuffmanTreeDemo(n0);
            }
        }
        return t;
    }
    
}
