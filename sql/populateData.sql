INSERT INTO Severity VALUES (1, '1,2,3,4,5,6,7,8,9,10');
INSERT INTO Severity VALUES (2, 'Normal,Severe');
INSERT INTO Severity VALUES (3, 'Low,High');
INSERT INTO Severity VALUES (4, 'Normal,Premium');
INSERT INTO Severity VALUES (5, 'Moderate,Heavy');

INSERT INTO Body_Parts VALUES ('ARM000','Left Arm');
INSERT INTO Body_Parts VALUES ('ARM001','Right Arm');
INSERT INTO Body_Parts VALUES ('ABD000','Abdominal');
INSERT INTO Body_Parts VALUES ('EYE000','EYE');
INSERT INTO Body_Parts VALUES ('HRT000','Heart');
INSERT INTO Body_Parts VALUES ('CST000','Chest');
INSERT INTO Body_Parts VALUES ('HED000','Head');
INSERT INTO Body_Parts VALUES ('OTH000','Nothing Specific');

INSERT INTO Symptoms VALUES ('SYM001', 'OTH000', 'Pain', 1);
INSERT INTO Symptoms VALUES ('SYM002', 'ABD000', 'Diarrhea', 2);
INSERT INTO Symptoms VALUES ('SYM003', 'OTH000', 'Fever', 3);
INSERT INTO Symptoms VALUES ('SYM004', 'OTH000', 'Physical Exam', 4);
INSERT INTO Symptoms VALUES ('SYM005', 'HED000', 'Lightheadedness', 2);
INSERT INTO Symptoms VALUES ('SYM006', 'EYE000', 'Blurred vision', 2);
INSERT INTO Symptoms VALUES ('SYM007', 'OTH000', 'Bleeding', 5);
INSERT INTO Symptoms VALUES ('SYM000', 'OTH000', 'Other', 5);
INSERT INTO Symptoms VALUES ('SYM008', 'HRT000', 'Break Up', 1);


INSERT INTO Medical_Facility (f_id, name, capacity, classification, numb, street, city, state, country) VALUES (1000, 'Wolf Hospital', 300, '03', 2650, 'Wolf Village Way Box 7220', 'Raleigh', 'NC 27695', 'USA');
INSERT INTO Medical_Facility (f_id, name, capacity, classification, numb, street, city, state, country) VALUES (1001, 'California Health Care', 150, '02', 2500, 'Sacramento Santa', 'Santa Cruz', 'CA 90021', 'USA');
INSERT INTO Medical_Facility (f_id, name, capacity, classification, numb, street, city, state, country) VALUES (1002, 'Suny Medical Center', 10, '01', 489, 'First Avenue', 'New York', 'New York 10001', 'USA');

INSERT INTO Certifications VALUES ('CSC', 'Comprehensive Stroke Certification', STR_TO_DATE('11/12/1990', '%m/%d/%Y'), STR_TO_DATE('11/11/2025', '%m/%d/%Y'));
INSERT INTO Certifications VALUES ('ISO', 'ISO Certification', STR_TO_DATE('05/09/2011', '%m/%d/%Y'), STR_TO_DATE('02/08/2024', '%m/%d/%Y'));
INSERT INTO Certifications VALUES ('PSC', 'Primary Stroke Certification', STR_TO_DATE('01/01/2018', '%m/%d/%Y'), STR_TO_DATE('12/31/2028', '%m/%d/%Y'));

INSERT INTO Facility_Certified (f_id, acronym) VALUES (1000, 'CSC');
INSERT INTO Facility_Certified (f_id, acronym) VALUES (1001, 'ISO');
INSERT INTO Facility_Certified (f_id, acronym) VALUES (1002, 'ISO');
INSERT INTO Facility_Certified (f_id, acronym) VALUES (1002, 'PSC');

INSERT INTO Medical_Staff VALUES ('89001');
INSERT INTO Medical_Staff VALUES ('93001');
INSERT INTO Medical_Staff VALUES ('67001');
INSERT INTO Medical_Staff VALUES ('88001');
INSERT INTO Medical_Staff VALUES ('91001');
INSERT INTO Medical_Staff VALUES ('66001');
INSERT INTO Medical_Staff VALUES ('67002');

INSERT INTO Non_Medical_Staff VALUES ('89002');
INSERT INTO Non_Medical_Staff VALUES ('93002');

INSERT INTO Facility_has_Dept VALUES (1000,'GP000');
INSERT INTO Facility_has_Dept VALUES (1000,'OP000');
INSERT INTO Facility_has_Dept VALUES (1000,'SE000');
INSERT INTO Facility_has_Dept VALUES (1001,'ER000');
INSERT INTO Facility_has_Dept VALUES (1001,'GP001');
INSERT INTO Facility_has_Dept VALUES (1002,'ER001');

INSERT INTO Secondary_Works_Dept VALUES ('88001', 'OP000');

INSERT INTO Equipments VALUES ('ER combo rack');
INSERT INTO Equipments VALUES ('Blood pressure monitor');
INSERT INTO Equipments VALUES ('thermometer');
INSERT INTO Equipments VALUES ('Vision Screener');

INSERT INTO Services VALUES ('SER01', 'Emergency');
INSERT INTO Services VALUES ('SGP01', 'General Practice');
INSERT INTO Services VALUES ('VIS01', 'Vision');
INSERT INTO Services VALUES ('OTH00', 'Other');

INSERT INTO Dept_Provides_Service VALUES ('ER000', 'SER01');
INSERT INTO Dept_Provides_Service VALUES ('GP000', 'SGP01');
INSERT INTO Dept_Provides_Service VALUES ('GP001', 'SGP01');
INSERT INTO Dept_Provides_Service VALUES ('OP000', 'VIS01');

INSERT INTO Performed_using VALUES ('SER01', 'ER combo rack');
INSERT INTO Performed_using VALUES ('SGP01', 'Blood pressure monitor');
INSERT INTO Performed_using VALUES ('SGP01', 'thermometer');
INSERT INTO Performed_using VALUES ('VIS01', 'Vision Screener');

INSERT INTO Rule_Priority (priority) VALUES ('HIGH');
INSERT INTO Assessment_Rules VALUES (1, 'SYM001', 'CST000', '>', '7');
INSERT INTO Assessment_Rules VALUES (1, 'SYM003', 'OTH000', '=', 'High');

INSERT INTO Rule_Priority (priority) VALUES ('HIGH');
INSERT INTO Assessment_Rules VALUES (2, 'SYM001', 'HED000', '>', '7');
INSERT INTO Assessment_Rules VALUES (2, 'SYM006', 'EYE000', '=', 'Normal');
INSERT INTO Assessment_Rules VALUES (2, 'SYM005', 'HED000', '=', 'Normal');

INSERT INTO Rule_Priority (priority) VALUES ('NORMAL');
INSERT INTO Assessment_Rules VALUES (3, 'SYM001', 'HED000', '<=', '7');
INSERT INTO Assessment_Rules VALUES (3, 'SYM006', 'EYE000', '=', 'Normal');

INSERT INTO Specialized_For VALUES ('EYE000', 'OP000');

INSERT INTO Patient (fname, lname, dob, phone_no, numb, street, city, state, country) VALUES ('John','Smith', STR_TO_DATE('01/01/1990', '%m/%d/%Y'), '9007004567', '100', 'Avent Ferry Rd', 'Raleigh', 'North Carolina', 'US');

INSERT INTO Patient (fname, lname, dob, phone_no, numb, street, city, state, country) VALUES ('Jane','Doe', STR_TO_DATE('02/29/2000', '%m/%d/%Y'), '9192453245', '1016', 'Lexington Rd', 'New York', 'New York', 'US');

INSERT INTO Patient (fname, lname, dob, phone_no, numb, street, city, state, country) VALUES ('Rock','Star', STR_TO_DATE('08/31/1970', '%m/%d/%Y'), '5403127893', '1022', 'Amphitheatre Parkway', 'Mountain View','California', 'US');

INSERT INTO Patient (fname, lname, dob, phone_no, numb, street, city, state, country) VALUES ('Sheldon','Cooper', STR_TO_DATE('05/26/1984', '%m/%d/%Y'), '6184628437', '1210', 'Sacramento', 'Santa Cruz', 'California', 'US');

INSERT INTO Reasons VALUES (1, 'Service unavailable at the time of Visit');
INSERT INTO Reasons VALUES (2, 'Service not present at the facility');
INSERT INTO Reasons VALUES (3, 'Non-Payment');


INSERT INTO Checks_In VALUES (1, 1, 1000, 98, 98, 98, STR_TO_DATE('01/01/2019', '%m/%d/%Y'), STR_TO_DATE('01/05/2019', '%m/%d/%Y'), STR_TO_DATE('01/10/2019', '%m/%d/%Y'), STR_TO_DATE('01/20/2019', '%m/%d/%Y'), 'High', 'Referred', 'true', 'fvvwebvv', 'flnbkbkbu', 1, 'yes', 'Fsvdabvdjsnbjkdsb dskj'); 
INSERT INTO Checks_In VALUES (2, 2, 1000, 98, 98, 98, STR_TO_DATE('01/01/2019', '%m/%d/%Y'), STR_TO_DATE('01/05/2019', '%m/%d/%Y'), STR_TO_DATE('01/10/2019', '%m/%d/%Y'), STR_TO_DATE('01/20/2019', '%m/%d/%Y'), 'Normal', 'Referred', 'true', 'fvvwebvv', 'flnbkbkbu', 2, 'yes', 'Fsvdabvdjsnbjkdsb dskj');
INSERT INTO Checks_In VALUES (3, 4, 1000, 98, 98, 98, STR_TO_DATE('01/01/2019', '%m/%d/%Y'), STR_TO_DATE('01/05/2019', '%m/%d/%Y'), STR_TO_DATE('01/10/2019', '%m/%d/%Y'), STR_TO_DATE('01/20/2019', '%m/%d/%Y'), 'Normal', 'Referred', 'true', 'fvvwebvv', 'flnbkbkbu', 2, 'yes', 'Fsvdabvdjsnbjkdsb dskj');
INSERT INTO Checks_In VALUES (4, 3, 1001, 98, 98, 98, STR_TO_DATE('01/01/2019', '%m/%d/%Y'), STR_TO_DATE('01/05/2019', '%m/%d/%Y'), STR_TO_DATE('01/10/2019', '%m/%d/%Y'), STR_TO_DATE('01/20/2019', '%m/%d/%Y'), 'Quarantine', 'Referred', 'true', 'fvvwebvv', 'flnbkbkbu', 0, 'yes', 'Fsvdabvdjsnbjkdsb dskj');
INSERT INTO Checks_In VALUES (5, 4, 1001, 98, 98, 98, STR_TO_DATE('01/01/2019', '%m/%d/%Y'), STR_TO_DATE('01/05/2019', '%m/%d/%Y'), STR_TO_DATE('01/10/2019', '%m/%d/%Y'), STR_TO_DATE('01/20/2019', '%m/%d/%Y'), 'Normal', 'Treated Successfully', 'true', 'fvvwebvv', 'flnbkbkbu', 1, 'yes', 'Fsvdabvdjsnbjkdsb dskj');

INSERT INTO Affected_Info VALUES (4, 'SYM008', 'HRT000', '3456.346098376', 'true', 'gagaghaga', 'agasgsagha', '8');
INSERT INTO Affected_Info VALUES (2, 'SYM007', 'CST000', '3456.346098376', 'true', 'gagaghaga', 'agasgsagha', 'Heavy');

INSERT INTO Referred_to VALUES (1002, 1, '89001');
INSERT INTO Referred_to VALUES (1002, 2, '89001'); 
INSERT INTO Referred_to VALUES (1000, 4, '89001'); 
INSERT INTO Referred_to VALUES (1001, 3, '89001');