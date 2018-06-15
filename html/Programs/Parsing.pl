#!/usr/bin/perl
use strict;
use warnings;

if(scalar @ARGV != 2){
	print STDERR "$0 [homework.csv] [program.java]\ne.g. $0 hw0.csv Bingo.java\n";
	exit 1;
}

my $homework;
my $folder;
if($ARGV[0] =~ /hw(\S+).csv/){ $homework = $1; $folder="hw$1"; }

my $hash;
my %user;
my %score;
my %time;
my %memory;
my %program;
my $count = 0;
open IN, "$ARGV[0]" or die "can not open $ARGV[0]\n";
while(my $in = <IN>){
    chomp $in;
    if($in =~ /(\.+)\r/){ $in = $1; }
    my $firstLine = "";
    if($in =~ /,"@(\S+)@(\.*)/){
    	$count++;
        $hash = $1;
    	my @tmp = split "@", $in;
    	$firstLine = $tmp[2];
    	$in =~ s/"//g;
    	my @data = split ",", $in;
        $user{$hash} = $data[0];
        $score{$hash} = $data[1];
    	$time{$hash} = $data[2];
    	$memory{$hash} = $data[3];
        $program{$hash} = "$firstLine\n" if defined $firstLine;
        $program{$hash} = "" if !defined $firstLine;
    }
    else{
    	next if($in =~ /@\S+@"/);
    	$program{$hash} .= "$in\n";
    }
}
close IN;

my $i = 1;
print<<EOF;
<html>
<head>
    <title>Homework $homework Program List</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-sortable.css">
</head>
<body>
    <div class='container'>
        <div class="page-header">
            <h1>
            Homework $homework Program List&nbsp
            <a href="index.html"><button type="button" class="btn btn-default" aria-label="Left Align">
            <span class="glyphicon glyphicon-home" aria-hidden="true"></span></button>
            </a></h1>
            <p> Click on the header to sort the programs by column.</p>
        </div>
        <table class='table table-bordered table-striped sortable'>
            <thead>
                <tr>
                    <th style="width: 20%; vertical-align: middle;" rowspan="2" class='az' data-defaultsign="nospan" data-defaultsort="asc"><i class="fa fa-map-marker fa-fw"></i> Programs </th>
                    <th colspan="4" data-mainsort="0" style="text-align: center;"> Evaluation Metrics </th>
                </tr>
                <tr>
                    <th style="width: 20%" data-defaultsort="desc"> ID </th>
                    <th style="width: 20%"> Score </th>
                    <th style="width: 20%"> Execution Time </th>
                    <th style="width: 20%"> Memory Usage </th>
                </tr>
            </thead>
            <tbody>
EOF

foreach $hash(reverse sort { $score{$a} <=> $score{$b}} keys %program){
    system("mkdir -p $folder/$hash");
    open OUTPUT, ">$folder/$hash/$ARGV[1]" or die "can not open $folder/$hash/$ARGV[1]";
    print OUTPUT "$program{$hash}";
    close OUTPUT;
    print<<EOF;
                <tr>
                    <td data-value="$ARGV[1]"> $ARGV[1]&nbsp&nbsp<a href="$folder/$hash/$ARGV[1]"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span></button></a></td>
                    <td data-value=$i> $i </td>
                    <td data-value=$score{$hash}> $score{$hash} </td>
EOF
    printf "                    <td data-value=%.4f> %.4f </td>\n", $time{$hash}, $time{$hash};
    print<<EOF;
                    <td data-value=$memory{$hash}> $memory{$hash} </td>
                </tr>
EOF
    $i++;
}

print<<EOF;
            </tbody>
        </table>
    </div>

    <script src="js/jquery-1.12.4.min.js"></script>
    <script src='js/moment.min.js'></script>
    <script src="js/bootstrap.min.js"></script>
    <script src='js/bootstrap-sortable.js'></script>

    <script>
    </script>
</body>
</html>
EOF
