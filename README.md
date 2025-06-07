# UFI

Unified File Interface. Aimed to parse any file types into Kotlin structure.

## Concept

Two general types of files are considered:
1. <span style="color: red">L</span>ocal files, which support random access (read or write anywhere).
2. <span style="color: red">R</span>emote files, which only support sequential access.

Any "file"s are categorized into L or R by its random accessibility while using.
