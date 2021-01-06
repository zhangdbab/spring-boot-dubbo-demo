#FROM 10.7.90.127/system_containers/java:8
FROM 10.7.92.62/service_mesh/oraclejdk:8u221-cpdep.1.0.1
MAINTAINER  zhangdb   dj009828zhangdongbin@djbx.com

ENV NACOS_NAMESPACE public
ENV JAEGER_ZIPKIN_URL 'http://zipkin.jaeger-system.svc.cluster.local:9411'
ENV NACOS_SERVER_URL 'nacos-ex.nacos-system.svc.cluster.local:8848'
ENV NACOS_REQUEST_RETRY 5
ENV CONSUMER_RETRIES 3
ENV MESH_SVC_NAME spring-boot-dubbo-provider
ENV NACOS_GROUP_NAME dev

ADD spring-boot-dubbo-provider-0.0.1-SNAPSHOT.jar  /app/app.jar

WORKDIR "/app"
EXPOSE 20881
EXPOSE 8080



##
#"-XX:+UnlockExperimentalVMOptions", \
#            "-XX:+UseCGroupMemoryLimitForHeap", \
#

ENTRYPOINT ["java", "-Xms512m", "-Xmx1g", \
#            "-Dspring.profiles.active=test", \
            "-Dnacos.namespace=${NACOS_NAMESPACE}", \
            "-Dnacos.address=${NACOS_SERVER_URL}", \
            "-Dnacos.group=${NACOS_GROUP_NAME}", \
            "-Dzipkin.service-name=${MESH_SVC_NAME}", \
            "-Dzipkin.address=${JAEGER_ZIPKIN_URL}", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-jar", "/app/app.jar"]
