# AGENTS.md — New Zealand Natives Mod

Fabric Minecraft mods (Java, GeckoLib entities, ModMenu config) ported from the Bedrock addon in `bedrock_code/` (`convert_bedrock.py` assists the port; Bedrock behavior is the spec when in doubt).

## Branches — the version matrix

- `1.21.x` — **Stonecutter branch**, builds 1.21.1 + 1.21.11 jars from one tree.
- `26.2` (default), `26.3` — plain Loom branches, one MC version each.
- `1.21.1`, `1.21.11` — legacy, superseded by `1.21.x`. Don't develop here.
- `bedrock` — upstream Bedrock reference, not Java.

## Stonecutter rules (`1.21.x` only)

- Plugin 0.9.8, Groovy DSL (`kotlinController = false`, `centralScript = "build.gradle"`).
- Nodes: `1.21.1`, `1.21.11`, `26.2`, `26.3` (Fabric) and `1.21.1-neoforge` (pilot, ModDevGradle). Per-loader buildscripts: `build.gradle` (Fabric) vs `build.neoforge.gradle` (NeoForge).
- **Shared `src/` is Mojang-official mapped for every node** — fabric nodes use `loomx.applyMojangMappings()` (`loom-back-compat` plugin picks the right Loom per MC), no Yarn anywhere. Version-scoped name drift lives in `stonecutter.gradle` `parameters.replacements`: `>=1.21.11` renames `ResourceLocation`→`Identifier`, moves `Dolphin`, render-state packages; `>=26.1` renames `GuiGraphics`→`GuiGraphicsExtractor` and GeckoLib `software.bernie.geckolib`→`com.geckolib`.
- Per-version deps live in `versions/<mc>/gradle.properties`, never root. 26.x pulls geckolib/modmenu from Modrinth maven (`geckolib_version`/`modmenu_modrinth`); 1.21.x uses geckolib_artifact/modmenu_version — `build.gradle` switches on `hasProperty`.
- Era-specific resources (worldgen JSON, loot tables, `items/` item models, geckolib assets, lang) live in `versions/<mc>/src/main/resources`; only byte-identical files stay in shared `src/*/resources`.
- Version deltas in shared `src/` use `//? if <predicate> { … //?}` sibling gates — flat, never nested (an inactive outer gate mangles inner markers). Common bands: `<=1.21.1`, `>1.21.1 && <26.1`, `>=26.1`, `>=26.3`. Loader deltas use `//? if fabric { … //?}` / `//? if neoforge { … //?}`. Raw file state must compile for the ACTIVE node: true-branch code plain, false branches inside `/* */`.
- Jars are named `{mod_version}+{mc}` via `sc.current.version` — keep it, releases depend on it.
- **Switch active version before building/running a target** (`Set active project to …` task); **run `Reset active project` before committing** or diffs carry the wrong morphed state.
- Never commit `versions/*/build` (generated, gitignored). `stonecutter.gradle` IS committed — but only in its reset state.

## NeoForge node (`1.21.1-neoforge`)

- Shared `src/` is Mojang-mapped, so the NeoForge node compiles it natively — no mapping table. Loader deltas are `//? if fabric/neoforge` gates; fix drift by editing gates or the controller replacements in `stonecutter.gradle`, never the generated output.
- `NeoForgeMod.java` (node-local, `versions/1.21.1-neoforge/src/main/java/`) registers everything via `DeferredRegister` — entities, items, blocks, sounds, creative tab, features, attributes, renderers. Fabric-only files (`NewZealandNativesMod`, `NativesSpawns`, `NativesItemGroups`, ModMenu/datagen/client entrypoints) are excluded from the NeoForge compile.
- `versions/1.21.1-neoforge/src/client/{java,resources}/` must exist (`.gitkeep`) or Stonecutter skips the client sourceset.
- Pilot scope: registration + renderers + attributes. Spawns/worldgen injection (Fabric `BiomeModifications`) has no NeoForge wiring yet — biome-modifier JSONs are the follow-up.

## Build

- `./gradlew build` (root task builds all Stonecutter nodes — fabric ×4 eras plus the NeoForge jar).
- Target one version: `./gradlew :1.21.1:build :26.2:build`; run client: `:1.21.1:runClient`.
- **Run Gradle on Temurin 25 for the whole branch** (`/home/blake7/.jdks/`) — Loom refuses 26.x nodes on a Java 21 daemon. Older nodes still compile at `release=21`. CI mirrors this.
- Mappings: all nodes Mojang-official (fabric: `loomx.applyMojangMappings()`; 26.x unobfuscated). Client code lives under both `src/client/java/blake7/client/` and `.../newzealandnativesmod/` — check which package a file belongs to before moving it.
- Dependency versions come from `gradle.properties` (per-branch, or per-node under `versions/`) — check https://modmuss50.me/fabric.html for current sets. GeckoLib is v4 on 1.21.1, v5 elsewhere; ModMenu versions also differ per MC — don't unify them.

## Release flow (CI)

- `build.yml` (every branch): build + upload jars on push/PR.
- `publish.yml` (default branch only): when **all** version branches share `mod_version` and no `vX` release exists, matrix-builds each branch and publishes **one** combined release. `0.*`/`*-*` versions publish as pre-release.
- To cut a release: bump `mod_version` in `gradle.properties` on every version branch and push. Manual escape hatch: `git tag vX && git push origin vX`.

## In-game verification

- Run the client headless: `Xvfb :NN -screen 0 1280x800x24`, then `DISPLAY=:NN ... ./gradlew runClient` (prefix `:1.21.1:` etc. on `1.21.x`). Drive menus/chat with `xdotool`, screenshot with `import -window root /tmp/*.png`, assert via server log output — not screenshots.
- Each version branch has its own `run/` dir (gitignored). Never reuse a save across MC versions without expecting an upgrade prompt.
- Keep screenshot reads to a minimum per session; image-heavy sessions hit provider limits — prefer log assertions (`Summoned…`, `Placed …`, `Changed the block …`).

## Stack decisions (don't relitigate without new evidence)

- Stonecutter + Fabric Loom (+ NeoGradle if NeoForge ever happens). No Architectury anything — the Stonecutter org archived its Architectury template; the maintained multiloader template is Loom + NeoGradle.
