package ${packageName};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "${packageName}")
@MapperScan(basePackages = "${mapperPackage}")
@EnableTransactionManagement(proxyTargetClass = true)
public class ${className} {

public static void main(String[] args) {
SpringApplication.run(${className}.class, args);
}

}