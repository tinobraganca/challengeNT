CREATE TABLE IF NOT EXISTS "NT_HOTEL_DB"."USER"
(
    "ID_USER"    serial NOT NULL,
    "FIRST_NAME" varchar(255) NOT NULL,
    "LAST_NAME"  varchar(255) NOT NULL,
    "PHONE"      varchar(50) NOT NULL,
    "EMAIL"      varchar(100) NOT NULL,
    "CREATED_AT"      timestamp NOT NULL,
    CONSTRAINT "PK_USER_1" PRIMARY KEY ( "ID_USER" )
);








