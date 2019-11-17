INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('89001', 'Medical', 'Robot', 'M', STR_TO_DATE('04/19/1989', '%m/%d/%Y'), STR_TO_DATE('06/21/2019', '%m/%d/%Y'), 'OP000', 'Scoth Plains');

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('93001', 'Musical', 'Robert', 'M', STR_TO_DATE('01/29/1993', '%m/%d/%Y'), STR_TO_DATE('08/29/2018', '%m/%d/%Y'), 'ER000', 'Blacksburg');

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('67001', 'Muscular', 'Rob', 'M', STR_TO_DATE('12/09/1967', '%m/%d/%Y'), STR_TO_DATE('10/12/1983', '%m/%d/%Y'), 'GP000', 'Derry');

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('88001', 'Mechanical','Roboto', 'M', STR_TO_DATE('05/18/1988', '%m/%d/%Y'), STR_TO_DATE('06/21/2019', '%m/%d/%Y'), 'GP000', 'Lutherville Timonium');

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('91001', 'Millennium', 'Roberten', 'M', STR_TO_DATE('06/28/1991', '%m/%d/%Y'), STR_TO_DATE('09/20/2018', '%m/%d/%Y'), 'GP001', 'Teaneck' );

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('66001', 'Missionary', 'Robinson', 'M', STR_TO_DATE('07/08/1966', '%m/%d/%Y'), STR_TO_DATE('10/01/1993', '%m/%d/%Y'), 'ER000', 'Branford');

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('89002', 'Miscellaneous', 'Robotor', 'NM', STR_TO_DATE('04/19/1989', '%m/%d/%Y'), STR_TO_DATE('08/19/2014', '%m/%d/%Y'), 'SE000', 'Macon');

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('93002', 'Musician', 'Root', 'NM', STR_TO_DATE('01/29/1993', '%m/%d/%Y'), STR_TO_DATE('10/18/2017', '%m/%d/%Y'), 'SE000', 'Sunnyside');

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('67002', 'Massaging', 'Robin', 'M', STR_TO_DATE('07/08/1966', '%m/%d/%Y'), STR_TO_DATE('12/10/1990', '%m/%d/%Y'), 'ER000', 'laurel');

INSERT INTO Service_department VALUES ('ER000', 'Emergency Room', '93001');

INSERT INTO Service_department VALUES ('ER001', 'Emergency Room', '67002');

INSERT INTO Service_department VALUES ('GP000', 'General practice department', '67001');

INSERT INTO Service_department VALUES ('GP001', 'General practice department', '91001');

INSERT INTO Service_department VALUES ('OP000', 'Optometry', '89001');

INSERT INTO Service_department VALUES ('SE000', 'Security', '89002');

ALTER TABLE Staff ADD FOREIGN KEY (primary_dept) REFERENCES Service_department(code) ON DELETE CASCADE;


