package de.hbz.nrw.to.science.forms.v2.validator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import de.hbz.nrw.to.science.forms.v2.model.forms.Researchdata;
import de.hbz.nrw.to.science.forms.v2.model.parent.SimpleObject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Hasan Adoud
 */
@Component
public class ValidAssociatedDatasetUrlsValidator implements ConstraintValidator<ValidAssociatedDatasetUrls, Researchdata> {

	@Override
	public boolean isValid(Researchdata researchdata, ConstraintValidatorContext context) {
		if (researchdata == null) {
			return true;
		}

		boolean associatedDatasetValid = validateUrls(
			researchdata.getAssociatedDataset(),
			"associatedDataset",
			"Please fill in the field \"Related data publication\" with a valid URL!",
			context);

		boolean additionalMaterialValid = validateUrls(
			researchdata.getAdditionalMaterial(),
			"additionalMaterial",
			"Please fill in the field \"Additional Material\" with a valid URL!",
			context);

		boolean isLikeValid = validateUrls(
			researchdata.getIsLike(),
			"isLike",
			"Please fill in the field \"Similar to\" with a valid URL!",
			context);

		return associatedDatasetValid && additionalMaterialValid && isLikeValid;
	}

	private boolean validateUrls(List<SimpleObject> values, String propertyName, String message, ConstraintValidatorContext context) {
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
				context.buildConstraintViolationWithTemplate(message)
					.addPropertyNode(propertyName)
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
