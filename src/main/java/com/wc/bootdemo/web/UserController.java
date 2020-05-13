package com.wc.bootdemo.web;

import com.wc.bootdemo.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value="/users")
public class UserController {
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long,User>());

    @GetMapping("/")
    public List<User> getUserList()
    {
        List<User> r = new ArrayList<User>(users.values());
        return r;
    }

    @PostMapping("/")
    public String postUser(@RequestBody User user)
    {
        users.put(user.getId(),user);
        return "post ok";
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id)
    {
        User user = users.get(id);
        return user;
    }

    @PutMapping("/{id}")
    public String putUser(@PathVariable Long id , @RequestBody User user)
    {
        User p = users.get(id);
        p.setAge(user.getAge());
        p.setName(user.getName());
        users.remove(id);
        users.put(id,p);
        return "put ok";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id)
    {
        users.remove(id);
        return "del ok";
    }
}
