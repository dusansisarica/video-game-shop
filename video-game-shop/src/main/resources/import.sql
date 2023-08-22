insert into address (address, city, country) VALUES ('Zeleznicka 3', 'Novi Sad', 'Serbia');
insert into address (address, city, country) VALUES ('Zeleznicka 3', 'Novi Sad', 'Serbia');
insert into address (address, city, country) VALUES ('Zeleznicka 7', 'Novi Sad', 'Serbia');
insert into address (address, city, country) VALUES ('Balzakova 1', 'Novi Sad', 'Serbia');
insert into address (address, city, country) VALUES ('Mise Dimitrijevica 3', 'Novi Sad', 'Serbia');
insert into users (password, email, verified, address_id, name, surname) VALUES ('$2a$12$h/HXDzJgpT4ff5EtiPN/UejwWLe/WfyFx/lsHmBe/lnuAYyqxtYEC', 'dsisarica40@gmail.com', true, 1, 'DusanAdmin', 'Sisarica');
insert into users (password, email, verified, address_id, name, surname) VALUES ('$2a$12$h/HXDzJgpT4ff5EtiPN/UejwWLe/WfyFx/lsHmBe/lnuAYyqxtYEC', 'dusansisarica@gmail.com', true, 2, 'DusanUser', 'Sisarica');
insert into role (name) VALUES ('ROLE_ADMIN');
insert into role (name) VALUES ('ROLE_USER');
insert into user_role (user_id, role_id) VALUES (1,1);
insert into user_role (user_id, role_id) VALUES (2,2);
insert into video_game (title, description, price, release_date, deleted, image) VALUES ('Starcraft 2 Battlechest (WoL/HotS/LotV)', '', 1399.99, '10/08/2023', false, 'starcraft2.png');
insert into video_game (title, description, price, release_date, deleted, image) VALUES ('Kingdom Come Deliverance', 'Jako dobra uf', 6499, '10/08/2023', false, 'kingdomcomedeliverance.png');
insert into video_game (title, description, price, release_date, deleted, image) VALUES ('Sonic Mania Plus', 'Jako dobra uf', 3999, '10/08/2023', false, 'sonicmaniaplus.png');
insert into video_game (title, description, price, release_date, deleted, image) VALUES ('Sonic Origins Plus Limited Edition', 'Jako dobra uf', 1399.99, '10/08/2023', false, 'sonicoriginplus.jpg');

insert into video_game_genres (video_game_id, genres) VALUES (1, 'STRATEGY');
insert into video_game_genres (video_game_id, genres) VALUES (2, 'ACTION');
insert into video_game_genres (video_game_id, genres) VALUES (2, 'ADVENTURE');
insert into video_game_genres (video_game_id, genres) VALUES (3, 'PLATFORM');
insert into video_game_genres (video_game_id, genres) VALUES (4, 'PLATFORM');

insert into video_game_platforms (video_game_id, platforms) VALUES (1, 'PC');
insert into video_game_platforms (video_game_id, platforms) VALUES (2, 'PC');
insert into video_game_platforms (video_game_id, platforms) VALUES (3, 'XboxOne');
insert into video_game_platforms (video_game_id, platforms) VALUES (4, 'PS5');

insert into shop (name, address_id) VALUES ('Video Game Shop 1', 3);
insert into shop (name, address_id) VALUES ('Video Game Shop 2', 4);
insert into shop (name, address_id) VALUES ('Video Game Shop 3', 5);

insert into game_quantity (game_id, shop_id, quantity) VALUES (1, 1, 10);
insert into game_quantity (game_id, shop_id, quantity) VALUES (2, 1, 8);
insert into game_quantity (game_id, shop_id, quantity) VALUES (3, 1, 10);
insert into game_quantity (game_id, shop_id, quantity) VALUES (1, 2, 10);
insert into game_quantity (game_id, shop_id, quantity) VALUES (2, 2, 10);
insert into game_quantity (game_id, shop_id, quantity) VALUES (1, 3, 10);
insert into game_quantity (game_id, shop_id, quantity) VALUES (2, 3, 10);
insert into game_quantity (game_id, shop_id, quantity) VALUES (4, 3, 10);
