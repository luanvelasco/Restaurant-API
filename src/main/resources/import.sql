insert into kitchen (id, name) values (1, 'Brazilian');
insert into kitchen (id, name) values (2, 'Asian');


insert into restaurant (id, name, shipping_tax, open, status, register_date, updated_date, kitchen_id) values (1, 'Volta Redonda', 6.50, true, true, '2011-01-18', '2011-01-18', 1);
insert into restaurant (id, name, shipping_tax, open, status, register_date, updated_date, kitchen_id) values (2, 'Resende', 9.50, true, true, '2013-06-11', '2015-01-20', 2);


insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);