CREATE TABLE Medical_Facility (
		f_id INT,
		name VARCHAR(30),
		capacity INT,
		classification VARCHAR(10) CHECK( classification IN ('01','02','03')),
		no_sdepts INT,
		numb INT,
		street VARCHAR(50),
		city  VARCHAR(30),
		state  VARCHAR(30),
		country VARCHAR(30),
		PRIMARY KEY (f_id)
);

CREATE TABLE Certifications (
		acronym VARCHAR(3),
		name VARCHAR(50),
		c_date DATE,
		e_date DATE,
		PRIMARY KEY (acronym)
);

CREATE TABLE Facility_Certified (
		f_id INT NOT NULL,
		acronym VARCHAR(3) NOT NULL,
		certifed_date DATE DEFAULT NULL,
		expiration_date DATE DEFAULT NULL,
		PRIMARY KEY (f_id, acronym),
		FOREIGN KEY (f_id) REFERENCES Medical_Facility(f_id) ON DELETE CASCADE,
		FOREIGN KEY (acronym) REFERENCES Certifications(acronym) ON DELETE CASCADE
);

CREATE TABLE Staff (
		e_id VARCHAR(20),
		fname VARCHAR(30),
		lname VARCHAR(30),
		designation VARCHAR(2) CHECK( designation IN ('M','NM')),
		dob DATE,
		hire_date DATE,
		addr VARCHAR(100) DEFAULT NULL,
		city VARCHAR(30),
		primary_dept VARCHAR(20) NOT NULL,
		PRIMARY KEY (e_id)
);

CREATE TABLE Medical_Staff (
		e_id VARCHAR(20),
		PRIMARY KEY (e_id),
		FOREIGN KEY (e_id) REFERENCES Staff(e_id) ON DELETE CASCADE
);

CREATE TABLE Non_Medical_Staff (
		e_id VARCHAR(20),
		PRIMARY KEY (e_id),
		FOREIGN KEY (e_id) REFERENCES Staff(e_id) ON DELETE CASCADE
);

CREATE TABLE Service_department (
		code VARCHAR(20),
		name VARCHAR(30),
		director_id VARCHAR(20) NOT NULL,
		PRIMARY KEY (code),
		FOREIGN KEY (director_id) REFERENCES Staff(e_id) ON DELETE NO ACTION
);

CREATE TABLE Facility_has_Dept (
		f_id INT,
		code VARCHAR(20),
		PRIMARY KEY (f_id, code),
		FOREIGN KEY (f_id) REFERENCES Medical_Facility(f_id) ON DELETE CASCADE,
		FOREIGN KEY (code) REFERENCES Service_department(code) ON DELETE CASCADE
);

CREATE TABLE Secondary_Works_Dept (
		e_id VARCHAR(20),
		code VARCHAR(20),
		PRIMARY KEY (e_id, code),
		FOREIGN KEY (e_id) REFERENCES Staff(e_id) ON DELETE CASCADE,
		FOREIGN KEY (code) REFERENCES Service_department(code) ON DELETE CASCADE
);

CREATE TABLE Body_Parts (
		code VARCHAR(20),
		name VARCHAR(30),
		PRIMARY KEY (code)
);

CREATE TABLE Specialized_For (
		b_code VARCHAR(20),
		s_code VARCHAR(20),
		PRIMARY KEY (b_code, s_code),
		FOREIGN KEY (s_code) REFERENCES Service_department(code) ON DELETE CASCADE,
		FOREIGN KEY (b_code) REFERENCES Body_Parts(code) ON DELETE CASCADE
);

CREATE TABLE Services (
		code VARCHAR(20),
		name VARCHAR(30),
		PRIMARY KEY (code)
);

CREATE TABLE Dept_Provides_Service (
		sdcode VARCHAR(20),
		secode VARCHAR(20),
		PRIMARY KEY (sdcode,secode),
		FOREIGN KEY (sdcode) REFERENCES Service_department(code) ON DELETE CASCADE,
		FOREIGN KEY (secode) REFERENCES Services(code) ON DELETE CASCADE
);

CREATE TABLE Equipments (
		name VARCHAR(30),
		PRIMARY KEY (name)
);

CREATE TABLE Performed_using (
		code VARCHAR(20),
		name VARCHAR(30),
		PRIMARY KEY (code, name),
		FOREIGN KEY (code) REFERENCES Services(code) ON DELETE CASCADE,
		FOREIGN KEY (name) REFERENCES Equipments(name) ON DELETE CASCADE
);

CREATE TABLE Patient (
		p_id INT AUTO_INCREMENT,
		fname VARCHAR(30),
		lname VARCHAR(30),
		dob DATE,
		phone_no VARCHAR(10),
		numb INT,
		street VARCHAR(50),
		city  VARCHAR(30),
		state  VARCHAR(30),
		country VARCHAR(30),
		PRIMARY KEY (p_id),
		UNIQUE KEY unique_login_id (lname, dob, city)
);

CREATE TABLE Checks_In (
		v_id INT AUTO_INCREMENT,
		p_id INT,
		f_id INT,
		temp INT,
		bp_systolic INT,
		bp_diastolic INT,
		checkin_start_time DATETIME,
		checkin_end_time DATETIME,
		trtment_start_time DATETIME,
		discharge_time DATETIME,
		priority VARCHAR(10) CHECK( priority IN ('High', 'Normal', 'Quarantine')),
		dis_status VARCHAR(25) CHECK( dis_status IN ('Treated Successfully', 'Deceased', 'Referred')),
		treatment VARCHAR(30) DEFAULT 'false' CHECK( treatment IN ('false', 'true')) ,
		trmt_description VARCHAR(100) DEFAULT NULL,
		neg_exp VARCHAR(30),
		neg_code INT DEFAULT 0 CHECK( neg_code IN (0, 1, 2)),
		acknowledged VARCHAR(30) CHECK( acknowledged IN ('yes', 'no')),
		ack_reason VARCHAR(100) DEFAULT NULL,
		PRIMARY KEY (v_id),
		FOREIGN KEY (f_id) REFERENCES Medical_Facility(f_id) ON DELETE SET NULL,
		FOREIGN KEY (p_id) REFERENCES Patient(p_id) ON DELETE SET NULL
);

CREATE TABLE Severity (
		s_id INT,
		type VARCHAR(30),
		PRIMARY KEY (s_id)
);

CREATE TABLE Symptoms (
		code VARCHAR(10),
		b_code VARCHAR(20),
		name VARCHAR(30),
		severity_type INT,
		PRIMARY KEY (code),
		FOREIGN KEY (b_code) REFERENCES Body_Parts(code) ON DELETE SET NULL,
		FOREIGN KEY (severity_type) REFERENCES Severity(s_id) ON DELETE SET NULL
);

CREATE TABLE Affected_Info (
		v_id INT,
		s_code VARCHAR(10),
		b_code VARCHAR(20) DEFAULT 'OTH000',
		duration FLOAT,
		is_first VARCHAR(5) CHECK( is_first IN ('true', 'false', NULL)),
		incident VARCHAR(30),
		optional_description VARCHAR(100) DEFAULT NULL,
		sev_value VARCHAR(30),
		PRIMARY KEY (v_id, s_code, b_code),
		FOREIGN KEY (v_id) REFERENCES Checks_In(v_id) ON DELETE CASCADE,
		FOREIGN KEY (b_code) REFERENCES Body_Parts(code) ON DELETE NO ACTION,
		FOREIGN KEY (s_code) REFERENCES Symptoms(code) ON DELETE CASCADE
);

CREATE TABLE Reasons (
		r_id INT,
		description VARCHAR(100),
		PRIMARY KEY (r_id)
);

CREATE TABLE Referred_to(	
		f_id INT,
		v_id INT,
		e_id VARCHAR(20),
		PRIMARY KEY (v_id),
		FOREIGN KEY (f_id) REFERENCES Medical_Facility(f_id) ON DELETE SET NULL,
		FOREIGN KEY (v_id) REFERENCES Checks_In(v_id) ON DELETE CASCADE,
		FOREIGN KEY (e_id) REFERENCES Medical_Staff(e_id) ON DELETE SET NULL
);

CREATE TABLE Referral_Reason (
		v_id INT,
		r_id INT,
		s_code VARCHAR(50),
		description VARCHAR(200),
		PRIMARY KEY (v_id, r_id, s_code, description),
		FOREIGN KEY (s_code) REFERENCES Services(code) ON DELETE NO ACTION,
		FOREIGN KEY (v_id) REFERENCES Checks_In(v_id) ON DELETE CASCADE,
		FOREIGN KEY (r_id) REFERENCES Reasons(r_id) ON DELETE CASCADE
);

CREATE TABLE Rule_Priority ( 
		asn_id INT AUTO_INCREMENT,
		priority VARCHAR(50) CHECK( priority IN ('HIGH', 'NORMAL', 'QUARANTINE')),
		PRIMARY KEY (asn_id)
);

CREATE TABLE Assessment_Rules ( 
		ar_id INT ,
		s_code VARCHAR(10),
		b_code VARCHAR(20),
		comparison VARCHAR(2) CHECK( comparison IN ('>', '=', '<', '>=', '<=', '!=')),
		severity_val VARCHAR(30),
		PRIMARY KEY (ar_id, s_code, b_code, severity_val)		
);

