package cn.bywind;

import org.apache.zookeeper.*;

public class ZkDemo implements Watcher {

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",3000*10,new ZkDemo());
        ZooKeeper.States state = zooKeeper.getState();
        System.out.println(state);
        zooKeeper.exists("/data",new ZkDemo());
        zooKeeper.create("/data","bywind".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        Thread.sleep(2000);

    }


    @Override
    public void process(WatchedEvent event) {
        System.out.println("watcher:"+event.getState());

        if (event.getType().equals(Event.EventType.NodeCreated)){
            System.out.println("watcher:node created");
        }


    }
}
