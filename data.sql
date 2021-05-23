INSERT INTO public.genre (id, name, created_at) VALUES (1, 'Роман', '2021-04-30 21:46:12.700531');
INSERT INTO public.genre (id, name, created_at) VALUES (2, 'Военная проза', '2021-04-30 21:46:12.700531');
INSERT INTO public.genre (id, name, created_at) VALUES (3, 'Философский роман', '2021-04-30 21:46:12.700531');
INSERT INTO public.genre (id, name, created_at) VALUES (4, 'Сказка', '2021-04-30 21:51:41.130754');
INSERT INTO public.genre (id, name, created_at) VALUES (5, 'Повесть', '2021-04-30 21:55:41.130754');

INSERT INTO public.author (id, first_name, last_name, description, created_at) VALUES (1, 'Лев', 'Толстой', 'Граф Лев Николаевич Толсто́й — один из наиболее известных русских писателей и мыслителей, один из величайших писателей-романистов мира. Участник обороны Севастополя.', '2021-04-30 21:42:34.038325');
INSERT INTO public.author (id, first_name, last_name, description, created_at) VALUES (2, 'Александр', 'Пушкин', 'Алекса́ндр Серге́евич Пу́шкин — русский поэт, драматург и прозаик, заложивший основы русского реалистического направления, литературный критик и теоретик литературы, историк, публицист, журналист; один из самых авторитетных литературных деятелей первой трети XIX века.', '2021-04-30 21:43:08.413575');
INSERT INTO public.author (id, first_name, last_name, description, created_at) VALUES (3, 'Фёдор', 'Достоевский', 'Фёдор Миха́йлович Достое́вский — русский писатель, мыслитель, философ и публицист. Член-корреспондент Петербургской академии наук с 1877 года. Классик мировой литературы. Ранние произведения писателя, как и повесть «Записки из Мёртвого дома», способствовали возникновению жанра психологической прозы.', '2021-04-30 21:43:54.800213');

INSERT INTO public.book (id, name, price, description, genre_id, published_date, created_at) VALUES (1, 'Сказка о рыбаке и рыбке', 98, '«Ска́зка о рыбаке́ и ры́бке» — сказка А. С. Пушкина. Написана 2 октября 1833 года. Впервые напечатана в 1835 году в журнале «Библиотека для чтения». В рукописи есть пометка: «18 песнь сербская». Эта помета означает, что Пушкин собирался включить её в состав «Песен западных славян».', 4, '1835-05-01', '2021-04-30 21:52:08.367292');
INSERT INTO public.book (id, name, price, description, genre_id, published_date, created_at) VALUES (2, 'Анна Каренина', 190, '«А́нна Каре́нина» — роман Льва Толстого о трагической любви замужней дамы Анны Карениной и блестящего офицера Вронского на фоне счастливой семейной жизни дворян Константина Лёвина и Кити Щербацкой.', 1, '1875-05-01', '2021-04-30 21:53:32.040284');
INSERT INTO public.book (id, name, price, description, genre_id, published_date, created_at) VALUES (3, 'Преступление и наказание', 225, '«Преступление и наказание» — социально-психологический и социально-философский роман Фёдора Михайловича Достоевского, над которым писатель работал в 1865—1866 годах. Впервые опубликован в 1866 году в журнале «Русский вестник».', 1, '1866-05-01', '2021-05-10 21:53:32.040284');
INSERT INTO public.book (id, name, price, description, genre_id, published_date, created_at) VALUES (4, 'Белые ночи', 140, '«Бе́лые но́чи» — повесть русского писателя XIX века Фёдора Михайловича Достоевского.', 5, '1848-05-01', '2021-05-30 21:53:32.040284');

INSERT INTO public.book_author (book_id, author_id) VALUES (1, 2);
INSERT INTO public.book_author (book_id, author_id) VALUES (2, 1);
INSERT INTO public.book_author (book_id, author_id) VALUES (3, 3);
INSERT INTO public.book_author (book_id, author_id) VALUES (4, 1);
INSERT INTO public.book_author (book_id, author_id) VALUES (4, 2);
INSERT INTO public.book_author (book_id, author_id) VALUES (4, 3);

INSERT INTO public.address (id, street, home, created_at) VALUES (1, 'Немига', 30, '2021-04-30 21:54:01.932982');
INSERT INTO public.address (id, street, home, created_at) VALUES (2, 'Пушкина', 22, '2021-04-30 21:55:01.932982');
INSERT INTO public.address (id, street, home, created_at) VALUES (3, 'Ольшевского', 12, '2021-04-30 21:56:01.932982');
INSERT INTO public.address (id, street, home, created_at) VALUES (4, 'Есенина', 6, '2021-04-30 21:57:01.932982');

INSERT INTO public.store (id, name, address_id, created_at) VALUES (1, 'Первый магазин', 1, '2021-04-30 21:54:50.559510');
INSERT INTO public.store (id, name, address_id, created_at) VALUES (2, 'Второй магазин', 2, '2021-04-30 21:55:50.559510');

INSERT INTO public.role (id, name, created_at) VALUES (1, 'User', '2021-04-30 21:51:41.130754');
INSERT INTO public.role (id, name, created_at) VALUES (2, 'Admin', '2021-04-30 21:52:41.130754');
INSERT INTO public.role (id, name, created_at) VALUES (3, 'Moderator', '2021-04-30 21:53:41.130754');

INSERT INTO public."user" (id, address_id, username, password, role, first_name, last_name, date_birth, created_at) VALUES (1, 3, 'UserLogin', 'UserPassword', 1, 'Имя', 'Фамилия', null, '2021-04-30 21:55:40.214308');

INSERT INTO public.delivery_type (id, name, created_at) VALUES (1, 'Delivery', '2021-04-30 21:51:41.130754');
INSERT INTO public.delivery_type (id, name, created_at) VALUES (2, 'Pickup', '2021-04-30 21:52:41.130754');

INSERT INTO public.order_status (id, name, created_at) VALUES (1, 'Moderation', '2021-04-30 21:52:41.130754');
INSERT INTO public.order_status (id, name, created_at) VALUES (2, 'Approved', '2021-04-30 21:52:41.130754');
INSERT INTO public.order_status (id, name, created_at) VALUES (3, 'Ready', '2021-04-30 21:52:41.130754');
INSERT INTO public.order_status (id, name, created_at) VALUES (4, 'Received', '2021-04-30 21:52:41.130754');
INSERT INTO public.order_status (id, name, created_at) VALUES (5, 'Canceled', '2021-04-30 21:52:41.130754');

INSERT INTO public."order" (id, user_id, store_id, address_id, delivery_type, order_status, created_at) VALUES (1, 1, 1, 3, 1, 1, '2021-04-30 21:57:23.747000');

INSERT INTO public.order_book (order_id, book_id, quantity) VALUES (1, 1, 1);
INSERT INTO public.order_book (order_id, book_id, quantity) VALUES (1, 2, 1);
INSERT INTO public.order_book (order_id, book_id, quantity) VALUES (1, 3, 1);

INSERT INTO public.comment (id, user_id, book_id, text, created_at) VALUES (1, 1, 1, 'Самая лучшая книга!', '2021-04-30 21:56:19.399777');
INSERT INTO public.comment (id, user_id, book_id, text, created_at) VALUES (2, 1, 2, 'Самая лучшая книга!', '2021-04-30 21:56:19.399777');
INSERT INTO public.comment (id, user_id, book_id, text, created_at) VALUES (3, 1, 3, 'Самая лучшая книга!', '2021-04-30 21:56:19.399777');
