insert into address (address, city, country) VALUES ('Zeleznicka 3', 'Novi Sad', 'Serbia');
insert into users (password, email, verified, address_id, name, surname) VALUES ('$2a$12$h/HXDzJgpT4ff5EtiPN/UejwWLe/WfyFx/lsHmBe/lnuAYyqxtYEC', 'dsisarica40@gmail.com', true, 1, 'Dusan', 'Sisarica');
insert into role (name) VALUES ('ROLE_ADMIN');
insert into role (name) VALUES ('ROLE_USER');
insert into user_role (user_id, role_id) VALUES (1,1);
insert into video_game (title, description, price, release_date, deleted, image) VALUES ('Starcraft 2 Battlechest (WoL/HotS/LotV)', '', 1399.99, '10/08/2023', false, 'starcraft2.png');
insert into video_game (title, description, price, release_date, deleted, image) VALUES ('Kingdom Come Deliverance', 'Jako dobra uf', 6499, '10/08/2023', false, 'kingdomcomedeliverance.png');
insert into video_game (title, description, price, release_date, deleted, image) VALUES ('Sonic Mania Plus', 'Jako dobra uf', 3999, '10/08/2023', false, 'sonicmaniaplus.png');
insert into video_game (title, description, price, release_date, deleted, image) VALUES ('Sonic Origins Plus Limited Edition', 'Jako dobra uf', 1399.99, '10/08/2023', true, 'sonicoriginplus.jpg');

insert into video_game_genres (video_game_id, genres) VALUES (1, 'STRATEGY');
insert into video_game_genres (video_game_id, genres) VALUES (2, 'ACTION');
insert into video_game_genres (video_game_id, genres) VALUES (2, 'ADVENTURE');
insert into video_game_genres (video_game_id, genres) VALUES (3, 'PLATFORM');
insert into video_game_genres (video_game_id, genres) VALUES (4, 'PLATFORM');

insert into video_game_platforms (video_game_id, platforms) VALUES (1, 'PC');
insert into video_game_platforms (video_game_id, platforms) VALUES (2, 'PC');
insert into video_game_platforms (video_game_id, platforms) VALUES (3, 'XboxOne');
insert into video_game_platforms (video_game_id, platforms) VALUES (4, 'PS5');
