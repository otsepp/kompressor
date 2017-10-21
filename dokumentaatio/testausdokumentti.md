# Testaus

#### [Pit]()


## LempelZivWelch
Testeiss� k�ytet��n merkkijonoa "! ! +" (tavuina), josta saadaan
koodit 33(!), 32(v�li), 256(!v�li) ja 43(+). N�iden 12-bittiset versiot laitetaan byte[] taulukkoon.

Viimeinen testi testaa poikkeustilannetta, jossa sanakirjasta ei l�ydy merkkijonoa luetulle koodille.
T�m� tapahtuu esim. k�ytetyll� merkkijonolla "1212121". T�m� selitet��n https://www.cs.duke.edu/csed/curious/compression/lzw.html 
kohdassa Uncompression 


#### lzwDictionary
Ensimm�inen testi tekee saman kuin lzw, kun se muodostaa sanakirjan pakkaamista varten,
ja tarkistaa, ett� merkkijonoavaimella l�ydet��n oikea koodi. 

T�m�n lis�ksi testataan, ett� oikea arvo l�ydet��n avaimille, joiden hajautufunktioiden
arvot ovat samat (String-luokan hashCode()-metodi palauttaa saman arvon merkkijonoille
"FB" ja "Ea", jolloin lzw:n hajautufunktion palauttama arvo on sama


#### lzwLinkedList
Kaksi yksinkertaista, itsest��n selv�� testi�. Syvemm�t testit vaatisivat luokan
muokkaamista.



## Huffman
Pakkaus- ja purkamisoperaatiot testataan samalla sy�tteell� kuin [toteutusdokumentissa](toteutusdokumentti.md) sek�
tyhjill� sy�tteill�.


#### TreeBuilder
Taas k�ytet��n toteutusdokumentin esim. sy�tett�, jolla testataan puun luontia sy�tteen tavuista sek� pakatun tiedoston alussa olevasta
headerista.


#### ByteArrayReader
Luetaan kahden tavun bitit, varmistetaan, ett� t�m�n j�lkeen metodi palauttaa ns. virhenumeron.

Kirjainten lukemisessa voi tapahtua virhe, jos j�ljell� luettavia bittej� ei ole tarpeeksi. T�h�n ei kuitenkaan edes valmistauduta metodin
koodissa, sill� sen ei kuulu tapahtua jos Huffman toimii oikein. Eli siis testataan vain lukemalla kaksi kirjainta.

Viimeisess� testiss� testataan eof-indeksi�. T�ss� tapauksessa se on 2, eli testi varmistaa, ett� tavuja ei lueta toiseksi
viimeisen tavun toisen bitin j�lkeen (viimeinen tavu ilmaisee eof-indeksin).


#### ByteArrayWriter
Ensimm�isess� testiss� kirjoitetaan 9 1-bitti�, jolloin pit�isi saada tavut [11111111][10000000]

Toisessa kirjoitetaan 'a', 0, 'c', jolloin pit�isi saada tavut 

**01100001**
0**01100011**
**1**0000000

Viimeisess� testiss� kirjoitetaan 4 bitti� ja varmistetaan, ett� toByteArray:n palauttaman taulukon viimeinen tavu
sis�lt�� arvon 4, koska parametrina on true. 


#### HuffmanTree
Taas k�ytet��n samaa esim. puuta

#### HuffmanQueue
Jonoon lis�t��n solmut joiden frekvenssit ovat 33, 51, 12 ,3 ,57. Ne
poistetaan jonosta yksi kerrallaan, joka kerta varmistaen ett� poistetun 
solmun frekvenssi on pienin.

### IntQueue
Yksinkertainen testi push- ja pop-operaatiolle, ja testi jonon koon kasvattamisesta.

## Suorituskykyvertailu

#### text1.txt (11484 tavua)

[kaavio1](kuvat/kaavio1.png)

N�hd��n, ett� Huffman pakkaa tiedostoja pienemp��n kokoon kuin LZW, kuten odotettiin. Lis�ksi Huffman on hieman nopeampi.

Selv�stikin algoritmien totetus sopii vain tekstin pakkaamiseen, sill� muiden tiedostotyyppien pakkamiinen vaikuttaa vain kasvattavan 
tiedoston kokoa. Generoidun merkkijonon testituloksista n�hd��n, ett� molemmat algoritmit vaativat ett� pakattavassa datassa esiintyy
toistoa, jotta ne toimisivat kunnolla. LZW: tapauksessa t�m� oli kuitenkin odotettavissa, sill� sen toimintaperiaate perustuu siihen.

[tarkka data](suorituskykyvertailu.md)

