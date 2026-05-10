# Vacation Planner

Aplicație console Java pentru planificarea vacanțelor.

## Descriere

Acest proiect implementează un sistem simplu de agenție de turism care permite adăugarea și afișarea de turiști, destinații, pachete de vacanță, hoteluri, transporturi, recenzii și itinerarii. De asemenea, oferă posibilitatea rezervării pachetelor, anulării rezervărilor și căutării de pachete pe baza unui cuvânt cheie.

## Funcționalități principale

- Gestionare turiști
- Gestionare destinații
- Gestionare pachete de călătorie
- Rezervări și anulări
- Sortare pachete după preț
- Căutare pachete
- Gestionare hoteluri
- Gestionare mijloace de transport
- Gestionare recenzii
- Gestionare itinerarii
- Încărcare și salvare date în fișiere CSV
- Auditare acțiuni într-un fișier CSV

## Tehnologii și concepte folosite

- Limbaj Java
- Programare orientată pe obiect
- Clase abstracte și moștenire
- Interfețe cu comportament implicit
- Excepții personalizate
- Enum-uri
- Singleton pentru servicii generice
- Colecții `List` și `Map` cu implementări multiple (`ArrayList`, `LinkedList`, `HashMap`, `TreeMap`)

## Structura proiectului

- `src/` - cod sursă Java
- `data/` - fișiere CSV pentru date
- `README.md` - documentație proiect

## Fișiere CSV folosite

- `data/users.csv`
- `data/destinations.csv`
- `data/packages.csv`
- `data/reservations.csv`
- `data/hotels.csv`
- `data/transports.csv`
- `data/reviews.csv`
- `data/itineraries.csv`
- `data/audit.csv`

## Instrucțiuni de rulare

1. Instalează un JDK (de exemplu OpenJDK) pe sistem.
2. Compilează codul din directorul proiectului:
   ```bash
   /opt/homebrew/opt/openjdk/bin/javac -d /tmp/vacationplanner_classes $(find src -name '*.java' | sort)
   ```
3. Rulează aplicația:
   ```bash
   /opt/homebrew/opt/openjdk/bin/java -cp /tmp/vacationplanner_classes Main
   ```

## Observații

- La pornire, aplicația încarcă automat datele din fișierele CSV.
- Orice acțiune definită de serviciu este înregistrată în `data/audit.csv`.
- Datele pot fi salvate manual în fișierele CSV folosind opțiunea din meniu.

## Autor

Proiect realizat pentru cerințele MAP Sem 2 / 2023.