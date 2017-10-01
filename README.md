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

Folder PubSub je C# njigohov, i u njemu pub_sub originalan

java je u javi.

# Goal

Your goal is to complete the implementation of simple (in-memory, MQTT-like) [_PubSub_](https://en.wikipedia.org/wiki/Publish%E2%80%93subscribe_pattern) system, by **making the provided test cases pass**.

Messages can be **published** to topics. When this happens, all subscribers which are **subscribed** to a matching topic receive published message.

## Example

1. Two subscribers are subscribed to `/example` topic, with their actions set to print the received message to standard output. When `"hello"` is published to `/example` , `"hello"` will be printed on the screen twice, once by each subscriber.

2. One subscriber is subscribed to `/home/+/temperature`. When message is published to `/home/bedroom/temperature` and `/home/kitchen/temperature`, both messages are received by the subscribers.

3. One subscriber is subscribed to `/home/office/#` . When message is published to `/home/office/temperature` and `/home/office/humidity`, both messages are received by the subscribers.

You can read more about MQTT topic structure on [HiveMQ][d29016f5] website.

## Implementation

The provided Stub exposes two methods, `Publish(topic, T message)` and `Subscribe(topic, Action<T> message)` which need to be implemented. Accompanying test suite should be used to verify the implementation correctness. It's not necessary to make 100% test cases pass.

## Topics

### Publishing

Topic is a non-empty string consisting of a `/` separated `levels`, where each level is identified by _non-empty, case-sensitive, alphanumeric string_. Topic must start with a `/` character but may not end with it, and must contain at least one level.

- Examples of valid topics:

  ```
  /home/bedroom/temperature
       ^       ^__________^
       |            |
    separator     level
  ```

- Examples of invalid topics:

  ```
  /
  home//
  /h!
  ```

### Subscribing

Subscribe topic extends publishing topic, and can contain _wildcards:_ `+` and `#`.

- The `+` substitutes single topic level and it can be placed in any part of the topic,
- The `#` substitutes multiple levels and can be only placed at the end of the topic.

### Matching topics

Some examples of valid subscription topics:

```
/home/bedroom/temperature
/home/bedroom/humidity
```

#### Matching wildcards

The `+` wildcard can substitute any single level, but there may exist multiple of such wildcards.

Example:

```
/home/+/temperature
```

Would match:

```
/home/kitchen/temperature
/home/bedroom/temperature
```

But it would not match:

```
/home/kitchen/humidity (invalid last segment)
/office/kitchen/temperature (invalid first segment)
/home/kitchen/temperature/celsius (Invalid number of levels)
```

--------------------------------------------------------------------------------

The `#` wildcard can substitute a multiple levels, but there may be only one such wildcard and it may only appear at the end of the topic.

Example:

```
/home/#
```

Would match:

```
/home/temperature
/home/humidity
/home/bedroom/temperature
```

But it would not match:

```
/office/temperature
```

Again, you can use [HiveMQ][d29016f5] as a reference.

## Notes

- Implement your solution in `Solution/SimplePubSub.cs`
- If C# isn't your thing, feel free to re-implement test cases in language of your choice.
- Wildcard topics are for bonus points, and as such not all test cases have to pass.
- It's only necessary to make test cases pass with your `in-memory` implementation, there are no requirements outside of what is specified.

[d29016f5]: http://www.hivemq.com/blog/mqtt-essentials-part-5-mqtt-topics-best-practices "HiveMQ"
