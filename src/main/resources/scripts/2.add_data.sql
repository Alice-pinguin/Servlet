use homework;
INSERT INTO developers VALUES
(1, 'Zina Ivanovich', 26, 'women', 5600),
(2, 'Vasya Pupkin', 32, 'men', 1000),
(3, 'Petro Ignatov', 21, 'men',2500),
(4, 'Kira Plastinina', 22, 'women', 3600),
(5, 'Gus Batcovich', 25, 'men', 2300),
(6, 'Irina Chursec', 35, 'women', 4200),
(7, 'Leonid Sagaidak', 26, 'men', 2600),
(8, 'Igor Saltikov', 32, 'men', 3200),
(9, 'Arkadiy Gryg', 29, 'men', 4100),
(10, 'Vera Zinckevich', 25, 'women', 4300);

INSERT INTO skills VALUES
(1,'Java','Junior'),
(2,'Java','Middle'),
(3,'Java','Senior'),
(4,'C++','Junior'),
(5,'C++','Middle'),
(6,'C++','Senior'),
(7,'C#','Junior'),
(8,'C#','Middle'),
(9,'C#','Senior'),
(10,'JS','Junior'),
(11,'JS','Middle'),
(12,'JS','Senior');

INSERT INTO projects VALUES
(1,'Heroes', 'Gaming',15000, '2020-10-12'),
(2, 'Money Conventer','Bank',10000, '2020-11-13'),
(3, 'Weather Prognose', 'NewsChannel',10000, '2020-12-15'),
(4, 'The Clever TeaPot', 'home appliances',20000, '2021-02-15'),
(5, 'Customer', 'Machine learning',111000, '2021-03-12');

INSERT INTO companies VALUES
(1,'Intellias ','Odessa'),
(2,'Itransition','Kiyv'),
(3,'SoftServe','Dnipro'),
(4, 'N-iX', 'Lviv'),
(5, 'ELEKS', 'Ternopil');

INSERT INTO customers VALUES
(1,'IKEA','Lviv', 'Home furniture'),
(2,'Pumb','Kyiv', 'Bank'),
(3,'TechoCase','Dnipro', 'Game'),
(4,'Udemy','Dnipro', 'Education'),
(5,'CRM','Chernigiv', 'News');


INSERT INTO developers_skills VALUES
(1,12), (1,9), (2,4), (3,1), (3,6), (4, 10), (5,7),(6,5), (7,2), (8,11),(9,8), (10,10), (1,3);

INSERT INTO developers_projects VALUES
(1,2),(3,1),(5,1), (2,3),(7,2),(10,3),(8,3), (1,4),(7,4),(4,3), (9,5),(7,5),(1,5);

INSERT INTO companies_projects VALUES
(1,1),(2,2),(3,3),(4,4),(5,5);

INSERT INTO customers_projects VALUES
(1,4),(2,2),(3,1),(4,5),(5,3);