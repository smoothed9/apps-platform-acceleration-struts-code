package org.superbiz.struts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserRepository service;

    public UserController(UserRepository service){
        this.service = service;
    }

    @GetMapping("/addUser")
    public String addUserForm(){
        return "addUserForm";
    }

    @PostMapping("/addUser")
    public String submitAddUser(String id,
                              String firstName,
                              String lastName){
        User user = new User();
        user.setId(Long.valueOf(id));
        user.setFirstName(firstName);
        user.setLastName(lastName);

        service.save(user);
        return "addedUser";
    }

    @GetMapping("/findUser")
    public String findUserForm() {
        return "findUserForm";
    }

    @PostMapping("/findUser")
    public String findUser(@RequestParam long id, Model model) {
        User user = service.findOne(id);

        if (user == null) {
            model.addAttribute("errorMessage", "User not found");
            return "findUserForm";
        }

        model.addAttribute("user", user);
        return "displayUser";
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", service.findAll());
        return "displayUsers";
    }
}
