/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/4/24 21:28:22                           */
/*==============================================================*/


drop table if exists CertificateCard;

drop table if exists Dictionary;

drop table if exists Insurance;

drop table if exists InsuranceAttach;

drop table if exists SystemResource;

drop table if exists SystemRoleResource;

drop table if exists SystemUser;

drop table if exists SystemUserRole;

drop table if exists systemRole;

/*==============================================================*/
/* Table: CertificateCard                                       */
/*==============================================================*/
create table CertificateCard
(
   id                   bigint comment ' 主键',
   code                 varchar(10) comment '授权码',
   createTime           datetime comment '生成日期',
   createUserId         bigint comment '生成人',
   status               varchar(20) comment '状态'
);

alter table CertificateCard comment '授权卡';

/*==============================================================*/
/* Table: Dictionary                                            */
/*==============================================================*/
create table Dictionary
(
   id                   bigint not null comment '主键',
   parentId             bigint comment '父级ID',
   name                 varchar(100) comment '名称',
   mark                 varchar(200) comment '描述',
   status               varchar(20) comment '状态',
   createTime           datetime comment '创建时间',
   createUserId         bigint comment '创建人',
   primary key (id)
);

alter table Dictionary comment '数据字典';

/*==============================================================*/
/* Table: Insurance                                             */
/*==============================================================*/
create table Insurance
(
   id                   bigint not null comment '主键',
   certificateCode      varchar(10) comment '授权码',
   telBrand             varchar(20) comment '品牌',
   telModel             varchar(200) comment '型号',
   telIMEI              varchar(17) comment 'IMEI码',
   card_num             varchar(20) comment '智保卡号',
   customerName         varchar(50) comment '顾客姓名',
   customerBirthday     date comment '顾客生日',
   mobileNumber         varchar(20) comment '手机号码',
   store                varchar(20) comment '门店',
   storeTransactor      varchar(50) comment '门店办理人',
   createTime           datetime comment '创建时间',
   status               varchar(20) comment '状态',
   mark                 varchar(200) comment '备注',
   primary key (id)
);

alter table Insurance comment '碎屏保险';

/*==============================================================*/
/* Table: InsuranceAttach                                       */
/*==============================================================*/
create table InsuranceAttach
(
   id                   bigint not null comment '主键',
   insuranceId          bigint comment '保险ID',
   attachName           varchar(255) comment '附件名称',
   attachSize           varchar(255) comment '附件大小',
   savePath             varchar(512) comment '存储路径',
   primary key (id)
);

alter table InsuranceAttach comment '碎屏险激活附件';

/*==============================================================*/
/* Table: SystemResource                                        */
/*==============================================================*/
create table SystemResource
(
   id                   bigint not null comment '主键',
   parentId             bigint comment '父级ID',
   name                 varchar(100) comment '资源名称',
   url                  varchar(200) comment '资源路径',
   status               varchar(20) comment '状态',
   createTime           datetime comment '创建时间',
   primary key (id)
);

alter table SystemResource comment '系统资源';

/*==============================================================*/
/* Table: SystemRoleResource                                    */
/*==============================================================*/
create table SystemRoleResource
(
   id                   bigint not null comment '主键',
   roleId               bigint comment '角色ID',
   resourceId           bigint comment '资源ID',
   createTime           datetime comment '创建时间',
   primary key (id)
);

alter table SystemRoleResource comment '角色-资源';

/*==============================================================*/
/* Table: SystemUser                                            */
/*==============================================================*/
create table SystemUser
(
   id                   bigint not null comment '主键',
   loginName            varchar(100) comment '登录名',
   nickName             varchar(100) comment '昵称',
   passWord             varchar(100) comment '密码',
   status               varchar(20) comment '状态',
   createTime           datetime comment '创建时间',
   primary key (id)
);

alter table SystemUser comment '系统用户';

/*==============================================================*/
/* Table: SystemUserRole                                        */
/*==============================================================*/
create table SystemUserRole
(
   id                   bigint not null comment '主键',
   userId               bigint comment '用户Id',
   roleId               bigint comment '角色Id',
   createTime           datetime comment '创建时间',
   primary key (id)
);

alter table SystemUserRole comment '用户-角色';

/*==============================================================*/
/* Table: systemRole                                            */
/*==============================================================*/
create table systemRole
(
   id                   bigint not null comment '主键',
   roleCode             varchar(100) comment '角色编码',
   roleName             varchar(200) comment '角色名称',
   status               varchar(20) comment '状态',
   createTime           datetime comment '创建时间',
   primary key (id)
);

alter table systemRole comment '系统角色';

alter table InsuranceAttach add constraint FK_Reference_5 foreign key (insuranceId)
      references Insurance (id) on delete restrict on update restrict;

alter table SystemRoleResource add constraint FK_Reference_3 foreign key (roleId)
      references systemRole (id) on delete restrict on update restrict;

alter table SystemRoleResource add constraint FK_Reference_4 foreign key (resourceId)
      references SystemResource (id) on delete restrict on update restrict;

alter table SystemUserRole add constraint FK_Reference_1 foreign key (userId)
      references SystemUser (id) on delete restrict on update restrict;

alter table SystemUserRole add constraint FK_Reference_2 foreign key (roleId)
      references systemRole (id) on delete restrict on update restrict;

