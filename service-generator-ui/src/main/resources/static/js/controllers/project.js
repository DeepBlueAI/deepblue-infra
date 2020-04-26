'use strict';

angular.module('app').controller('ProjectController', ['$scope', '$http', '$state', function ($scope, $http, $state) {

    // 基本信息
    $scope.groupId = 'com.deepblueai';
    $scope.artifactId = 'demo';
    $scope.port = '8000';
    $scope.version = '1.0.0';
    $scope.description = 'Demo project';
    $scope.javaVersion = '1.8';
    $scope.springBootVersion = '2.1.8.RELEASE';
    $scope.springCloudVersion = 'Greenwich.SR5';

    // 项目类型
    $scope.serviceType = 'BASIC';
    $scope.serviceTypeList = [
        "BASIC",
        "GATHER"
    ];

    // 项目依赖
    $scope.dependencies = [];

    // 数据库连接
    $scope.dbType = 'MySQL';
    $scope.dbIP = '10.16.33.117';
    $scope.dbPort = '3308';
    $scope.dbName = 'edu_device';
    $scope.dbUsername = 'root';
    $scope.dbPassword = '123456';

    // 数据库表
    $scope.tables = [];
    $scope.fields = [];

    // 待生成代码的数据库表
    $scope.tableList = [];

    // 包信息
    $scope.author = 'jason';
    $scope.javaDir = 'src/main/java';
    $scope.resourcesDir = 'src/main/resources';
    $scope.testDir = 'src/test/java';
    $scope.basePackage = 'com.deepblueai.demo';
    $scope.configPackage = 'config';
    $scope.controllerPackage = 'web';
    $scope.servicePackage = 'service';
    $scope.daoPackage = 'domain.mapper';
    $scope.entityPackage = 'domain.entity';
    $scope.converterPackage = 'domain.converter';
    $scope.reqPackage = 'request';
    $scope.respPackage = 'response';
    $scope.exceptionPackage = 'exception';
    $scope.dtoPackage = 'dto';
    $scope.voPackage = 'vo';

    $scope.facadePackage = 'client';

    // 表类型/主键类型/主表名称/连接字段名称
    $scope.tableType = 'Single';
    $scope.tableTypeList = [
        "Single",
        "SubTable"
    ];
    $scope.primaryKeyFieldType = 'ID';
    $scope.primaryKeyFieldName = '';
    $scope.keyTypeList = [
        "ID",
        "UUID"
        // "NONE",
    ];
    $scope.masterTableName = '';
    $scope.masterFieldName = '';

    $scope.sortTypeList = [
        "None",
        "ASC",
        "DESC"
    ];

    // 数据库表展开
    $scope.maxRows = 100;
    $scope.row_status = [];
    for (var i = 0; i < $scope.maxRows; i++) {
        $scope.row_status[i] = true;
    }

    $scope.resetRowStatus = function () {
        for (var i = 0; i < $scope.row_status.length; i++) {
            $scope.row_status[i] = true;
        }
    };

    // 生成项目
    $scope.save = function () {
        // 依赖的项目
        $scope.dependencyList = new Array();
        angular.forEach($scope.dependencies, function (dependency, index) {
            if (dependency.selected) {
                var item = {
                    'name': null,
                    'groupId': null,
                    'artifactId': null,
                    "version": null
                };
                item.name = dependency.artifactId;
                item.groupId = dependency.groupId;
                item.artifactId = dependency.artifactId;
                item.version = dependency.version;
                $scope.dependencyList.push(item);
            }
        });

        // 封装请求参数
        var param = {
            'projectInfo': null,
            'dependencies': null,
            'databaseInfo': null,
            'tables': null,
            'packageInfo': null
        };

        var projectInfo = {
            'groupId': null,
            'artifactId': null,
            'port': null,
            'version': null,
            'name': null,
            'description': null,
            'javaVersion': null,
            'springBootVersion': null,
            'springCloudVersion': null,
            'serviceType': null
        };

        var databaseInfo = {
            'dbType': null,
            'dbIP': null,
            'dbPort': null,
            'dbName': null,
            'dbUsername': null,
            'dbPassword': null
        };

        var packageInfo = {
            'basePackage': null,
            'configPackage': null,
            'controllerPackage': null,
            'servicePackage': null,
            'daoPackage': null,
            'entityPackage': null,
            'dtoPackage': null,
            'voPackage': null,
            'converterPackage': null,
            'reqPackage': null,
            'respPackage': null,
            'exceptionPackage': null,
            'facadePackage': null,
            'author': null,
            'projectPath': null,
            'javaPath': null,
            'resourcePath': null,
            'testPath': null
        };

        projectInfo.groupId = $scope.groupId;
        projectInfo.artifactId = $scope.artifactId;
        projectInfo.port = $scope.port;
        projectInfo.version = $scope.version;
        projectInfo.name = $scope.artifactId;
        projectInfo.description = $scope.description;
        projectInfo.javaVersion = $scope.javaVersion;
        projectInfo.springBootVersion = $scope.springBootVersion;
        projectInfo.springCloudVersion = $scope.springCloudVersion;
        projectInfo.serviceType = $scope.serviceType;

        databaseInfo.dbType = $scope.dbType;
        databaseInfo.dbIP = $scope.dbIP;
        databaseInfo.dbPort = $scope.dbPort;
        databaseInfo.dbName = $scope.dbName;
        databaseInfo.dbUsername = $scope.dbUsername;
        databaseInfo.dbPassword = $scope.dbPassword;

        packageInfo.basePackage = $scope.basePackage;
        packageInfo.configPackage = $scope.configPackage;
        packageInfo.controllerPackage = $scope.controllerPackage;
        packageInfo.servicePackage = $scope.servicePackage;
        packageInfo.daoPackage = $scope.daoPackage;
        packageInfo.entityPackage = $scope.entityPackage;
        packageInfo.dtoPackage = $scope.dtoPackage;
        packageInfo.voPackage = $scope.voPackage;
        packageInfo.reqPackage = $scope.reqPackage;
        packageInfo.respPackage = $scope.respPackage;
        packageInfo.exceptionPackage = $scope.exceptionPackage;
        packageInfo.converterPackage = $scope.converterPackage;
        packageInfo.facadePackage = $scope.facadePackage;
        packageInfo.author = $scope.author;
        packageInfo.projectPath = $scope.projectDir;
        packageInfo.javaPath = $scope.javaDir;
        packageInfo.resourcePath = $scope.resourcesDir;
        packageInfo.testPath = $scope.testDir;

        param.projectInfo = projectInfo;
        param.dependencies = $scope.dependencyList;
        param.databaseInfo = databaseInfo;
        param.tables = $scope.tableList;
        param.packageInfo = packageInfo;

        var jsonString = JSON.stringify(param);

        $http.post("/api/project/save", jsonString, {responseType: 'arraybuffer'}).success(function (data) {
            var a = document.createElement('a');
            var blob = new Blob([data], {'type': "application/octet-stream"});
            a.href = URL.createObjectURL(blob);
            a.download = "project.zip";
            a.click();
        }).error(function (data) {
            console.log('data=' + data);
        });
    };

    $scope.initDefaultDependency = function () {

        var swagger2 = {};
        swagger2.selected = true;
        swagger2.name = 'swagger2';
        swagger2.groupId = 'io.springfox';
        swagger2.artifactId = 'springfox-swagger2';
        swagger2.version = '2.9.2';

        var swagger_ui = [];
        swagger_ui.selected = true;
        swagger_ui.name = 'swagger-ui';
        swagger_ui.groupId = 'io.springfox';
        swagger_ui.artifactId = 'springfox-swagger-ui';
        swagger_ui.version = '2.9.2';
        $scope.dependencies.push(swagger2);
        $scope.dependencies.push(swagger_ui);
    };

    $scope.initDefaultDependency();

    // 测试数据源
    $scope.testDBConnection = function () {
        var param = {
            'dbType': null,
            'dbIP': null,
            'dbPort': null,
            'dbName': null,
            'dbUsername': null,
            'dbPassword': null
        };

        param.dbType = $scope.dbType;
        param.dbIP = $scope.dbIP;
        param.dbPort = $scope.dbPort;
        param.dbName = $scope.dbName;
        param.dbUsername = $scope.dbUsername;
        param.dbPassword = $scope.dbPassword;

        var jsonString = JSON.stringify(param);

        $http.post("/api/database/connect", jsonString).success(function (data) {
            if (data.code == 200) {
                window.alert("成功！")
            } else {
                window.alert(data.message)
            }
        }).error(function (data) {
            console.log('data=' + data);
        });
    };

    // 读取表的列表
    $scope.readTables = function () {
        var param = {
            'dbType': null,
            'dbIP': null,
            'dbPort': null,
            'dbName': null,
            'dbUsername': null,
            'dbPassword': null
        };

        param.dbType = $scope.dbType;
        param.dbIP = $scope.dbIP;
        param.dbPort = $scope.dbPort;
        param.dbName = $scope.dbName;
        param.dbUsername = $scope.dbUsername;
        param.dbPassword = $scope.dbPassword;

        var jsonString = JSON.stringify(param);

        $http.post("/api/database/tables", jsonString).success(function (data) {
            if (data.code == 200) {
                $scope.resetRowStatus();
                $scope.tables = data.data;
            } else {
                window.alert(data.message)
            }
        }).error(function (data) {
            console.log('data=' + data);
        });
    };

    // 读取表的详情
    $scope.select = function (index, table) {
        if (!$scope.row_status[index]) {
            $scope.row_status[index] = !$scope.row_status[index];
        } else {
            var param = {
                'dbType': null,
                'dbIP': null,
                'dbPort': null,
                'dbName': null,
                'dbUsername': null,
                'dbPassword': null
            };

            param.dbType = $scope.dbType;
            param.dbIP = $scope.dbIP;
            param.dbPort = $scope.dbPort;
            param.dbName = $scope.dbName;
            param.dbUsername = $scope.dbUsername;
            param.dbPassword = $scope.dbPassword;

            var jsonString = JSON.stringify(param);
            var tableName = table.name;
            $http.post("/api/database/tables/" + tableName, jsonString).success(function (data) {
                if (data.code == 200) {
                    $scope.resetRowStatus();
                    $scope.fields = data.data;
                    $scope.row_status[index] = !$scope.row_status[index];

                    // primary key type
                    $scope.primaryKeyFieldType = "NONE";
                    angular.forEach($scope.fields, function (field, index) {
                        if (field.primaryKey) {
                            if (field.typeName == 'BIGINT' || field.typeName == 'BIGINT UNSIGNED') {
                                $scope.primaryKeyFieldType = "ID";
                            } else if (field.typeName == 'CHAR' || field.typeName == 'VARCHAR') {
                                $scope.primaryKeyFieldType = "UUID";
                            } else {
                                console.log("Unknown primary key type = " + field.typeName);
                            }
                            $scope.primaryKeyFieldName = field.columnName;
                        }
                    });
                } else {
                    window.alert(data.message)
                }
            }).error(function (data) {
                console.log('data=' + data);
            });
        }
    };

    // 添加数据库表到待生成列表
    // tableType: 单表，主表，子表
    // keyType: ID，UUID
    // masterTableName: 主表名称
    // masterFieldName: 主表主键字段名称
    $scope.addTable = function (table, tableType, keyType, masterTableName, masterFieldName) {
        var found = false;
        angular.forEach($scope.tableList, function (tableItem, index) {
            if (tableItem.name == table.name) {
                found = true;
            }
        });

        if (found) {
            window.alert('"' + table.name + '"表已经存在');
            return;
        }

        var tableInfo = {
            'name': null,
            'remark': null,
            'type': null,   // Single/Main/Sub
            'key': null,
            'keyType': null,
            'main': null,
            'join': null,
            'query': null,
            'order': null
        };

        var primaryKeyFieldName = $scope.primaryKeyFieldName;
        var keyList = new Array();
        angular.forEach($scope.fields, function (field, index) {
            if (field.key) {
                keyList.push(field.columnName);
            }
        });

        var queryList = new Array();
        angular.forEach($scope.fields, function (field, index) {

            if (field.query) {
                queryList.push(field.columnName);
            }


            // if (field.primaryKey) {
            //     primaryKeyFieldName = field.columnName;
            // }
        });

        var orderByList = new Array();
        angular.forEach($scope.fields, function (field, index) {
            if (field.order != 'None') {
                var item = {
                    'field': field.columnName,
                    'sort': field.order
                };
                orderByList.push(item);
            }
        });


        tableInfo.name = table.name;
        tableInfo.remark = table.remark;
        tableInfo.type = tableType;
        tableInfo.key = primaryKeyFieldName;
        tableInfo.keyType = keyType;
        tableInfo.main = masterTableName;
        tableInfo.join = masterFieldName;
        tableInfo.query = queryList;
        tableInfo.order = orderByList;

        $scope.tableList.push(tableInfo);
        window.alert('成功');
    };

    // 移除待生成的数据库表
    $scope.removeTable = function (table) {
        for (var i = 0; i < $scope.tableList.length; i++) {
            if ($scope.tableList[i].name == table.name) {
                $scope.tableList.splice(i, 1);
                break;
            }
        }
    };

}]);