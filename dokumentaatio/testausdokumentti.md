# Testaus

#### [Pit]()


## LempelZivWelch
Testeissä käytetään merkkijonoa "! ! +" (tavuina), josta saadaan
koodit 33(!), 32(väli), 256(!väli) ja 43(+). Näiden 12-bittiset versiot laitetaan byte[] taulukkoon.

Viimeinen testi testaa poikkeustilannetta, jossa sanakirjasta ei löydy merkkijonoa luetulle koodille.
Tämä tapahtuu esim. käytetyllä merkkijonolla "1212121". Tämä selitetään https://www.cs.duke.edu/csed/curious/compression/lzw.html 
kohdassa Uncompression 


#### lzwDictionary
Ensimmäinen testi tekee saman kuin lzw, kun se muodostaa sanakirjan pakkaamista varten,
ja tarkistaa, että merkkijonoavaimella löydetään oikea koodi. 

Tämän lisäksi testataan, että oikea arvo löydetään avaimille, joiden hajautufunktioiden
arvot ovat samat (String-luokan hashCode()-metodi palauttaa saman arvon merkkijonoille
"FB" ja "Ea", jolloin lzw:n hajautufunktion palauttama arvo on sama


#### lzwLinkedList
Kaksi yksinkertaista, itsestään selvää testiä. Syvemmät testit vaatisivat luokan
muokkaamista.



## Huffman
Pakkaus- ja purkamisoperaatiot testataan samalla syötteellä kuin [toteutusdokumentissa](toteutusdokumentti.md) sekä
tyhjillä syötteillä.


#### TreeBuilder
Taas käytetään toteutusdokumentin esim. syötettä, jolla testataan puun luontia syötteen tavuista sekä pakatun tiedoston alussa olevasta
headerista.


#### ByteArrayReader
Luetaan kahden tavun bitit, varmistetaan, että tämän jälkeen metodi palauttaa ns. virhenumeron.

Kirjainten lukemisessa voi tapahtua virhe, jos jäljellä luettavia bittejä ei ole tarpeeksi. Tähän ei kuitenkaan edes valmistauduta metodin
koodissa, sillä sen ei kuulu tapahtua jos Huffman toimii oikein. Eli siis testataan vain lukemalla kaksi kirjainta.

Viimeisessä testissä testataan eof-indeksiä. Tässä tapauksessa se on 2, eli testi varmistaa, että tavuja ei lueta toiseksi
viimeisen tavun toisen bitin jälkeen (viimeinen tavu ilmaisee eof-indeksin).


#### ByteArrayWriter
Ensimmäisessä testissä kirjoitetaan 9 1-bittiä, jolloin pitäisi saada tavut [11111111][10000000]

Toisessa kirjoitetaan 'a', 0, 'c', jolloin pitäisi saada tavut 

**01100001**
0**01100011**
**1**0000000

Viimeisessä testissä kirjoitetaan 4 bittiä ja varmistetaan, että toByteArray:n palauttaman taulukon viimeinen tavu
sisältää arvon 4, koska parametrina on true. 


#### HuffmanTree
Taas käytetään samaa esim. puuta

#### HuffmanQueue
Jonoon lisätään solmut joiden frekvenssit ovat 33, 51, 12 ,3 ,57. Ne
poistetaan jonosta yksi kerrallaan, joka kerta varmistaen että poistetun 
solmun frekvenssi on pienin.

### IntQueue
Yksinkertainen testi push- ja pop-operaatiolle, ja testi jonon koon kasvattamisesta.

## Suorituskykyvertailu

#### text1.txt (11484 tavua)

[kaavio1](kuvat/kaavio1.png)

Nähdään, että Huffman pakkaa tiedostoja pienempään kokoon kuin LZW, kuten odotettiin. Lisäksi Huffman on hieman nopeampi.

Selvästikin algoritmien totetus sopii vain tekstin pakkaamiseen, sillä muiden tiedostotyyppien pakkamiinen vaikuttaa vain kasvattavan 
tiedoston kokoa. Generoidun merkkijonon testituloksista nähdään, että molemmat algoritmit vaativat että pakattavassa datassa esiintyy
toistoa, jotta ne toimisivat kunnolla. LZW: tapauksessa tämä oli kuitenkin odotettavissa, sillä sen toimintaperiaate perustuu siihen.

[tarkka data](suorituskykyvertailu.md)

