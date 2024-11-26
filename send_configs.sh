#!/bin/bash

# Store your JWT token
TOKEN="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMjU5MDAyNywiZXhwIjoxNzMyNjI2MDI3fQ.brbEyFBMRlHh9Zmf6xaobYIlRLikG-KNxeK-VU0c-0o"

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
    \"type\": \"type$i\",
    \"configKey\": \"key$i\",
    \"configValue\": \"value$i\"
  }"

  echo -e "\n"
  sleep 0.001
done

echo "Completed sending requests"