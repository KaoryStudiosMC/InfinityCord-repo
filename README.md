InfinityCord
=========

InfinityCord is a fork of the well-known [BungeeCord](https://github.com/SpigotMC/BungeeCord) server teleportation suite.

Waterfall focuses on three main areas:

- **Stability**: Waterfall aims to be stable. We will achieve this through making the code base testable and discouraging practices that lead to proxy lag.
- **Features**: Waterfall aims to include more features than canonical BungeeCord.
- **Scalability**: Waterfall should be able to handle a large number of concurrent players, given a reasonably modern CPU, memory, and good network connection.

## Why fork BungeeCord?

Think of Waterfall as a principles fork.

Waterfall was forked because of the fact that upstream does not accept many contributions that are intended to better the ecosystem. Simply put, Waterfall aims to better
the ecosystem by allowing changes to be exposed to a wider audience more quickly.

Waterfall will still track upstream BungeeCord and merge changes as needed.

## How To (Server Admins)

Download a copy of InfinityCord.jar here: [InfinityCord](https://github.com/HappyRogelio7/InfinityCord/blob/main/InfinityCord-Downloads/Update/Download-jar/InfinityCord.jar)

Waterfall requires **Java 8** or above.

## How To (Plugin Developers)
------
 * See our API patches [here InfinityCord-Patches](InfinityCord-Patches)
 * See our API patches [here BungeeCord-Patches](BungeeCord-Patches)
 * See our API patches [here FlameCord-Patches](FlameCord-Patches)
 * Waterfall API JavaDocs here: [papermc.io/javadocs](https://papermc.io/javadocs)
 * Maven repository (for `infinitycord-api`):
```xml
<repository>
    <id></id>
    <url></url>
</repository>
```
 * Artifact information:
```xml
<dependency>
    <groupId>com.github.happyrogelio7.infinitycord</groupId>
    <artifactId>infinitycord-api</artifactId>
    <version>1.18-R0.1-SNAPSHOT-001</version>
    <scope>provided</scope>
</dependency>
 ```

## How To (Compiling From Source)

To compile InfinityCord, you need JDK8, git, bash, maven, and an internet connection.

Clone this repo, run `./infinitycord b` from *bash*, get jar from InfinityCord-Proxy/bootstrap/target/

## Join us

* Feel free to open a PR! We accept contributions.
* Join us on IRC (irc.esper.net #waterfall, [Discord](https://discord.gg/3EebYUyeUX).
* Visit our forums (https://papermc.io/forums).

## Special Thanks To

![YourKit-Logo](https://yourkit.com/images/yklogo.png)

[YourKit](https://yourkit.com/), makers of the outstanding Java profiler, supports open source projects of all kinds with their full-featured [Java](https://yourkit.com/features/) and [.NET](https://yourkit.com/dotnet/features/) application profilers. We thank them for granting Waterfall an OSS license so that we can make our software the best it can be.
