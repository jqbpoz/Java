<h3>Zadanie 8</h3><h4>Idea zadania</h4>

<p>
Rzucanie kostkami do gry, tym razem jednak współbieżnie wieloma wątkami jednocześnie.
</p>

<h4>Co nowego?</h4>

<p>Zadanie jest co do podstawowej idei identyczne z poprzednim. Tym razem jednak zmieniamy sposób
pracy. Zamiast pracować z bezpośrednim użyciem wątków należy posłużyć się zadaniami i 
wprowadzać je do wykonania poprzez dostarczony ExecutorService.</p>

<h4>Kontrola liczby zadań współbieżnych</h4>

<p>W tym zadaniu cała kontrola nad liczbą używanych wątków realizowana jest za pomocą ExecutorService.
Do ExecutorService można dostarczyć dowolną liczbę zadań - nie ma jednak gwarancji na to, że wszystkie
będą jednocześnie wykonywane. Moment zakończenia zadania jest generalnie nieznany. Zadania
dostarczone wcześniej mogą zakończyć się po zadaniach kolejnych.</p>

<h4>Badanie kostek</h4>

<p>Tylko zadania realizowane poprzez dostarczony ExecutorService mają prawo do badania kostek.</p>
<p>Wszelkie metody związane z badaniem kostek mogą być realizowane przez wiele wątków jednocześnie.
Tylko metoda dostarczająca ExecutorService ma gwarancję jednokrotnego wywołania.</p>
<p>Jedno zadanie dla ExecutorService może zbadać tylko jedną kostkę.</p>
<p>Wszystkie kostki mają zostać poprawnie zbadane.</p>

<h4>Wyłączanie ExecutorService</h4>

<p>Wyłączenie ExecutorService jest moim zadaniem. Proszę ExecutorService samodzielnie nie wyłączać.</p>

<h4>Dostarczanie rozwiązania</h4>

<p>Proszę o dostarczenie kodu <b>źródłowego</b> klasy <code class="expectedclass">LeniwyEksperymentator</code>.
W klasie można umieścić własne metody i pola. Klasa 
ma implementować interfejs <code>LeniwyBadaczKostekDoGry</code>.
</p>

<h4>Blokowanie wątków użytkownika</h4>

<p>LeniwyEksperymentator nie ma prawa do długotrwałego blokowania wątku użytkownika. 
Otrzymanie wyniku nie może zależeć np. od faktu zakończenia zadania.
</p>