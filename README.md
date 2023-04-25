# The Guides Grand Adventure

This is a Repository for Roadrunner Interactive's (Team 11) Application Programming semester project.

<p align="center">
 <img src="./app/src/main/res/drawable/main_menu_logo.png" width="500" length="600" />
 </p>

<p align="center">
 <img src="./app/src/main/res/drawable/team_logo.png" width="300" length="75" />
 <img src="./app/src/main/res/drawable/utsacs.png" width="300" length="75" />
 </p>

**The Guides Grand Adventure** is a simple android java game inspired by the old 8-bit platform style of games. The project was mainly done in *Android Studio*, with some outside programs such as *Adobe Illustrator* and *Photoshop* used for asset creation and development. The files can easily be imported as an android studio project from this repository, instructions will be listed below. Various assets in the game were sourced from the public, the credits are listed in the credits section.

## Program Examples

<p align="center">
 <b>Regular Game Views:</b>
 </p>
 
<p align="center">
 <img src="https://media.giphy.com/media/F3wPk4Bf4SLpCHkT40/giphy.gif" width="150" height="300">
 <img  src="https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExNDk5NWQ1MTVjMmI0MjYyMTVkMjc5NTk1NzJlM2QwZGQ3ZjQ1ZTViMiZlcD12MV9pbnRlcm5hbF9naWZzX2dpZklkJmN0PWc/KyZa2SMrEAN4LW6C6c/giphy.gif" width="150" height="300">
  <img src="https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExNjQyNWMzYjQ4ZTYzMGViNDAyMTlhOTFkYWM3ZDFhYTA0N2U2YTg3YyZlcD12MV9pbnRlcm5hbF9naWZzX2dpZklkJmN0PWc/AxgLIm8odemBFVSXoe/giphy.gif" width="150" height="300">
  </p>
  
  <p align="center">
<b>Gameplay / Game Engine Example:</b>
 </p>
  
<p align="center"> <img src="https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExOGVlMjRjN2Q4YTNhNGU5ZDRhMTIxMmMxMTY4MzM2ODA4ODdkMjdmOSZlcD12MV9pbnRlcm5hbF9naWZzX2dpZklkJmN0PWc/Hmmi8Pu62toz3ZAZoQ/giphy.gif" width="450" height="200" </p>

## Motivation
 
At the start of the semester we (RR Int.) had a debate on what would motivate us the most to create, and the consensus was a video game! Making games are fun, challenging, and rewarding to create, as well as play, so the decision was a win/win on both fronts. Throughout creating many aspects of the project we learned  valuable lessions about teamwork and java/android programming that will serve us well in future classes and in the carreers we pursue. The game also gave us a chance to demonstrate the creative side of our team, as many of the image files were created by team members, barring some site logos that were included for crediting. In short, this project exists as a demonstration of the lessons we (RR Int.) learned and how we can implement and apply them to a stand-alone android app program.

## Notable Technical Concepts

 Related to CS3443, some important concepts implemented into this game include:
 
** CONTROLLERS **

- MainController:        Controls activity_main.xml view and shows the main menu page.
- CreditsController:     Controls activity_credits.xml view and shows a list of credits and awknowledgements for the game.
- GameController:        Controls activity_game.xml view and implements the game engine, along with the in game menu.
- GameOverController:    Controls activity_gameover.xml view and manages file I/O for the high score leaderboard.
- SettingsController:    Controls activity_settings.xml view and manages UI's across the program.

** MODEL **

-TODO

** ACTIVITY **

-TODO
 
## Installation

You can get the project simply by downloading the code from this repository. From there you can import the project to your android studio. Make sure that in the local.properties file in the repositorie's root directory, that the filepath directs to your local Android SDK. Then once your gradle build finishes you can run the project without any other configurations. (Though setting your android emulator device to a Pixel 6 API 32 is reccomended to get intended performance.)

### Prerequisites

- Have Android Studio
- Create a Virtual Device (Reccomended to get Pixel 6 with API 32)
- In the file dropdown, import the project (after downloading it from this repo).
- Done!

## Contributors

<a href="https://github.com/WillClfrd/GuidesGrandAdventure/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=WillClfrd/GuidesGrandAdventure" />
</a>

## Acknowledgements

These apply to assets in the game that were sourced from outside creators, huge thanks to them for providing open source assets!

[***Pixabay***](https://pixabay.com/) - Royalty Free Music and Sounds.

[***Freesound***](https://freesound.org/) - Royalty Free Sounds.

[***DaFont***](https://www.dafont.com/) - Fonts and Typefaces for free personal and commercial use. *(Highly reccomend for fellow UI nerds!)*

[***Spriters Resource***](https://www.spriters-resource.com/ds_dsi/pokemonblack2white2/) - Pokemon Black/White 2 Spritesheets.

(Used mainly for inspiration with some of the more basic characters directly used.)

## License

MIT © RR Interactive
