docker build -t xueyudarren/ms-configserver -f ./configserver/Dockerfile ./configserver

docker build -t xueyudarren/ms-eurekaserver -f ./eurekaserver/Dockerfile ./eurekaserver

docker build -t xueyudarren/ms-gatewayserver -f ./gatewayserver/Dockerfile ./gatewayserver

docker build -t xueyudarren/ms-accounts -f ./accounts/Dockerfile ./accounts

docker build -t xueyudarren/ms-loans -f ./loans/Dockerfile ./loans

docker build -t xueyudarren/ms-cards -f ./cards/Dockerfile ./cards
