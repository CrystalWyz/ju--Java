-- auto-generated definition
create table user_game
(
    id               bigserial
        primary key,
    create_time      timestamp(6),
    update_time      timestamp(6),
    created_by       varchar(64),
    last_modified_by varchar(64),
    user_id          bigserial,
    ju_level         integer,
    ju_game_count    integer,
    gold             integer,
    score            integer,
    blemish          boolean
);

comment on column user_game.id is 'id';
comment on column user_game.create_time is '创建时间';
comment on column user_game.update_time is '修改时间';
comment on column user_game.created_by is '创建者';
comment on column user_game.last_modified_by is '最终修改人';

comment on column user_game.user_id is '用户id';
comment on column user_game.ju_level is '剧本杀游戏等级';
comment on column user_game.ju_game_count is '剧本杀游戏场数';
comment on column user_game.gold is '金币';
comment on column user_game.score is '积分';
comment on column user_game.blemish is '是否存在污点';

-- 创建外键 ju_user.id > userId
alter table user_game
    add constraint fk_user_game_user_id
        foreign key (user_id) references ju.ju_user (id);

alter table user_game
    owner to postgres;

