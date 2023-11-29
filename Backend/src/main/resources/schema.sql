

CREATE TABLE Admin (
  userName VARCHAR(255) NOT NULL,
   email VARCHAR(255),
   region VARCHAR(255),
   firstName VARCHAR(255),
   lastName VARCHAR(255),
   password VARCHAR(255),
   CONSTRAINT pk_admin PRIMARY KEY (userName)
);
CREATE TABLE FAQ (
  faqID INT AUTO_INCREMENT NOT NULL,
   question VARCHAR(512) NOT NULL,
   answer VARCHAR(512) NOT NULL,
   date TIMESTAMP,
   CONSTRAINT pk_faq PRIMARY KEY (faqID)
);


CREATE TABLE GroupFantasy (
  groupID INT NOT NULL,
   name VARCHAR(255),
   code VARCHAR(255),
   isPrivate INT NOT NULL,
   ownerID INT NOT NULL,
   CONSTRAINT pk_groupfantasy PRIMARY KEY (groupID)
);


CREATE TABLE Request (
  requestID INT NOT NULL,
   userName VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   date TIMESTAMP,
   password VARCHAR(255),
   region VARCHAR(255),
   CONSTRAINT pk_request PRIMARY KEY (requestID, userName, email)
);
CREATE TABLE Rule (
  ruleID INT AUTO_INCREMENT NOT NULL,
   rule VARCHAR(255),
   date TIMESTAMP,
   CONSTRAINT pk_rule PRIMARY KEY (ruleID)
);

