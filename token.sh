#!/usr/bin/env bash

CRUMB=$(curl 192.168.64.3:8080/crumbIssuer/api/xml?xpath=concat\(//crumbRequestField,%22:%22,//crumb\) \
-c cookies.txt \
--user 'jenkins:jenkins')
echo $CRUMB

curl -H $CRUMB '192.168.64.3:8080/jenkins/admin/descriptorByName/jenkins.security.ApiTokenProperty/generateNewToken' \
--data 'newTokenName=kb-token' \
--user 'jenkins:jenkins' \
-b cookies.txt