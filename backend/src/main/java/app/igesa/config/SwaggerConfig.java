package app.igesa.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig  {

	public static final String AUTHORIZATION_HEADER = "Authorization";

		private ApiInfo apiInfo() {
			return new ApiInfo(" Swagger Configuration For a Multi Websites Application ",
					"\"  Spring security Swagger configuration  \"",
					"1.0",
					"REST API V1",
					new Contact("IGESA", "https://www.igesa.it/", "commerciale@igesa.it"),
					"\" Apache tomcat v 8.5 \"",
					"API license URL",
					Collections.emptyList());
		}

		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(apiInfo())
					.securityContexts(Arrays.asList(securityContext()))
					.securitySchemes(Arrays.asList(apiKey()))
					.select()
					.apis(RequestHandlerSelectors.any())
					.paths(PathSelectors.any())
					.build();
		}

		private ApiKey apiKey() {
			return new ApiKey(AUTHORIZATION_HEADER, "JWT", "header");
		}

		private SecurityContext securityContext() {
			return SecurityContext.builder()
					.securityReferences(defaultAuth())
					.build();
		}

		List<SecurityReference> defaultAuth() {
			AuthorizationScope authorizationScope
					= new AuthorizationScope("global", "accessEverything");
			AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
			authorizationScopes[0] = authorizationScope;
			return Arrays.asList(new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes));
		}
	}


