cd back/src
javac center/*.java entity/*.java exception/*.java Service/*.java -encoding UTF-8 -classpath "../mssql-jdbc-9.2.1.jre8.jar;../mybatis-3.5.5.jar"
cd ../
java -classpath "mssql-jdbc-9.2.1.jre8.jar;mybatis-3.5.5.jar;src" center.CenterServer