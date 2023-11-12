<h3>Zadanie 1</h3><h4>Idea zadania</h4>

<p>
Zadanie polega na napisaniu kodu programu (klasy), który będzie prostym kalkulatorem
wzbogaconym o pamięć i stos.
</p>

<h4>Niepoprawne operacje</h4>

<p>Zakładamy, że użytkownik będzie używać kalkulatora zgodnie z 
przeznaczeniem poszczególnych metod. Użytkownik nie będzie próbował
wykonywać operacji np. z użyciem ujemnych indeksów dla pamięci, czy
przepełniających stos.</p>

<h4>Indeksowanie pamięci</h4>

<p>Pamięć kalkulatora indeksowana jest w sposób naturalny (tak jak tablice w Java). Jeśli pamięć ma N pozycji, to 
pierwszą poprawną jest 0, ostatnią N-1.</p>

<h4>Stan początkowy</h4>

<p>Stan początkowy obiektu kalkulatora to:</p>

<ul>
<li>akumulator - ustawiony na 0
</li><li>pamięć - wypełniona zerami
</li><li>stos - nieużywany
</li></ul>

<h4>Konstruktor</h4>

<p>Klasę będącą rozwiązaniem można wyposażyć w konstruktor. Ja będę używać jej (tworzyć jej obiekty)
wyłącznie poprzez konstruktor bezparametrowy.
</p>

<h4>Wprowadzanie danych</h4>

<p>Państwa kod będzie używany poprzez mój program. Nie ma potrzeby wprowadzania do niego jakiegokolwiek
interfejsu użytkownika wczytującego dane np. z terminala. Użycie będzie opierać się wyłącznie o 
wywoływanie poszczególnych metod.</p>

<h4>Elementy statyczne</h4>

<p>Należy mieć na względzie, że w trakcie testu utworzonych zostanie wiele obiektów. Operacje
zlecone jednemu z nich nie mogą wpływać na pozostałe.</p>

<h4>Dostarczanie rozwiązania</h4>
<p>Proszę o dostarczenie kodu <b>źródłowego</b> klasy <code class="expectedclass">Calculator</code>.
W klasie można umieścić własne metody i pola. Klasa <code class="expectedclass">Calculator</code>
ma dziedziczyć po klasie <code>CalculatorOperations</code>.
</p>