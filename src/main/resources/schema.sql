create table if not exists Ingredient (
  id varchar(20) not null,
  name varchar(25) not null,
  type varchar(10) not null
);

create table if not exists Taco (
  id identity,
  name varchar(50) not null,
  taco_order bigint not null ,
  taco_order_key bigint not null ,
  created_at timestamp not null
);


create table if not exists Taco_Order (
	id identity,
	delivery_name varchar(50) not null,
	delivery_street varchar(50) not null,
	delivery_city varchar(50) not null,
	delivery_state varchar(20) not null,
	delivery_zip varchar(20) not null,
	cc_number varchar(16) not null,
	cc_expiration varchar(20) not null,
	cc_cvv varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists Ingredient_Ref (
    ingredient varchar(20) not null,
    taco bigint not null,
    taco_key bigint not null
);

alter table taco add foreign key (taco_order) references Taco_Order(id);






