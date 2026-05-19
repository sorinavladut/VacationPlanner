# Documentație proiect: Vacation Planner

## Scopul proiectului

Vacation Planner este o aplicație console Java pentru gestionarea unei agenții de turism. Scopul este de a demonstra concepte OOP, manipulare de colecții, persistență în fișiere CSV, excepții personalizate și auditare.

## Cerințe îndeplinite

1. Nu are erori de compilare (verificat cu OpenJDK 25.0.2).
2. Proiectul implementa cel puțin 10 acțiuni/ interogări din meniul aplicației.
3. Proiectul include cel puțin 8 tipuri de obiecte administrate.
4. Folosește Java și concepte OOP.
5. Există clase simple cu atribute private/protected.
6. Există clase abstracte și interfețe cu comportament implicit.
7. Se folosește moștenire și polimorfism.
8. Există cel puțin un serviciu care expune operațiunile sistemului.
9. Clasa `Main` apelează metode din serviciu.
10. Sunt definite excepții personalizate și folosite pentru decizii.
11. Există enum-uri.
12. Datele sunt stocate în fișiere CSV.
13. Există servicii singleton pentru citire și scriere CSV.
14. La pornire, datele se încarcă automat din fișierele CSV.
15. Există un serviciu de auditare care scrie acțiunile în `data/audit.csv`.

## Obiecte și entități gestionate

Proiectul include și gestionează următoarele tipuri de obiecte:

- `Tourist`
- `Destination`
- `TravelPackage`
- `Reservation`
- `Hotel`
- `Transport`
- `Review`
- `Itinerary`
- `User` (clasă abstractă)
- `Admin` (subclasă `User`)

Aceasta acoperă mai mult de 8 tipuri de obiecte relevante.

## Funcționalități oferite

Aplicația pune la dispoziție următoarele acțiuni:

1. Afișare turiști
2. Afișare destinații
3. Afișare pachete
4. Rezervare pachet
5. Anulare rezervare
6. Căutare pachete
7. Adăugare turist
8. Adăugare destinație
9. Adăugare pachet
10. Afișare hoteluri
11. Adăugare hotel
12. Afișare transporturi
13. Adăugare transport
14. Afișare recenzii
15. Adăugare recenzie
16. Afișare itinerarii
17. Adăugare itinerariu
18. Reîncărcare date din CSV
19. Salvare date în CSV
20. Sortare pachete după preț

## Structura proiectului

- `src/` - codul sursă Java
  - `Main.java` - clasa principală cu meniul aplicației
  - `models/` - clasele de model care definesc entitățile
  - `services/` - logica aplicației, persistență și audit
  - `interfaces/` - interfețe cu comportament definit
  - `exceptions/` - excepții personalizate
  - `enums/` - enum-uri folosite în aplicație
- `data/` - fișiere CSV de date
- `README.md` - fișă sumară a proiectului
- `DOCUMENTATION.md` - documentație detaliată

## Arhitectura aplicației

### Clase de model

- `User` - clasă abstractă pentru utilizatori
- `Tourist` - extinde `User`
- `Admin` - extinde `User`
- `Destination` - obiect ce reprezintă o destinație turistică
- `TravelPackage` - pachet de călătorie cu destinație, preț și locuri disponibile
- `Reservation` - rezervare asociată unui turist și unui pachet
- `Hotel` - hotel disponibil în sistem
- `Transport` - tip de transport și cost
- `Review` - recenzie a unui turist
- `Itinerary` - plan de activități pentru un pachet

### Interfețe

- `Bookable` - definește operațiuni de rezervare/cancelare
- `Searchable` - definește metoda `matches(...)` și oferă un comportament implicit `search(...)`

### Servicii

- `TravelAgencyService` - expune operațiunile aplicației și gestionează datele în memoria aplicației.
- `CsvService` - singleton generic pentru citire și scriere în fișiere CSV.
- `AuditService` - singleton care scrie înregistrări de audit în `data/audit.csv`.

### Enum-uri

- `ReservationStatus` - stările rezervării (`PENDING`, `CONFIRMED`, `CANCELLED`)
- `TransportType` - tipurile de transport (`PLANE`, `TRAIN`, `BUS`)

## Persistența datelor în CSV

Datele sunt stocate în următoarele fișiere CSV:

- `data/users.csv`
- `data/destinations.csv`
- `data/packages.csv`
- `data/reservations.csv`
- `data/hotels.csv`
- `data/transports.csv`
- `data/reviews.csv`
- `data/itineraries.csv`
- `data/audit.csv`

### Formate CSV importante

- `users.csv`: `id,name,email,passport`
- `destinations.csv`: `id,country,city,description`
- `packages.csv`: `id,title,destinationId,price,spots`
- `reservations.csv`: `id,touristId,packageId,status`
- `hotels.csv`: `id,name,stars`
- `transports.csv`: `id,type,price`
- `reviews.csv`: `touristId,rating,comment`
- `itineraries.csv`: `id,activity1;activity2;...`
- `audit.csv`: `action,timestamp`

## Flux de rulare

1. Start aplicație cu `Main`.
2. `TravelAgencyService` încarcă datele din fișierele CSV.
3. Utilizatorul vede meniul și poate alege o acțiune.
4. Fiecare acțiune efectuează modificări în memorie.
5. Salvarea explicită în CSV se face opțional la alegerea `19. Save data to CSV`.
6. Orice acțiune este înregistrată în `data/audit.csv`.

## Instrucțiuni de instalare și rulare

### 1. Instalează JDK

Recomandat: OpenJDK 25.0.2 pe macOS.

### 2. Compilează proiectul

```bash
/opt/homebrew/opt/openjdk/bin/javac -d /tmp/vacationplanner_classes $(find src -name '*.java' | sort)
```

### 3. Rulează aplicația

```bash
/opt/homebrew/opt/openjdk/bin/java -cp /tmp/vacationplanner_classes Main
```

### 4. Rulare în VS Code

- Deschide folderul proiectului în VS Code.
- Deschide `src/Main.java`.
- Apasă `Run` sau `Debug`.
- Alternativ, creează `.vscode/launch.json` dacă este necesar.

## Exemplu de utilizare

- Alege `1` pentru a vedea lista turiștilor.
- Alege `3` pentru a vedea pachetele disponibile.
- Alege `4` pentru a rezerva un pachet.
- Alege `19` pentru a salva modificările în fișiere CSV.

## Observații finale

- Aplicația este construită pentru demonstrarea conceptele MAP Sem 2.
- Există suport pentru multiple tipuri de colecții și implementări.
- Auditarea asigură istoricul operațiunilor executate.
- Salvarea datelor este controlată manual pentru a evita scrierea automată neintenționată.
