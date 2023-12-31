package web.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import web.model.User;

@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		validateName(user, errors);
		validateBirthday(user, errors);
		
	}
	
	private void validateBirthday(User user, Errors errors) {
		LocalDate maxDate = LocalDate.now();
		LocalDate minDate = LocalDate.now().minusYears(100);
		if(user.getBirthday().isAfter(maxDate)) {
			errors.rejectValue("birthday", "invalid", "Birthday date cannot be greater than today's date");
		}else if(user.getBirthday().isBefore(minDate.minusYears(100))){
			errors.rejectValue("birthday","invalid", "Birthday date cannot be lower than " + minDate);
		}else if(user.getBirthday() == null) {
			errors.rejectValue("birthday","invalid", "Birthday date cannot be null");
		}
	}
	
	private void validateName(User user, Errors errors) {
		if(user.getName().isBlank() || user.getName().isEmpty()) {
			errors.rejectValue("name", "invalid", "Name cannot be empty");
		}else if(user.getName().length() > 15){
			errors.rejectValue("name","invalid", "The name must not contain more than 15 letters");
		}
	}
}
