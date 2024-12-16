# CSCI 205 - Software Engineering and Design
Bucknell University<br>
Lewisburg, PA

### Course Info
Instructor: Brian King<br>
Semester: Spring 22

## Team Information

### Warren Wang<br>
I am a Computer Science and Engineering Major of the class of 2024. I have never made a Pokemon game before, and I am looking towards to all the things that I will learn along this journey.

### Nate Ahearn<br>
CS-E major, Education minor. I am graduating in 2024 and am very excited to work on a project revolving around pokemon.

### RuiTong Jiang<br>
2024 EE major, And I am very excited to implement a game of an animation we watched into real life.

### Quan Zhou
2024 CSE major, and I am interested in completing a game project related to a world famous IP.



## Project Information
This is a Pokemon Battle Factory Simulator.

The Pokemon Battle Factory was a very popular game that originated from the Pokemon game, Pokemon Emerald which came out in 2004. 
After completing the main story of t he game, players can travel to an island with a lot of different battle facilities that are widely
considered some of the hardest content in all of Pokemon. The Battle Factory specifically, incorporates a lot of randomness with skill
which can lead to some extremely fun and entertaining gameplay. The concept of the Battle Factory in the games revolves around the user
facing AI with a randomly generated team of 3 Pokemon. In the battles, players take turns attacking the opposing Pokemon, or switching 
their own to have a favorable matchup. Each turn consists of both players selecting an action, with the three choices being to attack,
switch, or forfeit. The battles end when either the player or the bot do not have any Pokemon left which can battle. In other words, when
all three of either player's Pokemon have less than or equal to zero health remaining. Pokemon have 4 different move choices, each with
their own individual affects. Some moves, like skyuppercut, deal damage as their only effect. Moves like scald will deal damage and have
a secondary effect, which is having a chance to i
The user begins the challenge by seeing an assortment of 6 random Pokemon, and
then selecting 3 of those Pokemon to face the bot with random Pokemon. Prior to each match, a helper in the facility will give the player
a preview of what the opponents team will consist of, with the information shared decreasing over the course of the challenge. Once the
player has accumulated 7 wins their challenge is over and they are given a reward. When the user completes the challenge 3 times in a row
they fight a boss, and if they have a streak of 5 total challenge wins they fight another boss, both of which have random teams with very
competitive abilities. In our version of the game, we opted for a more challenging approach for the player. The player will select a team
of 3 Pokemon from a randomly generated pool of 6 every time they face a new bot. The user also does not have any helper messages before
facing the bot. Additionally, we created an algorithm of similar difficult to the stronger AI to play the bot for us. Instead of having
completely random AI like the first matches have, our AI will check if any of their Pokemon's move can knock out the user and select that
move if it can. The user also can play infinite matches, rather than being limited to 7 at a time. Certain Pokemon also had their base
stats changed to make the game more fun and have wider variation from the base games. 
Another spin we added to make the game our own was to create a few Pokemon to add to the game. For example, the three titan 
characters are from a show that all of our group enjoyed called "Attack on Titan" with these characters being staples in that show.

## List of All 3rd Party Libraries
    implementation 'org.junit.jupiter:junit-jupiter:5.8.1'
    testImplementation(platform('org.junit:junit-bom:5.8.2'))
    testImplementation 'org.junit.jupiter:junit-jupiter:5.8.2'

## Video Demonstration
A video demonstration of our project can be found [here](https://mediaspace.bucknell.edu/media/Team+6+-+CSCI205+Final+Project+PresentationA+Pokemon+Battle+Factory/1_30hxy74s).

## How to run it

Install `Java 17.0.10` and `Gradle 7.2`.

Run via:
```
gradle build
gradle run
```

Alternatively a portable version for Windows should be released [here](https://github.com/warrenmwang/csci205finalproject/releases/tag/v1.0.0)
where you can easily find an .exe in the folder to double click and run.
