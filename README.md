# Forms - Spring Boot API

Dieses Projekt ist eine Spring-Boot-basierte API mit integriertem **Thymeleaf-Frontend**.  
Es stellt **Formulare für Forschungsdaten, Artikel und Monographien** für **FRL** bereit.

---

## Zweck der Anwendung

Die API dient dazu:

- strukturierte **Formulare** bereitzustellen  
- Eingaben für Forschungsdaten, Artikel und Monographien zu erfassen  
- eine Grundlage für die FRL-Erstellung zu liefern  
- HTML-Formulare direkt über Spring/Thymeleaf zu rendern  

Die App kombiniert **REST-Logik** mit serverseitig generierten **HTML-Oberflächen**.

---

## Thymeleaf – Was ist das & warum wird es genutzt?

## **Was ist Thymeleaf?**
Thymeleaf ist ein **serverseitiges Java-Template-Engine** für HTML.  
Es verarbeitet HTML-Dateien und bindet dynamische Daten aus Spring Boot ein.

## **Was ermöglicht es?**
- dynamisches Rendern von HTML-Formularen  
- Schleifen, Bedingungen, Validierungsfehler direkt im HTML  
- einfache Kommunikation zwischen Spring-Backend und HTML  
- kein separates JavaScript-Frontend notwendig  

In dieser Anwendung werden damit alle Formulare für FRL erstellt und angezeigt.

---

### ⚙️ Voraussetzungen

- Java JDK (hier: JDK21)
- Maven  
- Entwicklungsumgebung (z.B. Eclipse) 
- Lombok (für automatische Getter/Setter/Builder)    

> **Hinweis:** Lombok muss in Eclipse installiert und aktiviert sein, damit die Annotationen korrekt erkannt werden.
---

### Projekt in Eclipse importieren

1. **Eclipse öffnen**  
2. `File` → `Import`  
3. `Maven` → `Existing Maven Projects`  
4. Projektordner auswählen  

Eclipse lädt die Dependencies automatisch.

---

## Konfiguration der Umgebung (Dev / Prod)

Die Anwendung nutzt **Spring Profiles**, um zwischen **Dev-** und **Prod-Umgebung** zu unterscheiden.

---

### Dev-Version konfigurieren (Zugriff auf Ressourcen von FRL-Testserver)

1. **application.properties**  
   `spring.profiles.active=dev`

2. **pom.xml**  
   Ganz unten im `<build>`-Block:
   ```
     <build>  
         <finalName>forms-dev</finalName>  
     </build>
   ```

---

### Prod-Version konfigurieren (Zugriff auf Ressourcen von FRL-Produktionsserver)

1. **application.properties**  
   `spring.profiles.active=prod`

2. **pom.xml**  
   Ganz unten im `<build>`-Block:
     ``` 
     <build> 
         <finalName>forms</finalName>  
     </build>
     ```
---

In der `application.properties` befinden sich Platzhalter, z. B.:
```
  resource.example=${PATH_EXAMPLE}
```
> Diese Werte werden **nicht im Repository gepflegt**, sondern zur Laufzeit über **externe Properties-Dateien** geladen, die sich auf den FRL-Servern befinden.

### Einbindung der Umgebungsdateien
```
spring.config.import=optional:env-${spring.profiles.active}.properties
  ```
- Abhängig vom Profil werden eingebunden:  
  - `env-dev.properties`  
  - `env-prod.properties`

---

## Build der Anwendung (WAR-Datei erstellen)

- Nachdem die gewünschte Umgebung (`dev` oder `prod`) und der Dateiname (`forms-dev` oder `forms`) eingestellt wurde, kann die WAR-Datei erstellt werden mit:  
  ````mvn clean install````

### Ergebnis

- Die erzeugte WAR-Datei befindet sich unter `/target/`
- Dieser Befehl muss **einmal für forms** und **einmal für forms-dev** ausgeführt werden.

---

## Deployment

- Die erzeugten WAR-Dateien (`forms.war` und `forms-dev.war`) können nun auf den Server hochgeladen und dort deployed werden.
