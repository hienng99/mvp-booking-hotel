create database mhb;

create table hotels
(
id          int auto_increment
primary key,
name        varchar(100)                       not null,
address     varchar(255)                       not null,
phone       varchar(20)                        not null,
create_time datetime default CURRENT_TIMESTAMP not null,
update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
constraint address
unique (address),
constraint name
unique (name),
constraint name_2
unique (name)
);

create table bookings
(
id            int auto_increment
primary key,
customer_id   int                                not null,
hotel_id      int                                not null,
checkin_time  datetime                           not null,
checkout_time datetime                           not null,
total_amount  double                             not null,
create_time   datetime default CURRENT_TIMESTAMP not null,
update_time   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
constraint bookings_ibfk_1
foreign key (hotel_id) references hotels (id)
);

create index hotel_id
on bookings (hotel_id);

create index name_index
on hotels (name);

//insert
INSERT INTO mhb.hotels (id, name, address, phone, create_time, update_time) VALUES (1, 'Khách sạn Sheraton Sài Gòn', '88 Đ. Đồng Khởi, Bến Nghé, Quận 1, Thành phố Hồ Chí Minh', '028 3827 2828', '2024-06-05 21:11:33', '2024-06-05 21:11:33');
INSERT INTO mhb.hotels (id, name, address, phone, create_time, update_time) VALUES (2, 'Mai Phương (Ruby) Hotel', '213 Đồng Đen, Phường 11, Tân Bình, Thành phố Hồ Chí Minh ', '0819 638 789', '2024-06-05 22:39:58', '2024-06-05 22:39:58');
INSERT INTO mhb.hotels (id, name, address, phone, create_time, update_time) VALUES (3, 'Van An Hotel', '323 Đ. Hồng Bàng, Street, Quận 5, Thành phố Hồ Chí Minh', '028 3853 9448', '2024-06-05 22:50:42', '2024-06-05 22:50:42');
INSERT INTO mhb.hotels (id, name, address, phone, create_time, update_time) VALUES (4, 'Empress Dalat - Sky View Hotel', '50 Phan Như Thạch, Phường 1, Thành phố Đà Lạt, Lâm Đồng', '0263 3585', '2024-06-05 22:53:11', '2024-06-05 22:53:11');
INSERT INTO mhb.hotels (id, name, address, phone, create_time, update_time) VALUES (5, 'HANZ T68 Hotel Go Vap', 'Gò Vấp, Thành phố Hồ Chí Minh', '0385 027 777', '2024-06-08 17:13:53', '2024-06-08 17:13:53');
INSERT INTO mhb.hotels (id, name, address, phone, create_time, update_time) VALUES (6, 'Cozrum Homes - Sonata Residence', 'Thành phố Hồ Chí Minh', '0967 736 774', '2024-06-08 17:21:15', '2024-06-08 17:21:15');

INSERT INTO mhb.bookings (id, customer_id, hotel_id, checkin_time, checkout_time, total_amount, create_time, update_time) VALUES (1, 1, 2, '2024-06-05 02:22:47', '2024-06-05 02:22:47', 20, '2024-06-07 00:57:53', '2024-06-07 00:57:53');
INSERT INTO mhb.bookings (id, customer_id, hotel_id, checkin_time, checkout_time, total_amount, create_time, update_time) VALUES (2, 1, 2, '2024-06-05 02:22:47', '2024-06-05 02:22:47', 20, '2024-06-07 01:44:00', '2024-06-07 01:44:00');
INSERT INTO mhb.bookings (id, customer_id, hotel_id, checkin_time, checkout_time, total_amount, create_time, update_time) VALUES (3, 1, 5, '2024-06-05 02:22:49', '2024-06-05 02:22:57', 100, '2024-06-08 21:55:51', '2024-06-08 21:55:51');
INSERT INTO mhb.bookings (id, customer_id, hotel_id, checkin_time, checkout_time, total_amount, create_time, update_time) VALUES (4, 3, 6, '2024-06-05 02:22:49', '2024-06-05 02:39:37', 100, '2024-06-08 21:57:51', '2024-06-08 21:57:51');
INSERT INTO mhb.bookings (id, customer_id, hotel_id, checkin_time, checkout_time, total_amount, create_time, update_time) VALUES (5, 4, 6, '2024-06-05 02:22:49', '2024-06-05 02:39:37', 100, '2024-06-08 22:04:37', '2024-06-08 22:04:37');

