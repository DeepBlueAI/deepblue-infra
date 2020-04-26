# 服务名称
server:
port: ${servicePort?c}
tomcat:
uri-encoding: UTF-8
connection-timeout: 10000
spring:
application:
name: ${serviceName}
datasource:
driver-class-name: com.mysql.cj.jdbc.Driver
url: jdbc:mysql://${datasourceUrl}?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true&serverTimezone=GMT%2B8
username: ${datasourceUsername}
password: ${datasourcePassword}
type: com.alibaba.druid.pool.DruidDataSource
filters: stat
maxActive: 20
initialSize: 1
maxWait: 60000
minIdle: 1
timeBetweenEvictionRunsMillis: 60000
minEvictableIdleTimeMillis: 300000
validationQuery: select 'x'
testWhileIdle: true
testOnBorrow: false
testOnReturn: false
poolPreparedStatements: true
maxOpenPreparedStatements: 20
connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=2000
main:
allow-bean-definition-overriding: true
#  cloud:
#    config:
#      uri: ${r'${config_server_url:http://server-config:8888}'}
#      label: master
#      fail-fast: true
#      password: 2(20@_^8lKs
#      username: user
#      retry:
#        initial-interval: 5000
#        max-attempts: 6
#      profile: ${r'${spring.profiles.active}'}-common
loadbalancer:
retry:
enabled: true
profiles:
active: ${r'${env_profile:dev}'}
jackson:
time-zone: GMT+8:00

swagger:
api-info:
title: 深兰AI数字教育平台API文档
description: 字典服务（内部）
version: '0.1'
contact:
name: guanshenghui
email: developer@deepblueai.com
logging:
path: ${r'${log_dir}'}/${r'${spring.application.name}'}
level:
root: info
org.keycloak: debug
config: classpath:logback-spring.xml

mapper:
mappers:
- ${baseMapperClass}
- tk.mybatis.mapper.common.special.InsertListMapper
- tk.mybatis.mapper.common.IdsMapper
identity: ${dbType}
not-empty: false
enable-method-annotation: true
before: false
enum-as-simple-type: true
wrap-keyword: '`{0}`'
check-example-entity-class: true
use-simple-type: false
mybatis:
type-aliases-package: ${entityPackage}
mapper-locations:
- classpath*:mapper/*.xml
#  configuration:
#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
pagehelper:
reasonable: false
helper-dialect: ${r'${mapper.identity}'}
support-methods-arguments: true

management:
endpoints:
web:
exposure:
include: '*'
base-path: /actuator
endpoint:
health:
show-details: always
