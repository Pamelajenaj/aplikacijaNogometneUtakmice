# aplikacijaNogometneUtakmice
JavaFX aplikacija za kupnju karata za nogometne utakmice.

Applikacija se pokreće tako da nad klasom "HelloApplication" stisnemo desni klik i odabremo "run"

Kako bi se aplikacija mogla pokrenuti potrebno je otvoriti H2 > bin > prvi H2 i napraviti lokalni database 
Potrebno je odabrati Generic H2 (Embedded) sa proizvoljnim URL, Username i Password-om. 
Najjednostavnije: 
- URL = jdbc:h2:~/zavrsniprojekt
- Username = username
- Password = password

![image](https://user-images.githubusercontent.com/85134549/221926863-ace32423-bf78-4b22-91e5-ffdf54095de8.png)


Bitno je da su URL, username i password isti kao i u kodu (u folderu dat/database.properties).

![image](https://user-images.githubusercontent.com/85134549/221926942-7b40835a-6761-4641-9d62-99ae5d6d73c2.png)

Nakon toga je potrebno importati sve tablice u bazu pošto za ovaj projekt nije korištena online baza.

Tablice se importaju na sljedeći način:
- Download folder "tabliceUtakmice"
- kopirati path do tog foldera (npr: C:\Users\Dario\Desktop\tabliceUtakmice)
- u H2 napisati sljedeće komande: 
CREATE TABLE MENADZER AS SELECT * FROM CSVREAD('C:\Users\Dario\Desktop\tabliceUtakmice\menadzeri.txt');
CREATE TABLE STADION AS SELECT * FROM CSVREAD('C:\Users\Dario\Desktop\tabliceUtakmice\stadioni.txt');
CREATE TABLE TIM AS SELECT * FROM CSVREAD('C:\Users\Dario\Desktop\tabliceUtakmice\timovi.txt');
CREATE TABLE TIM_IGRACI AS SELECT * FROM CSVREAD('C:\Users\Dario\Desktop\tabliceUtakmice\timovi_igraci.txt');
CREATE TABLE UTAKMICA AS SELECT * FROM CSVREAD('C:\Users\Dario\Desktop\tabliceUtakmice\utakmice.txt');

*Pod uvijetom da se za path koristi kopirani path (ovo je samo primjer)*

Napomene: 
- H2 terminal je potrebno zatvoriti prije pokretanje aplikacije pošto na bazi istovremeno može biti samo jedna konekcija.
- Nakon svakog novog ulaženja na H2 bazu potrebno je odabrati H2 (Server) umjesto (Embedded)
- Za logiranje u samoj aplikaciji možete koristiti "admin admin" kao username i password
