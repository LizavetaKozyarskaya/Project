package com.shop.web.controllers;

import com.shop.web.models.Building;
import com.shop.web.models.Proposal;
import com.shop.web.models.Seller;
import com.shop.web.models.Users;
import com.shop.web.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ProposalRepository proposalRepository;
    @Autowired
    private TypeRepository typeRepository;


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/homeA")
    public String homeA(Model model) {
        model.addAttribute("title", "Главная страница");
        return "homeA";
    }

    @GetMapping("/homeR")
    public String homeR(Model model) {
        model.addAttribute("title", "Главная страница");
        return "homeR";
    }

    @GetMapping("/u/{id_Users}")
    public String homeU(@PathVariable(value="id_Users") Long id_Users, Model model) {
        Optional<Users> users = usersRepository.findById(id_Users);
        ArrayList<Users> res = new ArrayList<>();
        users.ifPresent(res::add);
        model.addAttribute("users", res);
        return "homeU";
    }

//    @GetMapping("/addS")
//    public String sellA(Model model) {
//        return "addS";
//    }
//
//    @PostMapping("/addS")
//    public String sellerAdd(@RequestParam String fName,@RequestParam String pName,
//                            @RequestParam int phone, @RequestParam String sName, Model model) {
//        Seller sellers = new Seller(fName, pName, phone,sName);
//        sellerRepository.save(sellers);
//        return "addS";
//    }

    @GetMapping("/log")
    public String log(Model model) {
        model.addAttribute("title", "Главная страница");
        return "log";
    }

    @PostMapping("/log/in")
    public String userLogin(@RequestParam String loginN,
                            @RequestParam String passwordN, Model model) {
        Iterable<Users> users = usersRepository.findAll();
        List<Users> res = new ArrayList<>();
        users.forEach(res::add);
        List<Users> result = new ArrayList<>();
        for (int i = 0; i < res.size(); i++) {
            if (loginN.equals("a") && passwordN.equals("1")) {
                return "homeA";
            }
       else if (loginN.equals(res.get(i).getLogin()) && passwordN.equals(res.get(i).getPassword())){
                result.add(res.get(i));
                model.addAttribute("users", result);
                return "homeU";
            }
            else if (loginN.equals("r") && passwordN.equals("r")){
                return "homeR";
            }
        }
        return "log";
    }

    @GetMapping("/reg")
    public String reg(Model model) {
        model.addAttribute("title", "Регистрация");
        return "reg";
    }

    @PostMapping("/reg")
    public String regAdd(@RequestParam String suName, @RequestParam String fuName,
                         @RequestParam String puName, @RequestParam String login,
                         @RequestParam String password,
                         @RequestParam int uphone, @RequestParam String role, Model model) throws IOException {
        Users user1 = new Users(suName, fuName, puName, login, password,  uphone, role);
        usersRepository.save(user1);

        File file = new File("D://users.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        else {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write("\nID пользователя: " + user1.getId_Users()
                    + ", фамилия пользователя: " + user1.getSuName()
                    + ", имя пользователя: " + user1.getFuName()
                    + " , отчество пользователя: " + user1.getPuName()
                    + ", номер телефона: " + user1.getUphone()
                    + ", роль: " + user1.getRole() + "\n");
            bufferWriter.close();
        }

        return "log";
    }

    @GetMapping("/sort")
    public String sort(Model model){
        Iterable<Building> buildings = buildingRepository.findAll(Sort.by(("rooms")));
        model.addAttribute("building", buildings);
        return "buy";
    }

    @GetMapping("/sortR")
    public String sortR(Model model){
        Iterable<Building> buildings = buildingRepository.findAll(Sort.by(("rooms")));
        model.addAttribute("building", buildings);
        return "rent";
    }

//    @PostMapping("/find")
//    public String find(@RequestParam String walls, Map<String, Object> model){
//        Iterable<Building> buildings;
//        if(walls != null && !walls.isEmpty()){
//            buildings = buildingRepository.findByWalls(walls);
//        } else{
//            buildings = buildingRepository.findAll();
//        }
//        model.put("buildings", buildings);
//        return "buy";
//    }

    @GetMapping("/buy")
    public String buy(Model model) {
        Iterable<Building> building = buildingRepository.findAll();
        model.addAttribute("building", building);
        return "buy";
    }
    @GetMapping("/buyR")
    public String buyR(Model model) {
        Iterable<Building> building = buildingRepository.findAll();
        model.addAttribute("building", building);
        return "buyR";
    }
    @GetMapping("/buyA")
    public String buyA(Model model) {
        Iterable<Building> building = buildingRepository.findAll();
        model.addAttribute("building", building);
        return "buyA";
    }
    @GetMapping("/buyA/{id_building}/edit")
    public String buyAEdit(@PathVariable(value ="id_building") long id_building, Model model) {
        if(!buildingRepository.existsById(id_building)){
            return "redirect:/byuA";
        }
        Optional<Building> building1 = buildingRepository.findById(id_building);
        ArrayList<Building> res = new ArrayList<>();
        building1.ifPresent(res::add);
        model.addAttribute("building1", res);
        return "buyAEdit";
    }

    @PostMapping("/buyA/{id_building}/edit")
    public String buyAUpdate(@PathVariable(value ="id_building") long id_building,
                             @RequestParam String address,@RequestParam String walls,
                           @RequestParam int id_seller, @RequestParam int price,
                           @RequestParam int rooms,@RequestParam int space,
                           @RequestParam String type, Model model) {
        Building building = buildingRepository.findById(id_building).orElseThrow(IllegalStateException::new);
        building.setAddress(address);
        building.setWalls(walls);
        building.setId_Seller(id_seller);
        building.setPrice(price);
        building.setRooms(rooms);
        building.setSpace(space);
        building.setType(type);
        buildingRepository.save(building);
        return "redirect:/buyA";
    }
    @PostMapping("/buyA/{id_building}/delete")
    public String buyADelete(@PathVariable(value ="id_building") long id_building, Model model) {
        Building building = buildingRepository.findById(id_building).orElseThrow(IllegalStateException::new);
        buildingRepository.delete(building);
        return "redirect:/buyA";
    }
//    @GetMapping("/buyMore/{id_building}")
//    public String buyMore(@PathVariable(value ="id_building") long id_building, Model model) {
//        Optional<Building> building1 = buildingRepository.findById(id_building);
//        ArrayList<Building> res = new ArrayList<>();
//        building1.ifPresent(res::add);
//        model.addAttribute("building1", res);
//        return "buyMore";
//    }

    @GetMapping("/buyU/{id_Users}")
    public String buyU(@PathVariable(value ="id_Users") long id_Users, Model model) {
        Optional<Users> users = usersRepository.findById(id_Users);
        ArrayList<Users> res2 = new ArrayList<>();
        users.ifPresent(res2::add);
        model.addAttribute("users1", res2);

        Iterable<Building> building = buildingRepository.findAll();
        model.addAttribute("building", building);

        return "buyU";
    }
    @GetMapping("/buyU/{id_building}/b")
    public String buyUb(@PathVariable(value ="id_building") long id_building, Model model) {
        Optional<Building> building1 = buildingRepository.findById(id_building);
        ArrayList<Building> res = new ArrayList<>();
        building1.ifPresent(res::add);
        model.addAttribute("building1", res);
        return "buyUb";
    }
    @PostMapping("/buyU/{id_building}/b")
    public String buyUbA(@PathVariable(value ="id_building") long id_building,
                         @RequestParam int id_buildingB, @RequestParam int id_UsersB,
                         @RequestParam String suName, @RequestParam String fuName,
                         @RequestParam String puName, @RequestParam int uphone, Model model) {
        Proposal proposal = new Proposal(id_buildingB, id_UsersB,suName,fuName,puName,uphone);
        proposalRepository.save(proposal);
        return "buyU";
    }
//    @GetMapping("/buyMoreU/{id_building}")
//    public String buyMoreU(@PathVariable(value ="id_Users") long id_Users,
//                           @PathVariable(value ="id_building") long id_building,Model model) {
//        Optional<Building> building1 = buildingRepository.findById(id_building);
//        ArrayList<Building> res = new ArrayList<>();
//        building1.ifPresent(res::add);
//        Optional<Users> users = usersRepository.findById(id_Users);
//        ArrayList<Users> res1 = new ArrayList<>();
//        users.ifPresent(res1::add);
//        model.addAttribute("building1", res);
//        model.addAttribute("users", res1);
//        return "buyMoreU";
//    }

    @GetMapping("/seller")
    public String seller(Model model) {
        Iterable<Seller> sellers = sellerRepository.findAll();
        model.addAttribute("sellers", sellers);
        return "seller";
    }

    @GetMapping("/sellerA")
    public String sellerA(Model model) {
        Iterable<Seller> sellers = sellerRepository.findAll();
        model.addAttribute("sellers", sellers);
        return "sellerA";
    }
    @GetMapping("/usrA")
    public String usrA(Model model) {
        Iterable<Users> users = usersRepository.findAll();
        model.addAttribute("users", users);
        return "usrA";
    }
    @GetMapping("/showA")
    public String showA(Model model) {
        Iterable<Proposal> proposals = proposalRepository.findAll();
        model.addAttribute("proposals", proposals);
        return "showA";
    }

    @GetMapping("/showR")
    public String showR(Model model) {
        Iterable<Proposal> proposals = proposalRepository.findAll();
        model.addAttribute("proposals", proposals);
        return "showR";
    }

    @PostMapping("/showR/{id_Proposal}/deleteP")
    public String showRR(@PathVariable(value ="id_Proposal") long id_Proposal, Model model) {

        Proposal proposal = proposalRepository.findById(id_Proposal).orElseThrow(IllegalStateException::new);
        proposalRepository.delete(proposal);

        return "showR";
    }

    @PostMapping("/showR/{id_Proposal}/deletePB")
    public String showRRD(@PathVariable(value ="id_Proposal") long id_Proposal, Model model) {

//        Building building = buildingRepository.findById(id_building).orElseThrow(IllegalStateException::new);
//        buildingRepository.delete(building);
        Proposal proposal = proposalRepository.findById(id_Proposal).orElseThrow(IllegalStateException::new);
        proposalRepository.delete(proposal);
        return "showR";
    }


    @GetMapping("/showU/{id_Users}")
    public String showU(@PathVariable(value ="id_Users") long id_Users, Model model) {
        Optional<Users> users = usersRepository.findById(id_Users);
        ArrayList<Users> res2 = new ArrayList<>();
        users.ifPresent(res2::add);
        model.addAttribute("users", res2);

        Iterable<Proposal> proposals = proposalRepository.findAll();
        model.addAttribute("proposals", proposals);
        return "showU";
    }

    @GetMapping("/sellerU/{id_Users}")
    public String sellerU(@PathVariable(value ="id_Users") long id_Users, Model model) {
        Iterable<Seller> sellers = sellerRepository.findAll();
        model.addAttribute("sellers", sellers);
        Optional<Users> users = usersRepository.findById(id_Users);
        ArrayList<Users> res2 = new ArrayList<>();
        users.ifPresent(res2::add);
        model.addAttribute("users", res2);
        return "sellerU";
    }

    @GetMapping("/sell")
    public String sell(Model model) {
        return "sell";
    }

    @GetMapping("/sellU/{id_Users}")
    public String sellU(@PathVariable(value ="id_Users") long id_Users,Model model) {
        Optional<Users> users = usersRepository.findById(id_Users);
        ArrayList<Users> res2 = new ArrayList<>();
        users.ifPresent(res2::add);
        model.addAttribute("users", res2);
        return "sellU";
    }
    @GetMapping("/sellA")
    public String sellA(Model model) {
        return "sellA";
    }

    @PostMapping("/sell")
    public String sellAdd(@RequestParam String address,@RequestParam String walls,
                          @RequestParam int id_seller, @RequestParam int price,
                          @RequestParam int rooms,@RequestParam int space,
                          @RequestParam String type, Model model) {
        Building building = new Building(address, walls, id_seller,price, rooms, space,type);
        buildingRepository.save(building);

        return "sell";
    }

    @PostMapping("/sellU")
    public String sellAddU(@RequestParam String address,@RequestParam String walls,
                          @RequestParam int id_seller, @RequestParam int price,
                          @RequestParam int rooms,@RequestParam int space,
                          @RequestParam String type,Model model) {
//        Optional<Users> users = usersRepository.findById(id_Users);
//        ArrayList<Users> res1 = new ArrayList<>();
//        users.ifPresent(res1::add);
//        model.addAttribute("users", res1);
        Building building = new Building(address, walls, id_seller,price, rooms, space,type);
        buildingRepository.save(building);
        return "sellU";
    }
    @PostMapping("/sellA")
    public String sellAddA(@RequestParam String address,@RequestParam String walls,
                          @RequestParam int id_seller, @RequestParam int price,
                          @RequestParam int rooms,@RequestParam int space,
                          @RequestParam String type, Model model) {
        Building building = new Building(address, walls, id_seller,price, rooms, space,type);
        buildingRepository.save(building);
        return "sellA";
    }

    @GetMapping("/rent")
    public String rent(Model model) {
        Iterable<Building> building = buildingRepository.findAll();
        model.addAttribute("building", building);
        return "rent";
    }
    @GetMapping("/rentR")
    public String rentR(Model model) {
        Iterable<Building> building = buildingRepository.findAll();
        model.addAttribute("building", building);
        return "rentR";
    }
    @GetMapping("/rentA")
    public String rentA(Model model) {
        Iterable<Building> building = buildingRepository.findAll();
        model.addAttribute("building", building);
        return "rentA";
    }
    @GetMapping("/rentU/{id_Users}")
    public String rentU(@PathVariable(value ="id_Users") long id_Users, Model model) {
        Optional<Users> users = usersRepository.findById(id_Users);
        ArrayList<Users> res2 = new ArrayList<>();
        users.ifPresent(res2::add);
        model.addAttribute("users1", res2);

        Iterable<Building> building = buildingRepository.findAll();
        model.addAttribute("building", building);
        return "rentU";
    }
    @GetMapping("/rentU/{id_building}/r")
    public String rentUr(@PathVariable(value ="id_building") long id_building, Model model) {
        Optional<Building> building1 = buildingRepository.findById(id_building);
        ArrayList<Building> res = new ArrayList<>();
        building1.ifPresent(res::add);
        model.addAttribute("building1", res);
        return "rentUr";
    }
    @PostMapping("/rentU/{id_building}/r")
    public String rentUrA(@PathVariable(value ="id_building") long id_building,
                         @RequestParam int id_buildingB, @RequestParam int id_UsersB,
                         @RequestParam String suName, @RequestParam String fuName,
                         @RequestParam String puName, @RequestParam int uphone, Model model) {
        Proposal proposal = new Proposal(id_buildingB, id_UsersB,suName,fuName,puName,uphone);
        proposalRepository.save(proposal);
        return "rentU";
    }
//    @GetMapping("/rent/{id_building}")
//    public String rentMore(@PathVariable(value ="id_building") long id_building, Model model) {
//        Optional<Building> building1 = buildingRepository.findById(id_building);
//        ArrayList<Building> res = new ArrayList<>();
//        building1.ifPresent(res::add);
//        model.addAttribute("building1", res);
//        return "rentMore";
//    }
//
//    @GetMapping("/rentU/{id_building}")
//    public String rentMoreU(@PathVariable(value ="id_building") long id_building, Model model) {
//        Optional<Building> building1 = buildingRepository.findById(id_building);
//        ArrayList<Building> res = new ArrayList<>();
//        building1.ifPresent(res::add);
//        model.addAttribute("building1", res);
//        return "rentMoreU";
//    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("title", "Информация");
        return "info";
    }
    @GetMapping("/infoA")
    public String infoA(Model model) {
        model.addAttribute("title", "Информация");
        return "infoA";
    }
    @GetMapping("/infoR")
    public String infoR(Model model) {
        model.addAttribute("title", "Информация");
        return "infoR";
    }
    @GetMapping("/infoU/{id_Users}")
    public String infoU(@PathVariable(value ="id_Users") long id_Users, Model model) {
        Optional<Users> users = usersRepository.findById(id_Users);
        ArrayList<Users> res2 = new ArrayList<>();
        users.ifPresent(res2::add);
        model.addAttribute("users1", res2);


        model.addAttribute("title", "Информация");
        return "infoU";
    }

}
