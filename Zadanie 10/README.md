
<h3>Zadanie 10</h3>
<h4>Idea zadania</h4>
<p>
Tworzymy prosty, programowalny kalkulator. Ciąg dalszy...
</p>

<h4>Nowości</h4>

<p>Do poprzedniego (zadanie 5) zestawu instrukcji dodajemy dwie nowe: GOSUB i RETURN.</p>

<h4>Dodatek do zestawu instrukcji programu</h4>

<dl>

<dt>GOSUB</dt>
<dd>Skok do podprogramu (GOSUB = GOTO SUBROUTINE).

<br>
Instrukcja działa podobnie jak GOTO, czyli powoduje przeskok do wskazanej linii kodu, ale
tu istotny jest mechanizm zakończenia pracy podprogramu. Koniec podprogramu powoduje kontynuację kodu 
od do kolejnej linii po wykonanym GOSUB.
<br>
<b>UWAGA: w podprogramie możliwe jest kolejne GOSUB. Poziom zagłębienia może być dowolny...</b>
<pre>1 LET A = 1
10 GOSUB 50
15 PRINT A
20 GOTO 10
50 PRINT "POCZATEK PODPROGRAMU"
60 LET A = A + 1
70 RETURN
</pre>

<pre>10 GOTO 80       &lt;-- tu start programu
20 LET A = A + 1
25 LET B = B + 1
30 RETURN
40 PRINT "ZMIENNE"
45 PRINT A
50 PRINT B
55 RETURN
80 LET A = 100
85 LET B = 120
87 GOSUB 40
90 GOSUB 20
95 GOSUB 40
</pre>

<pre>20 LET A = A + 1
25 LET B = B + 1
30 RETURN
40 PRINT "ZMIENNE"
45 PRINT A
50 PRINT B
55 RETURN
80 LET A = 100  &lt;-- tu start programu
85 LET B = 120
87 GOSUB 40
90 GOSUB 20
95 GOSUB 40
</pre>

</dd>

<dt>RETURN</dt>
<dd>Powrót z podprogramu.</dd>

</dl>

<h4>UWAGA! KONKURS!</h4>

<p>Poprawnie działające programy dostarczone w terminach I i II zostaną uruchomione w celu wykonania dłuższego kodu.
Kod będzie na pewno zawierać pętle, skoki, obliczenia i podprogramy. Zmierzony zostanie czas wykonania kodu.
Programy, które poprawnie wykonają kod, zostaną zestawione pod kątem szybkości interpretacji naszego języka. 
Programy otrzymają bonus do punktacji. 
Pięć programów najszybszych otrzyma kolejno: 3.0, 2.5, 2.1, 1.8 i 1.5 punktów bonusowych. Za miejsca 6-10 przyznany zostanie bonus
1.0pkt. Za miejsca 11-15 bonus wyniesie 0.5pkt.</p>

<h4>Dostarczanie rozwiązania</h4>

<p>Proszę o dostarczenie kodu <b>źródłowego</b> klasy <code class="expectedclass">ProgrammableCalculator</code>.
W klasie można umieścić własne metody i pola. Klasa 
ma implementować interfejs <code>ProgrammableCalculatorInterface</code>.
</p>


