package pl.ds.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ProductController {

    private List<Product> products = getProducts();

    @GetMapping("/")
    public String homepage() {
        return "productpage";
    }

    @GetMapping("/lista")
    public String printListProduct(Model model) {
//        List<Product> products = getProducts();
        double price = getPrice(products);

        model.addAttribute("produkty", products);
        model.addAttribute("suma", price);
        return "listpage";
    }


    @GetMapping("/tabela")
    public String printTableProduct(Model model) {
//        List<Product> products = getProducts();
        double price = getPrice(products);

        model.addAttribute("produkty", products);
        model.addAttribute("suma", price);
        return "tablepage";
    }

    @PostMapping("/add")
    @GetMapping("/add")
    public String addProduct(@RequestParam(value = "name") String name1, @RequestParam(value = "price") Double price1, Model model) {
        Product prd = new Product(name1, price1);
//        List<Product> products = getProducts();
        products.add(prd);

        double price = getPrice(products);

        model.addAttribute("produkty", products);
        model.addAttribute("suma", price);
        return "success";

    }

    private double getPrice(List<Product> products) {
        double price = 0;
        for (Product product : products) {
            price += product.getPrice();
        }
        return price;
    }


    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Mleko", 2.7));
        products.add(new Product("Sok", 3.5));
        products.add(new Product("Baton", 0.7));
        products.add(new Product("Banany", 6.3));
        return products;
    }
}