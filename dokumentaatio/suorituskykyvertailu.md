| Algoritmi | Koko (tavua) | % alkuperäisestä | Aika (pakkaus) | Aika (Purkaminen) |
| --------- | -------------| ---------------- | -------------- | ----------------- |
| LZW		| 11484		   | 79				  | 14			   | 7


## Suorituskykyvertailu

text1.txt (11484 tavua)
	lzw: 
		koko alkuperäisestä: 79%
		pakkaus: 14 ms
		purkaminen: 7 ms
	
	Huffman:
		koko alkuperäisestä: 57%
		pakkaus: 11 ms
		purkaminen: 12 ms

		
text2.txt (6617123 tavua)
	lzw: 
		koko alkuperäisestä: 73%
		pakkaus: 747 ms
		purkaminen: 385 ms
	
	Huffman:
		koko alkuperäisestä: 57%
		pakkaus: 572 ms
		purkaminen: 270 ms
		
		
Generoitu merkkijono (500 000 tavua)
	lzw: 
		koko alkuperäisestä: 194%
		pakkaus: 169 ms
		purkaminen: 125 ms
	
	Huffman:
		koko alkuperäisestä: 100%
		pakkaus: 83 ms
		purkaminen: 67 ms
		
		
img.jpg	(725249 tavua)´
	lzw: 
		koko alkuperäisestä: 189%
		pakkaus: 195 ms
		purkaminen: 139 ms
	
	Huffman:
		koko alkuperäisestä: 100%
		pakkaus: 118 ms
		purkaminen: 93 ms

gif.gif (1004638 tavua)
	lzw: 
		koko alkuperäisestä: 193%
		pakkaus: 238 ms
		purkaminen: 201 ms
	
	Huffman:
		koko alkuperäisestä: 100%
		pakkaus: 155 ms
		purkaminen: 118 ms