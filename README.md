# Dragon Realms: Optimized — crash fix source

Target: Minecraft Java 1.21.11 / NeoForge 21.11.x / Java 21.

## What was fixed

The supplied crash log shows `NullPointerException: Item id not set` while constructing the spawn egg. Minecraft 1.21.11 requires an item ResourceKey to be set on `Item.Properties` before the item constructor runs. `ModItems.java` now sets IDs for both registered items.

The log's `Entity dragonrealms:fire_drake has no attributes` appears after registry loading rolls back due to the item error; check it again after rebuilding. If it persists, the attribute registration needs a separate fix.

## Build

Use GitHub Actions: upload this project to a GitHub repository, open Actions, run **Build Dragon Realms**, and download the `dragonrealms-optimized` artifact from a successful run. Or run `gradle build` with Java 21 and internet access.

This is a crash-fix source revision, not a claim that a new JAR has been compiled or tested. The project is still a prototype, not a full Ice and Fire port.


## Build on GitHub Actions

The repository includes `.github/workflows/build.yml`. Push this project to GitHub, open the **Actions** tab, select **Build Dragon Realms**, and run **Run workflow** if needed. When the job finishes successfully, open the run and download the `dragonrealms-optimized` artifact. The artifact contains the compiled JAR.

This source package has not been compiled in the current environment because Maven/NeoForge hosts are unreachable here. A successful GitHub Actions build is still required before treating the JAR as usable.
