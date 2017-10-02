# Subscribers

Aplikacija je odrađena C# u razvojnom okruženju Visual Studio 2017 - Community edition sa .NET frameworkom 4.5.2.

Primenjen je in-memory, MQTT-like sistem koji uspešno izvršava kreirane testove.

Poruke se objavljuju na određene teme. Kada se ovo dogodi, svi korisnici koji su se prijavili za određenu temu dobijaju poruke.


U fajlu Solution/SimplePubSub.cs implementirano je rešenje, u kojem su:

 - koričćene metode `Publish(topic, T message)` i `Subscribe(topic, Action<T> message)` iz Stub-a i kojim je postignuto da testovi iz projekta uspešno izvrše.

 Rešenje omogućava:

 - ako su dva subscriber-a prijavljena na temu `/example`, sa akcijama da štampaju i primaju poruke u standardnom formatu. Kada se `"hello"` objavi na `/example` ,
 `"hello"` će se ispisati na ekranu dva puta, za svakog subsriber-a posebno.

 - jedan subscriber je prijavljen na temu `/home/+/temperature`. Kada se poruka objavi na  `/home/bedroom/temperature` i `/home/kitchen/temperature`, obe poruke će biti primljene kod subsriber-a.

 - jedan subscriber je prijavljen na temu `/home/office/#` . Kada se poruka objavi na `/home/office/temperature` i `/home/office/humidity`, obe poruke će biti primljene kod subscriber-a.


UPUTSTVO ZA IMPLEMENTACIJU:

Source kod se nalazi na lokaciji Solution/SimplePubSub.cs

izvršni i kopajlirani kod se nalazi na lokaciji tomcat/ROOT

PREREQUISITES:

Za source kod - razvojno okruženje Visual Studio 2017 - Community Edition

Za kontejnere - CentOS 7 x64, instalirani paketi docker engine i docker compose.
Instalacija se može naći na https://github.com/majstorki88/load_balanser

Za implementaciju je dovoljno pokrenuti komandu docker-compose -f docker-compose.yml iz foldera subscribers
