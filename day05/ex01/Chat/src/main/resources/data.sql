INSERT INTO chat.users (login, password)
VALUES ('user1', 'password1');
INSERT INTO chat.users (login, password)
VALUES ('user2', 'password2');
INSERT INTO chat.users (login, password)
VALUES ('user3', 'password3');
INSERT INTO chat.users (login, password)
VALUES ('user4', 'password4');
INSERT INTO chat.users (login, password)
VALUES ('user5', 'password5');

INSERT INTO chat.chatroom (name, owner)
VALUES ('chatroom1', 1);
INSERT INTO chat.chatroom (name, owner)
VALUES ('chatroom2', 2);
INSERT INTO chat.chatroom (name, owner)
VALUES ('chatroom3', 3);
INSERT INTO chat.chatroom (name, owner)
VALUES ('chatroom4', 4);
INSERT INTO chat.chatroom (name, owner)
VALUES ('chatroom5', 5);

INSERT INTO chat.messages (author, room, text, localdatetime)
VALUES (1, 1, 'message1', '2022-04-11 00:00:01');
INSERT INTO chat.messages (author, room, text, localdatetime)
VALUES (2, 3, 'message2', '2022-04-11 00:00:01');
INSERT INTO chat.messages (author, room, text, localdatetime)
VALUES (5, 3, 'message3', '2022-04-11 00:00:01');
INSERT INTO chat.messages (author, room, text, localdatetime)
VALUES (4, 2, 'message4', '2022-04-11 00:00:01');
INSERT INTO chat.messages (author, room, text, localdatetime)
VALUES (5, 1, 'message5', '2022-04-11 00:00:01');