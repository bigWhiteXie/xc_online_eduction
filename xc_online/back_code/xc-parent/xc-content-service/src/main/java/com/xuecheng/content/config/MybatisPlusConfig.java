package com.xuecheng.content.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.xuecheng.content.entity.CourseBase;
import org.mapstruct.ap.internal.model.assignment.UpdateWrapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <P>
 * 		Mybatis-Plus 配置
 * </p>
 */
@Configuration
@MapperScan("com.xuecheng.content.mapper")
public class MybatisPlusConfig {


	/**
	 * 添加自定义配置：逻辑删除时自动更新change_date
	 * @return
	 */
	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		return new ConfigurationCustomizer() {
			@Override
			public void customize(MybatisConfiguration configuration) {
				//插件拦截链采用了责任链模式，执行顺序和加入连接链的顺序有关
				DBDeleteInterceptor interceptor = new DBDeleteInterceptor();

				configuration.addInterceptor(interceptor);
				configuration.setUseDeprecatedExecutor(false);
			}
		};
	}

	/**
	 * 新的分页插件,一缓和二缓遵循mybatis的规则,
	 * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false
	 * 避免缓存出现问题(该属性会在旧插件移除后一同移除)
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

		return interceptor;
	}

//	@Bean
//	public ConfigurationCustomizer configurationCustomizer() {
//		return configuration -> configuration.setUseDeprecatedExecutor(false);
//	}

}