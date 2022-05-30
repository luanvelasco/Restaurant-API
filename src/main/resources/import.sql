insert into kitchen (id, name) values (1, 'Brazilian');
insert into kitchen (id, name) values (2, 'Asian');

insert into state (id, name) values (1, 'Minas Gerais');
insert into state (id, name) values (2, 'São Paulo');
insert into state (id, name) values (3, 'Ceará');

insert into city (id, name, state_id) values (1, 'Uberlândia', 1);
insert into city (id, name, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, name, state_id) values (3, 'São Paulo', 2);
insert into city (id, name, state_id) values (4, 'Campinas', 2);
insert into city (id, name, state_id) values (5, 'Fortaleza', 3);

insert into restaurant (id, name, shipping_tax, open, status, register_date, updated_date, kitchen_id, address_zipcode, address_street, address_number, address_complement, address_district, address_city_id) values (1, 'Volta Redonda', 6.50, true, true, '2011-01-18', '2011-01-18', 1, 'Test_zipCode', 'Test_street', 'Test_number', 'Test_complement', 'Test_district', 1);
insert into restaurant (id, name, shipping_tax, open, status, register_date, updated_date, kitchen_id) values (2, 'Resende', 9.50, true, true, '2013-06-11', '2015-01-20', 2);

insert into payment (id, description) values (1, 'Credit Card');
insert into payment (id, description) values (2, 'Debit Card');
insert into payment (id, description) values (3, 'Money');

insert into restaurant_payment_type (restaurant_id, payment_type_id) values (1, 1), (1, 2), (1, 3), (2, 3), (2, 2), (2, 3);