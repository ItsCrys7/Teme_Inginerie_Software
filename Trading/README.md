# Stock Trading Simulation System

Acest proiect este o aplicație web de simulare a tranzacționării bursiere (Stocks & Crypto), dezvoltată în **Java** folosind framework-ul **Spring Boot**. Aplicația pune accent pe arhitectura software, utilizând multiple **Design Patterns** pentru a asigura scalabilitatea și mentenabilitatea codului.

## 🚀 Tehnologii Utilizate

* **Backend:** Java 17+, Spring Boot 3.x (Spring Web, Spring Data JPA)
* **Frontend:** Thymeleaf (Template Engine), HTML5, Bootstrap 5.3 (Responsive & Dark Mode support)
* **Database:** H2 Database (In-Memory SQL)
* **Tools:** Maven, Lombok

## 🏗️ Arhitectură și Design Patterns

Proiectul implementează cerințele de "Social Engineering" demonstrând utilizarea următoarelor șabloane de proiectare:

### 1. Factory Pattern
* **Unde este folosit:** `AssetFactory.java`
* **Motivație:** Sistemul gestionează tipuri diferite de active (`Stock` și `Crypto`). Deși ambele moștenesc clasa `Asset`, crearea lor directă (`new Stock()`) ar fi creat un cod rigid. Factory Pattern centralizează logica de instanțiere, permițând adăugarea ușoară de noi tipuri de active (ex: Commodities) fără a modifica logica clientului.

### 2. Strategy Pattern
* **Unde este folosit:** `TradingStrategy` (Interfață) -> `StockStrategy.java` (Implementare)
* **Motivație:** Regulile de tranzacționare diferă fundamental între active:
    * **Stocks:** Pot fi tranzacționate doar Luni-Vineri, între 09:00 - 18:00.
    * **Crypto:** Pot fi tranzacționate 24/7.
    * **Day Trading vs Long Term:** Taxele și penalizările diferă.
    * Strategy Pattern permite schimbarea algoritmului de validare și taxare la runtime (`canTrade()`, `calculateFee()`), făcând sistemul flexibil.

### 3. Observer Pattern
* **Unde este folosit:** `PriceNotifier.java`
* **Motivație:** Atunci când prețul unui activ fluctuează semnificativ, utilizatorul trebuie notificat. Componenta `PriceNotifier` "observă" schimbările din piață și generează alerte, decuplând logica de simulare a pieței de logica de notificare (UI).

### 4. MVC (Model-View-Controller)
* **Unde este folosit:** Arhitectura generală (`WebController`, `TradingService`, `Thymeleaf Templates`).
* **Motivație:** Separă clar logica de business (Backend) de interfața cu utilizatorul (Frontend), facilitând dezvoltarea și testarea independentă.

---

## 💼 Funcționalități Cheie

### 1. Gestionarea Portofoliului
* Utilizatorii pot vizualiza balanța contului și activele deținute.
* Calcul automat al valorii portofoliului.

### 2. Tranzacționare (Buy/Sell)
* **Cumpărare:** Utilizatorul selectează activul, cantitatea și strategia (`Long Term` sau `Day Trading`).
* **Vânzare:** Se poate vinde orice activ din portofoliu, profitul fiind adăugat la balanță.
* **Validări:** Sistemul previne tranzacțiile de acțiuni (Stocks) în afara orelor de program (Weekend sau noaptea), conform strategiei implementate.

### 3. Simulare Piață
* Butonul **"Simulate Market"** generează fluctuații aleatorii de preț pentru toate activele.
* Dacă fluctuația este mare, sistemul generează o notificare automată (via Observer).

### 4. Interfață Modernă
* Design responsive folosind Bootstrap 5.
* **Dark Mode / Light Mode** cu persistența preferinței utilizatorului.

---

## ⚙️ Cum se rulează proiectul

1.  **Cerințe:** Java JDK 17 (sau mai nou) și Maven instalate.
2.  **Clonare/Deschidere:** Deschide folderul proiectului în VS Code sau IntelliJ.
3.  **Run:** Rulează clasa principală `TradingApplication.java`.
4.  **Acces:** Deschide browserul la adresa:
    ```
    http://localhost:8080
    ```
5.  **Baza de date:** Datele sunt inițializate automat la fiecare pornire (User: `student`, Balance: `10000`).

---

## 📝 Structura Proiectului