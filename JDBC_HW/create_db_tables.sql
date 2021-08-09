

use JDBC;
create table Product (
id int primary key auto_increment,
title nvarchar(50),
cost nvarchar(50),
brand_id int
);

create table Brand(
id int primary key auto_increment,
brandName nvarchar(50)
);