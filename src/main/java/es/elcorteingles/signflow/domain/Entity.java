package es.elcorteingles.signflow.domain;

import es.elcorteingles.signflow.Application;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Entity {

    public final void emit(Event event) {
        this.apply(event);
        Application.BUS.emit(event);
    }

    public final void apply(Event event) {
        List<Method> methods = getEventHandlers(event.type());
        for (final Method method : methods) {
            try {
                method.invoke(this, event);
                
                //TODO: handle errors
                
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(Entity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private List<Method> getEventHandlers(String eventType) {
        final List<Method> methods = new ArrayList<>();
        Class<?> klass = this.getClass();
        final List<Method> allMethods = new ArrayList<>(Arrays.asList(klass.getDeclaredMethods()));
        for (final Method method : allMethods) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                EventHandler annotation = method.getAnnotation(EventHandler.class);
                if (annotation.event().equals(eventType))
                    methods.add(method);
            }
        }
        return methods;
    }
}
