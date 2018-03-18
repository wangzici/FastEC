package wzt.latte_compiler;

import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import wzt.latte_annotations.AppRegisterGenerator;
import wzt.latte_annotations.EntryGenerator;
import wzt.latte_annotations.PayEntryGenerator;

/**
 * @author Tao
 * @date 2018/3/17
 * desc:
 */
@SuppressWarnings("unused")
@AutoService(Processor.class)
public class LatteProcessor extends AbstractProcessor{
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generateEntry(roundEnvironment);
        generatePayEntry(roundEnvironment);
        generateAppRegister(roundEnvironment);
        return true;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        final Set<Class<? extends Annotation>> supportedAnnotations = new LinkedHashSet<>();
        supportedAnnotations.add(EntryGenerator.class);
        supportedAnnotations.add(PayEntryGenerator.class);
        supportedAnnotations.add(AppRegisterGenerator.class);
        return supportedAnnotations;
    }

    private void scan(RoundEnvironment env, Class<? extends Annotation> type, AnnotationValueVisitor visitor) {
        for (Element typeElement : env.getElementsAnnotatedWith(type)) {
            final List<? extends AnnotationMirror> annotationMirrors =
                    typeElement.getAnnotationMirrors();
            System.out.println("annotationMirror :");
            for (AnnotationMirror annotationMirror : annotationMirrors) {
                System.out.println(annotationMirror.toString());
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();
                System.out.println("ExecutableElement\tAnnotationValue");
                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
                        : elementValues.entrySet()) {
                    System.out.println( entry.getKey().toString() + "\t" + entry.getValue().toString());
                    entry.getValue().accept(visitor, null);
                }
            }
        }
    }

    private void generateEntry(RoundEnvironment env) {
        EntryVisitor visitor = new EntryVisitor(processingEnv.getFiler());
        scan(env, EntryGenerator.class, visitor);
    }

    private void generatePayEntry(RoundEnvironment env) {
        PayEntryVisitor visitor = new PayEntryVisitor(processingEnv.getFiler());
        scan(env, PayEntryGenerator.class, visitor);
    }

    private void generateAppRegister(RoundEnvironment env) {
        AppRegisterVisitor visitor = new AppRegisterVisitor(processingEnv.getFiler());
        scan(env, AppRegisterGenerator.class, visitor);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportedAnnotations();
        for (Class<? extends Annotation> annotation : supportAnnotations) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }
}
