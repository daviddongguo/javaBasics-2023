package xyz.dongguo.lesson.exception;

/**
 * @author dongg
 */
public interface Assert {

  /**
   * @param t
   * @param args
   * @return
   */
  BaseException newException(Throwable t, Object... args);

  /**
   * <p> assert <cod>obj</cod> not null.
   * if <code>obj</code> is null, throw exception
   *
   * @param obj An Object will be checked.
   * @throws BaseException an Exception related to obj
   */
  default void assertNotNull(Object obj) throws BaseException {
    if (obj == null) {
      throw newException(obj);
    }
  }

  /**
   * @param obj
   * @param args
   */
  default  void assertNotNull(Object obj, Object... args){
    if(obj == null){
      throw newException(args);

    }
  }

  BaseException newException(Object... args);

}
