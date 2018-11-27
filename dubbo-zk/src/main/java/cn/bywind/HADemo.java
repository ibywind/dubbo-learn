package cn.bywind;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HADemo implements Watcher {

    private String role = "master";
    private static final String HA_PATH = "/HA";

    private ZooKeeper zooKeeper;



    public static void main(String[] args) throws Exception {
        HADemo haDemo = new HADemo();
        ZooKeeper zk = haDemo.getZk();
        Stat exists = zk.exists(HA_PATH, haDemo);
        if (exists == null) {
            zk.create(HA_PATH, "1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } else {
            haDemo.role = "slave";
            System.out.println("i am the :" + haDemo.role);
        }
        while (true) {
            if ("q".equalsIgnoreCase(readFromConsole())) {
                System.exit(0);
            } else {
                System.out.println("输入q停止当前程序");
            }
        }
    }

    private ZooKeeper getZk () {
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 3000, null);
            return zooKeeper;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void process(WatchedEvent event) {

        if (event.getType().equals(Event.EventType.NodeCreated)) {
            System.out.println("I am the :" + role);
        }

        if (event.getType().equals(Event.EventType.NodeDeleted)) {
            System.out.println("master is down");
            this.role = "master";
            try {
                zooKeeper.create(HA_PATH, "1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                System.out.println("I am the :" + role);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public static String readFromConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}


