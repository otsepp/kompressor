# Testaus

### [Pit]()


## LempelZivWelch
Testeiss� k�ytet��n merkkijonoa "! ! +" (tavuina), josta saadaan helposti k�sin simuloimalla
koodit 33, 32, 256 ja 43. N�iden 12-bittiset versiot laitetaan byte[] taulukkoon.

Viimeinen testi testaa poikkeustilannetta, jossa sanakirjasta ei l�ydy merkkijonoa luetulle koodille.
T�m� tapahtuu esim. k�ytetyll� merkkijonolla "1212121". T�m� selitet��n https://www.cs.duke.edu/csed/curious/compression/lzw.html 
kohdassa Uncompression 


### lzwDictionary
Ensimm�inen testi tekee saman kuin lzw, kun se muodostaa sanakirjan pakkaamista varten,
ja tarkistaa, ett� merkkijonoavaimella l�ydet��n oikea koodi.

T�m�n lis�ksi testataan, ett� oikea arvo l�ydet��n avaimille, joiden hajautufunktioiden
arvot ovat samat (String-luokan hashCode()-metodi palauttaa saman arvon merkkijonoille
"FB" ja "Ea", jolloin lzw:n hajautufunktion palauttama arvo on sama (jakoj��nn�smenetelm�)


### lzwLinkedList
Kaksi yksinkertaista, itsest��n selv�� testi�. Syvemm�t testit vaatisivat luokan
muokkaamista.



## Huffman
Huffman- ja HuffmanTree -testeist� ei ole t�ss� viel�,
sill� ne k�sittelev�t vain demoversiota (toivottavasti oikea versio
ensi viikolla)


### HuffmanQueue
Vain yksi yksinkertainen testi (samasta syyst� kuin lzwLinkedList:ss�)

Jonoon lis�t��n solmut joiden frekvenssit ovat 33, 51, 12 ,3 ,57. Ne
poistetaan jonosta yksi kerrallaan, joka kerta varmistaen ett� poistetun 
solmun frekvenssi on pienin.