create table if not exists business_question
(
    id         bigserial not null
        constraint business_question_pkey
            primary key,
    created_at timestamp,
    question       text,
    sender_id  bigint
        constraint fkkyge66xvqgl7hd2jk3o7bnfuc
            references retro_sender
);