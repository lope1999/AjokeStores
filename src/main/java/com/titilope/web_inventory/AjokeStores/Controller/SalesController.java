package com.titilope.web_inventory.AjokeStores.Controller;

import com.titilope.web_inventory.AjokeStores.Entity.Sale;
import com.titilope.web_inventory.AjokeStores.Service.ProductSaleService;
import com.titilope.web_inventory.AjokeStores.Service.ProductService;
import com.titilope.web_inventory.AjokeStores.Service.SaleService;
import com.titilope.web_inventory.AjokeStores.Utilities.FilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    SaleService saleService;

    @Autowired
    ProductSaleService productSaleService;

    @Autowired
    ProductService productService;

    @GetMapping("/list")
    public String listSales(Model model) {

        List<Sale> sales = saleService.findAll();

        model.addAttribute("sales", sales);

        return "sales/list";
    }

    @GetMapping("/view")
    public String viewSales(@RequestParam("saleId") int id, Model model) {

        Sale sale = saleService.findById(id);

        model.addAttribute("sale", sale);

        return "sales/view";
    }

    @GetMapping("/track-sales")
    public String trackSales(Model model) {
        List<Sale> sales = saleService.getSalesForToday();
        model.addAttribute("sales", sales);

        FilterRequest filterRequest = new FilterRequest();
        model.addAttribute("request", filterRequest);
        model.addAttribute("products", productService.findLowStockedProducts());
        return "sales/track-sales";
    }

    @PostMapping("/track-sales")
    public String trackSalesWithFilter(@ModelAttribute("request")FilterRequest filterRequest, Model model){
        List<Sale> sales = saleService.getSalesByFilterRequest(filterRequest);
        model.addAttribute("sales", sales);

        return "sales/track-sales";
    }

    @GetMapping("/search")
    public String searchSales(@RequestParam("search") String date, Model model){
        try{
            System.out.println(date);
            List<Sale> sales= saleService.findByDateSold(date);


            model.addAttribute("sales", sales);
        } catch(Exception exception){
            ///
        }

        return "sales/list";
    }
}
