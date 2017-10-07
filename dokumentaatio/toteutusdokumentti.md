# Toteutus


## LempelZivWelch

Pakkausalgorimti saa sy�tteen� taulukon tavuja, jotka kuvaavat ASCII-merkist�n symboleita.
Luettuja symboleita yhdistet��n merkkijonoiksi, jotka lis�t��n ns. sanakirjaan, joka sis�lt��
merkkijono-koodi -pareja. Hajautustaululla toteutettu sanakirja sis�lt�� alussa kaikki 256 ASCII-symbolia, 
ja kun sen maksimikoko (4096 merkkijonoa) saavutetaan, se resetoidaan takaisin tuohon alkutilaan.
Algoritmi palauttaa taulukon tavuja.

Pakkausalgorimti pohjautuu seuraavaan pseudokoodiin:

![lzw-encode-img](kuvat/lzwencode.png)

##### l�hde: https://www.cs.duke.edu/csed/curious/compression/lzw.html

## Huffman