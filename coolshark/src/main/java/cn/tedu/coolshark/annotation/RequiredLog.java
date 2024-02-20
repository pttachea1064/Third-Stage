package cn.tedu.coolshark.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自訂註解 一個特殊的類 所有的註解都默認繼承Annotation接口
 */
@Retention(RetentionPolicy.RUNTIME)//運行時有效的
@Target(ElementType.METHOD)//修飾方法
public @interface RequiredLog {
    String operation ();
}
