#!/usr/local/bin/perl5.8.3
#---------------------------------------------------------------------------
#
#                           LexisNexis, Inc.
#
# This software is proprietary to LexisNexis, Inc. and its contents are 
# proprietary and confidential to LexisNexis.  Reproduction in any form
# by anyone of the materials contained herein without the permission of 
# LexisNexis is prohibited.  Finders are asked to return this document to 
# LexisNexis Inc. Post Office Box 933, Dayton, Ohio 45401.
#
# ---------------------------------------------------------------------------
# Script Name: processgncodestamperlogs.pl    
# 
# Description: 
# 
# Short description:
#              
#
# Arguments:
#  
# Revision History:
# 07/18/2010 - Sumit Sharma
#   Initial creation.
#----------------------------------------------------------------------------

use POSIX ();
use Getopt::Long;

#global variables that are configurable
$archiveDays = 0;                   #keep log files for this number of days
$deleteDays = 20;                   #keep archive files for this number of days
$mail_user_id="kshirsac";
$base = "/gnc";
#$base = "/home/tpf655/sumitsx/gnc";


$logpath = $base . "/stamper/log";
$archiveDir = $logpath . "/archive";
$logging = ">$logpath/cleanup.log";
$start_time = normalize_time();
$deleteFlag = 1;
$logMsg = "";

GetOptions("-delete" => \$deleteFlag);

open logging;

$logMsg .= "Starting GNCode Stamper log cleanup , start time: $start_time \n";
$logMsg .= "deleteFlag: $deleteFlag";

if (!defined $logpath) {
    print("ERROR: logpath is undefined!!\n");
    exit 1;
}
if (!defined $archiveDir) {
    fail("ERROR: archiveDir must be specified\n");
}

if(!opendir(DIR, $logpath)) {
    fail("\nError: could not open directory $logpath\n");
}
else {
    #read directory holding all of the log files
    my @logfiles = readdir(DIR);
    foreach my $logfile (@logfiles) {
        if($logfile =~/GNCodeStamper.+log/) {
            #see if a candidate to archive
            if(-f "$logpath/$logfile" && -M "$logpath/$logfile" > $archiveDays) {
               #now copy to archive folder
                $retVal = system("cp -p $logpath/$logfile $archiveDir/$logfile");
                if($retVal != 0) {
                    fail("Failed to move file: $logpath/$logfile\n");
                }
                else {
                    $logMsg .= "\n\tCopied $logfile to archive";
                    if($deleteFlag) {
                        unlink("$logpath/$logfile");
                        $logMsg .= "\n\tDeleted $logfile";
                    }
                }
            }
        }
    }
	
	if(!opendir(DIR, $archiveDir)) {
    fail("\nError: could not open directory $archiveDir\n");
	}else{
		$logFileCounter=0;
		$zipFileCounter=0;
		my @logfiles = readdir(DIR);
		foreach my $logfile (@logfiles) {
			if($logfile =~/GNCodeStamper.+log/) {			
				#now compress file in archive dir
				$retVal = system("zip -m $archiveDir/GNCodeStamper.$start_time-$zipFileCounter.zip $archiveDir/$logfile");
				$logFileCounter++;
				if($retVal !=0){
					 fail("\nERROR zipping files in directory: $logfile");
				}
				if ($logFileCounter==1000){
					$logFileCounter=0;
					$zipFileCounter++;
				}
			}
		}
	}
    
    $logMsg .= "\n\nLogs files deleted and moved to archive folder.\n";
}


#now clean up archive folders
if(!opendir(DIRARCHIVE, $archiveDir)) {
    fail("\nError: could not open directory $archiveDir\n");
}
else {
    my @files = readdir(DIRARCHIVE);
    foreach my $file (@files) {
        if(-f "$archiveDir/$file") {
            if(-M "$archiveDir/$file" > $deleteDays){
                if($deleteFlag) {
                    unlink("$archiveDir/$file");
                    $logMsg .= "\n\tDeleted $archiveDir/$file";
                }
            }
        }
    }
}

$logMsg .= "\nFinished cleaning up $archiveDir folder\n";



#print out log file
print logging "$logMsg\n";
close logging;

$mailList="$base/stamper/cfg/mailid.txt";
open(DAT, $mailList) || die("Could not open file!");
@mailIds=<DAT>;
close(DAT);

	#create email message
	$msg = "TO: ";

foreach $mail_user_id (@mailIds){
	$msg .= "$mail_user_id";
}
	$msg .= "SUBJECT: GNCode Stamper Log Cleanup Complete\n";
	$msg .= "$logMsg";
                               
	# Send the email message
	$sendmail = "|/usr/lib/sendmail -oi -t";
	open MAILHANDLE, $sendmail;
	print MAILHANDLE $msg;
	close MAILHANDLE;

##################################
# fail()                         #
##################################
sub fail
{
    $logMsg .= shift;

    #print out log file
    print logging "$logMsg\n";
    close logging;

    $mailList="$base/stamper/cfg/mailid.txt";
    open(DAT, $mailList) || die("Could not open file!");
    @mailIds=<DAT>;
    close(DAT);

    	#create email message
    	$failureMsg = "TO: ";
    foreach $mail_user_id (@mailIds){
        $failureMsg .= "$mail_user_id";
    }
    	$failureMsg .= "SUBJECT: GNCode Stamper Log Cleanup Error\n";
    	$failureMsg .= "$logMsg";
    
    	# Send the email message
    	$sendmail = "|/usr/lib/sendmail -oi -t";
    	open MAILHANDLE, $sendmail;
    	print MAILHANDLE $failureMsg;
    	close MAILHANDLE;

      exit 1;
}

##################################
# getTime()                      #
#                                #
# Returns the current date/time. #
##################################
sub getTime {
    my $timestamp = `date +"%b %e %T"`;
    chomp($timestamp);
    return $timestamp;
}

#########################################
# normalize_time()                      #
#                                       #
# Subroutine for returning current time #
#########################################
sub normalize_time
{
    $time = time();
    return POSIX::strftime("%\Y%\m%\d", localtime($time));
}

