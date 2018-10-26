@ECHO OFF
start "Lobby" java -jar singlePlayerClientRunner.jar
start "Chatroom" java -jar Chat.jar
PAUSE