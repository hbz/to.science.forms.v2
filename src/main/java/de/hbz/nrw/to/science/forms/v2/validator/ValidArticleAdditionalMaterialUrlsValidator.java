package de.hbz.nrw.to.science.forms.v2.validator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import de.hbz.nrw.to.science.forms.v2.model.forms.Article;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Hasan Adoud
 */
@Component
public class ValidArticleAdditionalMaterialUrlsValidator implements ConstraintValidator<ValidArticleAdditionalMaterialUrls, Article> {

	@Override
	public boolean isValid(Article article, ConstraintValidatorContext context) {
		if (article == null) {
			return true;
		}

		boolean additionalMaterialValid = validateUrls(
			article.getAdditionalMaterial(),
			"additionalMaterial",
			"Additional Material",
			context
		);
		boolean publisherVersionValid = validateUrls(
			article.getPublisherVersion(),
			"publisherVersion",
			"Publisher Version",
			context
		);

		return additionalMaterialValid && publisherVersionValid;
	}

	private boolean validateUrls(List<SimpleObject> values, String fieldName, String fieldLabel, ConstraintValidatorContext context) {
		if (values == null) {
			return true;
		}

		boolean valid = true;

		for (int i = 0; i < values.size(); i++) {
			SimpleObject value = values.get(i);
			String url = value != null ? value.getId() : null;

			if (StringUtils.isBlank(url)) {
				continue;
			}

			if (!isValidHttpUrl(url)) {
				valid = false;
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Please fill in the field \"" + fieldLabel + "\" with a valid URL!")
					.addPropertyNode(fieldName)
					.inIterable().atIndex(i)
					.addPropertyNode("id")
					.addConstraintViolation();
			}
		}

		return valid;
	}

	private boolean isValidHttpUrl(String value) {
		try {
			URI uri = new URI(value);
			String scheme = uri.getScheme();
			String host = uri.getHost();
			return ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme))
				&& StringUtils.isNotBlank(host);
		} catch (URISyntaxException ex) {
			return false;
		}
	}
}
