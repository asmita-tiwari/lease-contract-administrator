create table customer
(
    customer_id     bigint       not null AUTO_INCREMENT,
    birth_date      date         not null,
    first_name      varchar(255) not null,
    last_name       varchar(255) not null,
    created_at      TIMESTAMP,
    last_updated_at TIMESTAMP,
    PRIMARY KEY (customer_id)
);

create table vehicle
(
    vehicle_id      bigint         not null AUTO_INCREMENT,
    brand           varchar(255)   not null,
    model           varchar(255)   not null,
    price           decimal(19, 2) not null,
    vin             varchar(255)   not null,
    year            integer        not null,
    created_at      TIMESTAMP,
    last_updated_at TIMESTAMP,
    primary key (vehicle_id)
);

create table leasing_contract
(
    contract_no         bigint         not null AUTO_INCREMENT,
    monthly_rate        decimal(19, 2) not null,
    customer_id_ref     bigint,
    vehicle_id_ref      bigint,
    created_at          TIMESTAMP,
    last_updated_at     TIMESTAMP,
    PRIMARY KEY (contract_no),
    constraint foreign key (customer_id_ref) references lease_contract_administrator.customer (customer_id),
    constraint foreign key (vehicle_id_ref) references lease_contract_administrator.vehicle (vehicle_id)
);


