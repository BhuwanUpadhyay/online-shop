package io.github.bhuwanupadhyay.rules;


import javax.validation.ConstraintViolation;
import java.util.Objects;

public class BasicProblem implements Problem {

  private final String beanClass;
  private final String propertyPath;
  private final String message;
  private final String messageTemplate;

  public BasicProblem(String beanClass, String propertyPath, String message, String messageTemplate) {
    this.beanClass = beanClass;
    this.propertyPath = propertyPath;
    this.message = message;
    this.messageTemplate = messageTemplate;
  }

  public String getBeanClass() {
    return beanClass;
  }

  public String getPropertyPath() {
    return propertyPath;
  }

  public String getMessage() {
    return message;
  }

  public String getMessageTemplate() {
    return messageTemplate;
  }

  public static <T> BasicProblem create(ConstraintViolation<T> violation) {
    return new BasicProblem(violation.getRootBeanClass().getName(), violation.getPropertyPath().toString(), violation.getMessage(), violation.getMessageTemplate());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BasicProblem that = (BasicProblem) o;
    return Objects.equals(beanClass, that.beanClass) &&
        Objects.equals(propertyPath, that.propertyPath) &&
        Objects.equals(messageTemplate, that.messageTemplate) &&
        Objects.equals(message, that.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(beanClass, propertyPath, messageTemplate, message);
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + " {" +
        "propertyPath='" + propertyPath + '\'' +
        ", message='" + message + '\'' +
        '}';
  }
}
