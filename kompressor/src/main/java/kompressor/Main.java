
package kompressor;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Node implements Comparable<Node>{
    Character c;
    int freq;
    Node right;
    Node left;
    Node parent;
    public Node(Character c, int freq) {
        this.c = c;
        this.freq = freq;
    }
    public Node(Character c, int freq, Node right, Node left) {
        this.c = c;
        this.freq = freq;
        this.right = right;
        this.left = left;
    }
    @Override
    public int compareTo(Node n) {
        if (n.freq > this.freq) {
            return -1;
        } else if (n.freq < this.freq) {
            return 1;
        }
        return 0;
    }
    @Override
    public String toString() {
        if (freq == 2) {
            System.out.println("left: " + left + ", right: " + right);
        }
        return c + ", freq: " + freq;
    }
}

class Tree {
    Node root;
    public Tree(Node root) {
        this.root = root;
    }
}

public class Main {

    public static void main(String[] args) {
        int[] freqs = new int[256];
//        Scanner s = new Scanner(System.in);
//        String input = s.nextLine();
String input = "ajan aina autoa";
        
        for (char c : input.toCharArray()) {
            freqs[c]++;
        }
        
        PriorityQueue<Node> q = new PriorityQueue();
        
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] > 0) {
                System.out.println((char) i + "->" + freqs[i]);
                q.add(new Node((char) i, freqs[i]));
            }
        }
        Tree t = null;
        
        while (q.size() > 0) {
            Node n0 = q.poll();
            Node n1 = q.poll();
            
            System.out.println(n0);
            System.out.println(n1);
            
            if (n1 != null) {
                Node nu = new Node(null, n0.freq + n1.freq, n0, n1);
                System.out.println(nu);
                q.add(nu);
            } else {
                t = new Tree(n0);
            }
            System.out.println("**");
        }

        
        
    }
    
}
