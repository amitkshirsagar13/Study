CREATE DATABASE IF NOT EXISTS ABS;

USE ABS;

CREATE TABLE IF NOT EXISTS SystemRoles (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  roleName VARCHAR(10)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS SaftyIndexes (
  id INT(1) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  saftyIndexName VARCHAR(15)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS SystemUsers (
  id INT(3) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  emailId VARCHAR(50) NOT NULL,
  roleId INT(4) UNSIGNED NOT NULL,
  status VARCHAR(10),
  password VARCHAR(15),
  crtDate DATE,
  updDate DATE,
  CONSTRAINT uc_emailId UNIQUE (emailId),
  FOREIGN KEY (roleId) REFERENCES SystemRoles(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS Addresses (
  id INT(6) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  firstLine VARCHAR(30) NOT NULL,
  secondLine VARCHAR(30),
  landMark VARCHAR(30),
  city VARCHAR(30) NOT NULL,
  zip INT(6) UNSIGNED NOT NULL,
  crtDate DATE,
  updDate DATE
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS Customers (
  id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  firstName VARCHAR(25) NOT NULL,
  lastName VARCHAR(25),
  addressId INT(6) UNSIGNED NOT NULL,
  contactNumber VARCHAR(10),
  emailId VARCHAR(50),
  occupation VARCHAR(20),
  birthDate DATE,
  saftyIndexId INT(1) UNSIGNED NOT NULL,
  barCode VARCHAR(16),
  creditPoints INT(3) UNSIGNED,
  crtDate DATE,
  updDate DATE,
  FOREIGN KEY (addressId) REFERENCES Addresses(id),
  FOREIGN KEY (saftyIndexId) REFERENCES SaftyIndexes(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS Suppliers (
  id INT(6) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  supplierName VARCHAR(30) NOT NULL,
  customerId INT(10) UNSIGNED NOT NULL,
  addressId INT(6) UNSIGNED NOT NULL,
  supplierComment VARCHAR(50),  
  crtDate DATE,
  updDate DATE,
  FOREIGN KEY (customerId) REFERENCES Customers(id),
  FOREIGN KEY (addressId) REFERENCES Addresses(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS Discounts (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  description VARCHAR(30) NOT NULL,
  severity INT(1) UNSIGNED NOT NULL,
  discount INT(2)  NOT NULL
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS Products (
  id INT(6) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  productName VARCHAR(30) NOT NULL,
  productType VARCHAR(30) NOT NULL,
  lotNumber INT(10) UNSIGNED NOT NULL,
  lotSize INT(5) UNSIGNED NOT NULL,
  inventory INT(5) UNSIGNED,
  basePrice FLOAT(5,2) UNSIGNED NOT NULL,
  margin INT(3) UNSIGNED,
  supplierId INT(6) UNSIGNED NOT NULL,
  crtDate DATE,
  updDate DATE,
  barCode VARCHAR(16),
  FOREIGN KEY (supplierId) REFERENCES Suppliers(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS Invoices (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  customerId INT(10) UNSIGNED  NOT NULL,
  totalPrice FLOAT(5,2) UNSIGNED NOT NULL,
  discountedPrice FLOAT(5,2) UNSIGNED,
  crtDate DATE,
  updDate DATE,
  systemUserId INT(3) UNSIGNED NOT NULL,
  returnComments VARCHAR(50),
  creditPoints INT(1),
  barCode VARCHAR(16),
  FOREIGN KEY (customerId) REFERENCES Customers(id),
  FOREIGN KEY (systemUserId) REFERENCES SystemUsers(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS Sales (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  invoiceId INT(16) UNSIGNED NOT NULL,
  productId INT(6) UNSIGNED NOT NULL,
  itemSrNo INT(2) UNSIGNED,
  itemPrice  FLOAT(5,2) UNSIGNED,
  discountId INT(4) UNSIGNED NOT NULL,
  quantity INT(2) UNSIGNED,
  salesPrice  FLOAT(5,2) UNSIGNED,
  FOREIGN KEY (invoiceId) REFERENCES Invoices(id),
  FOREIGN KEY (productId) REFERENCES Products(id),
  FOREIGN KEY (discountId) REFERENCES Discounts(id)
) engine=InnoDB;
