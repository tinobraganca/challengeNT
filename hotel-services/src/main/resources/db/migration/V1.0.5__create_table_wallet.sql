CREATE TABLE IF NOT EXISTS "NT_HOTEL_DB"."WALLET"
(
    "ID_WALLET" serial NOT NULL,
    "ID_USER"   bigint NOT NULL,
    "NAME"      varchar(50) NOT NULL,
    "TYPE"      varchar(50) NOT NULL,
    "CODE"      varchar(50) NOT NULL,
    "CREATED_AT"      timestamp NOT NULL,
    "UPDATED_AT"      timestamp NULL,
    CONSTRAINT "PK_WALLET_1" PRIMARY KEY ( "ID_WALLET" ),
    CONSTRAINT "FK_WALLET_1" FOREIGN KEY("ID_USER")  REFERENCES "NT_HOTEL_DB"."USER"("ID_USER")

);
CREATE INDEX "IDX_WALLET_1" ON "NT_HOTEL_DB"."WALLET"
(
    "ID_WALLET"
);

CREATE INDEX "IDX_WALLET_2" ON "NT_HOTEL_DB"."WALLET"
(
    "ID_USER"
);








