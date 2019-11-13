INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('89001', 'Medical', 'Robot', 'M', TO_DATE('04/19/1989', 'MM/DD/YYYY'), TO_DATE('06/21/2019', 'MM/DD/YYYY'), 'OP000', 'Scoth Plains');

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('93001', 'Musical', 'Robert', 'M', TO_DATE('01/29/1993', 'MM/DD/YYYY'), TO_DATE('08/29/2018', 'MM/DD/YYYY'), 'ER000', 'Blacksburg');

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('67001', 'Muscular', 'Rob', 'M', TO_DATE('12/09/1967', 'MM/DD/YYYY'), TO_DATE('10/12/1983', 'MM/DD/YYYY'), 'GP000', 'Derry');

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('88001', 'Mechanical','Roboto', 'M', TO_DATE('05/18/1988', 'MM/DD/YYYY'), TO_DATE('06/21/2019', 'MM/DD/YYYY'), 'GP000', 'Lutherville Timonium');

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('91001', 'Millennium', 'Roberten', 'M', TO_DATE('06/28/1991', 'MM/DD/YYYY'), TO_DATE('09/20/2018', 'MM/DD/YYYY'), 'GP001', 'Teaneck' );

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('66001', 'Missionary', 'Robinson', 'M', TO_DATE('07/08/1966', 'MM/DD/YYYY'), TO_DATE('10/01/1993', 'MM/DD/YYYY'), 'ER000', 'Branford');

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('89002', 'Miscellaneous', 'Robotor', 'NM', TO_DATE('04/19/1989', 'MM/DD/YYYY'), TO_DATE('08/19/2014', 'MM/DD/YYYY'), 'SE000', 'Macon');

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('93002', 'Musician', 'Root', 'NM', TO_DATE('01/29/1993', 'MM/DD/YYYY'), TO_DATE('10/18/2017', 'MM/DD/YYYY'), 'SE000', 'Sunnyside');

INSERT INTO Staff (e_id, fname, lname, designation, dob, hire_date, primary_dept,  city) VALUES ('67002', 'Massaging', 'Robin', 'M', TO_DATE('07/08/1966', 'MM/DD/YYYY'), TO_DATE('12/10/1990', 'MM/DD/YYYY'), 'ER000', 'laurel');

INSERT INTO Service_department VALUES ('ER000', 'Emergency Room', '93001');

INSERT INTO Service_department VALUES ('ER001', 'Emergency Room', '67002');

INSERT INTO Service_department VALUES ('GP000', 'General practice department', '67001');

INSERT INTO Service_department VALUES ('GP001', 'General practice department', '91001');

INSERT INTO Service_department VALUES ('OP000', 'Optometry', '89001');

INSERT INTO Service_department VALUES ('SE000', 'Security', '89002');

ALTER TABLE Staff ADD FOREIGN KEY (primary_dept) REFERENCES Service_department(code) ON DELETE SET NULL;


