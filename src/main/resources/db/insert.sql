INSERT INTO public.roles(id, job_title) VALUES (1, 'admin');
INSERT INTO public.rules(id, rules_user) VALUES (1, 1);
INSERT INTO public.rol_and_rul (roles_id, rules_id) values (1, 1);
INSERT INTO public.users(id, first_name, last_name, roles_id) VALUES (1, 'Vlad', 'Kosch', 1);
INSERT INTO public.categories(id, categorie) VALUES (1, 'job');
INSERT INTO public.states(id, state) VALUES (1, 'state');
INSERT INTO public.items(id, name, users_id, states_id, categories_id) VALUES (1, 'job', 1, 1, 1);
INSERT INTO public.comments(id, comment_text, items_id) VALUES (1, 'big comment', 1);
INSERT INTO public.attach(id, file, items_id) VALUES (1, 10011, 1);