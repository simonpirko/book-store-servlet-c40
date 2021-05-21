drop table if exists  comment;

drop table if exists order_book;

drop table if exists "order";

drop table if exists "user";

drop table if exists role;

drop table if exists store;

drop table if exists address;

drop table if exists delivery_type;

drop table if exists order_status;

drop table if exists book_author;

drop table if exists book;

drop table if exists genre;

drop table if exists author;

create table address
(
    id serial not null
        constraint address_pk
            primary key,
    street varchar(300) not null,
    home integer not null,
    created_at timestamp with time zone default now() not null
);

create table "role"
(
    id serial not null
        constraint role_pk
            primary key,
    name varchar(300) not null,
    created_at timestamp with time zone default now() not null
);

create table "user"
(
    id serial not null
        constraint user_pk
            primary key,
    address_id integer
        constraint user_address_id_fk
            references address,
    username varchar(300) not null,
    password varchar(300) not null,
    role integer
        constraint user_role_id_fk
            references role not null,
    first_name varchar(300),
    last_name varchar(300),
    date_birth date,
    created_at timestamp with time zone default now() not null
);

create unique index user_username_uindex
    on "user" (username);

create table store
(
    id serial not null
        constraint store_pk
            primary key,
    name varchar(300),
    address_id integer not null
        constraint store_address_id_fk
            references address,
    created_at timestamp with time zone default now() not null
);

create unique index store_id_uindex
    on store (id);

create table "delivery_type"
(
    id serial not null
        constraint delivery_type_pk
            primary key,
    name varchar(300) not null,
    created_at timestamp with time zone default now() not null
);

create table "order_status"
(
    id serial not null
        constraint order_status_pk
            primary key,
    name varchar(300) not null,
    created_at timestamp with time zone default now() not null
);

create table "order"
(
    id serial not null
        constraint order_pk
            primary key,
    user_id integer not null
        constraint order_user_id_fk
            references "user",
    store_id integer
        constraint order_store_id_fk
            references store,
    address_id integer
        constraint order_address_id_fk
            references address,
    delivery_type integer
        constraint order_delivery_type_id_fk
            references delivery_type not null,
    order_status integer
        constraint order_order_status_id_fk
            references order_status not null,
    created_at timestamp with time zone default now() not null
);

create unique index order_id_uindex
    on "order" (id);

create table genre
(
    id serial not null
        constraint genre_pk
            primary key,
    name varchar(300) not null,
    created_at timestamp with time zone default now() not null
);

create table book
(
    id serial not null
        constraint book_pk
            primary key,
    name varchar(300) not null,
    price integer not null,
    description text,
    genre_id integer not null
        constraint book_genre_id_fk
            references genre,
    published_date date not null,
    created_at timestamp with time zone default now() not null
);

create table comment
(
    id serial not null
        constraint comment_pk
            primary key,
    user_id integer not null
        constraint comment_user_id_fk
            references "user",
    book_id integer not null
        constraint comment_book_id_fk
            references book,
    text text not null,
    created_at timestamp with time zone default now() not null
);

create table order_book
(
    order_id integer not null
        constraint order_book_order_id_fk
            references "order",
    book_id integer not null
        constraint order_book_book_id_fk
            references book,
    quantity integer default 1 not null
);

create unique index book_id_uindex
    on book (id);

create unique index genre_id_uindex
    on genre (id);

create table author
(
    id serial not null
        constraint author_pk
            primary key,
    first_name varchar(300) not null,
    last_name varchar(300) not null,
    description text,
    created_at timestamp with time zone default now() not null
);

create unique index author_id_uindex
    on author (id);

create table book_author
(
    book_id integer not null
        constraint book_author_book_id_fk
            references book,
    author_id integer not null
        constraint book_author_author_id_fk
            references author
);
