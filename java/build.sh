#!/bin/bash

echo "-----------------------------------------"
echo "Building ...."
echo "-----------------------------------------"

mvn clean package -DskipTests=true

rm -rf jars/*
cp mqspringSend/target/mqspring-send-0.0.1-SNAPSHOT.jar jars/.
cp mqspringRecv/target/mqspring-recv-0.0.1-SNAPSHOT.jar jars/.

