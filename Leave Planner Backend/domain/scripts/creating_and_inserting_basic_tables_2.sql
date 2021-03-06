create table Timetable(
	timetable_id varchar(10),
	class_id varchar(10),
	PRIMARY KEY(timetable_id),
	FOREIGN KEY(class_id) REFERENCES Class_details(class_id)
);

insert into Timetable values('17PW_T8', '17PW');

create table Students_details(
	rollno varchar(6),
	name varchar(25),
	email_id varchar(25),
	class_id varchar(10),
	PRIMARY KEY(rollno),
	FOREIGN KEY(class_id) REFERENCES Class_details(class_id)
);

insert into Students_details values ('17PW01', 'Aakash Hemadri', '17pw01@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW02', 'Aditya Sivasankar', '17pw02@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW03', 'Aishwarya A', '17pw03@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW04', 'Akilesh R', '17pw04@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW05', 'Apsara G', '17pw05@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW06', 'Aravind RM', '17pw06@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW07', 'Ashfaaq IIM', '17pw07@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW08', 'Dharaneedharan S', '17pw08@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW10', 'Ganga B', '17pw10@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW11', 'Grandhi Venkataniteesh', '17pw11@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW12', 'Hari Rahul R', '17pw12@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW13', 'Harisaipravin SV', '17pw13@psgtech.ac.in',  '17PW');
insert into Students_details values ('17PW14', 'Harish Kumar V', '17pw14@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW15', 'Harsha K', '17pw15@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW16', 'Ihjaas Thasbekha', '17pw16@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW17', 'Jeivardan V', '17pw17@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW18', 'Karanraj M', '17pw18@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW19', 'Kavitha J', '17pw19@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW20', 'Kowshika Rani A', '17pw20@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW21', 'Madhupprasad S', '17pw21@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW22', 'Medehal Pavan Achyut', '17pw22@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW23', 'Narayanaa SR', '17pw23@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW24', 'Nitesh Kumar S', '17pw24@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW25', 'Niveydhithaa R', '17pw25@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW27', 'Padmanabhan M', '17pw27@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW28', 'Prachi Dhingra', '17pw28@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW29', 'Rahul Leonard AK', '17pw29@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW30', 'Sai Charan Prasad T', '17pw30@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW31', 'Sai Swetha R', '17pw31@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW32', 'Sathwik Ch', '17pw32@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW33', 'Sivaranjan M', '17pw33@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW34', 'Soundarya S', '17pw34@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW35', 'Sreharine D', '17pw35@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW36', 'Sri Nandita S', '17pw36@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW37', 'Subasree N', '17pw37@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW38', 'Velkumaran AP', '17pw38@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW39', 'Vigneshwaran S', '17pw39@psgtech.ac.in', '17PW');
insert into Students_details values ('17PW40', 'Visrutha BV', '17pw40@psgtech.ac.in', '17PW');
