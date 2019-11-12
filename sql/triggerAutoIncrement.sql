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