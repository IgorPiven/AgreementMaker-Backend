create table discounts (
    id bigserial primary key,
    discount varchar(15),
    descr_ru varchar(255),
    descr_en varchar(255)
);

insert into discounts (discount, descr_ru, descr_en)
values
('D1', 'скидка D1 в размере 10% предоставляется на заказы','the discount D1 in the amount of 10% is granted to the POs'),
('D2','скидка D2 в размере 20% предоставляется на заказы','the discount D2 in the amount of 20% is granted to the POs'),
('D3', 'скидка D3 в размере 30% предоставляется на заказы','the discount D3 in the amount of 30% is granted to the POs');
