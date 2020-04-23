create table if not exists retro_sender
(
    id         bigserial not null
        constraint retro_sender_pkey
            primary key,
    first_name text,
    last_name  text,
    username   varchar(255)
);

create table if not exists retro_message
(
    id         bigserial not null
        constraint retro_message_pkey
            primary key,
    created_at timestamp,
    text       text,
    sender_id  bigint
        constraint fkkyge66xvqgl7hd2jk3o7bnfuc
            references retro_sender
);