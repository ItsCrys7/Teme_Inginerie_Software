# Trading – Design Patterns

## Factory (Simple Factory)
- Clasa [src/main/java/com/project/trading/patterns/AssetFactory.java](src/main/java/com/project/trading/patterns/AssetFactory.java) construiește instanțe de [src/main/java/com/project/trading/model/Asset.java](src/main/java/com/project/trading/model/Asset.java) pe baza tipului, întorcând subtipuri precum [src/main/java/com/project/trading/model/Stock.java](src/main/java/com/project/trading/model/Stock.java) și [src/main/java/com/project/trading/model/Crypto.java](src/main/java/com/project/trading/model/Crypto.java).
- Folosire: inițializarea datelor în [src/main/java/com/project/trading/service/TradingService.java](src/main/java/com/project/trading/service/TradingService.java).

## Strategy
- Interfața [src/main/java/com/project/trading/patterns/TradingStrategy.java](src/main/java/com/project/trading/patterns/TradingStrategy.java) definește contractul pentru verificarea tranzacțiilor (`canTrade`) și calculul comisioanelor (`calculateFee`).
- Implementarea [src/main/java/com/project/trading/patterns/StockStrategy.java](src/main/java/com/project/trading/patterns/StockStrategy.java) impune reguli de tranzacționare pentru acțiuni (interval orar M–V, 09–18).
- Folosire: în [src/main/java/com/project/trading/service/TradingService.java](src/main/java/com/project/trading/service/TradingService.java) pentru verificarea perioadelor de tranzacționare la `buy()` și `sell()`.

## Observer-like Notifications
- Componenta [src/main/java/com/project/trading/patterns/PriceNotifier.java](src/main/java/com/project/trading/patterns/PriceNotifier.java) colectează notificări atunci când prețurile se schimbă semnificativ.
- Folosire: în [src/main/java/com/project/trading/service/TradingService.java](src/main/java/com/project/trading/service/TradingService.java) în `simulateMarketChange()` pentru a emite alerte.

## Extensii rapide
- Adaugă noi subtipuri de `Asset` și extinde `AssetFactory` pentru alte tipuri de instrumente.
- Implementează alte strategii care extind `TradingStrategy` (ex: `CryptoStrategy`).
- Înlocuiește `PriceNotifier` cu un mecanism real de publish/subscribe (ex: WebSocket, messaging).
