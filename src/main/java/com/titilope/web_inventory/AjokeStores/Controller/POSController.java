package com.titilope.web_inventory.AjokeStores.Controller;

import com.titilope.web_inventory.AjokeStores.Entity.Product;
import com.titilope.web_inventory.AjokeStores.Entity.ProductSale;
import com.titilope.web_inventory.AjokeStores.Entity.Sale;
import com.titilope.web_inventory.AjokeStores.Entity.User;
import com.titilope.web_inventory.AjokeStores.Service.PackagingUnitService;
import com.titilope.web_inventory.AjokeStores.Service.ProductSaleService;
import com.titilope.web_inventory.AjokeStores.Service.ProductService;
import com.titilope.web_inventory.AjokeStores.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/sales")
public class POSController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private PackagingUnitService packagingUnitService;

    @GetMapping("/pointOfSaleForm")
    public String showSalesForm(Model model){

        List<Product> products= productService.findAll();
        Sale sale = new Sale();
        model.addAttribute("products", products);
        model.addAttribute("sale", sale);

        return "sales/pos-form";
    }


    @PostMapping("/save")
    public String processSales(@ModelAttribute("sale") Sale sale) {

        sale.setDateSold(new Date());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        sale.setSalesOfficer((User) auth.getPrincipal());

        List<ProductSale> productSales = sale.getProductSales();

        double total = 0;
        int stockQuantity;
        for (ProductSale productSale : productSales) {
            total+= productSale.getTotalPrice();

            stockQuantity=productSale.getProduct().getPackagingUnit().getStockQuantity();
            stockQuantity-= productSale.getQuantity();
            productSale.getProduct().getPackagingUnit().setStockQuantity(stockQuantity);

        }

        sale.setTotalPrice(total);

        saleService.save(sale);

        return "redirect:/products/list";
    }

}
