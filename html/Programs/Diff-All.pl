#!/usr/bin/perl
use strict;
use warnings;

my @files = `ls $ARGV[0]/*/*.java`;

for(my $i = 0; $i < scalar @files; $i++){
    printf STDERR "processing $ARGV[0] %d/%d...\n", $i+1, scalar @files if $i % 50 == 49;
    my $fileA = $files[$i];
    chomp $fileA;
    my $userA;
    if($fileA =~ /\/\/(\S+)-\S+-\S+/){
        $userA = $1;
    }
    for(my $j = $i + 1; $j < scalar @files; $j++){
        my $fileB = $files[$j];
        chomp $fileB; 
        my $userB;
        if($fileB =~ /\/\/(\S+)-\S+-\S+/){
            $userB = $1;
        }
        next if $userA eq $userB;
        my $diff = `diff $fileA $fileB`;
        printf "%d\t$fileA\t$fileB\n", 0 if length($diff) == 0;
    }
}
