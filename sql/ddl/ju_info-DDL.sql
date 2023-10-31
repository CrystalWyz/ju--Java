-- auto-generated definition
create table ju_info
(
    id                   bigserial
        primary key,
    title                text,
    participant          bigint[],
    begin                timestamp(6),
    finish               timestamp(6),
    create_time          timestamp(6),
    description          text,
    user_id              bigint,
    update_time          timestamp(6),
    scale                integer,
    girl_participant_num integer,
    boy_participant_num  integer,
    config               jsonb,
    type                 integer
);

comment on column ju_info.id is 'id';

comment on column ju_info.title is '
聚主题';

comment on column ju_info.participant is '参与用户';

comment on column ju_info.begin is '
预计开始时间';

comment on column ju_info.finish is '预计结束时间';

comment on column ju_info.create_time is '创建时间';

comment on column ju_info.description is '聚描述信息';

comment on column ju_info.user_id is '创建者id';

comment on column ju_info.update_time is '更新时间';

comment on column ju_info.scale is '规模';

comment on column ju_info.girl_participant_num is '小姐姐参与人数';

comment on column ju_info.boy_participant_num is '小哥哥参与人数';

comment on column ju_info.config is '配置信息';

comment on column ju_info.type is '类型';

alter table ju_info
    owner to postgres;

