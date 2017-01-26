./create_remote_raw_logs.sh
scp ubuntu@10.8.230.9:/home/ubuntu/raw_logs.json .

sed '2,11d;$d' raw_logs.json > raw_logs.json.temp

sed 's/hits/logdata/1' raw_logs.json.temp > raw_logs.json.temp.temp

rm -f raw_logs.json

rm -f raw_logs.json.temp

mv raw_logs.json.temp.temp raw_logs.json
