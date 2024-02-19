<h3>Zadanie 7</h3>
<h4>Idea zadania</h4>

<p>
Rzucanie kostkami do gry, tym razem jednak współbieżnie wieloma wątkami jednocześnie.
</p>

<h4>Praca równoległa</h4>

<p>Program otrzymywać będzie kostki do gry. Zadanie polega na zbadaniu kostki poprzez wykonanie
wielu rzutów. Ponieważ program będzie mógł używać wątków, będzie też mógł badać wiele kostek
w tym samym czasie. Z uwagi jednak na ograniczenia sprzętowe (ograniczona liczba rdzeni procesora)
liczba jednocześnie badanych kostek będzie ograniczona. Należy tak zarządzać wątkami, aby
pracowała tylko dozwolona liczba. Jeśli dodatkowe wątki zostaną uruchomione, mają czekać <b>uśpione</b> 
na możliwość wykonania pracy.
</p>

<h4>Warunki pracy równoległej</h4>

<ul>
<li>Tylko określona liczbą wątków może w tym samym czasie badać kostki do gry
</li><li>Jeśli tylko liczba dostarczonych kostek jest wystarczająca, program ma używać wszystkich dozwolonych wątków.
Oczekuje się więc, że program będzie efektywnie używał zasobów (wątków) w celu maksymalnego skrócenia
czasu potrzebnego do zbadania kostek.
</li><li>Jedną kostką może rzucać tylko jeden wątek.
</li><li>Jeden wątek może zbadać tylko jedną kostkę. Nie wolno mu zająć się kolejną.
</li><li>Wątki, które czekają na możliwość rozpoczęcia badania kostek czekają na swoją kolej
nie obciążają procesora. Należy takie wątki uśpić.
</li><li>Wolno używać wyłącznie wątków, które zostały dostarczone przez ThreadFactory.
</li><li>Program nie może blokować(*) przekazywania mu kolejnych kostek. Ma to być możliwe 
w dowolnym momencie pracy programu. Nawet w trakcie badania przekazanych już wcześniej kostek.
</li><li>Program nie może blokować(*) wykonywania zapytań o wynik. 
</li><li>Żadna z kostek nie może zostać pominięta. Wszystkie muszą zostać przebadane. Wynik każdego
rzutu musi trafić do generowanego histogramu.
</li><li>Dostarczanie kostek do gry czy pytania o wynik mogą być realizowane wieloma wątkami, nawet jednocześnie.
</li></ul>

<p>(*) - zabronione jest długotrwałe blokowanie wątku, blokowanie na krótką chwilę, niezbędną 
do synchronizacji pracy kodu, jest dozwolone.
</p>

<h4>Dostarczanie rozwiązania</h4>

<p>Proszę o dostarczenie kodu <b>źródłowego</b> klasy <code class="expectedclass">WatkowyEksperymentator</code>.
W klasie można umieścić własne metody i pola. Klasa ma implementować interfejs <code>BadaczKostekDoGry</code>.
</p>
