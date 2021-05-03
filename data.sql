INSERT INTO public.genre (id, name, created_at) VALUES (1, 'Роман', '2021-04-30 21:46:12.700531');
INSERT INTO public.genre (id, name, created_at) VALUES (2, 'Военная проза', '2021-04-30 21:46:12.700531');
INSERT INTO public.genre (id, name, created_at) VALUES (3, 'Философский роман', '2021-04-30 21:46:12.700531');
INSERT INTO public.genre (id, name, created_at) VALUES (4, 'Сказка', '2021-04-30 21:51:41.130754');

INSERT INTO public.author (id, first_name, last_name, description, created_at) VALUES (1, 'Лев', 'Толстой', 'Граф Лев Николаевич Толсто́й — один из наиболее известных русских писателей и мыслителей, один из величайших писателей-романистов мира. Участник обороны Севастополя.', '2021-04-30 21:42:34.038325');
INSERT INTO public.author (id, first_name, last_name, description, created_at) VALUES (2, 'Александр', 'Пушкин', 'Алекса́ндр Серге́евич Пу́шкин — русский поэт, драматург и прозаик, заложивший основы русского реалистического направления, литературный критик и теоретик литературы, историк, публицист, журналист; один из самых авторитетных литературных деятелей первой трети XIX века.', '2021-04-30 21:43:08.413575');
INSERT INTO public.author (id, first_name, last_name, description, created_at) VALUES (3, 'Фёдор', 'Достоевский', 'Фёдор Миха́йлович Достое́вский — русский писатель, мыслитель, философ и публицист. Член-корреспондент Петербургской академии наук с 1877 года. Классик мировой литературы. Ранние произведения писателя, как и повесть «Записки из Мёртвого дома», способствовали возникновению жанра психологической прозы.', '2021-04-30 21:43:54.800213');

INSERT INTO public.book (id, name, price, description, genre_id, published_date, created_at) VALUES (5, 'Сказка о рыбаке и рыбке', 98, '«Ска́зка о рыбаке́ и ры́бке» — сказка А. С. Пушкина. Написана 2 октября 1833 года. Впервые напечатана в 1835 году в журнале «Библиотека для чтения». В рукописи есть пометка: «18 песнь сербская». Эта помета означает, что Пушкин собирался включить её в состав «Песен западных славян».', 4, '2021-05-01', '2021-04-30 21:52:08.367292');
INSERT INTO public.book (id, name, price, description, genre_id, published_date, created_at) VALUES (6, 'Анна Каренина', 190, '«А́нна Каре́нина» — роман Льва Толстого о трагической любви замужней дамы Анны Карениной и блестящего офицера Вронского на фоне счастливой семейной жизни дворян Константина Лёвина и Кити Щербацкой.', 1, '2021-05-01', '2021-04-30 21:53:32.040284');

INSERT INTO public.book_author (book_id, author_id) VALUES (5, 1);

INSERT INTO public.address (id, street, home, created_at) VALUES (1, 'Немига', 30, '2021-04-30 21:54:01.932982');

INSERT INTO public.store (id, name, address_id, created_at) VALUES (2, 'Топ магазин', 1, '2021-04-30 21:54:50.559510');

INSERT INTO public."user" (id, address_id, username, password, role, first_name, last_name, date_birth, created_at) VALUES (1, null, 'Boss', 'boss123', 'user', null, null, null, '2021-04-30 21:55:40.214308');

INSERT INTO public."order" (id, user_id, store_id, address_id, delivery_type, order_status, created_at) VALUES (1, 1, null, 1, 'delivery', 'confirm', '2021-04-30 21:57:23.747000');

INSERT INTO public.order_book (order_id, book_id, quantity) VALUES (1, 5, 1);

INSERT INTO public.comment (id, user_id, book_id, text, created_at) VALUES (3, 1, 5, 'Самая лучшая книга!', '2021-04-30 21:56:19.399777');