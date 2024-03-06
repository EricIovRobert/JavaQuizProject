# Titlu proiect: Quiz
### Student: Iov Eric-Robert

## Descriere
Proiectul constă în dezvoltarea unei aplicații interactive de tip quiz de cultură generală, având ca scop atât testarea cât și îmbogățirea cunoștințelor generale ale utilizatorilor. Această aplicație va oferi o experiență plăcută și educațională, permițând utilizatorilor să se distreze în timp ce învață lucruri noi. Întrebările din quiz vor acoperi o gamă variată de domenii, inclusiv istorie, știință, artă, geografie, muzică și altele.

## Obiective


* Testarea cunoștințelor utilizatorilor.
* Dezvoltarea unui sistem de gestionare a întrebărilor și răspunsurilor.
* Implementarea unui sistem de scor și feedback pentru utilizatori.
* Crearea unei interfețe ușor de utilizat.

## Arhitectura


![Alt text](uml.png)

În această diagramă am reprezentat fiecare clasa și relațiile dintre ele. 

Quiz și Întrebare:
O clasă Quiz poate să aibă o relație de agregare cu clasa Întrebare. Asta înseamnă că întrebările sunt o parte din Quiz.

Jucator și Quiz:
Un Jucator poate să participe la un quiz. Deci, există o relație de asociere între Jucator și Quiz.

Jucator și Întrebare:
Un Jucator poate să răspundă la mai multe întrebări în cadrul unui quiz. Deci, există o relație de asociere între Jucator și Întrebare în contextul jocului.


## Functionalitati/Exemple utilizare
* Posibilitatea de a răspunde la întrebări
* Afișarea răspunsurilor corecte
* Afișarea punctajului total
* Afișarea clasamentului
* Afișarea timpului total pentru a rezolva quiz-ul

 Utilizatorul începe quiz-ul și răspunde la o serie de întrebări, urmând să îi fie afișat punctajul după ce răspunde la ultima întrebare. Apoi, utilizatorul va fi clasat pe o anumită poziție în clasamentul general.

### Resurse
https://www.youtube.com/watch?v=UI6lqHOVHic

