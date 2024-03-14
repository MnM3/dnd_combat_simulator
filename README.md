# About the Simulator

_First, let me explain what this project is about: learning and developing something useful._

Crafting combat encounters can be very tricky when it comes to dungeon mastering a campaign. Balancing is a timeconsuming task and the desire to minimize any fudging of rolls also exists. Now, we could discuss the different philosophies that come with DMing, but let's skip that.

So, the idea is to create a program that can simulate fights in a fairly accurate manner and provide insight into:

- the outcome,
- the decisions made,
- and the cost of resources for the party.

Additionally, I want to provide functionality to engage in fights manually by leveraging the game-loop that is running on the AWT-based engine I am concurrently developing.

## Current Features

Not very much really. Right now the project is in between states. I started out with simualting fights based on random selecting enemies.

Currently I am implementing a Pathfinding algorithm _(A*)_ and a battlfield the individuals can be placed and moved on.

Connecting everything to the engine is probably taking some time and not planned out yet. 

### What you can do:
normally you can enter at the Main class or the game class. As of right now those those things are completely seperate.

## Todos
- [ ]Character creation
- [ ]database/data persistence
- [ ] rudimentary fight sequence in engine
- [ ] graphics
- [ ] AI
- [ ]combat features
  - [ ] cover,
  - [ ] climbing,
  - [ ] so much more...
- [ ] Magic
- [ ] Pathfinding
- [ ] more stuff for the todo list

