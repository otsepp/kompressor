# Testaus

#### [Pit](https://htmlpreview.github.io/?https://github.com/otsepp/kompressor/blob/master/dokumentaatio/201710071751/index.html)


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
"FB" ja "Ea", jolloin lzw:n hajautufunktion palauttama arvo on sama (jakojäännösmenetelmä)


#### lzwLinkedList
Kaksi yksinkertaista, itsestään selvää testiä. Syvemmät testit vaatisivat luokan
muokkaamista.



## Huffman
Huffman- ja HuffmanTree -testeistä ei ole tässä vielä,
sillä ne käsittelevät vain demoversiota (toivottavasti oikea versio
ensi viikolla)


#### HuffmanQueue
Vain yksi yksinkertainen testi (samasta syystä kuin lzwLinkedList:ssä)

Jonoon lisätään solmut joiden frekvenssit ovat 33, 51, 12 ,3 ,57. Ne
poistetaan jonosta yksi kerrallaan, joka kerta varmistaen että poistetun 
solmun frekvenssi on pienin.