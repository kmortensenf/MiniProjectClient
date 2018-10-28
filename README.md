# MiniProjectClient

This project is a client/server blackjack game combined with a chatroom feature.

## Server
[Server Github](https://github.com/kmortensenf/MiniProjectServer)

## How to run the project?

To run this project download the latest release from both Client and Server repositories and either;

Java files:

1. Locate to the 'Final Client' and 'Final Server' folders found in the Client and Server repositories
2. Inside these folders, the .java files are located for both the server/client and the blackjack/chatroom
3. These .java files can then be executed. (Important note: Make sure to run the server files first, else the client cannot connect)

Batch and jar files:

1. Locate to the 'Executables Client' and 'Executables Server' folders found in the Client and Server repositories
2. Inside these folders, are .jar files and .bat files.
3. Start by running the serverRunner.bat file from 'Executables Server'
4. Next run the singlePlayerRunner.bat file from 'Executables Client'
5. You are now running both the server and client files and are free to both play and chat!


## UML Diagrams

Class diagram, Use Case diagram and Sequence diagram can be found in 'UML Diagrams' folder in Client github

## Communication Protocol 

The communication protocol used is TCP/IP

Here the server listens for a socket connection to accept while the client requests connection to the server

Once a connection has been established it can be used to transfer data in both directions.

In the blackjack game, once the connection has been established, the client sends requests to the server based on which buttons the user has clicked, either Deal Cards, Hit, Stand, Reset etc.

Dependent on what data is transfered to the server, it will know what send back to the client. And the client will in turn know what to do with the data received from the server.

## Contributors

Contributors to this project are:

- [Delan88](https://github.com/Delan88)
- [emilbalefrank](https://github.com/emilbalefrank)
- [danielchrone](https://github.com/danielchrone)
- [jtbenne](https://github.com/jtbenne)
- [kmortensenf](https://github.com/kmortensenf)
