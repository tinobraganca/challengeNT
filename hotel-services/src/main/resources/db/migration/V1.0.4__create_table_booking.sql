CREATE TABLE IF NOT EXISTS "NT_HOTEL_DB"."BOOKING"
(
    "ID_BOOKING"   serial NOT NULL,
    "ID_ROOM"      bigint NOT NULL,
    "ID_PAYMENT"   bigint NOT NULL,
    "ID_USER"      bigint NOT NULL,
    "NAME"         varchar(255) NOT NULL,
    "START_AT"     date NOT NULL,
    "END_AT"       date NOT NULL,
    "COUNT_PEOPLE" int NOT NULL,
    "STATUS"       varchar(50) NOT NULL,
    "CREATED_AT"      timestamp NOT NULL,
    "UPDATED_AT"      timestamp NULL,
    CONSTRAINT "PK_BOOKING_1" PRIMARY KEY ( "ID_BOOKING" ),
    CONSTRAINT "FK_BOOKING_1" FOREIGN KEY("ID_ROOM")  REFERENCES "NT_HOTEL_DB"."ROOM"("ID_ROOM"),
    CONSTRAINT "FK_BOOKING_2" FOREIGN KEY("ID_PAYMENT")  REFERENCES "NT_HOTEL_DB"."PAYMENT"("ID_PAYMENT"),
    CONSTRAINT "FK_BOOKING_3" FOREIGN KEY("ID_USER")  REFERENCES "NT_HOTEL_DB"."USER"("ID_USER")
);

CREATE INDEX "IDX_BOOKING_1" ON "NT_HOTEL_DB"."BOOKING"
(
    "ID_BOOKING"
);

CREATE INDEX "IDX_BOOKING_2" ON "NT_HOTEL_DB"."BOOKING"
(
    "ID_ROOM"
);

CREATE INDEX "IDX_BOOKING_3" ON "NT_HOTEL_DB"."BOOKING"
(
    "ID_PAYMENT"
);

CREATE INDEX "IDX_BOOKING_4" ON "NT_HOTEL_DB"."BOOKING"
(
    "ID_USER"
);



