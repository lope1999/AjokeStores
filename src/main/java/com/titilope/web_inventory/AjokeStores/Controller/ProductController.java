package com.titilope.web_inventory.AjokeStores.Controller;

import com.titilope.web_inventory.AjokeStores.Entity.Product;
import com.titilope.web_inventory.AjokeStores.Service.ProductSaleService;
import com.titilope.web_inventory.AjokeStores.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSaleService productSaleService;

    @GetMapping("/list")
    public String listProduct(Model model){

        List<Product> products= productService.findAll();

        model.addAttribute("products", products);
        return "products/list-products";
    }

    @GetMapping("/create")
    public String create(Model model) {

        Product product = new Product();

        model.addAttribute("product", product);

        return "products/product-form";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("productId") int id,
                                    Model model) {

        Optional<Product> product = Optional.ofNullable(productService.findById(id));

        model.addAttribute("product", product);

        return "products/product-form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product){

        productService.save(product);

        System.out.println(product);

        return "redirect:/products/list";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("productId") int id) {

        productService.deleteById(id);

        return "redirect:/products/list";
    }


    public String processSales() {
        // create products
        // create sales
        // add product sales

        return "";
    }

}
