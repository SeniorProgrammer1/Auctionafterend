package com.accp.Auctionafterend.boot.config;

import com.accp.Auctionafterend.boot.Interceptor.LoginIntereptor;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ServletComponentScan // 支持自定义web过滤器和servlet
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public HttpMessageConverters httpMessageConverters() {
		FastJsonHttpMessageConverter fjhmc = new FastJsonHttpMessageConverter();
		fjhmc.setCharset(Charset.forName("UTF-8"));
		List<MediaType> types = new ArrayList<MediaType>();
		types.add(MediaType.APPLICATION_JSON_UTF8);
		fjhmc.setSupportedMediaTypes(types);
		fjhmc.setFeatures(SerializerFeature.WriteEnumUsingToString, SerializerFeature.WriteMapNullValue,
				SerializerFeature.QuoteFieldNames, SerializerFeature.PrettyFormat,
				SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullNumberAsZero,
				SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.DisableCircularReferenceDetect);
		return new HttpMessageConverters(fjhmc);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginIntereptor())
				.addPathPatterns("/**") //所有请求都会被拦截，包括静态资源
				.excludePathPatterns("/","/boot/users","/css/**","/js/**","/images/**","/fonts/**");//放行的请求
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 设置允许跨域的路径
		registry.addMapping("/**")
				// 设置允许跨域请求的域名
				.allowedOriginPatterns("*")
				//允许请求头
				.allowedHeaders("*")
				//设置请求的方法
				.allowedMethods("*")
				// 是否允许跨域访问cookie
				.allowCredentials(true)
				// 设置允许的方法
				.allowedMethods("*")
				.maxAge(3600);
	}
}
