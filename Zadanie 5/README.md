<h3>Zadanie 5</h3><h4>Idea zadania</h4>

<p>
Tworzymy prosty, programowalny kalkulator.
</p>
<h4>Program</h4>

<p>Nasz język programowania ma przypominać BASIC. Czyli, ma być bardzo prosty.
Nam wystarczy zaledwie kilka instrukcji.</p>

<p>Każda z instrukcji poprzedzona jest numerem linii programu. Numery będą liczbami całkowitymi
od 1 wzwyż. Pomiędzy numerem linii a instrukcją będzie umieszczona dokładnie jedna spacja.
Zaraz po instrukcji przechodzimy do kolejnej linii. Kolejne linie kodu odbierane będą jako
ciągi znaków, które trzeba będzie rozkodować. Wielkość liter w instrukcji nie ma znaczenia.
Kod wykonywany jest wg. rosnącego numeru linii (o ile nie nastąpi GOTO). 
Numery linii nie muszą być inkrementowane
o jeden. Wszystkie zmienne są typu "int". Wszystkie zmienne zostaną zainicjalizowane
przed pierwszym użyciem (za pomocą LET lub INPUT). Wielość liter z nazwie zmiennej nie ma znaczenia.
Nazwy zmiennych mogą zawierać wyłącznie litery. Pomiędzy elementami instrukcji
występują pojedyncze spacje.</p>

<p>Poniżej program wysyłający na standardowe wejście liczbę 1</p>
<pre>110 lEt a = 1
111 prInt a
112 eNd
</pre>

<h4>Optymistyczne założenia</h4>

<p>Zakładamy, że otrzymany kod będzie poprawny. Będzie sformatowany zgodnie z wcześniejszym opisem.
Nie będzie prowadzić do błędów typu dzielenie przez zero. Generalnie, poprawnie napisany interpreter
wykona go poprawnie.</p>

<h4>Zestaw instrukcji programu</h4>

<dl>
<dt>LET</dt>
<dd>przypisanie wartości. Po LET następuje nazwa zmiennej, następnie znak równości,
po nim literał lub wyrażenie matematyczne (dozwolone wyłącznie operacje
to dodawanie, odejmowanie, mnożenie i dzielenie). Np. 
<pre>190 LET a = 1
195 LET b = 2
200 LET a = a + 1
210 LET a = a * b
</pre>
</dd>

<dt>PRINT</dt>
<dd>Wysłanie tekstu lub zmiennej na standardowe wejście. Każde PRINT powoduje przejście do nowej linii.
<pre>10 LET a = 100
11 PRINT "Czesc a = "
12 PRINT a
</pre>
</dd>

<dt>GOTO</dt>
<dd>Skok do linii o podanym numerze
<pre>10 PRINT "CZESC"
20 GOTO 10
</pre>
</dd>

<dt>END</dt>
<dd>Koniec pracy programu. Inna metoda zakończenia działania, to zrealizowanie ostatniej linii programu.</dd>

<dt>IF</dt>
<dd>Instrukcja warunkowa. Po IF podawana jest nazwa zmiennej. Następnie znak porównania (= lub &lt; lub &gt;).
Ponownie zmienna lub literał. Jeszcze tylko GOTO i numer linii, do której warunkowo
przenosi się wykonywanie kodu o ile warunek jest prawdziwy. 
Jeśli warunek jest nieprawdziwy, to program kontynuuje pracę dla kolejnej linii kodu.
<pre>10 LET a = 10
20 LET b = 20
30 IF a &lt; b GOTO 50
40 PRINT "a nie jest &lt; od b" 
45 GOTO 60
50 PRINT "a &lt; od b" 
60 END
</pre>
</dd>

<dt>INPUT</dt>
<dd>Wczytanie do zmiennej wartości ze standardowego wejścia.

<pre>10 INPUT licznik
20 PRINT "PODANO licznik="
30 PRINT licznik
40 END
</pre>
</dd>

<h4>UWAGA</h4>

<ul>
<li>Plik z kodem programu może zawierać na końcu puste linie.
</li><li>Operacje INPUT i PRINT należy realizować poprzez przekazane obiekty zgodne z 
interfejsami: <code>ProgrammableCalculatorInterface.LineReader</code>
i <code>ProgrammableCalculatorInterface.LinePrinter</code>.
</li><li>Program nie ma prawa wysyłać na terminal jakichkolwiek innych znaków niż te, które wynikają z pracy programu.
</li><li>Pojawi się jeszcze jedno zadanie bazujące na tym samym pomyśle - warto zadbać o czystość kodu, aby można było
łatwo go przerobić.
</li><li>Nie wolno zmieniać nagłówków metod moich interfejsów!
</li></ul>

<h4>Dostarczanie rozwiązania</h4>

<p>Proszę o dostarczenie kodu <b>źródłowego</b> klasy <code class="expectedclass">ProgrammableCalculator</code>.
W klasie można umieścić własne metody i pola. Klasa 
ma implementować interfejs <code>ProgrammableCalculatorInterface</code>.
</p>
