
package it.igesa.config;
/**
 *
 * @author Tarchoun Abir
 *
 */

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

//@Configuration
public class I18nConfiguration  {

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		/* Passing basename sould match to the properties file basename */
		messageSource.setBasename("i18n/messages");
		return messageSource;
	}

	
	
	
}