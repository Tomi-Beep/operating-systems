# Problem 1
Implement a chat client that is able to connect to a central server. The server uses the TCP protocol and listens for inbound connections on port 9753 on the following address: 194.149.135.49


Your first login message to the server should be: "login:123456" if 123456 is your index number. On a successful login, you will receive a confirmation from the server trough the established socket. If your login attempt is unsuccessful, you should terminate the connection and try again from start.


After receiving a confirmation of a successful login, the next message to the server should be: "hello:123456" if 123456 is your index number. After receiving a confirmation on this you can communicate with your colleagues that are active at the same time on the server. The messages sent (for example, if your colleague has an id of 111111) should be in the format "111111: some message" . The message will be delivered only if the recipient is active on the server at that time.


Hint: use separate threads for inbound and outbound communication with the server.

Note: do not forget to terminate each message with a new line character "\n" !

# Problem 2
Implement a chat client that is able to connect to a central server. The server uses the TCP protocol and listens for inbound connections on port 9753 on the following address: 194.149.135.49

Your first login message to the server should be: "hello:123456" if 123456 is your index number. On a successful login, you will receive a confirmation from the server trough the established socket. If your login attempt is unsuccessful, you should terminate the connection and try again from start.


After receiving a confirmation of a successful login, the next message to the server should be: "hello:123456" if 123456 is your index number. After receiving a confirmation on this you can communicate with your colleagues that are active at the same time on the server. The messages sent (for example, if your colleague has an id of 111111) should be in the format "111111: some message" . The message will be delivered only if the recipient is active on the server at that time.

You should additionally write the entire communication through the connection to a log file, named chatlog123456.txt, if 123456 is your index number. Then you should attach this file together with your java code.


Hint: use separate threads for inbound and outbound communication with the server.

Note: do not forget to terminate each message with a new line character "\n" !
