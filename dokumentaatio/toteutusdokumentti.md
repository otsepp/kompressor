# Toteutus


## LempelZivWelch

Pakkausalgorimti saa syötteenä taulukon tavuja, jotka kuvaavat ASCII-merkistön symboleita.
Luettuja symboleita yhdistetään merkkijonoiksi, jotka lisätään ns. sanakirjaan, joka sisältää
merkkijono-koodi -pareja. Hajautustaululla toteutettu sanakirja sisältää alussa kaikki 256 ASCII-symbolia, 
ja kun sen maksimikoko (4096 merkkijonoa) saavutetaan, se resetoidaan takaisin tuohon alkutilaan.
Algoritmi palauttaa taulukon tavuja.

Pakkausalgorimti pohjautuu seuraavaan pseudokoodiin:

![lzw-encode-img](kuvat/lzwencode.png)

##### lähde: https://www.cs.duke.edu/csed/curious/compression/lzw.html

## Huffman