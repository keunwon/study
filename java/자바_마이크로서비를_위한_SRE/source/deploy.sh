#!/usr/bin/env bash

PATH="/usr/local/lib/jdk-17.0.2/bin:/sbin:/bin:/usr/sbin:/usr/bin"
HOME="/home/dev-centos"

PROFILE=$1
if [ -z "${PROFILE}" ]; then
  echo "> 배포 환경 지정이 필요"
  exit 1
else
  echo "> 배포 환경: ${PROFILE}"
fi

APP_NAME="spring-sre"
echo "> 서비스 명: ${APP_NAME}"

echo "> 구동중인 서비스 PID 확인"
CURRENT_PID=$( pgrep -f ${APP_NAME}*.jar )
echo "> 구동중인 서비스 PID: ${CURRENT_PID}"

if [ -z "${CURRENT_PID}" ]; then
  echo "> 현재 구동중인 서비스가 존재하지 않음"
else
  echo "> kill -15 ${APP_NAME}"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 변경 사항 배포(${PROFILE})"

JAR_PATH="${HOME}/app"
JAR_NAME=$( ls ${JAR_PATH}/ | grep jar | tail -n 1 )
echo "> JAR 파일 경로: ${JAR_PATH}"
echo "> JAR 이름: ${JAR_NAME}"

nohup java -jar \
  -Dspring.profiles.active="${PROFILE}" \
  -Xms256m \
  -Xmx256m \
  "${JAR_PATH}/${JAR_NAME}" 2>&1 &
