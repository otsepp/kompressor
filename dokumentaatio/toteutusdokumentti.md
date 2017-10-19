# Toteutus



## LempelZivWelch

**Pakkausalgoritmi** saa sy�tteen� taulukon tavuja, jotka kuvaavat ASCII-merkist�n symboleita.
Luettuja symboleita yhdistet��n merkkijonoiksi, jotka lis�t��n ns. sanakirjaan, joka sis�lt��
merkkijono-koodi -pareja. Hajautustaululla toteutettu sanakirja sis�lt�� alussa kaikki 256 ASCII-symbolia, 
ja kun sen maksimikoko (4096) saavutetaan, se resetoidaan takaisin tuohon alkutilaan.
Koodit ovat 12-bitin kokoisia, ja ne kootaan yhteen k�ytt�en luokkaa ByteArrayOutputStream.
Algoritmi palauttaa taulukon tavuja.

Pakkausalgoritmi pohjautuu seuraavaan pseudokoodiin:

![lzw-encode-img](kuvat/lzwencode.png)
**l�hde: https://www.cs.duke.edu/csed/curious/compression/lzw.html**


**Purkamisalgoritmi** saa my�s sy�tteen� taulukon tavuja, joka sis�lt�� 12-bittisi� koodeja. Koodit 
k��nnet��n merkkijonoiksi sanakirjan avulla, mutta nyt koodit ovat avaimia. Sanakirjaa t�ydennet��n taas 
sy�tett� luettaessa, mutta erilaisella periaatteella. Taas k�ytet��n ByteArrayOutputStream.

Purkamisalgoritmi pohjautuu seuraavaan pseudokoodiin:

![lzw-encode-img](kuvat/lzwdecode.png)
**l�hde: https://www.cs.duke.edu/csed/curious/compression/lzw.html**



### Aika- ja tilavaativuudet

**Pakkaus**

Alussa luodaan sanakirja; varataan vakiom��r� tilaa ja lis�t��n vakiom��r� pareja. Tilavaativuus 
siis O(1) ja niin on my�s keskim��r�inen aikavaativuus (hajautustaulun insert-operaation keskim��r�inen 
aikavaativuus on O(1), pahin on O(n)).

T�m�n j�lkeen k�yd��n n kokoinen sy�te l�pi yksi kerrallaan, koko ajan muodostaen uusia merkkijonoja,
jotka lis�t��n sanakirjaan, aikavaativuus on siis O(n). Pahimmassa tapauksessa aikavaativuus
on O(n^2) (hajautustaulun pahimman tapauksen takia). 

Koodeille muodostetaan taulu ByteArrayOutputStream:in kautta, jonka koko riippuu sy�tteen pituudesta, eli
tilavaativuus on O(n) 

Eli pakkausalgoritmin aikavaativuus on keskim��rin O(n), ja tilavaativuus on O(n).


**Purkaminen**

Alussa luodaan sanakirja samalla tavalla kuin pakatessa, aika- ja tilavaativuudet samat.

Sy�te k�yd��n l�pi koodi kerrallaan, sanakirjaan lis�t��n pareja (aikavaativuus keskim��rin O (1), pahin O(n)).
ByteArrayOutputStream:iin kirjoitetaan sanakirjasta l�ydetyt merkkijonot kirjain kerrallaan (aikavaativuus O(n)), 
ja lopussa se sis�lt�� n kokoisen merkkijonon (tilavaativuus O(n)).

Eli my�s purkamisalgoritmin aikavaativuus on keskim��rin O(n), ja tilavaativuus on O(n).


### Paranneltavaa

Toteutuksen pahin puute on 12 bittiset koodit. Olisi tietenkin tehokkaampaa jos k�ytett�isiin ns. variable-length -koodeja. Lis�ksi, koodeja ei kirjoiteta tiivisti eli jokaiseen kuuluu 8 turhaa nollabitti�.



## Huffman

T�m� valmiiksi sitten kuin Huffmanista on oikea versio (toivottavasti ensi viikolla)



## LZW VS Huffman

--||--