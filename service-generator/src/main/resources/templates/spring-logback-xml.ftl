<?xml version="1.0" encoding="UTF-8" ?>
<configuration>

    <include resource="org/springframework/boot/logging/logback/defaults.xml"/>

    <contextName>config</contextName>

    <springProperty name="LOG_PATH" scope="context" source="logging.path"/>
    <springProperty name="SERVICE_NAME" scope="context" source="spring.application.name"/>
    <springProperty name="ENV" scope="context" source="spring.cloud.config.profile"/>

    <appender class="ch.qos.logback.core.rolling.RollingFileAppender" name="FILE">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${r'${LOG_PATH}'}/${r'${SERVICE_NAME}'}-%d{yyyy-MM-dd}.log</fileNamePattern>
        </rollingPolicy>
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} %contextName [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <appender class="ch.qos.logback.core.ConsoleAppender" name="CONSOLE">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>

    <springProfile name="test,uat,prod">
        <logger level="INFO" name="org.springframework"/>
        <root level="INFO">
            <appender-ref ref="CONSOLE"/>
            <appender-ref ref="FILE"/>
        </root>
    </springProfile>

    <springProfile name="dev">
        <logger level="DEBUG" name="${groupId}"/>
        <logger level="INFO" name="org.springframework"/>
        <root level="INFO">
            <appender-ref ref="CONSOLE"/>
            <appender-ref ref="FILE"/>
        </root>
    </springProfile>

</configuration>
