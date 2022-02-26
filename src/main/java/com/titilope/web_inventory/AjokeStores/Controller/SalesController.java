package com.titilope.web_inventory.AjokeStores.Controller;

import com.titilope.web_inventory.AjokeStores.Entity.Sale;
import com.titilope.web_inventory.AjokeStores.Service.ProductSaleService;
import com.titilope.web_inventory.AjokeStores.Service.SaleService;
import com.titilope.web_inventory.AjokeStores.Utilities.DateUtils;
import com.titilope.web_inventory.AjokeStores.Utilities.FilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    SaleService saleService;

    @Autowired
    ProductSaleService productSaleService;

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
        return "sales/track-sales";
    }

    @PostMapping("/track-sales")
    public String trackSalesWithFilter(@ModelAttribute("request")FilterRequest filterRequest, Model model){
        List<Sale> sales = saleService.getSalesByFilterRequest(filterRequest);
        model.addAttribute("sales", sales);
        return "sales/track-sales";
    }
}
