#!/usr/bin/env	bash

count=$1
echo "반복 횟수: ${count}"
if [ -z $count ]; then
	echo "반복 횟수를 입력해주세요"
	exit 1
fi

api=http://192.168.45.42

for ((i=1; i<=${count}; i++))
do
    curl -X GET "$api/chapter02/cache/" | jq 
done

