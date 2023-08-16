insert into users (password, email, verified) VALUES ('$2a$12$h/HXDzJgpT4ff5EtiPN/UejwWLe/WfyFx/lsHmBe/lnuAYyqxtYEC', 'dsisarica40@gmail.com', true);
insert into role (name) VALUES ('ROLE_ADMIN');
insert into role (name) VALUES ('ROLE_USER');
insert into user_role (user_id, role_id) VALUES (1,1);