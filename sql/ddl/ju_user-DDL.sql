-- auto-generated definition
create table ju_user
(
    id               bigserial
        primary key,
    create_time      timestamp(6),
    update_time      timestamp(6),
    created_by       varchar(64),
    last_modified_by varchar(64),
    name             text,
    nick_name        text,
    gender           integer,
    blemish          smallint,
    phone            text,
    phone_area       text,
    identity         integer,
    username         varchar(64),
    password         varchar(128),
    enabled          boolean
);

comment on column ju_user.id is 'id';
comment on column ju_user.create_time is '创建时间';
comment on column ju_user.update_time is '修改时间';
comment on column ju_user.created_by is '创建者';
comment on column ju_user.last_modified_by is '最终修改人';

comment on column ju_user.name is '用户名';
comment on column ju_user.nick_name is '昵称';
comment on column ju_user.gender is '性别';
comment on column ju_user.blemish is '污点';
comment on column ju_user.phone is '手机号';
comment on column ju_user.phone_area is '区号';
comment on column ju_user.identity is '身份';
comment on column ju_user.username is '账户名';
comment on column ju_user.password is '登录密码';
comment on column ju_user.enabled is '是否启用';

alter table ju_user
    owner to postgres;

