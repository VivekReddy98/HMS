CREATE SEQUENCE med_facility_id_seq >
CREATE OR REPLACE TRIGGER med_facility_insert
	BEFORE INSERT ON Medical_Facility
	FOR EACH ROW
BEGIN
	SELECT med_facility_id_seq.nextval
	INTO :new.f_id
	FROM dual;
END; >

CREATE SEQUENCE patient_id_seq >
CREATE OR REPLACE TRIGGER patient_insert
	BEFORE INSERT ON Patient
	FOR EACH ROW
BEGIN
	SELECT patient_id_seq.nextval
	INTO :new.p_id
	FROM dual;
END; >

CREATE SEQUENCE visit_id_seq >
CREATE OR REPLACE TRIGGER visit_insert
	BEFORE INSERT ON Checks_In
	FOR EACH ROW
BEGIN
	SELECT visit_id_seq.nextval
	INTO :new.v_id
	FROM dual;
END; >

CREATE SEQUENCE staff_id_seq >
CREATE OR REPLACE TRIGGER staff_insert
	BEFORE INSERT ON Staff
	FOR EACH ROW
BEGIN
	SELECT staff_id_seq.nextval
	INTO :new.e_id
	FROM dual;
END; >