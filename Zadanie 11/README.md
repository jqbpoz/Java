<h3>Zadanie 11</h3>
<h4>Idea zadania</h4>
<p>
Klient TCP/IP, którego zadaniem jest rozpoznanie czy serwis jest prawdziwy, czy ktoś się za niego podaje.
</p>

<h4>Jak sprawdzić czy usługa sieciowa jest autentyczna?</h4>

<p>Aby potwierdzić autentyczność usługi możemy posłużyć się hasłem, ale przecież jednocześnie nie chcemy go przy okazji
ujawnić (może zostać np. podsłuchane). Można zastosować nieco inny mechanizm: wysyłany jest komunikat, który obie strony
przetwarzają swoimi hasłami. Następnie przekazywany jest skrót z otrzymanej wiadomości. Obie strony porównują skróty (własny
i otrzymany). Jeśli skróty są identyczne, to użyte hasła były takie same...</p>

<p>Coś podobnego użyjemy tutaj.</p>

<h4>Klient-Serwer</h4>

<p>Państwa program uzyska informację o poprawnych haśle. Otrzyma także dane serwisu, z którym będzie musiał się połączyć.
Serwis po ustaleniu czy klientem jest program czy człowiek (w wersji człowiek czas reakcji klienta będzie dłuższy)
przekaże klientowi serię liczb. Trzeba je do siebie dodać. Liczby będą typu całkowitego. Dodając je do siebie trzeba będzie 
użyć klasy BigInteger. Do otrzymanej liczby należy dodać hasło (to też będzie duża liczba). Serwer przekaże
informację o własnym wyniku i poprosi o wynik klienta. Jeśli klient uzyskał taki sam wynik to go przekaże do serwera 
(trochę to wszystko bez sensu, ale w tym zadaniu nie o to chodzi...), jeśli jednak wyniki nie będą takie same, to 
klient przekaże do serwera zawartość pola NetConnection.ODPOWIEDZ_DLA_OSZUSTA. I to właściwie wszystko - można 
kończyć połączenie...</p> 

<h4>Praca w trybie demo</h4>

<p>Uruchomiony został serwer testowy. Jest on dostępny z spk-ssh.if.uj.edu.pl. Można się dostać do niego (na początek)
tak:</p>

<pre>telnet 172.30.24.12 9090
</pre>

<h4>Praca w trybie rzeczywistego testu</h4>

<p>Test będzie zachowywać się podobnie, ale tryb "człowiek" nie będzie działać. Bez względu na to, co zostanie przekazane
do serwera uruchomi się tryb "program".</p>

<h4>Dostarczanie rozwiązania</h4>

<p>Proszę o dostarczenie kodu <b>źródłowego</b> klasy <code class="expectedclass">Klient</code>.
W klasie można umieścić własne metody i pola. Klasa 
ma implementować interfejs <code>NetConnection</code>.
</p>

