package org.example;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author Carson
 * @created 2025/3/26 星期三 下午 03:01
 */
public class MyAgent1 {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("MyAgent1 premain");
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                System.out.println("Transforming1 " + className);
                return classfileBuffer;
            }
        });
    }
}
