#!/bin/bash

# Store your JWT token
TOKEN="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMjUyOTYzOSwiZXhwIjoxNzMyNTY1NjM5fQ.gq61qU3eNOz4VYMqaXcQqcMKAtgCgICy2cFcW9oGZpk"

for i in {1..100}
do
  echo "Sending request $i..."
  
  curl -X 'POST' \
    'http://localhost:8081/api/v1/config/' \
    -H 'accept: application/json' \
    -H 'Content-Type: application/json' \
    -H "Authorization: Bearer $TOKEN" \
    -d "{
    \"group\": \"group$i\",
    \"type\": \"type\",
    \"configKey\": \"key\",
    \"configValue\": \"value\"
  }"

  echo -e "\n"
  sleep 0.001
done

echo "Completed sending requests"