#!/bin/sh
# this bash is for test

until_case() {
    local var1=3
    until [[ ${var1} -eq 0 ]]
    do
        echo "until loop $var1"
        var1=$[ $var1-1 ]
    done
    exit 0
}

for_case() {
    for file in $(pwd)/*
    do
        if [[ -d "$file" ]]
        then
            echo "$file is a directory"
        elif [[ -f "$file" ]]
        then
            echo "$file is a file"
        fi
    done
}

if_case() {
    local ii=123
    if [[ ${ii} == 1* ]]
    then
        echo "line 1";
        echo "line 2";
    else
        echo "else logic"
    fi
}

case_case() {
    local ii=123

    case ${ii} in
    123 | 12)
        echo "1";;
    *)
        echo "2";;
    esac
}

loop_output_redirect_case() {
    local si="$IFS"
    local fp="dir/redirect.txt"
    IFS=$':'
    echo $(date) > ${fp}
    for pp in $PATH; do
        echo "$pp"
    done >> "dir/redirect.txt"
    IFS="$si"
}

function while_case {
    trap "echo good bye!" EXIT
    local count=1
    while [[ ${count} -le 1 ]]
    do
        echo "looping ${count}"
        sleep 1
        count=$[ $count + 1 ]
    done
    echo "looping done"
    return 259
}

function select_case {
    PS3="select your item: "
    select option in "aaa" "vvv" "bbb"
    do
        case ${option} in
        aaa)
            echo "aaaaaaaaaaaaaaaa";;
        vvv)
            echo "vvvvvvvvvvvvvvvvvv";;
        bbb)
            echo "bbbbbbbbbbbbbbbbbb";;
        *)
            echo "wrong input, exiting..."
            break;;
        esac
    done
}

function return_value_case {
    echo "$@"
}

function sed_case {
    echo "i am happy" | sed -e 's/happy/sad/; s/sad/red/'
}

function gawk_case {
    gawk '{print "hello world!"}'
}

main() {
    gawk_case
}

main