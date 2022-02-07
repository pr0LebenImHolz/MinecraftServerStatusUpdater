# MinecraftStatusUpdater

This Mod sends an update containing the current state to a server.

## About

The Mod is intended to control a discord bot (e.g. [MinecraftServerStatusBot](https://github.com/pr0LebenImHolz/MinecraftServerStatusBot)).<br />
Because modding for 1.7.10 is almost impossible, the Mod is held very small without unnecessary features.

## Supported Minecraft Versions

- [Current] 1.12.2
- [**Development Discontinued!**] 1.7.10

## States

- STARTING
- RUNNING
- STOPPING
- OFFLINE

## Triggers

- When a player logs in
- When a player logs out
- When the server starts
- When the server started
- When the server stops
- When the server stopped

## API

The Mod accesses following endpoints:

### Version

[GET] `https://example.com:443/foo/bar?token=B&target=version`

The Mod expects an HTTP 200 response, the response body must be the API version of the mod without linebreaks:

`1.0.0`

### Update

[POST] `https://example.com:443/foo/bar?token=B&target=update&status=STARTING`

The Mod expects an HTTP 204 response.
  
