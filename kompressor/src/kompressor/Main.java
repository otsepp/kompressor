
package kompressor;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        String s = "TOBEORNOTTOBEORTOBEORNOT"; //[84, 79, 66, 69, 79, 82, 78, 79, 84, 256, 258, 260, 265, 259, 261, 263]
//        String s = "abababab"; //poikkeustapaus
        String s = "";
        System.out.println(s + "(length: " + s.length() + ")");
        
        List<Integer> encoded = LempelZivWelch.encode(s);
        System.out.println("-> " + encoded + " (length: " + encoded.size() + ")");
//
        String decoded = LempelZivWelch.decode(encoded);
        System.out.println("-> " + decoded + ", " + s.equals(decoded));
        System.out.println(decoded.equals(""));
    }
    
}
