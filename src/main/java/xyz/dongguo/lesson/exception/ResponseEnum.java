package xyz.dongguo.lesson.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum implements BusinessExceptionAssert {

  /**
   * Bad licence type
   */
  BAD_LICENCE_TYPE(7001, "Bad licence type."),
  /**
   * Licence not found
   */
  LICENCE_NOT_FOUND(7002, "Licence not found.")
  ;

  /**
   */
  private int code;
  /**
   */
  private String message;

  ResponseEnum(int code, String message){
    this.code = code;
    this.message = message;
  }

  @Override
  public int getCode(){
    return this.code;
  }

  @Override
  public String getMessage(){
    return this.message;
  }
}
