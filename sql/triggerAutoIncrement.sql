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

CREATE SEQUENCE rule_id_seq >
CREATE OR REPLACE TRIGGER rule_insert
	BEFORE INSERT ON Rule_Priority
	FOR EACH ROW
BEGIN
	SELECT rule_id_seq.nextval
	INTO :new.asn_id
	FROM dual;
END; >

CREATE OR REPLACE TRIGGER restrict_assess_rule 
	BEFORE INSERT ON Referral_Reason
	FOR EACH ROW
DECLARE 
	my_var INT;
BEGIN
	SELECT count(*) INTO my_var
	FROM Referral_Reason
	WHERE Referral_Reason.v_id=:new.v_id;
    IF 4 <= my_var THEN
      RAISE_APPLICATION_ERROR( -20001, 'You have exceeded the number of entries you can have for Referral Reason' );
   	END IF;
END; >