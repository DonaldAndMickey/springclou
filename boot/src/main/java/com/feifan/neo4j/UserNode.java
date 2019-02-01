package com.feifan.neo4j;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * @author Donald
 * @create 2019-01-27 11:46
 * 表示节点类型
 */
@NodeEntity(label = "User")//节点实体
public class UserNode {
    @GraphId//图形ID
    private Long nodeId;

    @Property
    private String userId;
    @Property
    private String name;
    @Property
    private int age;

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
