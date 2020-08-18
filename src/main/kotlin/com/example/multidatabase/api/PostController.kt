package com.example.multidatabase.api

import com.example.multidatabase.dao.Post
import com.example.multidatabase.dao.PostRepository
import com.example.multidatabase.dao.configuration.DBContextHolder
import com.example.multidatabase.dao.configuration.DbBrand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam
import kotlin.random.Random


@RestController
class PostController {
    @Autowired
    private val postRepository: PostRepository? = null

    // test -> main DB
    // test?client=client-a -> Client A DB
    // test?client=client-b -> Client B DB
    @RequestMapping("/test/{main}")
    @ResponseBody
    fun getTest(@PathVariable( "main") client: String?): Iterable<Post> {

        when (client) {
            "client" -> DBContextHolder.currentDb = DbBrand.DB1
            "client-a" -> DBContextHolder.currentDb = DbBrand.DB2
            "client-b" -> DBContextHolder.currentDb = DbBrand.DB3
        }
        return postRepository!!.findAll()
    }

    @GetMapping("/init-data")
    @ResponseBody
    fun initialData(): String {
        DBContextHolder.currentDb = DbBrand.DB1
        val name = "main - " + Math.random()
        postRepository!!.save(Post(id = 1, name = "Main DB"))
        DBContextHolder.currentDb = DbBrand.DB2
        postRepository.save(Post(id = 2, name = "Client A DB"))
        DBContextHolder.currentDb = DbBrand.DB3
        postRepository.save(Post(id = 3, name = "Client B DB"))
        return "Success!"
    }




}