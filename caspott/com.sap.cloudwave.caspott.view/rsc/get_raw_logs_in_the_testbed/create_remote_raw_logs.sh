ssh ubuntu@10.8.230.9 << EOF
 rm -f /home/ubuntu/raw_logs.json
 curl 'localhost:9200/logstash-2015.11.10/_search?q=file:/home/ubuntu/cw-java-auth/log4j-application.log' >> /home/ubuntu/raw_logs.json
EOF
