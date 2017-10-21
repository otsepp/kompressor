## M��rittelydokumentti

Aiheena on sy�tetyn tekstin tiivist�minen Lempel-Ziv-Welch- ja Huffman-algoritmeilla. 

LZW:n sanakirjat (kaksi erilaista, toinen tiivist�miseen ja toinen purkamiseen) toteutettu hajautustaulujen avulla, koska t�ll�in insert-operaatio on vakioaikainen ja search-operaatio on my�s (keskim��rin).
Algoritmin aikavaativuus pit�isi t�ll�in olla o(n).

Huffman-algoritmi k�ytt�� puurakennetta ja prioritettijonoa. Lis�ksi Huffman k�ytt�� listarakennetta koodien tallettamiseen. Tavoitteena on O(n log k), miss� n on sy�tteen koko
ja k on eri merkkien lukum��r�.


##### L�hteet
https://www.cs.duke.edu/csed/curious/compression/lzw.html
http://www.soc.napier.ac.uk/~bill/pdf/ADCO_C03.PDF