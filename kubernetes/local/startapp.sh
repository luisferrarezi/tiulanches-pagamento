#!/bin/bash

echo 'Subindo aplicação...'
kubectl apply -f tlpagamento-deployment.yaml

https=http://localhost:31500/actuator/health
status=0
while [ $status -eq 0 ]
do
  sleep 5
  status=`curl $https -k -s -f -o /dev/null && echo 1 || echo 0`
done

echo 'Aplicação pronta, divirta-se ;)'