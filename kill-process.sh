#!/bin/bash

# Loop through the list of ports
for port in 8080 8010 8020 8021 8022 8030 8040 8050 8060; do
  # Use lsof to find the process ID of the process listening on the port
  pid=$(lsof -i :$port -t)
  
  # If a process is found, kill it with signal 9
  if [ -n "$pid" ]; then
    kill -9 $pid
    echo "Killed process $pid on port $port"
  else
    echo "No process found listening on port $port"
  fi
done