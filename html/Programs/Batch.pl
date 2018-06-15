#!/usr/bin/perl
use strict;
use warnings;

my @array = ("0", "1", "2", "3", "4", "5-1", "5-2", "6-1",
             "6-2", "7-1", "7-2", "8", "9", "10");

for(@array){
    my $now = $_;
    print "hw$now:\n";
    system("./Diff-All.pl hw$now-diff/");
}
