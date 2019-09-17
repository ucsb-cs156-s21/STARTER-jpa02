#!/bin/sh

mvn clean package
ret=$?
if [ $ret -ne 0 ]; then
    exit $ret
fi
rm -rf target

exit
