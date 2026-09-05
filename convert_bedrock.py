#!/usr/bin/env python3
"""
Convert Bedrock addon assets to GeckoLib/Java Edition format.
Handles: geo models, animations, textures.
Does NOT convert entity behaviors (those need manual Java code).
"""

import json
import os
import shutil
from pathlib import Path

BEDROM_RP = Path("bedrock_code/resource_packs/natives_rp")
BEDROM_BP = Path("bedrock_code/behavior_packs/natives_bp")
JAVA_ASSETS = Path("src/main/resources/assets/newzealandnativesmod")

def convert_geo_model(src_path, dst_path):
    """Convert Bedrock geo.json to GeckoLib format if needed."""
    with open(src_path) as f:
        data = json.load(f)

    fmt = data.get("format_version", "1.12.0")

    if fmt == "1.12.0" and "minecraft:geometry" in data:
        # Already GeckoLib format, copy as-is
        dst_path.parent.mkdir(parents=True, exist_ok=True)
        shutil.copy2(src_path, dst_path)
        return "copied (already GeckoLib)"

    if "minecraft:geometry" not in data:
        # Try to find the geometry key
        geo_key = None
        for key in data:
            if key.startswith("geometry.") or key == "description":
                geo_key = key
                break

        if geo_key is None:
            return f"SKIPPED (no geometry key found in {src_path.name})"

        geo_data = data[geo_key]

        # Build GeckoLib format
        out = {
            "format_version": "1.12.0",
            "minecraft:geometry": [
                {
                    "description": {
                        "identifier": geo_key if geo_key.startswith("geometry.") else f"geometry.{geo_key}",
                        "texture_width": geo_data.get("texturewidth", geo_data.get("texture_width", 64)),
                        "texture_height": geo_data.get("textureheight", geo_data.get("texture_height", 64)),
                        "visible_bounds_width": geo_data.get("visible_bounds_width", 2),
                        "visible_bounds_height": geo_data.get("visible_bounds_height", 2),
                        "visible_bounds_offset": geo_data.get("visible_bounds_offset", [0, 0, 0])
                    },
                    "bones": geo_data.get("bones", [])
                }
            ]
        }

        # Fix field names in bones (Bedrock uses some different names)
        for bone in out["minecraft:geometry"][0]["bones"]:
            if "pivot" not in bone and "origin" in bone:
                bone["pivot"] = bone.pop("origin")

        dst_path.parent.mkdir(parents=True, exist_ok=True)
        with open(dst_path, 'w') as f:
            json.dump(out, f, indent='\t')
        return "converted (1.10.0 -> 1.12.0)"

    # Has minecraft:geometry but wrong version
    data["format_version"] = "1.12.0"
    dst_path.parent.mkdir(parents=True, exist_ok=True)
    with open(dst_path, 'w') as f:
        json.dump(data, f, indent='\t')
    return "fixed version"


def convert_animation(src_path, dst_path):
    """Animations are compatible — just copy."""
    dst_path.parent.mkdir(parents=True, exist_ok=True)
    shutil.copy2(src_path, dst_path)
    return "copied"


def convert_texture(src_path, dst_path):
    """Textures are just PNGs — copy as-is."""
    dst_path.parent.mkdir(parents=True, exist_ok=True)
    shutil.copy2(src_path, dst_path)
    return "copied"


def main():
    stats = {"models": [], "animations": [], "textures": []}

    # --- Models ---
    models_dir = BEDROM_RP / "models" / "entity"
    if models_dir.exists():
        for src in sorted(models_dir.glob("*.geo.json")):
            name = src.stem  # e.g. "kiwi.geo"
            dst = JAVA_ASSETS / "geo" / src.name
            result = convert_geo_model(src, dst)
            stats["models"].append((src.name, result))
            print(f"  MODEL  {src.name:40s} -> {result}")

    # --- Animations ---
    anim_dir = BEDROM_RP / "animations"
    if anim_dir.exists():
        for src in sorted(anim_dir.glob("*.json")):
            if src.name == "manifest.json":
                continue
            dst = JAVA_ASSETS / "animations" / src.name
            result = convert_animation(src, dst)
            stats["animations"].append((src.name, result))
            print(f"  ANIM   {src.name:40s} -> {result}")

    # --- Textures ---
    tex_dir = BEDROM_RP / "textures" / "entity"
    if tex_dir.exists():
        for src in sorted(tex_dir.rglob("*.png")):
            rel = src.relative_to(tex_dir)
            dst = JAVA_ASSETS / "textures" / "entity" / rel
            result = convert_texture(src, dst)
            stats["textures"].append((str(rel), result))
            print(f"  TEX    {str(rel):40s} -> {result}")

    # --- Summary ---
    print(f"\n{'='*60}")
    print(f"Converted {len(stats['models'])} models, "
          f"{len(stats['animations'])} animations, "
          f"{len(stats['textures'])} textures")
    print(f"Output: {JAVA_ASSETS}")

    # --- List what's still needed ---
    print(f"\n{'='*60}")
    print("REMAINING MANUAL WORK:")
    print("  - Entity behavior JSONs need Java entity classes")
    print("  - Loot tables need Java loot table JSONs")
    print("  - Spawn rules need Java biome modifications")
    print(f"  - Check bedrock_code/behavior_packs/natives_bp/entities/ for behaviors")
    print(f"  - Check bedrock_code/behavior_packs/natives_bp/loot_tables/ for drops")


if __name__ == "__main__":
    main()
