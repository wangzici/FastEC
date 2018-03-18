package wzt.latte_compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * @author Tao
 * @date 2018/3/17
 * desc:
 */
public class AppRegisterVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {
    private final Filer FILER;
    private String mPackageName = null;

    AppRegisterVisitor(Filer filer) {
        this.FILER = filer;
    }

    @Override
    public Void visitString(String s, Void p) {
        mPackageName = s;
        return p;
    }

    @Override
    public Void visitType(TypeMirror typeMirror, Void p) {
        generateJavaCode(typeMirror);
        return p;
    }

    private void generateJavaCode(TypeMirror typeMirror) {
        TypeSpec typeSpec = TypeSpec.classBuilder("AppRegister")
                .addModifiers(Modifier.FINAL, Modifier.PUBLIC)
                .superclass(TypeName.get(typeMirror))
                .build();
        JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", typeSpec)
                .build();
        try {
            javaFile.writeTo(FILER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
