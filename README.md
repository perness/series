**DokumentasjonNRK oppgave**

## Intro

Jeg har valgt oppgave 1 for
intervjuet.

Dette er en Spring boot
applikasjon med bruk av Kotlin og
Maven som henter ut informasjon om
tv-serier. Applikasjonen skal svare på
forskjellige http kall fra bruker.IDE
brukt er Intellij

## Oppgave

**Programmet skal kunne gi
data på følgende forespørsler:**

1) Hent all data for en gitt
serieid og en gitt dato

2) List opp alle serieider og
antall views

3) List opp alle serieider som
er sett på tv

4) Hent ut den serien som er
mest populær (har flest visninger) i 2018

5) List opp alle serieider og
antall views sortert etter dato

## Løsning

**For
å kjøre løsningen trengs**

- JDK 1.8 eller høyere 
- Maven 3.5 eller høyere 
- Et IDE for å kjøre
	applikasjonen 

**Bruk av applikasjon**

1. Løsningen ligger i
	mappen merket «series», denne importeres av IDE.  
2. I root katalogen kjør
	kommando «mvn package -DskipTests» for å laste ned alle
	avhengigheter(maven dependencies) 
3. For å starte
	applikasjonen gå inn på org/filereader/series/SeriesApplication.kt
	og kjør «main» metoden 
4. Applikasjonen skal nå
	være kjørende å kunne svare på http kall 

**Det er 2 måter
applikasjonen kan testes**

1. Bruke nettleser for å
	kjøre alle endepunkter 
2. Bruke Swagger 

Med bruk av nettleser kan vi
bruke følgende url for å hente data

- For å hente
	all data for en gitt serieid og en gitt dato - [http://localhost:8080/seriesrest/api/series?](http://localhost:8080/seriesrest/api/series?seriesId)[seriesId](http://localhost:8080/seriesrest/api/series?seriesId)=’navn
	på serie’&date=’valgt dato’ - Eksempel -
	[http://localhost:8080/seriesrest/api/series?seriesId=martin-og-mikkelsen&date=20180105](http://localhost:8080/seriesrest/api/series?seriesId=martin-og-mikkelsen&date=20180105)
	– gir svar  

- For å hente alle
	serieider og antall views -
	[http://localhost:8080/seriesrest/api/series/seriesandviews](http://localhost:8080/seriesrest/api/series/seriesandviews)
	- 
- For
	å hente alle
	serieider som er sett på tv -
	http://localhost:8080/seriesrest/api/series/seenontv - 
- For
	å hente den
	serien som er mest populær (har flest visninger) -
	http://localhost:8080/seriesrest/api/series/mostpopular - 
- For
	hente alle serierId og views sortert etter dato fikk jeg ikke helt
	til så der hentes all data sortert etter dato -
	http://localhost:8080/seriesrest/api/series/seriesandviewssorted - 

Med bruk av Swagger kan vi
bruke

- [http://localhost:8080/seriesrest/api/swagger-ui.html](http://localhost:8080/seriesrest/api/swagger-ui.html) 

Løsningen kan også lastes
ned fra github - https://github.com/perness/series.git -
