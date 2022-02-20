# MinecraftStatusUpdater

This Mod sends an update containing the current state to a server.

## About

The Mod is intended to control a discord bot (e.g. [MinecraftServerStatusBot](https://github.com/pr0LebenImHolz/MinecraftServerStatusBot)).

Because modding for 1.7.10 is almost impossible, the Mod is held very small without unnecessary features.

## Supported Minecraft Versions

- [Current] 1.12.2
- [**Development Discontinued!**] 1.7.10

## API

The Mod accesses following endpoints:

### Version

[GET] `https://example.com:443/foo/bar?token=<TOKEN>&target=version`

The Mod expects an HTTP 200 response, the response body must be the API version of the mod without linebreaks:

`1.0.0`

### Update

[POST] `https://example.com:443/foo/bar?token=<TOKEN>&target=update&status=<STARTING>`

The Mod expects an HTTP 204 response.

#### States

- STARTING
- RUNNING
- STOPPING
- OFFLINE

#### Triggers

An Update is triggered when...

- When a player logs in
- When a player logs out
- When the server starts
- When the server started
- When the server stops
- When the server stopped

## Error Codes

### `0x??` - Success

| Code   | Message                                  | Description                                                  |
|--------|------------------------------------------|--------------------------------------------------------------|
| `0x14` | Connected To API: Performed Request      | _Unused_                                                     |
| `0x15` | Connected To API: Compatible API Version | Logged on successful version check during API initialization |
| `0x21` | Updated Status                           | Logged on successful status update                           |
| `0x30` | Registered PlayerEvents                  | Logged on successful PlayerEvent listener registration       |

### `1x??` - Error

| Code   | Message                                                                                                         | Description                                                                                                                    | Possible Solution                                                                                                                                                                                           |
|--------|-----------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `1x00` | Unknown Exception                                                                                               | _Unused_                                                                                                                       | N/A                                                                                                                                                                                                         |
| `1x01` | Not Implemented                                                                                                 | _Unused (Development only)_                                                                                                    | N/A                                                                                                                                                                                                         |
| `1x10` | Can't Connect To API: Missing Credentials                                                                       | Thrown when the config file is empty                                                                                           | Put the credentials (`token@url`) in the config file (`config/ServerStatusUpdater.txt`)<br />Example: `1234@https://example.com:443/foo/bar/`                                                               |
| `1x11` | Can't Connect To API: Invalid Credentials                                                                       | Thrown when the configured credentials are invalid                                                                             | Check the syntax of the credentials                                                                                                                                                                         |
| `1x12` | Can't Connect To API: Invalid URL                                                                               | Thrown when the configured credentials (specially the URL) is invalid                                                          | Check the syntax of the credentials, specially the URL (the part after the `@`)                                                                                                                             |
| `1x13` | Can't Connect To API: Incompatible API Version                                                                  | Thrown when the DiscordBot API does not support the Mod API                                                                    | Check the API versions in the releases of the [bot](https://github.com/pr0LebenImHolz/MinecraftServerStatusBot/releases) and [mod](https://github.com/pr0LebenImHolz/MinecraftServerStatusUpdater/releases) |
| `1x14` | Can't Connect To API: Connection Failure                                                                        | _(Internal)_ Thrown when an `IOException` occurs during an API request or the server returned an unexpected HTTP response code | Try to perform the HTTP request manually - for details read the [bot](https://github.com/pr0LebenImHolz/MinecraftServerStatusBot) readme                                                                    |
| `1x15` | Can't Connect To API: SSLHandshakeException: Self-Signed Certificates Are Not Supported ;)                      | Thrown when an `SSLHandshakeException` occurs during an API request                                                            | Check your TLS certificate, performing a manual HTTP request or read the error message of a web browser may help                                                                                            |
| `1x20` | Can't Update Status: %e                                                                                         | Thrown when an unexpected error occurs while performing an update request                                                      | Check for previously thrown errors                                                                                                                                                                          |
| `1x21` | Can't Update Status: API Not Initialized. Check Logs, Configs And Restart The Server                            | Thrown when an API request is performed but the API is not initialized                                                         | Check for previously thrown errors (specially `1x10` - `1x14`) to find out the reason why the API is not initialized                                                                                        |
| `1x30` | Didn't Register PlayerEvents To Save Resources: API Not Initialized. Check Logs, Configs And Restart The Server | Thrown when the API is not initialized                                                                                         | Check for previously thrown errors (specially `1x10` - `1x14`) to find out the reason why the API is not initialized                                                                                        |

## JavaDoc

Location: `/docs/javadoc/`<br>
Site: https://pr0lebenimholz.github.io/MinecraftServerStatusUpdater/javadoc/