#!/usr/bin/perl
use strict;
use warnings;

open IN, "$ARGV[0]" or die "can not open $ARGV[0]\n";
while(my $in = <IN>){
    chomp $in;

}
close IN;
