mvn clean install -Dmaven.test.skip=true -f ./configserver

mvn clean install -Dmaven.test.skip=true -f ./eurekaserver

mvn clean install -Dmaven.test.skip=true -f ./gatewayserver

mvn clean install -Dmaven.test.skip=true -f ./accounts

mvn clean install -Dmaven.test.skip=true -f ./loans

mvn clean install -Dmaven.test.skip=true -f ./cards
