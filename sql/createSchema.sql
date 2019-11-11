CREATE TABLE Medical_Facility (
		f_id INT,
		name VARCHAR2(30),
		capacity INT,
		classification VARCHAR2(10) CHECK( classification IN ('1','2','3')),
		no_sdepts INT,
		numb INT,
		street VARCHAR2(50),
		city  VARCHAR2(30),
		state  VARCHAR2(30),
		country VARCHAR2(30),
		PRIMARY KEY (f_id)
);

CREATE TABLE Certifications (
		acronym VARCHAR2(3),
		name VARCHAR2(30),
		PRIMARY KEY (acronym)
);

CREATE TABLE Facility_Certified (
		f_id INT,
		acronym VARCHAR2(3),
		certifed_date DATE,
		expiration_date DATE,
		PRIMARY KEY (f_id, acronym),
		FOREIGN KEY (f_id) REFERENCES Medical_Facility ON DELETE CASCADE,
		FOREIGN KEY (acronym) REFERENCES Certifications ON DELETE CASCADE
);

CREATE TABLE Staff (
		e_id INT,
		name VARCHAR2(30),
		designation VARCHAR2(30),
		hire_date  DATE,
		primary_dept INT NOT NULL,
		PRIMARY KEY (e_id)
);

CREATE TABLE Medical_Staff (
		e_id INT,
		PRIMARY KEY (e_id),
		FOREIGN KEY (e_id) REFERENCES Staff ON DELETE CASCADE
);

CREATE TABLE Non_Medical_Staff (
		e_id INT,
		PRIMARY KEY (e_id),
		FOREIGN KEY (e_id) REFERENCES Staff ON DELETE CASCADE
);

CREATE TABLE Service_department (
		code INT,
		name VARCHAR2(30),
		director_id INT NOT NULL,
		PRIMARY KEY (code),
		FOREIGN KEY (director_id) REFERENCES Medical_Staff(e_id) ON DELETE SET NULL
);

ALTER TABLE Staff ADD FOREIGN KEY (primary_dept) REFERENCES Service_department(code) ON DELETE SET NULL;


CREATE TABLE Facility_Employs_Staff (
		f_id INT,e_id INT,
		PRIMARY KEY (f_id,e_id),
		FOREIGN KEY (f_id) REFERENCES Medical_Facility ON DELETE CASCADE,
		FOREIGN KEY (e_id) REFERENCES Staff ON DELETE CASCADE
);

CREATE TABLE Facility_has_Dept (
		f_id INT,
		code INT,
		PRIMARY KEY (f_id, code),
		FOREIGN KEY (f_id) REFERENCES Medical_Facility ON DELETE CASCADE,
		FOREIGN KEY (code) REFERENCES Service_department ON DELETE CASCADE
);

CREATE TABLE Secondary_Works_Dept (
		e_id INT,
		code INT,
		PRIMARY KEY (e_id, code),
		FOREIGN KEY (e_id) REFERENCES Staff ON DELETE CASCADE,
		FOREIGN KEY (code) REFERENCES Service_department ON DELETE CASCADE
);

CREATE TABLE Body_Parts (
		code INT,
		name VARCHAR2(30),
		PRIMARY KEY (code)
);

CREATE TABLE Specialized_For (
		b_code INT,
		s_code INT,
		PRIMARY KEY (b_code, s_code),
		FOREIGN KEY (s_code) REFERENCES Service_department(code) ON DELETE CASCADE,
		FOREIGN KEY (b_code) REFERENCES Body_Parts(code) ON DELETE CASCADE
);

CREATE TABLE Services (
		code INT,
		name VARCHAR2(30),
		PRIMARY KEY (code)
);

CREATE TABLE Dept_Provides_Service (
		sdcode INT,
		secode INT,
		PRIMARY KEY (sdcode,secode),
		FOREIGN KEY (sdcode) REFERENCES Service_department(code) ON DELETE CASCADE,
		FOREIGN KEY (secode) REFERENCES Services(code) ON DELETE CASCADE
);

CREATE TABLE Equipments (
		name VARCHAR2(30),
		PRIMARY KEY (name)
);

CREATE TABLE Performed_using (
		code INT,
		name VARCHAR2(30),
		PRIMARY KEY (code, name),
		FOREIGN KEY (code) REFERENCES Services(code) ON DELETE CASCADE,
		FOREIGN KEY (name) REFERENCES Equipments(name) ON DELETE CASCADE
);

CREATE TABLE Patient (
		p_id INT,
		fname VARCHAR2(30),
		lname VARCHAR2(30),
		dob DATE,
		phone_no VARCHAR2(10),
		numb INT,
		street VARCHAR2(50),
		city  VARCHAR2(30),
		state  VARCHAR2(30),
		country VARCHAR2(30),
		CONSTRAINT unique_login_id UNIQUE (lname, dob, city),
		PRIMARY KEY (p_id)
);

CREATE TABLE Checks_In (
		v_id INT,
		p_id INT,
		f_id INT,
		temp INT,
		bp_systolic INT,
		bp_diastolic INT,
		checkin_start_time DATE,
		checkin_end_time DATE,
		trtment_start_time DATE,
		priority VARCHAR2(10) CHECK( priority IN ('High', 'Medium', 'Low')),
		dis_status VARCHAR2(10) CHECK( dis_status IN ('Treated Successfully', 'Deceased', 'Referred')),
		treatment VARCHAR2(30),
		neg_exp VARCHAR2(30) CHECK( neg_exp IN ('Misdiagnosis', 'Service Not Available')),
		acknowledged VARCHAR2(30),
		PRIMARY KEY (v_id),
		FOREIGN KEY (f_id) REFERENCES Medical_Facility(f_id) ON DELETE SET NULL,
		FOREIGN KEY (p_id) REFERENCES Patient(p_id) ON DELETE SET NULL
);

CREATE TABLE Severity (
		s_id INT,
		type VARCHAR2(30),
		PRIMARY KEY (s_id)
);

CREATE TABLE Symptoms (
		code VARCHAR2(10),
		b_code INT,
		name VARCHAR2(30),
		severity_id  INT,
		PRIMARY KEY (code),
		FOREIGN KEY (b_code) REFERENCES Body_Parts(code) ON DELETE SET NULL,
		FOREIGN KEY (severity_id) REFERENCES Severity(s_id) ON DELETE SET NULL
);

CREATE TABLE Affected_Info (
		v_id INT,
		s_code VARCHAR2(10),
		b_code INT,
		duration NUMBER,
		is_first CHAR(1),
		incident VARCHAR2(30),
		sev_value INT,
		PRIMARY KEY (v_id, s_code, b_code),
		FOREIGN KEY (v_id) REFERENCES Checks_In(v_id) ON DELETE CASCADE,
		FOREIGN KEY (b_code) REFERENCES Body_Parts(code) ON DELETE SET NULL,
		FOREIGN KEY (s_code) REFERENCES Symptoms(code) ON DELETE CASCADE
);

CREATE TABLE Reasons (
		r_id INT,
		description VARCHAR2(30),
		PRIMARY KEY (r_id)
);

CREATE TABLE Referred_to(	
		f_id INT,
		v_id INT,
		e_id INT,
		PRIMARY KEY (v_id),
		FOREIGN KEY (f_id) REFERENCES Medical_Facility(f_id) ON DELETE SET NULL,
		FOREIGN KEY (v_id) REFERENCES Checks_In(v_id) ON DELETE CASCADE,
		FOREIGN KEY (e_id) REFERENCES Staff(e_id) ON DELETE SET NULL
);

CREATE TABLE Referral_Reason(
		r_id INT,
		v_id INT,
		PRIMARY KEY (v_id, r_id),
		FOREIGN KEY (v_id) REFERENCES Checks_In(v_id) ON DELETE CASCADE,
		FOREIGN KEY (r_id) REFERENCES Reasons(r_id) ON DELETE CASCADE
);

CREATE TABLE Assessment_Priority ( 
		asn_id INT,
		priority VARCHAR2(5) CHECK( priority IN ('High', 'Medium', 'Low')),
		PRIMARY KEY (asn_id)
);

CREATE TABLE Assessment_Rules ( 
		ar_id INT,
		s_code VARCHAR2(10),
		b_code INT,
		severity_val VARCHAR2(10),
		PRIMARY KEY (ar_id, s_code, b_code, severity_val),
		FOREIGN KEY (s_code) REFERENCES symptoms(code) ON DELETE CASCADE,
		FOREIGN KEY (b_code) REFERENCES body_parts(code) ON DELETE CASCADE,
		FOREIGN KEY (ar_id) REFERENCES Assessment_Priority(asn_id) ON DELETE CASCADE
);


