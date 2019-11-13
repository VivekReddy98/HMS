INSERT INTO Severity VALUES (1, '1-10');
INSERT INTO Severity VALUES (2, 'Normal/Severe');
INSERT INTO Severity VALUES (3, 'Low/High');
INSERT INTO Severity VALUES (4, 'Normal/Premium');

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
INSERT INTO Symptoms VALUES ('SYM007', 'OTH000', 'Other', 1);

INSERT INTO Medical_Facility (f_id) VALUES ('III');
INSERT INTO Patient (lname, dob, city) VALUES ('Smith', TO_DATE('05/18/1988', 'MM/DD/YYYY'), 'Raleigh');
INSERT INTO Checks_In (p_id, f_id) VALUES (1, 'III');


