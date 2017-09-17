### Viikko1
K‰ytin aikaa LZW-algoritmin ymm‰rt‰miseen ja testailuun. Sain toteutettua merkkijonojen pakkaamisen sek‰
purkamisen, vaikka t‰ss‰ vaiheessa pakattu merkkijono on vain Integer-lista. K‰ytin aikaa noin 8 tuntia.
Hieman ep‰selv‰‰ on, ett‰ mink‰laisessa muodossa pakattu merkkijono tai tekstitiedosto tulee olla. Oletettavasti
Integer-lista ei ole, koska se ei oikeasti pakkaa mit‰‰n. Se taitaa itse asiassa kasvattaa kokoa, vaikka koodeja on v‰hemm‰n kuin merkkej‰...
Ilmeisesti merkkien koodit t‰ytyisi olla v‰lill‰ 0-4095, eli bittimuodossa niiden koko olisi v‰lill‰ 1-12. Mik‰ olisi paras tapa tehd‰ t‰m‰?