create table if not exists retro_opt_sender_entity
(
    id        bigserial not null
        constraint rretro_opt_sender_entity_pkey
            primary key,
    sender_id bigint
        constraint fkzzge66xvqgl7hd2jk3o7bnfuc
            references retro_sender,
    type      varchar(255)
);