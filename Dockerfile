#
# Dockerfile
# uPerm 
#

FROM eclipse-temurin:8-jre 
 
RUN apt-get update && apt-get install -y git vim file && rm -rf /var/lib/apt/lists/* 
 
RUN useradd -ms /bin/bash uperm 
ENV CLASSPATH="/home/uperm/bin/" 
WORKDIR /home/uperm 
USER uperm 
 
RUN git clone https://github.com/Permusoft/uPerm.git /tmp/uperm \ 
   && cp -r /tmp/uperm/. /home/uperm/ \ 
   && rm -rf /tmp/uperm 
 
CMD ["java", "-jar", "bin/uperm.jar", "samples/LRM/hello.p"] 
