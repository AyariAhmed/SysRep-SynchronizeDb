///////// to run  replace <path_to> by "path to"  :') 
///////// and <app> by    HO   or 	BO 1 	or 	BO 2 


cd ./bin
java -cp <path_to>/slf4j-simple-1.7.26.jar:<path_to>/slf4j-api-1.7.26.jar:<path_to>/rabbitmq-jms-1.11.1.jar:<path_to>/postgresql-42.2.19.jar:<path_to>/gson-2.8.6.jar:<path_to>/amqp-client-5.7.1.jar:. <app>


/////////// to recompile 

cd ./

javac -cp <path_to>/slf4j-simple-1.7.26.jar:<path_to>/slf4j-api-1.7.26.jar:<path_to>/rabbitmq-jms-1.11.1.jar:<path_to>/postgresql-42.2.19.jar:<path_to>/gson-2.8.6.jar:<path_to>/amqp-client-5.7.1.jar:. -d ./bin ./src/*.java
