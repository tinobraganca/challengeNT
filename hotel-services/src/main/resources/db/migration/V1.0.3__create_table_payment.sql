CREATE TABLE IF NOT EXISTS "NT_HOTEL_DB"."PAYMENT"
(
    "ID_PAYMENT" serial NOT NULL,
    "NAME"       varchar(50) NOT NULL,
    "TYPE"       varchar(50) NOT NULL,
    "STATUS"     varchar(50) NOT NULL,
    "CREATED_AT"      timestamp NOT NULL,
    "UPDATED_AT"      timestamp NULL,
    CONSTRAINT "PK_PAYMENT_1" PRIMARY KEY ( "ID_PAYMENT" )
);





