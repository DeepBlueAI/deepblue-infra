<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>${projectInfo.groupId}</groupId>
    <version>${projectInfo.version}</version>
    <artifactId>${projectInfo.artifactId}</artifactId>
    <packaging>jar</packaging>


    <properties>
        <version.spring.boot>${projectInfo.springBootVersion}</version.spring.boot>
        <version.spring.cloud>${projectInfo.springCloudVersion}</version.spring.cloud>
        <version.jdk>1.8</version.jdk>
        <version.mapstruct>1.2.0.Final</version.mapstruct>
        <version.swagger>2.9.2</version.swagger>
        <version.druid>1.1.20</version.druid>
        <version.tkmapper>2.1.5</version.tkmapper>
        <version.pagehelper>1.2.12</version.pagehelper>
        <version.swagger.models>1.5.21</version.swagger.models>
        <version.deepblue.base>0.0.1-SNAPSHOT</version.deepblue.base>
        <version.squareup.okhttp3>3.8.1</version.squareup.okhttp3>
    </properties>

    <dependencies>
        <dependency>
            <groupId>io.deepblueai</groupId>
            <artifactId>deepblueai-base</artifactId>
            <version>${r'${version.deepblue.base}'}</version>
        </dependency>
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct-jdk8</artifactId>
            <version>${r'${version.mapstruct}'}</version>
        </dependency>
        <dependency>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct-processor</artifactId>
            <version>${r'${version.mapstruct}'}</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>${r'${version.druid}'}</version>
        </dependency>
        <dependency>
            <groupId>com.squareup.okhttp3</groupId>
            <artifactId>okhttp</artifactId>
            <version>${r'${version.squareup.okhttp3}'}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper-spring-boot-starter</artifactId>
            <version>${r'${version.tkmapper}'}</version>
        </dependency>
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-starter</artifactId>
            <version>${r'${version.pagehelper}'}</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-access</artifactId>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>${r'${version.swagger}'}</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>${r'${version.swagger}'}</version>
        </dependency>
        <dependency>
            <groupId>io.swagger</groupId>
            <artifactId>swagger-models</artifactId>
            <version>${r'${version.swagger.models}'}</version>
        </dependency>

        <!--spring cloud-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-oauth2</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>

        <!--spring boot-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <distributionManagement>
        <repository>
            <id>deepblue-releases</id>
            <name>deepblue-releases</name>
            <url>https://nexus.deepblueai.com/repository/deepblue-releases/</url>
        </repository>
        <snapshotRepository>
            <id>deepblue-snapshots</id>
            <name>deepblue-snapshots</name>
            <url>http://nexus.deepblueai.com/repository/deepblue-snapshots/</url>
        </snapshotRepository>
    </distributionManagement>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${r'${version.spring.cloud}'}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${r'${version.spring.boot}'}</version>
                <scope>import</scope>
                <type>pom</type>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <finalName>${r'${project.artifactId}'}</finalName>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${r'${version.spring.boot}'}</version>
                <configuration>
                    <finalName>${r'${project.name}'}</finalName>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <artifactId>maven-deploy-plugin</artifactId>
                <version>2.8.2</version>
                <executions>
                    <execution>
                        <id>deploy</id>
                        <phase>deploy</phase>
                        <goals>
                            <goal>deploy</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <artifactId>maven-source-plugin</artifactId>
                <version>3.0.1</version>
                <executions>
                    <execution>
                        <id>attach-sources</id>
                        <phase>deploy</phase>
                        <goals>
                            <goal>jar-no-fork</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <version>2.0.2</version>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${r'${version.jdk}'}</source>
                    <target>${r'${version.jdk}'}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>