-- auto-generated definition
create table blemish_detail
(
    id                bigserial
        primary key,
    user_id           bigint not null,
    type              smallint,
    other_description text,
    description       text,
    create_time       timestamp,
    ju_info_id        bigint
);

comment on table blemish_detail is '不良记录';

comment on column blemish_detail.id is 'id';

comment on column blemish_detail.user_id is '用户id';

comment on column blemish_detail.type is '污点类型';

comment on column blemish_detail.other_description is '其他——类型描述';

comment on column blemish_detail.description is '描述信息';

comment on column blemish_detail.create_time is '创建时间';

comment on column blemish_detail.ju_info_id is '聚id';

alter table blemish_detail
    owner to postgres;

