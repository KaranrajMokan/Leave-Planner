
create table Timetable_details(
	timetable_details_key SERIAL PRIMARY KEY,
	timetable_id varchar(10),
	day varchar(10),
	duration varchar(10),
	course_id varchar(15),
	FOREIGN KEY(timetable_id) REFERENCES Timetable(timetable_id),
	FOREIGN KEY(course_id) REFERENCES Course(course_id)
);

insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Monday', '9-10', '15XWAF');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Monday', '9-10', '15XT82');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Monday', '10-11', '15XW82');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Monday', '11-12', '15O5O4AK');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Monday', '11-12', '15XWO7');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Monday', '12-1', '15XW88');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Tuesday', '9-10', '15XW81');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Tuesday', '10-11', '15XWAF');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Tuesday', '10-11', '15XT82');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Tuesday', '11-12', '15XW82');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Tuesday', '12-1', '17PWTWM');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Wednesday', '9-10', '15O5O4AK');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Wednesday', '9-10', '15XWO7');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Wednesday', '10-11', '15XW81');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Wednesday', '11-12', '15XW83');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Wednesday', '2-3', '15XW86');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Thursday', '9-10', '15XW82');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Thursday', '10-11', '15XW81');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Thursday', '11-12', '15XW83');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Thursday', '12-1', '17PWSEM');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Friday', '9-10', '15XW83');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Friday', '10-11', '15O5O4AK');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Friday', '10-11', '15XWO7');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Friday', '11-12', '15XW87');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Friday', '12-1', '15XWAF');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Friday', '12-1', '15XT82');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Saturday', '9-10', '15XW83');
insert into Timetable_details(timetable_id, day, duration, course_id) values('17PW_T8', 'Saturday', '10-11', '15XWO7');

create table Course_details(
	course_details_key SERIAL PRIMARY KEY,
	rollno varchar(6),
	course_id varchar(15),
	FOREIGN KEY(rollno) REFERENCES Students_details(rollno),
	FOREIGN KEY(course_id) REFERENCES Course(course_id)
);

insert into Course_details(rollno,course_id) values ('17PW01','15XW81');
insert into Course_details(rollno,course_id) values ('17PW01','15XW82');
insert into Course_details(rollno,course_id) values ('17PW01','15XW83');
insert into Course_details(rollno,course_id) values ('17PW01','15XW86');
insert into Course_details(rollno,course_id) values ('17PW01','15XW87');
insert into Course_details(rollno,course_id) values ('17PW01','15XW88');
insert into Course_details(rollno,course_id) values ('17PW01','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW01','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW01','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW01','15XT82');

insert into Course_details(rollno,course_id) values ('17PW02','15XW81');
insert into Course_details(rollno,course_id) values ('17PW02','15XW82');
insert into Course_details(rollno,course_id) values ('17PW02','15XW83');
insert into Course_details(rollno,course_id) values ('17PW02','15XW86');
insert into Course_details(rollno,course_id) values ('17PW02','15XW87');
insert into Course_details(rollno,course_id) values ('17PW02','15XW88');
insert into Course_details(rollno,course_id) values ('17PW02','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW02','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW02','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW02','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW03','15XW81');
insert into Course_details(rollno,course_id) values ('17PW03','15XW82');
insert into Course_details(rollno,course_id) values ('17PW03','15XW83');
insert into Course_details(rollno,course_id) values ('17PW03','15XW86');
insert into Course_details(rollno,course_id) values ('17PW03','15XW87');
insert into Course_details(rollno,course_id) values ('17PW03','15XW88');
insert into Course_details(rollno,course_id) values ('17PW03','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW03','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW03','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW03','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW04','15XW81');
insert into Course_details(rollno,course_id) values ('17PW04','15XW82');
insert into Course_details(rollno,course_id) values ('17PW04','15XW83');
insert into Course_details(rollno,course_id) values ('17PW04','15XW86');
insert into Course_details(rollno,course_id) values ('17PW04','15XW87');
insert into Course_details(rollno,course_id) values ('17PW04','15XW88');
insert into Course_details(rollno,course_id) values ('17PW04','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW04','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW04','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW04','15XT82');

insert into Course_details(rollno,course_id) values ('17PW05','15XW81');
insert into Course_details(rollno,course_id) values ('17PW05','15XW82');
insert into Course_details(rollno,course_id) values ('17PW05','15XW83');
insert into Course_details(rollno,course_id) values ('17PW05','15XW86');
insert into Course_details(rollno,course_id) values ('17PW05','15XW87');
insert into Course_details(rollno,course_id) values ('17PW05','15XW88');
insert into Course_details(rollno,course_id) values ('17PW05','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW05','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW05','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW05','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW06','15XW81');
insert into Course_details(rollno,course_id) values ('17PW06','15XW82');
insert into Course_details(rollno,course_id) values ('17PW06','15XW83');
insert into Course_details(rollno,course_id) values ('17PW06','15XW86');
insert into Course_details(rollno,course_id) values ('17PW06','15XW87');
insert into Course_details(rollno,course_id) values ('17PW06','15XW88');
insert into Course_details(rollno,course_id) values ('17PW06','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW06','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW06','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW06','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW07','15XW81');
insert into Course_details(rollno,course_id) values ('17PW07','15XW82');
insert into Course_details(rollno,course_id) values ('17PW07','15XW83');
insert into Course_details(rollno,course_id) values ('17PW07','15XW86');
insert into Course_details(rollno,course_id) values ('17PW07','15XW87');
insert into Course_details(rollno,course_id) values ('17PW07','15XW88');
insert into Course_details(rollno,course_id) values ('17PW07','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW07','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW07','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW07','15XT82');

insert into Course_details(rollno,course_id) values ('17PW08','15XW81');
insert into Course_details(rollno,course_id) values ('17PW08','15XW82');
insert into Course_details(rollno,course_id) values ('17PW08','15XW83');
insert into Course_details(rollno,course_id) values ('17PW08','15XW86');
insert into Course_details(rollno,course_id) values ('17PW08','15XW87');
insert into Course_details(rollno,course_id) values ('17PW08','15XW88');
insert into Course_details(rollno,course_id) values ('17PW08','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW08','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW08','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW08','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW10','15XW81');
insert into Course_details(rollno,course_id) values ('17PW10','15XW82');
insert into Course_details(rollno,course_id) values ('17PW10','15XW83');
insert into Course_details(rollno,course_id) values ('17PW10','15XW86');
insert into Course_details(rollno,course_id) values ('17PW10','15XW87');
insert into Course_details(rollno,course_id) values ('17PW10','15XW88');
insert into Course_details(rollno,course_id) values ('17PW10','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW10','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW10','15XWO7');
insert into Course_details(rollno,course_id) values ('17PW10','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW11','15XW81');
insert into Course_details(rollno,course_id) values ('17PW11','15XW82');
insert into Course_details(rollno,course_id) values ('17PW11','15XW83');
insert into Course_details(rollno,course_id) values ('17PW11','15XW86');
insert into Course_details(rollno,course_id) values ('17PW11','15XW87');
insert into Course_details(rollno,course_id) values ('17PW11','15XW88');
insert into Course_details(rollno,course_id) values ('17PW11','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW11','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW11','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW11','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW12','15XW81');
insert into Course_details(rollno,course_id) values ('17PW12','15XW82');
insert into Course_details(rollno,course_id) values ('17PW12','15XW83');
insert into Course_details(rollno,course_id) values ('17PW12','15XW86');
insert into Course_details(rollno,course_id) values ('17PW12','15XW87');
insert into Course_details(rollno,course_id) values ('17PW12','15XW88');
insert into Course_details(rollno,course_id) values ('17PW12','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW12','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW12','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW12','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW13','15XW81');
insert into Course_details(rollno,course_id) values ('17PW13','15XW82');
insert into Course_details(rollno,course_id) values ('17PW13','15XW83');
insert into Course_details(rollno,course_id) values ('17PW13','15XW86');
insert into Course_details(rollno,course_id) values ('17PW13','15XW87');
insert into Course_details(rollno,course_id) values ('17PW13','15XW88');
insert into Course_details(rollno,course_id) values ('17PW13','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW13','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW13','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW13','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW14','15XW81');
insert into Course_details(rollno,course_id) values ('17PW14','15XW82');
insert into Course_details(rollno,course_id) values ('17PW14','15XW83');
insert into Course_details(rollno,course_id) values ('17PW14','15XW86');
insert into Course_details(rollno,course_id) values ('17PW14','15XW87');
insert into Course_details(rollno,course_id) values ('17PW14','15XW88');
insert into Course_details(rollno,course_id) values ('17PW14','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW14','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW14','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW14','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW15','15XW81');
insert into Course_details(rollno,course_id) values ('17PW15','15XW82');
insert into Course_details(rollno,course_id) values ('17PW15','15XW83');
insert into Course_details(rollno,course_id) values ('17PW15','15XW86');
insert into Course_details(rollno,course_id) values ('17PW15','15XW87');
insert into Course_details(rollno,course_id) values ('17PW15','15XW88');
insert into Course_details(rollno,course_id) values ('17PW15','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW15','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW15','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW15','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW16','15XW81');
insert into Course_details(rollno,course_id) values ('17PW16','15XW82');
insert into Course_details(rollno,course_id) values ('17PW16','15XW83');
insert into Course_details(rollno,course_id) values ('17PW16','15XW86');
insert into Course_details(rollno,course_id) values ('17PW16','15XW87');
insert into Course_details(rollno,course_id) values ('17PW16','15XW88');
insert into Course_details(rollno,course_id) values ('17PW16','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW16','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW16','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW16','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW17','15XW81');
insert into Course_details(rollno,course_id) values ('17PW17','15XW82');
insert into Course_details(rollno,course_id) values ('17PW17','15XW83');
insert into Course_details(rollno,course_id) values ('17PW17','15XW86');
insert into Course_details(rollno,course_id) values ('17PW17','15XW87');
insert into Course_details(rollno,course_id) values ('17PW17','15XW88');
insert into Course_details(rollno,course_id) values ('17PW17','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW17','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW17','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW17','15XT82');

insert into Course_details(rollno,course_id) values ('17PW18','15XW81');
insert into Course_details(rollno,course_id) values ('17PW18','15XW82');
insert into Course_details(rollno,course_id) values ('17PW18','15XW83');
insert into Course_details(rollno,course_id) values ('17PW18','15XW86');
insert into Course_details(rollno,course_id) values ('17PW18','15XW87');
insert into Course_details(rollno,course_id) values ('17PW18','15XW88');
insert into Course_details(rollno,course_id) values ('17PW18','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW18','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW18','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW18','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW19','15XW81');
insert into Course_details(rollno,course_id) values ('17PW19','15XW82');
insert into Course_details(rollno,course_id) values ('17PW19','15XW83');
insert into Course_details(rollno,course_id) values ('17PW19','15XW86');
insert into Course_details(rollno,course_id) values ('17PW19','15XW87');
insert into Course_details(rollno,course_id) values ('17PW19','15XW88');
insert into Course_details(rollno,course_id) values ('17PW19','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW19','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW19','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW19','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW20','15XW81');
insert into Course_details(rollno,course_id) values ('17PW20','15XW82');
insert into Course_details(rollno,course_id) values ('17PW20','15XW83');
insert into Course_details(rollno,course_id) values ('17PW20','15XW86');
insert into Course_details(rollno,course_id) values ('17PW20','15XW87');
insert into Course_details(rollno,course_id) values ('17PW20','15XW88');
insert into Course_details(rollno,course_id) values ('17PW20','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW20','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW20','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW20','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW21','15XW81');
insert into Course_details(rollno,course_id) values ('17PW21','15XW82');
insert into Course_details(rollno,course_id) values ('17PW21','15XW83');
insert into Course_details(rollno,course_id) values ('17PW21','15XW86');
insert into Course_details(rollno,course_id) values ('17PW21','15XW87');
insert into Course_details(rollno,course_id) values ('17PW21','15XW88');
insert into Course_details(rollno,course_id) values ('17PW21','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW21','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW21','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW21','15XT82');

insert into Course_details(rollno,course_id) values ('17PW22','15XW81');
insert into Course_details(rollno,course_id) values ('17PW22','15XW82');
insert into Course_details(rollno,course_id) values ('17PW22','15XW83');
insert into Course_details(rollno,course_id) values ('17PW22','15XW86');
insert into Course_details(rollno,course_id) values ('17PW22','15XW87');
insert into Course_details(rollno,course_id) values ('17PW22','15XW88');
insert into Course_details(rollno,course_id) values ('17PW22','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW22','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW22','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW22','15XT82');

insert into Course_details(rollno,course_id) values ('17PW23','15XW81');
insert into Course_details(rollno,course_id) values ('17PW23','15XW82');
insert into Course_details(rollno,course_id) values ('17PW23','15XW83');
insert into Course_details(rollno,course_id) values ('17PW23','15XW86');
insert into Course_details(rollno,course_id) values ('17PW23','15XW87');
insert into Course_details(rollno,course_id) values ('17PW23','15XW88');
insert into Course_details(rollno,course_id) values ('17PW23','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW23','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW23','15XWO7');
insert into Course_details(rollno,course_id) values ('17PW23','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW24','15XW81');
insert into Course_details(rollno,course_id) values ('17PW24','15XW82');
insert into Course_details(rollno,course_id) values ('17PW24','15XW83');
insert into Course_details(rollno,course_id) values ('17PW24','15XW86');
insert into Course_details(rollno,course_id) values ('17PW24','15XW87');
insert into Course_details(rollno,course_id) values ('17PW24','15XW88');
insert into Course_details(rollno,course_id) values ('17PW24','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW24','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW24','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW24','15XT82');

insert into Course_details(rollno,course_id) values ('17PW25','15XW81');
insert into Course_details(rollno,course_id) values ('17PW25','15XW82');
insert into Course_details(rollno,course_id) values ('17PW25','15XW83');
insert into Course_details(rollno,course_id) values ('17PW25','15XW86');
insert into Course_details(rollno,course_id) values ('17PW25','15XW87');
insert into Course_details(rollno,course_id) values ('17PW25','15XW88');
insert into Course_details(rollno,course_id) values ('17PW25','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW25','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW25','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW25','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW27','15XW81');
insert into Course_details(rollno,course_id) values ('17PW27','15XW82');
insert into Course_details(rollno,course_id) values ('17PW27','15XW83');
insert into Course_details(rollno,course_id) values ('17PW27','15XW86');
insert into Course_details(rollno,course_id) values ('17PW27','15XW87');
insert into Course_details(rollno,course_id) values ('17PW27','15XW88');
insert into Course_details(rollno,course_id) values ('17PW27','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW27','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW27','15XWO7');
insert into Course_details(rollno,course_id) values ('17PW27','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW28','15XW81');
insert into Course_details(rollno,course_id) values ('17PW28','15XW82');
insert into Course_details(rollno,course_id) values ('17PW28','15XW83');
insert into Course_details(rollno,course_id) values ('17PW28','15XW86');
insert into Course_details(rollno,course_id) values ('17PW28','15XW87');
insert into Course_details(rollno,course_id) values ('17PW28','15XW88');
insert into Course_details(rollno,course_id) values ('17PW28','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW28','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW28','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW28','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW29','15XW81');
insert into Course_details(rollno,course_id) values ('17PW29','15XW82');
insert into Course_details(rollno,course_id) values ('17PW29','15XW83');
insert into Course_details(rollno,course_id) values ('17PW29','15XW86');
insert into Course_details(rollno,course_id) values ('17PW29','15XW87');
insert into Course_details(rollno,course_id) values ('17PW29','15XW88');
insert into Course_details(rollno,course_id) values ('17PW29','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW29','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW29','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW29','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW30','15XW81');
insert into Course_details(rollno,course_id) values ('17PW30','15XW82');
insert into Course_details(rollno,course_id) values ('17PW30','15XW83');
insert into Course_details(rollno,course_id) values ('17PW30','15XW86');
insert into Course_details(rollno,course_id) values ('17PW30','15XW87');
insert into Course_details(rollno,course_id) values ('17PW30','15XW88');
insert into Course_details(rollno,course_id) values ('17PW30','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW30','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW30','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW30','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW31','15XW81');
insert into Course_details(rollno,course_id) values ('17PW31','15XW82');
insert into Course_details(rollno,course_id) values ('17PW31','15XW83');
insert into Course_details(rollno,course_id) values ('17PW31','15XW86');
insert into Course_details(rollno,course_id) values ('17PW31','15XW87');
insert into Course_details(rollno,course_id) values ('17PW31','15XW88');
insert into Course_details(rollno,course_id) values ('17PW31','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW31','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW31','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW31','15XT82');

insert into Course_details(rollno,course_id) values ('17PW32','15XW81');
insert into Course_details(rollno,course_id) values ('17PW32','15XW82');
insert into Course_details(rollno,course_id) values ('17PW32','15XW83');
insert into Course_details(rollno,course_id) values ('17PW32','15XW86');
insert into Course_details(rollno,course_id) values ('17PW32','15XW87');
insert into Course_details(rollno,course_id) values ('17PW32','15XW88');
insert into Course_details(rollno,course_id) values ('17PW32','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW32','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW32','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW32','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW33','15XW81');
insert into Course_details(rollno,course_id) values ('17PW33','15XW82');
insert into Course_details(rollno,course_id) values ('17PW33','15XW83');
insert into Course_details(rollno,course_id) values ('17PW33','15XW86');
insert into Course_details(rollno,course_id) values ('17PW33','15XW87');
insert into Course_details(rollno,course_id) values ('17PW33','15XW88');
insert into Course_details(rollno,course_id) values ('17PW33','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW33','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW33','15XWO7');
insert into Course_details(rollno,course_id) values ('17PW33','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW34','15XW81');
insert into Course_details(rollno,course_id) values ('17PW34','15XW82');
insert into Course_details(rollno,course_id) values ('17PW34','15XW83');
insert into Course_details(rollno,course_id) values ('17PW34','15XW86');
insert into Course_details(rollno,course_id) values ('17PW34','15XW87');
insert into Course_details(rollno,course_id) values ('17PW34','15XW88');
insert into Course_details(rollno,course_id) values ('17PW34','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW34','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW34','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW34','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW35','15XW81');
insert into Course_details(rollno,course_id) values ('17PW35','15XW82');
insert into Course_details(rollno,course_id) values ('17PW35','15XW83');
insert into Course_details(rollno,course_id) values ('17PW35','15XW86');
insert into Course_details(rollno,course_id) values ('17PW35','15XW87');
insert into Course_details(rollno,course_id) values ('17PW35','15XW88');
insert into Course_details(rollno,course_id) values ('17PW35','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW35','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW35','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW35','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW36','15XW81');
insert into Course_details(rollno,course_id) values ('17PW36','15XW82');
insert into Course_details(rollno,course_id) values ('17PW36','15XW83');
insert into Course_details(rollno,course_id) values ('17PW36','15XW86');
insert into Course_details(rollno,course_id) values ('17PW36','15XW87');
insert into Course_details(rollno,course_id) values ('17PW36','15XW88');
insert into Course_details(rollno,course_id) values ('17PW36','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW36','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW36','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW36','15XT82');

insert into Course_details(rollno,course_id) values ('17PW37','15XW81');
insert into Course_details(rollno,course_id) values ('17PW37','15XW82');
insert into Course_details(rollno,course_id) values ('17PW37','15XW83');
insert into Course_details(rollno,course_id) values ('17PW37','15XW86');
insert into Course_details(rollno,course_id) values ('17PW37','15XW87');
insert into Course_details(rollno,course_id) values ('17PW37','15XW88');
insert into Course_details(rollno,course_id) values ('17PW37','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW37','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW37','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW37','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW38','15XW81');
insert into Course_details(rollno,course_id) values ('17PW38','15XW82');
insert into Course_details(rollno,course_id) values ('17PW38','15XW83');
insert into Course_details(rollno,course_id) values ('17PW38','15XW86');
insert into Course_details(rollno,course_id) values ('17PW38','15XW87');
insert into Course_details(rollno,course_id) values ('17PW38','15XW88');
insert into Course_details(rollno,course_id) values ('17PW38','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW38','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW38','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW38','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW39','15XW81');
insert into Course_details(rollno,course_id) values ('17PW39','15XW82');
insert into Course_details(rollno,course_id) values ('17PW39','15XW83');
insert into Course_details(rollno,course_id) values ('17PW39','15XW86');
insert into Course_details(rollno,course_id) values ('17PW39','15XW87');
insert into Course_details(rollno,course_id) values ('17PW39','15XW88');
insert into Course_details(rollno,course_id) values ('17PW39','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW39','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW39','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW39','15XWAF');

insert into Course_details(rollno,course_id) values ('17PW40','15XW81');
insert into Course_details(rollno,course_id) values ('17PW40','15XW82');
insert into Course_details(rollno,course_id) values ('17PW40','15XW83');
insert into Course_details(rollno,course_id) values ('17PW40','15XW86');
insert into Course_details(rollno,course_id) values ('17PW40','15XW87');
insert into Course_details(rollno,course_id) values ('17PW40','15XW88');
insert into Course_details(rollno,course_id) values ('17PW40','17PWSEM');
insert into Course_details(rollno,course_id) values ('17PW40','17PWTWM');
insert into Course_details(rollno,course_id) values ('17PW40','15O5O4AK');
insert into Course_details(rollno,course_id) values ('17PW40','15XWAF');



create table Leave_details(
	leave_id varchar(25),
	rollno varchar(6),
	leave_start_date Date,
	leave_end_date Date,
	leave_duration int,
	leave_type varchar(25),
	PRIMARY KEY(leave_id),
	FOREIGN KEY(rollno) REFERENCES Students_details(rollno)
);


create table Login_details(
	login_details_key SERIAL PRIMARY KEY,
	rollno varchar(6),
	password varchar(30),
	FOREIGN KEY(rollno) REFERENCES Students_details(rollno)
);


insert into Login_details(rollno,password) values ('17PW01','17pw@psg');
insert into Login_details(rollno,password) values ('17PW02','17pw@psg');
insert into Login_details(rollno,password) values ('17PW03','17pw@psg');
insert into Login_details(rollno,password) values ('17PW04','17pw@psg');
insert into Login_details(rollno,password) values ('17PW05','17pw@psg');
insert into Login_details(rollno,password) values ('17PW06','17pw@psg');
insert into Login_details(rollno,password) values ('17PW07','17pw@psg');
insert into Login_details(rollno,password) values ('17PW08','17pw@psg');
insert into Login_details(rollno,password) values ('17PW10','17pw@psg');
insert into Login_details(rollno,password) values ('17PW11','17pw@psg');
insert into Login_details(rollno,password) values ('17PW12','17pw@psg');
insert into Login_details(rollno,password) values ('17PW13','17pw@psg');
insert into Login_details(rollno,password) values ('17PW14','17pw@psg');
insert into Login_details(rollno,password) values ('17PW15','17pw@psg');
insert into Login_details(rollno,password) values ('17PW16','17pw@psg');
insert into Login_details(rollno,password) values ('17PW17','17pw@psg');
insert into Login_details(rollno,password) values ('17PW18','17pw@psg');
insert into Login_details(rollno,password) values ('17PW19','17pw@psg');
insert into Login_details(rollno,password) values ('17PW20','17pw@psg');
insert into Login_details(rollno,password) values ('17PW21','17pw@psg');
insert into Login_details(rollno,password) values ('17PW22','17pw@psg');
insert into Login_details(rollno,password) values ('17PW23','17pw@psg');
insert into Login_details(rollno,password) values ('17PW24','17pw@psg');
insert into Login_details(rollno,password) values ('17PW25','17pw@psg');
insert into Login_details(rollno,password) values ('17PW27','17pw@psg');
insert into Login_details(rollno,password) values ('17PW28','17pw@psg');
insert into Login_details(rollno,password) values ('17PW29','17pw@psg');
insert into Login_details(rollno,password) values ('17PW30','17pw@psg');
insert into Login_details(rollno,password) values ('17PW31','17pw@psg');
insert into Login_details(rollno,password) values ('17PW32','17pw@psg');
insert into Login_details(rollno,password) values ('17PW33','17pw@psg');
insert into Login_details(rollno,password) values ('17PW34','17pw@psg');
insert into Login_details(rollno,password) values ('17PW35','17pw@psg');
insert into Login_details(rollno,password) values ('17PW36','17pw@psg');
insert into Login_details(rollno,password) values ('17PW37','17pw@psg');
insert into Login_details(rollno,password) values ('17PW38','17pw@psg');
insert into Login_details(rollno,password) values ('17PW39','17pw@psg');
insert into Login_details(rollno,password) values ('17PW40','17pw@psg');

