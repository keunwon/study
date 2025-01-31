#!/bin/bash

redis_image='redis:6.2.6'
redis_container_name='local-redis'

docker run -it --network infra-docker_redis-net --rm ${redis_image} redis-cli -h ${redis_container_name}

