package biubiu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MainApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(MainApplication.class);
	}

	public static void main(String[] args)
	{
		SpringApplication.run(MainApplication.class, args);
	}

}
