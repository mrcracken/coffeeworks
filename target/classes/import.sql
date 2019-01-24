-- Created by IBA Group 2018 (c)
-- Data from https://www.latteartguide.com/2016/01/different-types-of-coffee.html

-- Bean 1
insert into Beans (id, name) values (1, 'Arabica');
-- Bean 2
insert into Beans (id, name) values (2, 'Robusta');

-- Coffee 1
insert into Coffee (id, description, location, name, stars, beans_id) values (1, 'The espresso (aka “short black”) is the foundation and the most important part to every espresso based drink.', 'Italia', 'Espresso', 5, 1);
-- Coffee 2
insert into Coffee (id, description, location, name, stars, beans_id) values (2, 'A double espresso (aka “Doppio”) is just that, two espresso shots in one cup.', 'Italia', 'Double Espresso', 4, 1);
-- Coffee 3
insert into Coffee (id, description, location, name, stars, beans_id) values (3, 'A short macchiato is similar to an espresso but with a dollop of steamed milk and foam to mellow the harsh taste of an espresso. You will find that baristas in different countries make short macchiatos differently.', 'Italia', 'Short Macchiato', 4, 2);
-- Coffee 4
insert into Coffee (id, description, location, name, stars, beans_id) values (4, 'A long macchiato is the same as a short macchiato but with a double shot of espresso.', 'Italia', 'Long Macchiato', 5, 1);
-- Coffee 5
insert into Coffee (id, description, location, name, stars, beans_id) values (5, 'A ristretto is an espresso shot that is extracted with the same amount of coffee but half the amount of water. ', 'Italia', 'Ristretto', 3, 1);
