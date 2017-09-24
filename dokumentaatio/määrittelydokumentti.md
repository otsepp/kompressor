## Määrittelydokumentti

Aiheena on syötetyn tekstin tiivistäminen Lempel-Ziv-Welch- ja Huffman-algoritmeilla. Ohjelma ei oikeasti pakkaa tietoa tiivistettyyn binäärimuotoon, vaan demonstroi edellämainittujen algoritmien toimintaa.

LZW:n sanakirjat (kaksi erilaista, toinen tiivistämiseen ja toinen purkamiseen) toteutettu hajautustaulujen avulla, koska tällöin insert-operaatio on vakioaikainen ja search-operaatio on myös (keskimäärin).
Algoritmin aikavaativuus pitäisi tällöin olla o(n).

Huffman-algoritmi käyttää puurakennetta ja prioritettijonoa, jolloin voidaan saavutta aikavaativuus O(n log n).


##### Lähteet
https://www.cs.duke.edu/csed/curious/compression/lzw.html
http://web.mit.edu/6.02/www/s2012/handouts/3.pdf
http://www.soc.napier.ac.uk/~bill/pdf/ADCO_C03.PDF