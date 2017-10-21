## Määrittelydokumentti

Aiheena on syötetyn tekstin tiivistäminen Lempel-Ziv-Welch- ja Huffman-algoritmeilla. 

LZW:n sanakirjat (kaksi erilaista, toinen tiivistämiseen ja toinen purkamiseen) toteutettu hajautustaulujen avulla, koska tällöin insert-operaatio on vakioaikainen ja search-operaatio on myös (keskimäärin).
Algoritmin aikavaativuus pitäisi tällöin olla o(n).

Huffman-algoritmi käyttää puurakennetta ja prioritettijonoa. Lisäksi Huffman käyttää listarakennetta koodien tallettamiseen. Tavoitteena on O(n log k), missä n on syötteen koko
ja k on eri merkkien lukumäärä.


##### Lähteet
https://www.cs.duke.edu/csed/curious/compression/lzw.html
http://www.soc.napier.ac.uk/~bill/pdf/ADCO_C03.PDF