insert into registered_user(name, lastname, username, password, email, type, id) values ('ivana', 'zeljkovic', 'ivana', '1111', 'ivana.zeljkovic@gmail.com', 'Registered', 10);
insert into registered_user(name, lastname, username, password, email, type, id) values ('nemanja', 'brzak', 'nemanja', '2222', 'nemanja.brzak@gmail.com', 'Registered', 11);
insert into registered_user(name, lastname, username, password, email, type, id) values ('katarina', 'cukurov', 'kaca', '3333', 'kaca.cukurov@gmail.com', 'Registered', 12);
insert into registered_user(name, lastname, username, password, email, type, id) values ('nikola', 'garabandic', 'nikola', '4444', 'nikola.garabandic@gmail.com', 'Registered', 13);
insert into registered_user(name, lastname, username, password, email, type, id) values ('marko', 'belic', 'marko', '5555', 'marko.belick@gmail.com', 'Registered', 14);
insert into registered_user(name, lastname, username, password, email, type, id) values ('stefan', 'ilic', 'stefam', '6666', 'stefan.ilic@gmail.com', 'Registered', 15);

insert into manager_system(name, lastname, username, password, email, type, id) values ('kaca', 'cukurov', 'kacac', 'cuki', 'kaca.cukurov@gmail.com', 'Manager', 16);
insert into manager_restaurant(name, lastname, username, password, email, type, id) values ('karolina', 'stajic', 'karolina', 'kkkk', 'kaca.cukurov@gmail.com', 'RestaurantManager', 20);


insert into barman(name, lastname, username, password, type, date_of_birth, dress_size, shoe_size, id) values ('nemanja', 'brzak', 'spori', '2222', 'Barman',  '10.10.1995.', 42, 38, 21);
insert into chef(name, lastname, username, password, type, date_of_birth, dress_size, shoe_size, id) values ('nemanja', 'brzak', 'srednji', '2222', 'Chef',  '10.10.1995.', 42, 38, 22);




insert into restaurant(id, name, description, street, city, kitchen_type) values (20, 'Sabbiadoro', 'Neki opis za neki restoran', 'Janka Veselinovica 2', 'Novi Sad', 'Paste');
insert into manager_restaurant(name, lastname, username, password, email, type, id, restaurant_id) values ('djudja', 'cukurov', 'djudja', 'djuli', 'kaca.cukurov@gmail.com', 'RestaurantManager', 18, 20);
insert into waiter(name, lastname, username, password, type, date_of_birth, dress_size, shoe_size, id, restaurant_id) values ('nemanja', 'brzak', 'brzi', '2222', 'Waiter',  '10.10.1995.', 42, 38, 17, 20);
insert into waiter(name, lastname, username, password, type, date_of_birth, dress_size, shoe_size, id, restaurant_id) values ('nemanja', 'brzak', 'brzak', '2222', 'Waiter',  '10.10.1995.', 42, 38, 19, 20);
insert into chef(name, lastname, username, password, type, date_of_birth, dress_size, shoe_size, id, restaurant_id) values ('nemanja', 'brzak', 'onako', '2222', 'Chef',  '10.10.1995.', 42, 38, 23, 20);

insert into restaurant_table(id,name,chair_number,left,top,rotation,segment,restaurant_id) values (1,'P1',4,0,0,0,'InsideSmoking', 20);
insert into restaurant_table(id,name,chair_number,left,top,rotation,segment,restaurant_id) values (2,'P2',4,0,0,0,'InsideSmoking', 20);
insert into restaurant_table(id,name,chair_number,left,top,rotation,segment,restaurant_id) values (3,'B1',4,0,0,0,'GardenSmoking', 20);
insert into restaurant_table(id,name,chair_number,left,top,rotation,segment,restaurant_id) values (4,'B2',4,0,0,0,'GardenSmoking', 20);
insert into restaurant_table(id,name,chair_number,left,top,rotation,segment,restaurant_id) values (5,'PN1',4,0,0,0,'InsideNoSmoking', 20);
insert into restaurant_table(id,name,chair_number,left,top,rotation,segment,restaurant_id) values (6,'PN2',4,0,0,0,'InsideNoSmoking', 20);
insert into restaurant_table(id,name,chair_number,left,top,rotation,segment,restaurant_id) values (7,'BN1',4,0,0,0,'GardenNoSmoking', 20);


insert into dish(id,name,description,price,restaurant_id) values (1,'Pizza','Mala pizza(30cm)',150,20);
insert into dish(id,name,description,price,restaurant_id) values (2,'Pizza','Srednja pizza(40cm)',250,20);
insert into drink(id,name,description,price,restaurant_id) values(3,'Pivo','0.5l',150,20);

insert into table_segment(id, waiter_id, segment) values (1,17,'InsideSmoking');
insert into table_segment(id, waiter_id, segment) values (2,17,'GardenSmoking');
insert into table_segment(id, waiter_id, segment) values (3,19,'GardenSmoking');
insert into table_segment(id, waiter_id, segment) values (4,19,'InsideNoSmoking');
insert into table_segment(id, waiter_id, segment) values (5,19,'GardenNoSmoking');
insert into day_schedule(id, employee_id, day, start,end) values (1,17, '15/6/2017','7:30','15:30');