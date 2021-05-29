package com.keunwon.chapter09;

import java.io.IOException;
import java.nio.file.*;
import java.util.Date;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;

public class WatcherThread extends Thread {
    private String dirName;

    public WatcherThread(String dirName) {
        this.dirName = dirName;
    }

    @Override
    public void run() {
        System.out.println("Watcher is started");
        fileWatcher();
        System.out.println("watcher is ended");
    }

    private void fileWatcher() {
        try {
            Path dir = Paths.get(dirName);
            WatchService watcher = FileSystems.getDefault().newWatchService();

            dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

            WatchKey key;
            for (int loop = 0; loop < 4; loop++) {
                key = watcher.take();
                String watchedTime = new Date().toString();
                List<WatchEvent<?>> eventList = key.pollEvents();

                for (WatchEvent<?> even : eventList) {
                    Path name = (Path) even.context();
                    if (even.kind() == ENTRY_CREATE) {
                        System.out.format("%s created at %s\n", name, watchedTime);
                    } else if (even.kind() == ENTRY_DELETE) {
                        System.out.format("%s deleted at %s\n", name, watchedTime);
                    } else if (even.kind() == ENTRY_MODIFY) {
                        System.out.format("%s modified at %s\n", name, watchedTime);
                    }
                }

                if (!key.reset()) {
                    System.out.println("key reset failed");
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
