#!/bin/bash

# Navigate to each microservice and start it
gnome-terminal -- bash -c "cd eureka-server && ./mvnw spring-boot:run; exec bash"
gnome-terminal -- bash -c "cd api-gateway && ./mvnw spring-boot:run; exec bash"

# Wait for all processes to complete
