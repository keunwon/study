package com.myshop.common.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Events {
    private static ApplicationEventPublisher publisher;

    static void setPublisher(ApplicationEventPublisher publisher) {
        Events.publisher = publisher;
    }

    public static void raise(Object event) {
        if (publisher != null) {
            publisher.publishEvent(event);
        }
    }

    /*private static ThreadLocal<List<EventHandler<?>>> syncHandlers = new ThreadLocal<>();
    private static ThreadLocal<Boolean> publishing = new ThreadLocal<>() {
        @Override
        protected Boolean initialValue() {
            return Boolean.FALSE;
        }
    };
    private static ThreadLocal<List<EventHandler<?>>> asyncHandlers = new ThreadLocal<>();
    private static ExecutorService executorService;

    public static void raiseSync(Object event) {
        if (publishing.get()) { return; }

        try {
            publishing.set(Boolean.TRUE);

            List<EventHandler<?>> eventHandlers = syncHandlers.get();
            if (eventHandlers == null) { return; }

            for (EventHandler eventHandler : eventHandlers) {
                if (eventHandler.canHandle(event)) {
                    eventHandler.handler(event);
                }
            }
        } finally {
            publishing.set(Boolean.FALSE);
        }
    }

    public static void handlerSync(EventHandler<?> handler) {
        if (publishing.get()) { return; }

        List<EventHandler<?>> eventHandlers = syncHandlers.get();

        if (eventHandlers == null) {
            eventHandlers = new ArrayList<>();
            syncHandlers.set(eventHandlers);
        }
        eventHandlers.add(handler);
    }

    public static void init(ExecutorService executorService) {
        Events.executorService = executorService;
    }

    public static void close() {
        if (executorService == null) { return; }

        executorService.shutdown();

        try {
            if (executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                log.info("남아있는 작업 없이 정상 종료");
                return;
            }

            log.info("남은 작업이 존재하지만 강제 종료");
            executorService.shutdownNow();
        } catch (InterruptedException ignored) {
        }
    }

    public static void raise(Object event) {
        if (publishing.get()) { return; }

        try {
            publishing.set(Boolean.TRUE);

            List<EventHandler<?>> asyncHandlers = Events.asyncHandlers.get();
            if (asyncHandlers == null) { return; }

            for (EventHandler eventHandler : asyncHandlers) {
                if (eventHandler.canHandle(event)) {
                    executorService.submit(() -> eventHandler.handler(event));
                }
            }

            List<EventHandler<?>> syncHandler = syncHandlers.get();
            if (syncHandler == null) { return; }

            for (EventHandler eventHandler : syncHandler) {
                if (eventHandler.canHandle(event)) {
                    eventHandler.handler(event);
                }
            }
        } finally {
            publishing.set(Boolean.FALSE);
        }
    }

    public static void handleAsync(EventHandler<?> handler) {
        if (publishing.get()) { return; }

        List<EventHandler<?>> eventHandlers = asyncHandlers.get();
        if (eventHandlers == null) {
            eventHandlers = new ArrayList<>();
            asyncHandlers.set(eventHandlers);
        }
        eventHandlers.add(handler);
    }

    public static void reset() {
        if (!publishing.get()) {
            syncHandlers.remove();
            asyncHandlers.remove();
        }
    }*/
}
