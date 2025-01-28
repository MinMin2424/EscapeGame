## GameBoard

Třída **'GameBoard'** slouží k reprezentaci herního pole a poskytuje metody pro manipulaci s 
herními objekty v rámci této herní plochy.

Herní pole je reprezentováno dvourozměrným polem celých čísel, kde každé číslo odpovídá určitému 
objektu nebo hernímu prvku na daném místě v herní ploše. Počet políček v herním poli je pevně 
stanoven na hodnotu 10x10.

**Třída obsahuje metody pro:**

1. Inicializaci herního pole s výchozími hodnotami - **'initializeBoard()'**
2. Získání herního pole - **'getBoard()'**
3. Získání velikosti herního pole v pixelech - **'getSize()'**
4. Získání rozměru dlaždice v herním poli v pixelech - **'getTileDim()'**
5. Umístění herních objektů, jako jsou překážky, hráč, herní předměty a pole vedoucí do
   dalšího levelu, na herní pole - **'placeObject()'**, **'placePlayer()'**, **'placeItem()'**,
   **'placeNextLevel()'**

## Player

Třída **'Player'** reprezentuje hráče v herním prostředí a poskytuje funkcionalitu pro správu 
životů hráče, jeho pozici a inventáře.

Hráč má určitý počet životů, který je inicializován na maximální hodnotu při vytvoření instance 
hráče. Hráč je umístěn na herním poli na základě svých souřadnic **'playerX'** a **'playerY'**,
které lze nastavit pomocí příslušných metod.

Každý hráč má také svůj inventář, který je instancí třídy **'Inventory'**. Inventář umožňuje 
hráči ukládat a manipulovat s herními předměty.

**Hráč může interagovat s herním světem pomocí různých metod:**

1. Zvýšení počtu životů hráče pomocí lektvaru z inventáře - **'increaseHealth()'**
2. Ztráta životů hráče, což může nastat například při kolizi s překážkami - **'loseHealth()'**, **'collideWithObstacle()'**
3. Sběr herních předmětů, které může hráč použít nebo vyrobit další předměty - **'collectItem()'**
4. Použití předmětů z inventáře - **'useItem()'**
5. Vytváření nových předmětů pomocí surovin v inventáři - **'craftItem()'**

## Inventory

Třída **'Inventory'** představuje inventář, ve kterém jsou uloženy předměty. 
Každý předmět je reprezentován instancí třídy **'Item'**. Inventář umožňuje přidávat nové 
předměty, odebírat je a získávat seznam všech předmětů uložených v inventáři.

**Hlavní funkce třídy 'Inventory' zahrnují:**

1. Přidání předmětů: Metoda **'addItem()'** umožňuje přidat nový předmět do inventáře. 
Pokud je předmět již v inventáři obsažen, zvýší se počet kusů tohoto předmětu o počet nových 
kusů. Pokud předmět v inventáři ještě není, je přidán do seznamu předmětů
2. Odebrání předmětů: Metoda **'removeItem()'** umožňuje odebrat předmět z inventáře. Pokud je počet 
kusů předmětů větší než 1, sníží se počet kusů o 1. Pokud je počet kusů předmětu roven 1, 
je předmět kompletně odebrán z inventáře
3. Získání seznamu předmětů: Metoda **'getItems()'** vrací seznam všech předmětů uložených v 
inventáři

## Item

Třída **'Item'** reprezentuje herní předmět a umožňuje nám pracovat s informacemi o názvu a 
množství daného předmětu.

Každý předmět má svůj název, který je definován jako řetězec, a množství, které udává počet 
daného předmětu. Třída obsahuje konstruktor pro vytvoření instance předmětu s určeným názvem
a množstvím.

**Hlavní metody třída 'Item' zahrnují:**

1. Metoda pro získání názvu předmětu - **'getName()'**
2. Metoda pro získání množství předmětu - **'getQuantity()'**
3. Metoda pro nastavení množství předmětu na zadanou hodnotu - **'setQuantity()'**

## ItemFactory

Třída **'ItemFactory'** je zodpovědná za vytváření nových herních předmětů na základě zadaného 
typu výroby a stavu inventáře.

Hlavní metoda této třídy je **'createItem()'**, která přijímá jako parametry typ předmětu, 
který se má vytvořit, a inventář, ve kterém jsou uloženy potřebné suroviny pro výrobu. 
Metoda nejprve získá seznam potřebných surovin pro vytvoření daného předmětu. 
Poté zkontroluje, zda jsou v inventáři dostatečné množství surovin pro vytvoření předmětu.

Pokud jsou všechny potřebné suroviny k dispozici, provede se jejich odebrání z inventáře a 
vytvoří se nový předmět. Typ vytvořeného předmětu závisí na zadaném typu výroby. 
Pokud v inventáři nejsou dostatečné suroviny pro výrobu předmětu, metoda vrací hodnotu **'null'**.

## PlayerController

Třída **'PlayerController'** slouží k řízení pohybu a interakce hráče v herním světě.

Třída pracuje s instancemi tříd **'Player'** a **'GameBoard'**, kde **'Player'** reprezentuje 
hráče a **'GameBoard'** herní pole, na kterém se hráč pohybuje.

Hlavní metoda této třídy je **'move()'**, která umožňuje hráči pohybovat se po herním poli v 
závislosti na zadanému směru.

**Tato metoda provádí několik důležitých kroků:**

1. Získání aktuální pozice hráče - **'currentX'** a **'currentY'**
2. Kontrola hráčova zdraví - **'checkPlayerHealth()'**
3. Výpočet nové pozice hráče na základě zadaného směru - **'newX'** a **'newY'**
4. Zjištění, zda nová pozice hráče nevychází mimo herní pole
5. Zjištění typu objektu na nové poli v herním poli
6. Interakce hráče s objekty na herním poli, jako jsou překážky, nepřátelé a herní předměty.
7. Přesun hráče na novou pozici v herním poli - **'movePlayer()'**

Třída obsahuje pomocné metody pro kontrolu zdraví hráče, zjištění přítomnosti klíče v 
inventáři hráče a manipulaci s herními objekty.

## GameObjects

Třída **'GameObjects'** je výčtový typ (enum), který reprezentuje různé objekty v herním 
světě (WALL, GHOST, FIRE, WATER).

Každý objekt má svůj kód **'code'**, který slouží k identifikaci, a další atributy, 
jako jsou informace o tom, zda je objekt překážkou **'isObstacle'** nebo způsobuje poškození 
hráči **'isDamage'**, a název obrázku **'imageName'**, který je s ním spojen.

Kromě toho třída obsahuje metody pro získání hodnoty kódu, názvu obrázku, informace o tom, 
zda je objekt překážkou nebo způsobuje poškození, a také metodu pro získání instance objektu 
podle jeho kódu.

## GameItems

Třída **'GameItems'** je výčtový typ (enum), reprezentující různé herní předměty, 
které mohou být umístěny na herní pole (HERB, ORE, WATER_ITEM, KEY). 
Každý herní předmět je identifikován svým kódem 'code' a má název obrázku **'imageName'**.

**Třída obsahuje:**

1. Definice jednotlivých herních předmětů jako konstant s jejich kódem a obrázků
2. Konstruktor, který inicializuje kód a obrázku pro každý herní předmět
3. Metody pro získání kódu **'getCode'** a obrázku **'getImageName'** daného herního předmětu
4. Metodu **'getByCode'**, která umožňuje získat herní předmět na základě jeho kódu. 
Tato metoda prochází všechny dostupné herní předměty a porovnává jejich kódy s kódem 
poskytnutým jako argument.

## CraftingItems

Třída **'CraftingItems'** je výčtový typ (enum), který reprezentuje různé předměty, 
které lze vytvořit pomocí craftingu v herním světě (SWORD, POTION). 
Každý prvek v tomto výčtu představuje konkrétní výrobní recept na určitý herní předmět.

**Každý prvek výčtu obsahuje informace o:**

1. Kód předmětu, který identifikuje daný výrobek
2. Pole požadovaných herních předmětů nebo surovin, které jsou potřebné k vytvoření daného
    předmětu
3. Názvu obrázku předmětu, které je zobrazen při tvorbě a používání daného předmětu

**Třída poskytuje metody pro:**

1. Získání kód předmětu
2. Získání pole požadovaných herních předmětů
3. Získání názvu obrázku předmětu.

## GameNextLevel

Třída **'GameNextLevel'** je výčtový typ (enum), který slouží k reprezentaci herního 
prvku pole vedoucí do dalšího levelu.

Třída poskytuje metody pro získání kódu a názvu obrázku příslušícího danému herním prvku.

## Direction

Třída **'Direction'** představuje výčtový typ (enum), který definuje základní směry pohybu 
v rámci herního prostředí. Konkrétně jsou to směry "UP", "DOWN", "LEFT" a "RIGHT".





