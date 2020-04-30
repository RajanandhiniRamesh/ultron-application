package com.ancestry.Ultron.config;

	import javax.sql.DataSource;

	import org.springframework.beans.factory.annotation.Value;
	import org.springframework.boot.jdbc.DataSourceBuilder;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Primary;
	import org.springframework.stereotype.Component;

	@Component
	public class DatasourceConfig {

		@Value("${spring.datasource.url}")
		private String url;
		
		@Value("${spring.datasource.username}")
		private String userName;
		
		@Value("${spring.datasource.password}")
		private String password;

		@Bean
		@Primary
		public DataSource getDataSource() {
			DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
			dataSourceBuilder.url(url);
			dataSourceBuilder.username(userName);
			dataSourceBuilder.password(password);
			return dataSourceBuilder.build();
		}
	}



