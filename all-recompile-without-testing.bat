call mvn clean install -Dmaven.test.skip=true -f ./configserver

call mvn clean install -Dmaven.test.skip=true -f ./eurekaserver

call mvn clean install -Dmaven.test.skip=true -f ./gatewayserver

call mvn clean install -Dmaven.test.skip=true -f ./accounts

call mvn clean install -Dmaven.test.skip=true -f ./loans

call mvn clean install -Dmaven.test.skip=true -f ./cards

pause
