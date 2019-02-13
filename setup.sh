#!/usr/bin/env bash

APP=inventory

. ../../env.sh

#turn on "Prevent Cross-site scripting"
CRUMB_JSON=$(${CURL} "https://${JENKINS_USER}:${JENKINS_TOKEN}@${JENKINS}/crumbIssuer/api/json")

echo CRUMB_JSON=$CRUMB_JSON
CRUMB=$(echo $CRUMB_JSON | jq -r .crumb)
echo CRUMB=$CRUMB

${CURL} -H "Content-Type: text/xml" \
  --user ${JENKINS_USER}:${JENKINS_TOKEN} \
  -H Jenkins-Crumb:${CRUMB} \
  -X POST https://${JENKINS}/job/amazin-${APP}/doDelete

sleep 5

${CURL} -H "Content-Type: text/xml" \
  --user ${JENKINS_USER}:${JENKINS_TOKEN} \
  -H Jenkins-Crumb:${CRUMB} \
  --data-binary @config.xml \
  -X POST https://${JENKINS}/createItem?name=amazin-${APP}

