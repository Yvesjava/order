-- auto-generated definition
create table `order`
(
  id         int auto_increment
    primary key,
  u_id       int default '0'         not null
  comment '订餐人的id',
  dish       varchar(255) default '' not null
  comment '点了什么菜,菜的名字',
  money      int default '0'         not null
  comment '订餐的金额',
  createtime datetime                null
  comment '订餐的时间',
  status     int default '0'         not null
  comment '保留字段,在这个小项目中暂时未使用到
订单状态值:
0 : 下单成功
1 : 支付成功
2 : 完成
-1: 取消订单',
  deleted    tinyint default '0'     not null
  comment '删除的状态
0:未删除,默认值
1;删除'
)
  comment '点餐页面';

create table `set`
(
  id    int auto_increment
    primary key,
  `key` varchar(50) default '' not null comment '把设置做成key-value键值对形式保存到数据库',
  value text                   null comment '把设置做成key-value键值对形式保存到数据库',
  constraint set_key_uindex
    unique (`key`)
);


-- auto-generated definition
create table user
(
  id                  int auto_increment
    primary key,
  name                varchar(255) default '' not null,
  password            varchar(255) default '' not null,
  wechat              varchar(255) default '' not null,
  nickname            varchar(50) default ''  not null,
  phoneNum            varchar(64) default ''  not null,
  isAdmin             int default '0'         not null,
  email               varchar(255) default '' not null,
  enable_email_remind tinyint default '0'     not null
  comment '0:不开启
1:开启',
  avatar              varchar(500) default '' not null
  comment '头像地址'
);

