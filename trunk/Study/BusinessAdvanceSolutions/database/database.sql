CREATE TABLE `businessuser` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `userrole` int(1) DEFAULT 2,
  `userpassword` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userid`)
);


INSERT INTO `businessadvancedatabase`.`businessuser` (`username`, `userrole`, `userpassword`) VALUES ('amit', '1', 'poomit');


CREATE  TABLE `businessadvancedatabase`.`businesscustomer` (
  `customerID` INT NOT NULL AUTO_INCREMENT ,
  `customerFirstName` VARCHAR(45) NULL ,
  `CustomerLastName` VARCHAR(45) NULL ,
  `BirthDate` DATE NULL ,
  `AddressFirstLine` VARCHAR(255) NULL ,
  `AddressSecondLine` VARCHAR(255) NULL ,
  `LandMark` VARCHAR(45) NULL ,
  `City` VARCHAR(45) NULL ,
  `State` VARCHAR(45) NULL ,
  `Zip` INT NULL ,
  `ContactNumber` BIGINT NULL ,
  `Occupation` VARCHAR(45) NULL ,
  `CustomerIndex` VARCHAR(45) NULL ,
  `CustomerBarCode` VARCHAR(45) NULL ,
  PRIMARY KEY (`customerID`) );
  
INSERT INTO `businessadvancedatabase`.`businesscustomer` (`customerFirstName`, `CustomerLastName`, `BirthDate`, `AddressFirstLine`, `AddressSecondLine`, `LandMark`, `City`, `State`, `Zip`, `ContactNumber`, `Occupation`, `CustomerIndex`, `CustomerBarCode`) VALUES 
('Amit', 'Kshirsagar', '1984-4-05', 'Flat No A101, Saheel''s Elegance', 'Thergaon', 'Behind D-Mart', 'Pune', 'Maharashtra', '411033', '7798982320', 'Team Lead', '1', '123154648');

INSERT INTO `businessadvancedatabase`.`businesscustomer` (`customerFirstName`, `CustomerLastName`, `BirthDate`, `AddressFirstLine`, `AddressSecondLine`, `LandMark`, `City`, `State`, `Zip`, `ContactNumber`, `Occupation`, `CustomerIndex`, `CustomerBarCode`) VALUES 
('Amogh', 'Kshirsagar', '2009-11-13', 'Flat No A101, Saheel''s Elegance', 'Thergaon', 'Behind D-Mart', 'Pune', 'Maharashtra', '411033', '7798982320', 'Team Lead', '1', '123154648');

INSERT INTO `businessadvancedatabase`.`businesscustomer` (`customerFirstName`, `CustomerLastName`, `BirthDate`, `AddressFirstLine`, `AddressSecondLine`, `LandMark`, `City`, `State`, `Zip`, `ContactNumber`, `Occupation`, `CustomerIndex`, `CustomerBarCode`) VALUES 
('Poonam', 'Kshirsagar', '1984-3-10', 'Flat No A101, Saheel''s Elegance', 'Thergaon', 'Behind D-Mart', 'Pune', 'Maharashtra', '411033', '7798982320', 'Team Lead', '1', '123154648');

CREATE  TABLE `businessadvancedatabase`.`businessdress` (
  `dressid` INT NOT NULL ,
  `dressName` VARCHAR(45) NULL ,
  `dressColor` VARCHAR(45) NULL ,
  `sellPrice` INT NULL ,
  `supplierId` INT NULL ,
  `supplierPrice` INT NULL ,
  `discount` VARCHAR(45) NULL ,
  `lotNumberDate` VARCHAR(45) NULL ,
  `dressBarCode` VARCHAR(45) NULL ,
  `damageId` VARCHAR(45) NULL ,
  PRIMARY KEY (`dressid`) );

CREATE  TABLE `businessadvancedatabase`.`businesssell` (
  `sellId` INT NOT NULL ,
  `invoiceId` INT NULL ,
  `dressBarCode` VARCHAR(45) NULL ,
  `itemSrNo` VARCHAR(45) NULL ,
  `quantity` INT NULL ,
  `sellPrice` VARCHAR(45) NULL ,
  `totalPrice` INT NULL ,
  PRIMARY KEY (`sellId`) );

  
  CREATE  TABLE `businessadvancedatabase`.`businessinvoice` (
  `invoiceId` INT NOT NULL ,
  `customerId` INT NULL ,
  `invoiceTotal` INT NULL ,
  `totalDiscount` INT NULL ,
  `invoiceBarCode` VARCHAR(45) NULL ,
  `invoiceDate` DATE NULL ,
  PRIMARY KEY (`invoiceId`) );
  
  