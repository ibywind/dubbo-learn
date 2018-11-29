package cn.bywind;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HADemo implements Watcher {

    private String role = "master";
    private final static String HA_PATH = "/HA";
    private ZooKeeper zooKeeper;

    private ZooKeeper getZooKeeper(){
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181",3000,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }


    // 利用zk实现一个HA程序.模拟高可用场景
    public static void main(String[] args) throws KeeperException, InterruptedException {

        HADemo haDemo = new HADemo();
        ZooKeeper zooKeeper = haDemo.getZooKeeper();
        Stat exists = zooKeeper.exists(HA_PATH, haDemo);
        if (exists == null){
            zooKeeper.create(HA_PATH,"1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        }else{
            haDemo.role = "slave";
            System.out.println("I am the :"+haDemo.role);
        }

        while (true){
            if ("q".equals(readFromConsole())){
                System.exit(0);
            }else {
                System.out.println("输入q停止当前程序");
            }

        }

    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType().equals(Event.EventType.NodeCreated)){
            System.out.println(" I am the :"+role);
        }

        if (event.getType().equals(Event.EventType.NodeDeleted)){
            System.out.println("master is down");
            this.role = "master";

            try {
                zooKeeper.create(HA_PATH,"1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                System.out.println("I am the :"+role);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    public static String readFromConsole(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}


