#!/bin/bash
set -e

echo ">>> Loading secrets ..."

export DB_HOST=$(cat /secrets/host)
export DB_USER=$(cat /secrets/user)
export DB_PASSWORD=$(cat /secrets/password)
export DB_NAME=$(cat /secrets/dbname)

echo "DB_HOST loaded: $DB_HOST"
echo "DB_NAME loaded: $DB_NAME"

echo ">>> Starting Spring Boot ..."
exec java -jar /app/app.jar
