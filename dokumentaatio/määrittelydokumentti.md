## Määrittelydokumentti

Aiheena on syötetyn tekstin ja tekstitiedostojen tiivistäminen, joka toteutetaan (ainakin) Lempel-Ziv-Welch -algoritmilla. Valitsin tämän algoritmin, koska sen toiminta on helppo ymmärtää.

LZW:n sanakirjat (kaksi erilaista, toinen tiivistämiseen ja toinen purkamiseen) toteutettu hajautustaulujen avulla, koska tällöin insert-operaatio on vakioaikainen ja search-operaatio on myös (keskimäärin).

Tavoitteena on totetuttaa molemmat LZW:n toiminnot aika-ja tilavaativuudella O(n).


##### Lähteet
https://www.cs.duke.edu/csed/curious/compression/lzw.html
http://web.mit.edu/6.02/www/s2012/handouts/3.pdf
