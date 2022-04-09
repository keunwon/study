package com.myshop.catalog.ui;

import com.myshop.catalog.query.category.CategoryData;
import com.myshop.catalog.query.category.CategoryDataDao;
import com.myshop.catalog.query.product.CategoryProduct;
import com.myshop.catalog.query.product.ProductData;
import com.myshop.catalog.query.product.ProductQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProductController {
    private final CategoryDataDao categoryDataDao;
    private final ProductQueryService productQueryService;

    @GetMapping(value = "/categories")
    public String categories(ModelMap model) {
        List<CategoryData> categories = categoryDataDao.findAll();
        model.addAttribute(categories);
        return "category/categoryList";
    }

    @GetMapping(value = "/categories/{categoryId}")
    public String list(@PathVariable("categoryId") Long categoryId,
                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       ModelMap model) {
        CategoryProduct productInCategory = productQueryService.getProductInCategory(categoryId, page, 10);
        model.addAttribute(productInCategory);
        return "category/productList";
    }

    @GetMapping(value = "/products/{productId}")
    public String detail(@PathVariable("productId") String productId, ModelMap model, HttpServletResponse response) throws IOException {
        Optional<ProductData> product = productQueryService.getProduct(productId);

        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "category/productDetail";
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return null;
    }
}
