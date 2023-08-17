insert into users (password, email, verified) VALUES ('$2a$12$h/HXDzJgpT4ff5EtiPN/UejwWLe/WfyFx/lsHmBe/lnuAYyqxtYEC', 'dsisarica40@gmail.com', true);
insert into role (name) VALUES ('ROLE_ADMIN');
insert into role (name) VALUES ('ROLE_USER');
insert into user_role (user_id, role_id) VALUES (1,1);
insert into video_game (title, description, price, release_date, deleted) VALUES ('Igrica', 'Jako dobra uf', 1399.99, '10/08/2023', false);
insert into video_game (title, description, price, release_date, deleted) VALUES ('Igrica2', 'Jako dobra uf', 1399.99, '10/08/2023', false);
insert into video_game (title, description, price, release_date, deleted) VALUES ('Igrica3', 'Jako dobra uf', 1399.99, '10/08/2023', false);
insert into video_game (title, description, price, release_date, deleted) VALUES ('Igrica4', 'Jako dobra uf', 1399.99, '10/08/2023', true);

insert into video_game_genres (video_game_id, genres) VALUES (1, 'ADVENTURE');
insert into video_game_genres (video_game_id, genres) VALUES (1, 'STRATEGY');
insert into video_game_genres (video_game_id, genres) VALUES (2, 'STRATEGY');
insert into video_game_genres (video_game_id, genres) VALUES (3, 'HORROR');
insert into video_game_genres (video_game_id, genres) VALUES (4, 'SPORT');

insert into video_game_platforms (video_game_id, platforms) VALUES (1, 'PC');
insert into video_game_platforms (video_game_id, platforms) VALUES (1, 'PS5');
insert into video_game_platforms (video_game_id, platforms) VALUES (1, 'Nintento3DS');
insert into video_game_platforms (video_game_id, platforms) VALUES (2, 'PC');
insert into video_game_platforms (video_game_id, platforms) VALUES (2, 'PS4');
insert into video_game_platforms (video_game_id, platforms) VALUES (3, 'XboxOne');
insert into video_game_platforms (video_game_id, platforms) VALUES (4, 'PC');
insert into video_game_platforms (video_game_id, platforms) VALUES (4, 'VR');