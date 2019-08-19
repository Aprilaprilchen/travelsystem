insert into area(name, consumption_level, location, description) values
('DC', '6', 'city', 'Washington District of Columbia'),
('VA', '4', 'country', 'Virginia'),
('Maryland', '3', 'mountain', 'Maryland'),
('NYC', '8', 'city', 'New York City'),
('FL', '7', 'sea', 'Florida')
;
commit;

insert into hotel(name, location, price, comfort_level, area_id) values
('green tree', 'downtown', '150', '3', '5'),
('Shelton', 'downtown', '300', '4', '3'),
('Inn', 'airport', '200', '2', '2'),
('Motel', 'old town', '100', '1', '2'),
('Cando', 'midtown', '350', '5', '1')
;
commit;

insert into customer(name, first_name, last_name, password, email, age, budget, gender, area_id) values
('dwang', 'David', 'Wang', '123', 'davey.wang@ascending.com', '18', '2000', 'F', '3'),
('rhang', 'Ryo', 'Hang', '456', 'ryo.hang@ascending.com', '20', '3500', 'F', '3'),
('gzhang', 'Gloria', 'Zhang', '789', 'gloria.zhang@ascending.com', '17', '789', 'M', '2'),
('xhuang', 'Xingyue', 'Huang', '123', 'xingyue.Hang@ascending.com', '22', '5000', 'M', '1')
;
commit;