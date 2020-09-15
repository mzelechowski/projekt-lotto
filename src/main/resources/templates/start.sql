# roles
insert into role (role_id, role) values (1, 'ADMIN');
insert into role (role_id, role) values (2, 'USER');

#  users
insert into user (id, active, email, name, last_name, login, password)
values (1,1,'lucjan@gmail.com', 'Lucjan', 'Pawarotti', 'admin', '$2a$10$/r39t7RH7tykA33JbGt84eYeeMD.A3HbqT.Wh.H9W9h1LsdNiaZHG');

insert into user (id, active, email, name, last_name, login, password)
values (2,1,'adas@gmail.com', 'Adaś', 'Nowakowski', 'user', '$2a$10$3Mis/djdswmq63olMVNEjec2E.f5MGazqSQXkeZF.mT4j1lZFJAZu');

insert into user (id, active, email, name, last_name, login, password)
values (3,1,'aaa@gmail.com', 'Aaa', 'Aaaa', 'aaa', '$2a$10$SiEpJbYCiHW/MXhwBAdA.use1IXDqdkQVaazzenICTxbMvidyf5w2');

insert into user (id, active, email, name, last_name, login, password)
values (4,1,'bbb@gmail.com', 'Bbb', 'Bbbb', 'bbb', '$2a$10$hPwrC6o9PM8XSIACBQc0hutcYQj5Nx8JCEN7ZfMnOqJe7ob9mbfwG');

# add roles to users
insert into user_role (user_id, role_id) values (1,2);
insert into user_role (user_id, role_id) values (1,1);
insert into user_role (user_id, role_id) values (2,2);
insert into user_role (user_id, role_id) values (3,2);
insert into user_role (user_id, role_id) values (4,2);

# bets
insert into bets (id, game, user_id) values (1,'Lotto', 3);
insert into bet_app_bet (bet_app_id, bet) values (1, 1);
insert into bet_app_bet (bet_app_id, bet) values (1, 7);
insert into bet_app_bet (bet_app_id, bet) values (1, 17);
insert into bet_app_bet (bet_app_id, bet) values (1, 8);
insert into bet_app_bet (bet_app_id, bet) values (1, 10);
insert into bet_app_bet (bet_app_id, bet) values (1, 12);
insert into bets (id, game, user_id) values (2,'Lotto Plus', 3);
insert into bet_app_bet (bet_app_id, bet) values (2, 12);
insert into bet_app_bet (bet_app_id, bet) values (2, 14);
insert into bet_app_bet (bet_app_id, bet) values (2, 16);
insert into bet_app_bet (bet_app_id, bet) values (2, 18);
insert into bet_app_bet (bet_app_id, bet) values (2, 20);
insert into bet_app_bet (bet_app_id, bet) values (2, 22);
insert into bets (id, game, user_id) values (3,'Mini Lotto', 3);
insert into bet_app_bet (bet_app_id, bet) values (3, 32);
insert into bet_app_bet (bet_app_id, bet) values (3, 34);
insert into bet_app_bet (bet_app_id, bet) values (3, 16);
insert into bet_app_bet (bet_app_id, bet) values (3, 8);
insert into bet_app_bet (bet_app_id, bet) values (3, 30);
insert into bet_app_bet (bet_app_id, bet) values (3, 2);
