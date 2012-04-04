tar -xzf mysql-5.0.75.tar.gz
cd mysql-5.0.75
./configure --prefix=/usr/local/mysql-5.0.75 --with-charset=utf8 --with-collation=utf8_general_ci	
make
make install

ln -s /usr/local/mysql-5.0.75/ /usr/local/mysql

This soft link will help during installation of PHP wen you use the --with-mysql=/usr/local/mysql 
in configure option

# cp support-files/my-small.cnf /etc/my.cnf
# chown root /etc/my.cnf
# chgrp root /etc/my.cnf
# chmod 644 /etc/my.cnf

# vi /etc/my.cnf

Search for [mysqld] clause, and add immediately below it:
user = mysql

If you want to use InnoDB databases (what you probably will), uncomment (and perhaps edit) all innodb options in the my.cnf file. Save all changes ( :wq).
additional settings

For proper functioning, MySQL needs a "mysql" database. To create this database, simply run:
# /usr/local/mysql/bin/mysql_install_db --user=mysql

# /usr/local/mysql/bin/mysqld_safe --user=mysql &

Hit enter again to get your prompt back. The MySQL server should now be running. To check that server is running and works properly enter
# /usr/local/mysql/bin/mysqladmin version

You should get some response about the server software version.

Connect to MySQL server:
# /usr/local/mysql/bin/mysql -u root

If you get a welcome message and the prompt changes to mysql>, the server works and everything is fine. If this failed for any reason, it may indicate some problems with your installation/configuration.
set the root password

Now, before you do anything else, set root user's password (!). Stay connected to MySQL and enter:
DELETE FROM mysql.user WHERE User = '';
FLUSH PRIVILEGES;
SELECT Host, User FROM mysql.user;

Look for the record that has root in the User column and something other than localhost in the Host column. This is the host_name.
SET PASSWORD FOR 'root'@'localhost' = PASSWORD('new_password');
SET PASSWORD FOR 'root'@'host_name' = PASSWORD('new_password');

After everything is set up, restart MySQL server:
# /usr/local/mysql/bin/mysqladmin -u root -p shutdown
# /usr/local/mysql/bin/mysqld_safe --user=mysql &

Voila, your MySQL server is up and running!
automatic startup

Set up an automatic startup so you don't need to start MySQL server manually after each system reboot. Go back to the directory where you extracted the downloaded mysql tarball file. Enter
# cp support-files/mysql.server /etc/init.d/mysql
# chmod 755 /etc/init.d/mysql
# chkconfig --add mysql
# chkconfig --level 35 mysql on 






/usr/local/mysql/bin/mysqld_safe --user=mysql &


drop table MileStone_Login;
create table MileStone_Login(
employeeID int(5) Unique NOT NULL AUTO_INCREMENT,
userid varchar(50),
password varchar(50),
role int(1)
) TYPE=innodb;
auto_increment=1;

insert into MileStone_Login values(56813,'charu','charu',1);
insert into MileStone_Login values(62341,'somnath','somnath',1);
insert into MileStone_Login values(62341,'diksha','diksha',1);


insert into MileStone_Login values(56813,'amit','289870',1);
insert into MileStone_Login values(62341,'amogh','289870',2);
insert into MileStone_Login values(62343,'poonam','289870',2);

select * from MileStone_Login;

update MileStone_Login set userid='Aai' , role=1 where employeeID='56465'

drop table MileStone_ApplicationRelease
create table MileStone_ApplicationRelease(
ReleaseID int Unique NOT NULL AUTO_INCREMENT,
ApplicationID int(5),
ReleaseName varchar(50),
poc varchar(50)
) TYPE=innodb
auto_increment=1;

MileStone_ApplicationRelease.ReleaseID AUTO_INCREMENT

select max(ReleaseID) from MileStone_ApplicationRelease

select * from MileStone_ApplicationRelease

update MileStone_ApplicationRelease set ApplicationID=3 where ReleaseID=3;
update MileStone_ApplicationRelease set ApplicationID=5 where ReleaseID=4;
update MileStone_ApplicationRelease set ApplicationID=6 where ReleaseID=5;
update MileStone_ApplicationRelease set ApplicationID=3 where ReleaseID=6;
update MileStone_ApplicationRelease set ApplicationID=1 where ReleaseID=7;
update MileStone_ApplicationRelease set ApplicationID=2 where ReleaseID=8;

drop table MileStone_Application
CREATE TABLE MileStone_Application(
ApplicationID int Unique NOT NULL auto_increment,
ApplicationName varchar(50),
owner varchar(5)
) TYPE=innodb
auto_increment=1;
select * from MileStone_Application;
update MileStone_Application set ApplicationName='Got start' where ApplicationID=3;
update MileStone_Application set ApplicationName='Hey Man' where ApplicationID=4;

drop table MileStone_ReleaseAllocation
create table MileStone_ReleaseAllocation(
AllocationID int Unique NOT NULL AUTO_INCREMENT,
ReleaseID int(5),
UserID int(5),
POC  int(1)
) TYPE=innodb
auto_increment=1;

insert into MileStone_ReleaseAllocation values(1,1,56813,1);
insert into MileStone_ReleaseAllocation values(2,2,62343,1);
insert into MileStone_ReleaseAllocation values(3,3,62343,0);
insert into MileStone_ReleaseAllocation values(4,4,62343,1);
insert into MileStone_ReleaseAllocation values(5,5,62344,1);
insert into MileStone_ReleaseAllocation values(6,6,62344,1);
insert into MileStone_ReleaseAllocation values(7,3,56813,1);
delete from MileStone_ReleaseAllocation where AllocatonID=7

select * from MileStone_ReleaseAllocation


select MileStone_ReleaseAllocation.AllocatonID, MileStone_ReleaseAllocation.ReleaseId, 
MileStone_ReleaseAllocation.UserID, MileStone_ReleaseAllocation.POC, 
MileStone_Login.userid, MileStone_ApplicationRelease.ReleaseName, MileStone_ApplicationRelease.ApplicationID
from MileStone_ReleaseAllocation, MileStone_Login, MileStone_ApplicationRelease
where MileStone_ReleaseAllocation.UserID = MileStone_Login.employeeID 
and MileStone_ApplicationRelease.ReleaseID=MileStone_ReleaseAllocation.ReleaseID 
and MileStone_ReleaseAllocation.ReleaseID=5 order by MileStone_ReleaseAllocation.UserID

select ReleaseID, ApplicationID, ReleaseName, POC from MileStone_ApplicationRelease where ApplicationID='2' order by ReleaseID



