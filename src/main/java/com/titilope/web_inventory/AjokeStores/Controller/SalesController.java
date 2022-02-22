package com.titilope.web_inventory.AjokeStores.Controller;

import com.titilope.web_inventory.AjokeStores.Entity.ProductSale;
import com.titilope.web_inventory.AjokeStores.Entity.Sale;
import com.titilope.web_inventory.AjokeStores.Service.ProductSaleService;
import com.titilope.web_inventory.AjokeStores.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    SaleService saleService;

    @Autowired
    ProductSaleService productSaleService;

    @GetMapping("/list")
    public String listSales(Model model){

        List<Sale> sales = saleService.findAll();

        model.addAttribute("sales", sales);

        return "sales/list";
    }

    @GetMapping("/view")
    public String viewSales(@RequestParam("saleId") int id, Model model ){

        Sale sale = saleService.findById(id);

        model.addAttribute("sale", sale);

        return "sales/view";
    }
}
