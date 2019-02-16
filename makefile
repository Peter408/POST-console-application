WITH_JAR=-cp "./:../jars/gson-2.8.5.jar"

install:
	./scripts/downloadgson

clean:
	rm -rf src/*.class

build:
	cd src; javac ${WITH_JAR} Main.java

run:
	cd src; java ${WITH_JAR} Main

gson:
	cd src; javac ${WITH_JAR} item/GSONTest.java; java ${WITH_JAR} item/GSONTest

api:
	cd src; javac ${WITH_JAR} network/ApiTest.java; java ${WITH_JAR} network/ApiTest
