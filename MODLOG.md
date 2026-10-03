# MODLOG — New Zealand Natives Mod

Fabric mod, 7-version Stonecutter matrix (1.20.1 / 1.20.4 / 1.20.6 / 1.21.1 /
1.21.11 / 26.2 / 26.3). Route: loader API (Fabric + Fabric API, GeckoLib
entities). No game code in the repo.

## Current state

`./gradlew build` produces all 7 jars on 1.21.x branch. Nodes: 1.20.1,
1.20.4, 1.20.6, 1.21.1, 1.21.11, 26.2, 26.3.

src/ holds the active version's fully-morphed state (yarn-1.21.1). `<26.1`
band in `stonecutter.gradle` converts that forward for yarn; `>=26.1` band
inverts it for mojang. Both bands see the FULL band rule set — the stitcher
merges everything before validating, so no source may share a target string
with another rule.

## Stitcher rules that cost hours (verified this session)

1. Two rules with the same target **cancel silently** — dedupe or error.
2. A rule whose target matches another rule's source is **shielded** from
   other bands' rules — cross-band and cross-executor.
3. The string band runs **forward when the predicate matches, backward when it
   doesn't** — on a different direction path than the regex band. Never use
   `string(current.parsed > X ...)` for era-specific text unless the backward
   direction is safe on every other version.
4. Two sources with the same target is rejected; one source with two targets
   is rejected; a chain `a -> sentinel -> b -> c` works in the validator.
5. Apostrophes in `//` comments leak an unclosed GString — Groovy reports
   `Unexpected input` several lines later. If the error is at
   `stonecutter.parameters {`, look several lines up.
6. The active node compiles `src/` directly with no band or gates, so get
   the entire woven band right before trusting "build succeeded one version
   but not another".
7. `versions/*/build/generated/` is the actual compiled input; it's wiped by
   `stonecutterGenerate` — never treat it as evidence of a prior failure.

## Verified

`./gradlew build` = 7 jars (1.20.1, 1.20.4, 1.20.6, 1.21.1, 1.21.11, 26.2,
26.3) on this branch. All node-specific APIs (spawngates, registry calls,
entity builders, client screens) verified per-node against the yarn tiny + jar.

## Next

Commit and push; the YAML CI is at .github/workflows/.