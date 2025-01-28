package fast.campus.netplix3.aspect;

import fast.campus.netplix3.annotation.PasswordEncryption;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class PasswordEncryptionAspect {

    private final PasswordEncoder passwordEncoder;

    @Around("execution(* fast.campus.netplix3.controller..*.*(..))")
    public Object passwordEncryptionAspect(final ProceedingJoinPoint pjp) throws Throwable {
        Arrays.stream(pjp.getArgs()).forEach(this::fieldEncryption);
        return pjp.proceed();
    }

    public void fieldEncryption(final Object object) {
        if(ObjectUtils.isEmpty(object)) {
            return;
        }

        FieldUtils.getAllFieldsList(object.getClass()).stream()
                .filter(filter -> !(Modifier.isFinal(filter.getModifiers())) || !(Modifier.isStatic(filter.getModifiers())))
                .forEach(field -> {
                    try {
                        boolean encryptionTarget = field.isAnnotationPresent(PasswordEncryption.class);

                        if(!encryptionTarget) {
                            return;
                        }

                        Object encryptionField = FieldUtils.readField(field, object, true);

                        String encrypted = passwordEncoder.encode((String) encryptionField);

                        FieldUtils.writeField(field, object, encrypted);

                    } catch (Exception e) {
                        throw new SecurityException(e);
                    }
                });
    }
}
