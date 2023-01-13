# Discord Clip Detection Bot

## Running the bot
To use this bot:
1. Create an application on the Discord Developer Portal
2. Under the "Bot" settings, select URL Generator
3. Select bot and application.commands scopes
4. Select "send messages" and "use slash commands" under "Bot Permissions" and copy/paste the link to add the bot to your server
5. Get your bot token from the Bot page under Settings in the dev portal
6. Pass in your token as the first parameter when running the bot class

## Slash Commands
| Command        | Function                                             | Required              |
|----------------|------------------------------------------------------|-----------------------|
| /search-by-pic | Upload a screenshot of an anime to get it's details  | The image file        |
| /search-by-url | Provide a link to an image to get it's anime details | The link to the image |

## Result
The bot will return an embed message with information of the closest matching anime.
* Title (clicking it takes you to a short clip of the scene)
* Name
* Romaji Name
* Episode
* Similarity (Results with similarities under 90% are most likely inaccurate)
* Time in episode (An estimation of when the scene takes place in the episode - can be off by a few seconds)
* Image provided

This project was created using [JDA](https://github.com/DV8FromTheWorld/JDA) and [Trace.Moe](https://soruly.github.io/trace.moe-api/#/)

[Gson](https://github.com/google/gson) was used for json to object conversion
