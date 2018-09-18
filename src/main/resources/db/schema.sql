DROP TABLE comment IF EXISTS;
DROP TABLE post IF EXISTS;

create table post
(
  id          bigint IDENTITY
    primary key,
  creation_at datetime     null,
  img_url     varchar(255) null,
  post_body   LONGVARCHAR  null,
  title       varchar(255) null
);

create table comment
(
  id           bigint IDENTITY
    primary key,
  author       varchar(255) null,
  comment_body LONGVARCHAR  null,
  created_at   datetime     null,
  post_id      bigint       null,
  constraint FKs1slvnkuemjsq2kj4h3vhx7i1
  foreign key (post_id) references post (id)
);
