INSERT INTO Staff VALUES ('89001', 'Medical Robot', 'M', TO_DATE('04/19/1989', 'MM/DD/YYYY'), TO_DATE('06/21/2019', 'MM/DD/YYYY'), 'OP000');

INSERT INTO Staff VALUES ('93001', 'Musical Robert', 'M', TO_DATE('01/29/1993', 'MM/DD/YYYY'), TO_DATE('08/29/2018', 'MM/DD/YYYY'), 'ER000');

INSERT INTO Staff VALUES ('67001', 'Muscular Rob', 'M', TO_DATE('12/09/1967', 'MM/DD/YYYY'), TO_DATE('10/12/1983', 'MM/DD/YYYY'), 'GP000');

INSERT INTO Staff VALUES ('88001', 'Mechanical Roboto', 'M', TO_DATE('05/18/1988', 'MM/DD/YYYY'), TO_DATE('06/21/2019', 'MM/DD/YYYY'), 'GP000');

INSERT INTO Staff VALUES ('93003', 'Millennium Roberten', 'M', TO_DATE('06/28/1991', 'MM/DD/YYYY'), TO_DATE('09/20/2018', 'MM/DD/YYYY'), 'GP001');

INSERT INTO Staff VALUES ('66001', 'Missionary Robinson', 'M', TO_DATE('07/08/1966', 'MM/DD/YYYY'), TO_DATE('10/01/1993', 'MM/DD/YYYY'), 'ER000');

INSERT INTO Staff VALUES ('89002', 'Miscellaneous Robotor', 'NM', TO_DATE('04/19/1989', 'MM/DD/YYYY'), TO_DATE('08/19/2014', 'MM/DD/YYYY'), 'SE000');

INSERT INTO Staff VALUES ('93002', 'Musician Root', 'NM', TO_DATE('01/29/1993', 'MM/DD/YYYY'), TO_DATE('10/18/2017', 'MM/DD/YYYY'), 'SE000');

INSERT INTO Staff VALUES ('67002', 'Massaging Robin', 'M', TO_DATE('07/08/1966', 'MM/DD/YYYY'), TO_DATE('12/10/1990', 'MM/DD/YYYY'), 'ER000');

INSERT INTO Service_department VALUES ('ER000', 'Emergency Room', '93001');

INSERT INTO Service_department VALUES ('GP000', 'General practice department', '67001');

INSERT INTO Service_department VALUES ('GP001', 'General practice department', '93001');

INSERT INTO Service_department VALUES ('OP000', 'Optometry', '89001');

INSERT INTO Service_department VALUES ('SE000', 'Security', '89002');

#INSERT INTO Medical_Staff VALUES ('89001'), ('93001'), ('67001'), ('88001'), ('93001'), ('66001'), ('67002');

#INSERT INTO Non_Medical_Staff VALUES ('89002'), ('93002');

ALTER TABLE Staff ADD FOREIGN KEY (primary_dept) REFERENCES Service_department(code) ON DELETE SET NULL;


