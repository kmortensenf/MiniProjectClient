There are two ways of making this project work. method 1 works the best and is easier. both use java but in method 1, jar files are used so eclipse is not necessary.

method 1

	1. first navigate into "MiniProjectServer\Executables Server" 

	2. in there double click on the Batch file "serverRunner.bat"

	3. afterwards navigate into "MiniProjectClient\Executables Client"

	4. now execute (like in step 2) the Batch file "singlePlayerRunner.bat"

	5. you will then be asked to type in an ip-adress. If the server has been started on the same Computer simply type in either "127.0.0.1", "localhoast" or "l"
		without the quotations ("") otherwise type in the computers ip.

	6. after that you will be asked to type in a name, simply type in any name you wish to have

	7. now minimize the Commandpromts (the line, NOT the x) till see the green lobby window (it is usually behind the chat systems it opens up first)

	8. simply press the start button

	9. to begin the game press the button "Deal cards" 

	10. from there press hit or stand depending on your cards (your aim is to have a total of 21 so read the "white" window in the middle of the screen)

	11. from there just follow the steps given in the window. if you wish to close the lobby simply press the button in the bottom right corner

method 2

	1. first import the library's (server and client) into eclipse "e.g. by using; open file from system path" 

	2. then navigate (from eclipse) to "MiniProjectServer\Final Server\singlePlayerBlackjackServer\src\singlePlayerBlackjackServer" and start the scripts "ChatServer" and "singlePlayerServer"

	3. after that, navigate (from eclipse) to "MiniProjectClient\Final Client\singlePlayerBlackjackClient\src\singlePlayerBlackjackClient" and start the scripts "Chat" and "singlePlayerClient"

	4. from there follow the same steps in "method 1" from step 5 - 11




if (in method 2) you get an error concerning a main method or the like. try to import the library's differently:

	- for the server make sure you import from the folder at the end of the path "MiniProjectServer\Final Server\singlePlayerBlackjackServer\src" (make sure it's the first src in singlePlayerBlackjackServer)

	- for the client make sure you import from the folder at the end of the path "MiniProjectClient\Final Client\singlePlayerBlackjackClient\src" (make sure it's the first src in singlePlayerBlackjackClient)

	- from there follow the same steps in "method 2" form step 2 - 4