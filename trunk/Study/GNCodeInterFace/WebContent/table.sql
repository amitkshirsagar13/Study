
drop table MileStone_Login;
create table MileStone_Login(
employeeID int(5) Unique NOT NULL AUTO_INCREMENT,
userid varchar(50),
password varchar(50),
role int(1)
) TYPE=innodb;
auto_increment=1;


insert into MileStone_Login values(56813,'amit','289870',1);
insert into MileStone_Login values(63453,'amogh','289870',2);
insert into MileStone_Login values(62343,'poonam','289870',2);

select * from MileStone_Login;

update MileStone_Login set userid='Aai' , role=1 where employeeID='56465'


create table MileStone_ApplicationRelease(
ReleaseID int Unique NOT NULL AUTO_INCREMENT,
ApplicationID int(5),
ReleaseName varchar(50),
poc varchar(50)
) TYPE=innodb
auto_increment=1;

MileStone_ApplicationRelease.ReleaseID AUTO_INCREMENT

select * from MileStone_ApplicationRelease

CREATE TABLE MileStone_Application(ApplicationID int Unique NOT NULL auto_increment,
ApplicationName varchar(50),
owner varchar(5)
) TYPE=innodb
auto_increment=1;
select * from MileStone_Application;