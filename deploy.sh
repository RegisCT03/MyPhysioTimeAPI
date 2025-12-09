#!/bin/bash

export HOME=/home/ubuntu

git config --global --add safe.directory /home/ubuntu/MyPhysioTimeAPI

cd /home/ubuntu/Docker/Proyectos/cruz_roja

git checkout .

git pull origin develop

pwd

sudo docker compose down

sudo docker compose up -d --build

echo "Fecha: $(date)" > version.txt

