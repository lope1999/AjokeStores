package com.titilope.web_inventory.AjokeStores.Controller;

import com.titilope.web_inventory.AjokeStores.Entity.Product;
import com.titilope.web_inventory.AjokeStores.Service.ProductSaleService;
import com.titilope.web_inventory.AjokeStores.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public String listBooks(Model model,@RequestParam("page")Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
            final int currentPage = page.orElse(1);
            final int pageSize = size.orElse(5);

            Page<Product> productPage = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
            model.addAttribute("products", productPage);

            int totalPages = productPage.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
            return"products/list-products";
    }

/*    @GetMapping("/list")
    public String listProduct(Model model){

        List<Product> products= productService.findAll();

        model.addAttribute("products", products);
        return "products/list-products";
    }*/

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

    @GetMapping("/search")
    public String findProductByName(@RequestParam("search") String search, Model model){
        List<Product> products = productService.findProductByName(search);

        model.addAttribute("products", products);

        return "products/list-products";
    }

}
