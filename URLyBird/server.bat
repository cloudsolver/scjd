@echo off
java -Djava.rmi.dgc.leaseValue=20000 -Xmx4 -jar runme.jar server