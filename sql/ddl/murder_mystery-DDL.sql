-- auto-generated definition
create table murder_mystery
(
    id                   bigint    not null
        primary key,
    title                text,
    begin                timestamp(3),
    finish               timestamp(3),
    create_time          timestamp(3),
    description          text,
    user_id              bigint,
    update_time          timestamp(3),
    scale                integer,
    girl_participant_num integer,
    boy_participant_num  integer,
    config               jsonb,
    girl_participant     bigint[],
    boy_participant      bigint[],
    area                 integer[] not null,
    address              text,
    shop_name            text
);

comment on column murder_mystery.title is '
聚主题';

comment on column murder_mystery.begin is '
预计开始时间';

comment on column murder_mystery.finish is '预计结束时间';

comment on column murder_mystery.create_time is '创建时间';

comment on column murder_mystery.description is '聚描述信息';

comment on column murder_mystery.user_id is '创建者id';

comment on column murder_mystery.update_time is '更新时间';

comment on column murder_mystery.scale is '规模';

comment on column murder_mystery.girl_participant_num is '女生参与人数';

comment on column murder_mystery.boy_participant_num is '男生参与人数';

comment on column murder_mystery.config is '配置信息';

comment on column murder_mystery.girl_participant is '女生参与列表';

comment on column murder_mystery.boy_participant is '男生参与人数';

alter table murder_mystery
    owner to postgres;

