# Widgets

Widgets zeigen verschiedene Informationen auf der Hud an

## Einstellungen

Die Einstellungen der Widgets könne über den Chat angepasst werden und werden, wie alle anderen Einstellungen der Mod in der `lama-mod-options.json` Datei gespeichert.

Die Groß- und Kleinschreibung spielt beim Ändern der Einstellungen der Widgets über den Chat keine Rolle.

Alle Widgets haben die folgenden Einstellungen:

- `<Name des Widgets>` Zeigt das Widget an oder blendet es aus
- `<Name des Widgets> x` Gibt den aktuellen Wert der X-Koordinate des Widgets wieder
- `<Name des Widgets> y` Gibt den aktuellen Wert der Y-Koordinate des Widgets wieder
- `<Name des Widgets> x <Zahl>` Ändert den Wert der X-Koordinate des Widgets
- `<Name des Widgets> y <Zahl>` Ändert den Wert der Y-Koordinate des Widgets

Alle Widgets, die Text anzeigen haben außerdem die folgenden Einstellungen:

- `<Name des Widgets> prefix` Zeigt den Prefix den Widgets an oder versteckt ihn
- `<Name des Widgets> color` Gibt die aktuelle Farbe des Widgets zurück
- `<Name des Widgets> color <Name der Farbe>` Ändert die Farbe des Widgets auf eine der voreingestellten Farben (zB red, green, blue, black, white, pink)
- `<name des Widgets> color <Rot> <Grün> <Blau>` Ändert die Farbe des Widgets auf einen anderen RGB Wert

## Fps Widget

Name: `FpsWidget`  
Zeigt die aktuellen FPS an

## Koordinaten Widget

Name: `CoordsWidget`  
Zeigt die aktuellen Koordinaten an

Einstellungen:

- `CoordsWidget nether` Schaltet das Anzeigen der Nether Koordinaten an oder aus

## Keystrokes Widget

Name: `KeystrokesWidget`  
Zeigt die aktuell gedrückten Tasten an

## Time Widget

Name: `TimeWidget`  
Zeigt die aktuelle Zeit an

Einstellungen:

- `TimeWidget seconds` Zeigt die Sekunden an oder blendet sie aus
- `TimeWidget date` Zeigt das Datum an oder blendet es aus

## Players Widget

Name: `PlayersWidget`  
Zeigt eine Liste aller Spieler, die auf dem Server sind an

Einstellungen:

- `PlayersWidget maxPlayers <Zahl>` Legt fest, wie viele Spieler maximal im Widget angezeigt werden. Die Zahl 0 bedeutet unendlich.

## Version Widget

Name: `Version Widget`  
Zeigt die Version der Lama Mod an, die du benutzt und ob eine neuere verfügbar ist

## Biome Widget

Name: `BiomeWidget`  
Zeigt den Namen des Biomes an, in dem du dich befindest

## LightLevel Widget

Name: `LightLevelWidget`  
Zeigt das Light Level des Blocks an, den man anguckt

## TargetedBlock Widget

Name: `TargetedBlockWidget`  
Zeigt die Koordinaten des Blocks an, den man anguckt

