call docker build -t xueyudarren/ms-configserver -f ./configserver/Dockerfile ./configserver

call docker build -t xueyudarren/ms-eurekaserver -f ./eurekaserver/Dockerfile ./eurekaserver

call docker build -t xueyudarren/ms-gatewayserver -f ./gatewayserver/Dockerfile ./gatewayserver

call docker build -t xueyudarren/ms-accounts -f ./accounts/Dockerfile ./accounts

call docker build -t xueyudarren/ms-loans -f ./loans/Dockerfile ./loans

call docker build -t xueyudarren/ms-cards -f ./cards/Dockerfile ./cards

pause
