putty -ssh root@10.8.1.232 -pw 0penstack! -m get_raw_logs_on_testbed.sh

pscp -pw 0penstack! root@10.8.1.232:/home/Aryan/get_raw_logs/raw_logs.json .
