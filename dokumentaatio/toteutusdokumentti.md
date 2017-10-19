# Toteutus



## LempelZivWelch

**Pakkausalgoritmi** saa sy�tteen� taulukon tavuja, jotka kuvaavat ASCII-merkist�n symboleita.
Luettuja symboleita yhdistet��n merkkijonoiksi, jotka lis�t��n ns. sanakirjaan, joka sis�lt��
merkkijono-koodi -pareja. Hajautustaululla toteutettu sanakirja sis�lt�� alussa kaikki 256 ASCII-symbolia, 
ja kun sen maksimikoko (4096) saavutetaan, se resetoidaan takaisin alkutilaan.
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

Toteutuksen pahin puute on 12 bittiset koodit. Olisi tietenkin tehokkaampaa jos k�ytett�isiin ns. variable-length -koodeja.
Lis�ksi, koodeja ei kirjoiteta tiivisti eli jokaiseen kuuluu 4 turhaa nollabitti�.



## Huffman

K�ytet��n esimerkkin� sy�tett� "cbboooiiiieeeee"

**Pakkaus**

Alussa TreeBuilder-luokkaa k�ytt�en luodaan HuffmanTree-puurakenne:

Tavut k�yd��n l�pi tallettaen merkkien esiintymism��r�t. T�m�n tiedon avulla luodaan jokaiselle esiintyv�lle merkille oma
HuffmanNode-olio, joka sis�lt�� merkkijonon ja sen esiintymism��r�n. Solmut laitetaan prioriteettijonoon, jossa ensimm�isen alkion
merkin esiintymism��r� on pienin.
Jono tyhjennet��n kaksi alkiota kerrallaan; kaksi solmua, joiden merkkien esiintymism��r�t ovat pienimm�t, yhdistet��n uudeksi
null-solmuksi, jonka frekvenssiarvo on n�iden solmujen merkkifrekvenssien summa.
Kun viimeinen solmu l�ydet��n, se asetetaan puun juureksi. Saadaan seuraava puu:

![tree](kuvat/tree.png)

T�m�n j�lkeen luodaan pakattujen bittien alkuun tuleva header, jonka avulla luodaan puu pakattuja bittej� purkaessa:

Puu yksinkertaisesti k�yd��n rekursiivisesti l�pi esij�rjestyksess�. Kun k�sittelyyn tulee null-solmu, kirjoitetaan ByteArrayWriter-
luokan avulla 0-bitti, ja lehtien tapauksessa kirjoitetaan 1-bitti ja lehden merkin ASCII-koodi.
ByteArrayWriter kirjoittaa bittej� ty�nt�en ne vasemmalle puolelle.
Seuraava kuva esitt�� luokan toimintaa:

![bytewrite](kuvat/bytewrite.png)

Saatu header on 
0  0  1  01100101(e)  1  01101001(i)  0  0  1  01100010(b)  1  01100011(c)  1  01101111(o)

Nyt parametrina saadut tavut k�yd��n taas l�pi, ja jokaiselle etsit��n puusta koodi:
Koodit l�ydet��n k�ym�ll� puu rekursiivisesti l�pi. Kun liikutaan vasemmalle, lis�t��n koodiin 1, ja oikealle liikkuessa taas 0.
Esim. esimerkkipuusta saadaan koodi 010 merkille 'c'.
Koodit kirjoitetaan taas taulukkoon ByteArrayWriter-luokalla. Kun kutsutaan toByteArray-metodia, true arvolla ilmaistaan, ett�
loppuun kirjoitetaan eof-tavu, jonka arvo kertoo, kuinka monta bitti� luetaan "viimeisest�" tavusta (oikeasti toiseksi viimeinen)

**Purkaminen**


















