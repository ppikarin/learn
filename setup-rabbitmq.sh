#!/bin/bash

docker exec -i learn-rabbitmq-container sh -c '
rabbitmqctl add_user admin Ux22uapRTTeQ=rMfJBxgxtQq+V5fq3^LRcGYa+%T?%+JZ6Ru7j25kH9@H8+gYpGf &&
rabbitmqctl set_user_tags admin administrator && 
rabbitmqctl set_permissions -p / admin ".*" ".*" ".*"
rabbitmqctl add_user monitor vtufgfhjkm
rabbitmqctl set_user_tags monitor monitoring
rabbitmq-plugins enable rabbitmq_management
'