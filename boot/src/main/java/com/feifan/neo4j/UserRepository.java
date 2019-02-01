package com.feifan.neo4j;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Donald
 * @create 2019-01-27 11:56
 */
@Component
public interface UserRepository extends Neo4jRepository {

    @Query("MATCH (n:User) RETURN n")
    List<UserNode> getUserNodeList();

    @Query("create (n:User(age:{age},name:{name}) return n ")
    List<UserNode> addUserNodeList(@Param("name") String name,@Param("age") int age);

}
