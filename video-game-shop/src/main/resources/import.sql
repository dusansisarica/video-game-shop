insert into country (name) VALUES ('Srbija');
insert into city (city_name, country_id) VALUES ('Novi Sad', 1);
insert into city (city_name, country_id) VALUES ('Beograd', 1);
--
insert into address (address, city_id, longitude, latitude) VALUES ('Zeleznicka 3', 1, 45.251624, 19.842497);
insert into address (address, city_id,  longitude, latitude) VALUES ('Zeleznicka 3', 1, 45.251624, 19.842497);
insert into address (address, city_id,  longitude, latitude) VALUES ('Zeleznicka 7', 1, 45.251009, 19.841845);
insert into address (address, city_id,  longitude, latitude) VALUES ('Balzakova 1', 2, 45.240962338967634, 19.832409477210312);
insert into address (address, city_id,  longitude, latitude) VALUES ('Mise Dimitrijevica 3', 2,45.24635146335202, 19.833262412655518);
insert into address (address, city_id,  longitude, latitude) VALUES ('Mise Dimitrijevica 3', 1,45.24635146335202, 19.833262412655518);
insert into address (address, city_id,  longitude, latitude) VALUES ('Mise Dimitrijevica 3', 2,45.24635146335202, 19.833262412655518);
insert into address (address, city_id,  longitude, latitude) VALUES ('Mise Dimitrijevica 3', 1,45.24635146335202, 19.833262412655518);
insert into address (address, city_id,  longitude, latitude) VALUES ('Mise Dimitrijevica 3', 2,45.24635146335202, 19.833262412655518);
insert into address (address, city_id,  longitude, latitude) VALUES ('Mise Dimitrijevica 3', 1,45.24635146335202, 19.833262412655518);
insert into address (address, city_id,  longitude, latitude) VALUES ('Mise Dimitrijevica 3', 1,45.24635146335202, 19.833262412655518);
insert into address (address, city_id,  longitude, latitude) VALUES ('Mise Dimitrijevica 3', 1,45.24635146335202, 19.833262412655518);
insert into address (address, city_id,  longitude, latitude) VALUES ('Mise Dimitrijevica 3', 1,45.24635146335202, 19.833262412655518);
insert into address (address, city_id,  longitude, latitude) VALUES ('Mise Dimitrijevica 3', 1,45.24635146335202, 19.833262412655518);
insert into address (address, city_id,  longitude, latitude) VALUES ('Mise Dimitrijevica 3', 1,45.24635146335202, 19.833262412655518);
insert into address (address, city_id,  longitude, latitude) VALUES ('Mise Dimitrijevica 3', 2,45.24635146335202, 19.833262412655518);
insert into address (address, city_id,  longitude, latitude) VALUES ('Mise Dimitrijevica 3', 1,45.24635146335202, 19.833262412655518);
insert into address (address, city_id,  longitude, latitude) VALUES ('Mise Dimitrijevica 3', 1,45.24635146335202, 19.833262412655518);

insert into shop (name, address_id, deleted) VALUES ('Video Game Shop 1', 3, false);
insert into shop (name, address_id, deleted) VALUES ('Video Game Shop 2', 4, false);
insert into shop (name, address_id, deleted) VALUES ('Video Game Shop 3', 5, true);


insert into users (password, email, verified, address_id, name, surname, deleted) VALUES ('$2a$12$h/HXDzJgpT4ff5EtiPN/UejwWLe/WfyFx/lsHmBe/lnuAYyqxtYEC', 'dusanadmin@gmail.com', true, 1, 'DusanAdmin', 'Sisarica', false);
insert into users (password, email, verified, address_id, name, surname, deleted) VALUES ('$2a$12$h/HXDzJgpT4ff5EtiPN/UejwWLe/WfyFx/lsHmBe/lnuAYyqxtYEC', 'dusansisarica@gmail.com', true, 2, 'DusanUser', 'Sisarica', false);
insert into users (password, email, verified, address_id, name, surname, shop_id, deleted) VALUES ('$2a$12$h/HXDzJgpT4ff5EtiPN/UejwWLe/WfyFx/lsHmBe/lnuAYyqxtYEC', 'dusanradnja1@gmail.com', true, 5, 'Milica', 'Zivancevic', 1, false);
insert into users (password, email, verified, address_id, name, surname, shop_id, deleted) VALUES ('$2a$12$h/HXDzJgpT4ff5EtiPN/UejwWLe/WfyFx/lsHmBe/lnuAYyqxtYEC', 'dusanradnja2@gmail.com', true, 6, 'DusanUser', 'Sisarica', 2, false);
insert into users (password, email, verified, address_id, name, surname, shop_id, deleted) VALUES ('$2a$12$h/HXDzJgpT4ff5EtiPN/UejwWLe/WfyFx/lsHmBe/lnuAYyqxtYEC', 'dusanradnja3@gmail.com', true, 7, 'DusanUser', 'Sisarica', 3, false);
insert into users (password, email, verified, address_id, name, surname, deleted) VALUES ('$2a$12$h/HXDzJgpT4ff5EtiPN/UejwWLe/WfyFx/lsHmBe/lnuAYyqxtYEC', 'dusanisarica@gmail.com', true, 8, 'DusanUser', 'Sisarica', false);
insert into users (password, email, verified, address_id, name, surname, deleted) VALUES ('$2a$12$h/HXDzJgpT4ff5EtiPN/UejwWLe/WfyFx/lsHmBe/lnuAYyqxtYEC', 'dusanssarica@gmail.com', true, 9, 'DusanUser', 'Sisarica', false);
insert into users (password, email, verified, address_id, name, surname, deleted) VALUES ('$2a$12$h/HXDzJgpT4ff5EtiPN/UejwWLe/WfyFx/lsHmBe/lnuAYyqxtYEC', 'dusnsisarica@gmail.com', true, 10, 'DusanUser', 'Sisarica', false);
insert into users (password, email, verified, address_id, name, surname, deleted) VALUES ('$2a$12$h/HXDzJgpT4ff5EtiPN/UejwWLe/WfyFx/lsHmBe/lnuAYyqxtYEC', 'dsansisarica@gmail.com', true, 11, 'DusanUser', 'Sisarica', false);
insert into users (password, email, verified, address_id, name, surname, deleted) VALUES ('$2a$12$h/HXDzJgpT4ff5EtiPN/UejwWLe/WfyFx/lsHmBe/lnuAYyqxtYEC', 'dusansisaica@gmail.com', true, 12, 'DusanUser', 'Sisarica', false);
insert into role (name) VALUES ('ROLE_ADMIN');
insert into role (name) VALUES ('ROLE_USER');
insert into role (name) VALUES ('ROLE_STAFF');
insert into role (name) VALUES ('ROLE_MANAGER');
insert into user_role (user_id, role_id) VALUES (1,1);
insert into user_role (user_id, role_id) VALUES (2,2);
insert into user_role (user_id, role_id) VALUES (3,3);
insert into user_role (user_id, role_id) VALUES (4,3);
insert into user_role (user_id, role_id) VALUES (5,3);
insert into user_role (user_id, role_id) VALUES (6,4);
insert into user_role (user_id, role_id) VALUES (7,2);
insert into user_role (user_id, role_id) VALUES (8,2);
insert into user_role (user_id, role_id) VALUES (9,2);
insert into user_role (user_id, role_id) VALUES (10,2);

insert into discount(name, action_type, discount_value, start_date, end_date) VALUES('50% na odredjenje artikle', 'BUY_ONE_GET_ONE_FREE', 50, '2024-09-09', '2024-12-25');
insert into discount(name, action_type, discount_value, start_date, end_date) VALUES('30% na odredjenje artikle', 'QUANTITY_DISCOUNT', 30, '2023-09-13', '2024-12-25');
insert into discount(name, action_type, discount_value, start_date, end_date) VALUES('20% na odredjenje artikle', 'FIXED_DISCOUNT', 20, '2023-09-13', '2024-12-25');
insert into discount(name, action_type, discount_value, start_date, end_date) VALUES('40% na odredjenje artikle', 'PERCENTAGE_DISCOUNT', 40, '2024-09-10', '2024-12-25');
insert into discount(name, action_type, discount_value, start_date, end_date) VALUES('40% na odredjenje artikle', 'PERCENTAGE_DISCOUNT', 30, '2023-09-13', '2024-12-25');
insert into discount(name, action_type, discount_value, start_date, end_date) VALUES('40% na odredjenje artikle', 'PERCENTAGE_DISCOUNT', 10, '2023-09-13', '2024-12-25');





INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('The Witcher 3: Wild Hunt', 'Open-world RPG u fantastičnom svetu.',  '19/05/2015', false, 'witcher3.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Red Dead Redemption 2', 'Akcioni avanturistički naslov u divljem zapadu.',  '05/11/2019', false, 'reddeadredemption.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Cyberpunk 2077', 'Open-world RPG smešten u futuristički distopijski svet.', '10/12/2020', false, 'cyberpunk.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('The Elder Scrolls V: Skyrim', 'Fantazijski RPG sa ogromnim otvorenim svetom.', '11/11/2011', false, 'skyrim.png');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Minecraft', 'Sandbox igra koja omogućava građenje i istraživanje.',  '18/11/2011', false, 'minecraft.png');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Grand Theft Auto V', 'Akcioni avanturistički naslov smešten u otvoren svet grada.','14/04/2015', false, 'gtav.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Dark Souls III', 'Izazovni akcioni RPG sa intenzivnim borbama.',  '12/04/2016', false, 'darksouls3.png');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Fallout 4', 'Otvoreni svet RPG smešten u postapokaliptičnom okruženju.', '10/11/2015', false, 'fallout4.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('The Legend of Zelda', 'Akcioni avanturistički naslov smešten u fantastični svet.',  '03/03/2017', false, 'zeldabotw.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Doom Eternal', 'Brza pucačina iz prvog lica sa borbenim akcijama protiv demona.',  '20/03/2020', false, 'doometernal.png');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Amnesia: The Dark Descent', 'Klasik horor naslov poznat po atmosferičnoj napetosti.', '08/09/2010', false, 'amnesiatdd.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Outer Wilds', 'Istraživačka igra smeštena u minijaturni solarni sistem.', '18/06/2020', false, 'outerwilds.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Stardew Valley', 'Simulacija života sa poljoprivredom i zajednicom.', '26/02/2016', false, 'stardewvalley.png');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('The Sims 4', 'Životni simulator sa kreiranjem likova i kuća.', '02/09/2014', false, 'thesims4.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Among Us', 'Online multiplayer igra sa detektivskom tematikom.', '15/06/2018', false, 'amongus.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Sekiro: Shadows Die Twice', 'Akcioni naslov sa japanskim samurajem kao protagonistom.',  '22/03/2019', false, 'sekiro.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Hades', 'Roguelike igra sa bogovima i mitološkim pričama.',  '17/09/2020', false, 'hades.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Cuphead', 'Akcioni platformer sa ručno crtanim stilom.',  '29/09/2017', false, 'cuphead.png');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Resident Evil Village', 'Horor igra smeštena u selo sa vampirima i vukodlacima.',  '07/05/2021', false, 'residentevilvillage.png');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Genshin Impact', 'Akciona RPG igra sa otvorenim svetom.',  '28/09/2020', false, 'genshinimpact.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Hollow Knight', 'Metroidvania igra sa dubokim svetom.','24/02/2017', false, 'hollownight.png');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Celeste', 'Platformer igra sa izazovnim nivoima.',  '25/01/2018', false, 'celeste.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Terraria', 'Sandbox avantura sa kopanjem i gradnjom.',  '16/05/2011', false, 'terraria.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Stellaris', 'Strategijska igra sa svemirom i vanzemaljcima.', '09/05/2016', false, 'stellaris.png');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Ori and the Will of the Wisps', 'Platformer sa prelepim umetničkim stilom.', '11/03/2020', false, 'ori.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Mortal Kombat 11', 'Borilačka igra sa poznatim borcima.', '23/04/2019', false, 'mortalkombat11.png');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Slay the Spire', 'Roguelike igra sa kartama i strategijom.', '23/01/2019', false, 'slaythespire.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('The Binding of Isaac: Rebirth', 'Roguelike igra sa tamnom tematikom.',  '04/11/2014', false, 'bindingofisaacrebirth.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Subnautica', 'Survival igra sa podvodnim istraživanjem.',  '23/01/2018', false, 'subnautica.png');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Dont Starve', 'Survival igra sa čudnim stvorenjima.',  '23/04/2013', false, 'dontstarve.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Halo: The Master Chief Collection', 'Kolekcija Halo igara.', '03/12/2019', false, 'halomasterchief.png');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Cities: Skylines', 'Graditeljska simulacija sa urbanim planiranjem.', '10/03/2015', false, 'citiesskylines.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('The Forest', 'Survival horor igra sa boravkom u šumi.',  '30/04/2018', false, 'theforest.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Phasmophobia', 'Horor igra sa istraživanjem duhova.',  '18/09/2020', false, 'phasmophobia.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Valheim', 'Survival igra sa nordijskom mitologijom.',  '02/02/2021', false, 'valheim.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Hunt: Showdown', 'PVP/PVE lovački naslov sa stvorenjima iz pakla.',  '27/08/2019', false, 'huntshowdown.png');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Factorio', 'Simulacija izgradnje fabrike na drugom svetu.',  '14/08/2020', false, 'factorio.png');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Bioshock Infinite', 'Akcioni FPS sa pričom u nebu.',  '26/03/2013', false, 'bioshockinfinite.jpg');
INSERT INTO video_game (title, description,  release_date, deleted, image) VALUES('Rust', 'Survival igra sa izgradnjom baze.',  '08/02/2018', false, 'rust.png');


insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 1, 1);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 2, 1);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 3, 1);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 4, 1);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 5, 1);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 6, 4);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 7, 4);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 8, 5);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 9, 6);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 10, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 11, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 12, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 13, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 14, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 15, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 16, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 17, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 18, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 19, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 20, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 21, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 22, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 23, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 24, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 25, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 26, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 27, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 28, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 29, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 30, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 31, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 32, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 33, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 34, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 35, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 36, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 37, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 38, null);
insert into price(price, start_date, end_date, game_id, action_id) VALUES (3499.99, '2025-06-01', '2025-07-01', 39, null);



INSERT INTO video_game_genres (video_game_id, genres) VALUES (1, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (1, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (2, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (2, 'ADVENTURE');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (3, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (3, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (4, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (4, 'ADVENTURE');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (5, 'SIMULATION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (5, 'ADVENTURE');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (6, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (6, 'ADVENTURE');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (7, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (7, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (8, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (8, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (9, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (9, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (10, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (10, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (11, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (11, 'FPS');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (12, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (12, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (13, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (13, 'ADVENTURE');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (14, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (14, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (15, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (15, 'ADVENTURE');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (16, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (16, 'ADVENTURE');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (17, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (17, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (18, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (18, 'ADVENTURE');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (19, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (19, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (20, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (20, 'ADVENTURE');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (21, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (21, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (22, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (22, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (23, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (23, 'ADVENTURE');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (24, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (24, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (25, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (25, 'ADVENTURE');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (26, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (26, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (27, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (27, 'ADVENTURE');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (28, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (28, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (29, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (29, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (30, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (30, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (31, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (31, 'ADVENTURE');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (32, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (32, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (33, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (33, 'ADVENTURE');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (34, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (34, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (35, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (35, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (36, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (36, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (37, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (37, 'ADVENTURE');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (38, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (38, 'ACTION');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (39, 'RPG');
INSERT INTO video_game_genres (video_game_id, genres) VALUES (39, 'ACTION');


INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (1, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (1, 'PS5');

INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (2, 'PS5');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (2, 'XboxOne');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (2, 'NintendoSwitch');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (3, 'PS5');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (3, 'XboxSeriesX');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (3, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (4, 'PS4');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (5, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (5, 'XboxSeriesX');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (5, 'PSP');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (6, 'PS5');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (6, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (6, 'NintendoSwitch');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (7, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (8, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (9, 'XboxSeriesX');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (10, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (11, 'NintendoSwitch');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (12, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (13, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (14, 'NintendoSwitch');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (15, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (15, 'VR');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (16, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (17, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (18, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (19, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (20, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (21, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (22, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (23, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (24, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (25, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (26, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (27, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (28, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (29, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (30, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (31, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (32, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (33, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (34, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (35, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (36, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (37, 'PC');
INSERT INTO video_game_platforms (video_game_id, platforms) VALUES (38, 'PC');


-- Game 1, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (1, 1, 42, 1, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (1, 2, 55, 1, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (1, 3, 27, 1, 0);

-- Game 2, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (2, 1, 31, 2, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (2, 2, 64, 2, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (2, 3, 49, 2, 0);

-- Game 3, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (3, 1, 18, 3, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (3, 2, 72, 3, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (3, 3, 33, 3, 0);

-- Continue for Games 4 to 40...
-- Game 4, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (4, 1, 50, 4, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (4, 2, 60, 4, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (4, 3, 45, 4, 0);

-- Game 5, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (5, 1, 35, 5, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (5, 2, 70, 5, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (5, 3, 55, 5, 0);

-- Game 6, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (6, 1, 40, 6, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (6, 2, 65, 6, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (6, 3, 58, 6, 0);

-- Game 7, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (7, 1, 48, 7, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (7, 2, 55, 7, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (7, 3, 52, 7, 0);

-- Game 8, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (8, 1, 42, 8, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (8, 2, 62, 8, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (8, 3, 47, 8, 0);

-- Game 9, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (9, 1, 37, 9, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (9, 2, 68, 9, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (9, 3, 53, 9, 0);

-- Game 10, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (10, 1, 55, 10, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (10, 2, 50, 10, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (10, 3, 60, 10, 0);

-- Continue this pattern for Games 11 to 40...
-- Game 11, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (11, 1, 45, 11, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (11, 2, 70, 11, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (11, 3, 55, 11, 0);

-- Game 12, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (12, 1, 38, 12, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (12, 2, 65, 12, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (12, 3, 58, 12, 0);

-- Game 13, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (13, 1, 50, 13, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (13, 2, 60, 13, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (13, 3, 45, 13, 0);

-- Game 14, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (14, 1, 30, 14, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (14, 2, 65, 14, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (14, 3, 48, 14, 0);

-- Game 15, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (15, 1, 42, 15, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (15, 2, 60, 15, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (15, 3, 55, 15, 0);

-- Game 16, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (16, 1, 55, 16, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (16, 2, 50, 16, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (16, 3, 60, 16, 0);

-- Game 17, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (17, 1, 38, 17, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (17, 2, 70, 17, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (17, 3, 53, 17, 0);

-- Game 18, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (18, 1, 48, 18, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (18, 2, 55, 18, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (18, 3, 52, 18, 0);

-- Game 19, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (19, 1, 42, 19, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (19, 2, 62, 19, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (19, 3, 47, 19, 0);

-- Game 20, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (20, 1, 37, 20, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (20, 2, 68, 20, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (20, 3, 53, 20, 0);

-- Continue this pattern for Games 21 to 40...
-- Game 21, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (21, 1, 45, 21, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (21, 2, 70, 21, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (21, 3, 55, 21, 0);

-- Game 22, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (22, 1, 38, 22, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (22, 2, 65, 22, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (22, 3, 58, 22, 0);

-- Game 23, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (23, 1, 50, 23, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (23, 2, 60, 23, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (23, 3, 45, 23, 0);

-- Game 24, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (24, 1, 30, 24, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (24, 2, 65, 24, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (24, 3, 48, 24, 0);

-- Game 25, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (25, 1, 42, 25, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (25, 2, 60, 25, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (25, 3, 55, 25, 0);

-- Game 26, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (26, 1, 55, 26, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (26, 2, 50, 26, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (26, 3, 60, 26, 0);

-- Game 27, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (27, 1, 38, 27, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (27, 2, 70, 27, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (27, 3, 53, 27, 0);

-- Game 28, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (28, 1, 48, 28, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (28, 2, 55, 28, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (28, 3, 52, 28, 0);

-- Game 29, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (29, 1, 42, 29, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (29, 2, 62, 29, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (29, 3, 47, 29, 0);

-- Game 30, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (30, 1, 58, 30, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (30, 2, 45, 30, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (30, 3, 50, 30, 0);

-- Game 31, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (31, 1, 44, 31, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (31, 2, 66, 31, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (31, 3, 51, 31, 0);

-- Game 32, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (32, 1, 33, 32, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (32, 2, 72, 32, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (32, 3, 56, 32, 0);

-- Game 33, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (33, 1, 51, 33, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (33, 2, 64, 33, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (33, 3, 59, 33, 0);

-- Game 34, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (34, 1, 39, 34, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (34, 2, 63, 34, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (34, 3, 49, 34, 0);

-- Game 35, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (35, 1, 45, 35, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (35, 2, 59, 35, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (35, 3, 54, 35, 0);

-- Game 36, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (36, 1, 35, 36, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (36, 2, 68, 36, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (36, 3, 57, 36, 0);

-- Game 37, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (37, 1, 47, 37, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (37, 2, 61, 37, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (37, 3, 52, 37, 0);

-- Game 38, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (38, 1, 31, 38, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (38, 2, 70, 38, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (38, 3, 58, 38, 0);

-- Game 39, Shop 1-3
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (39, 1, 49, 39, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (39, 2, 55, 39, 0);
INSERT INTO game_quantity (gameid, shop_id, quantity, video_game_id, reserved) VALUES (39, 3, 54, 39, 0);

-- Game 40, Shop 1-3

insert into central_inventory (quantity, game_id) VALUES (10, 3);
insert into central_inventory (quantity, game_id) VALUES (15, 2);
insert into central_inventory (quantity, game_id) VALUES (3, 1);
insert into central_inventory (quantity, game_id) VALUES (10, 13);
insert into central_inventory (quantity, game_id) VALUES (23, 21);
insert into central_inventory (quantity, game_id) VALUES (11, 8);

insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 5, 1, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, '', false, false, 3, 1, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, '', false, false, 5, 1, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 4, 1, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 2, 1, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 1, 1, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 5, 1, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 3, 1, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 5, 2, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 5, 2, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 3, 2, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 2, 3, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 1, 4, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 4, 5, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 2, 6, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 5, 11, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 5, 2, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 5, 4, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 3, 33, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 4, 1, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 5, 6, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 1, 7, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 2, 14, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 5, 16, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 5, 18, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 5, 21, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 4, 12, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 2, 1, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 5, 1, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 4, 16, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 3, 17, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 1, 1, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 5, 34, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 5, 1, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (true, 'Savesna igra bas mi se dopada bolju nisam igrao', false, false, 5, 20, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (false, 'Uzasna igra bas mi se dopada bolju nisam igrao', false, false, 5, 20, 2);
insert into review (approved, comment, deleted, denied, rating, game_id, user_id) VALUES (false, 'FUJ igra bas mi se dopada bolju nisam igrao', false, false, 5, 20, 2);
