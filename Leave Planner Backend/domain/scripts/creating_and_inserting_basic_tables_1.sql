create table Class_details(
	class_id varchar(10),
	tutor_name varchar(25),
	tutor_email varchar(25),
	PRIMARY KEY(class_id)
);

insert into Class_details values('17PW', 'Dr. Anandhi S', 'san.amcs@psgtech.ac.in');


create table Course(
	course_id varchar(15),
	course_name varchar(50),
	faculty_name varchar(50),
	PRIMARY KEY(course_id)
);


insert into Course values('15XW81', 'Data Mining', 'Dr. Shina Sheen');
insert into Course values('15XW82', 'Soft Computing', 'Dr. Geetha N');
insert into Course values('15XW83', 'Software Project Management', 'Dr. Mohanraj N');
insert into Course values('15XW86', 'Data Mining Lab', 'Dr. Kirubavathi G');
insert into Course values('15XW87', 'Soft Computing Lab', 'Dr. Poonthalir G');
insert into Course values('15XW88', 'Case Study Lab', 'Dr. Mohan K');
insert into Course values('17PWSEM', 'Seminar', 'Dr. Kirubavathi G');
insert into Course values('17PWTWM', 'Tutor Ward Meeting', 'Dr. Anandhi S');
insert into Course values('15O5O4AK', 'Data Visualization', 'Dr. Kaja Mohideen A');
insert into Course values('15XWAF', 'Cloud Computing', 'Ms. Sivakami K');
insert into Course values('15XT82', 'Parallel and Distributed Computing', 'Dr. Suresh Balusamy');
insert into Course values('15XWO7', 'Cryptography', 'Dr. Thanalakshmi P');

