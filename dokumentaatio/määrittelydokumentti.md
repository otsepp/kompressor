##H2 M��rittelydokumentti

Aiheena on sy�tetyn tekstin ja tekstitiedostojen tiivist�minen, joka toteutetaan (ainakin) Lempel-Ziv-Welch -algoritmilla. Valitsin t�m�n algoritmin, koska sen toiminta on helppo ymm�rt��.

LZW:n sanakirjat (kaksi erilaista, toinen tiivist�miseen ja toinen purkamiseen) toteutettu hajautustaulujen avulla, koska t�ll�in insert-operaatio on vakioaikainen ja search-operaatio on my�s (keskim��rin).

Tavoitteena on totetuttaa molemmat LZW:n toiminnot aika-ja tilavaativuudella O(n).

##H5 L�hteet
https://www.cs.duke.edu/csed/curious/compression/lzw.html