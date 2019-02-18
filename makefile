WITH_JAR=-cp "./:../jars/gson-2.8.5.jar"

install:
	./scripts/downloadgson

clean:
	find src -type f -name "*.class" -delete

build:
	cd src; javac ${WITH_JAR} Driver.java

run:
	cd src; java ${WITH_JAR} Driver

gson:
	cd src; javac ${WITH_JAR} item/GSONTest.java; java ${WITH_JAR} item/GSONTest

api:
	cd src; javac ${WITH_JAR} network/ApiTest.java; java ${WITH_JAR} network/ApiTest
