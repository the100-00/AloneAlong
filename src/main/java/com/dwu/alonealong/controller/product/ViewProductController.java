package com.dwu.alonealong.controller.product;

import com.dwu.alonealong.exception.NullProductException;
import com.dwu.alonealong.exception.StockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dwu.alonealong.domain.Product;
import com.dwu.alonealong.service.AloneAlongFacade;

@Controller
@SessionAttributes({"product"})
public class ViewProductController {
	private AloneAlongFacade aloneAlong;

	@Autowired
	public void setAloneAlong(AloneAlongFacade aloneAlong) {
		this.aloneAlong = aloneAlong;
	}
	
	@RequestMapping("/shop/{productId}")
	public String handleRequest(@PathVariable("productId") String productId,
			@RequestParam(value="quantity",  defaultValue="1") int quantity,
			ModelMap model) throws Exception {
		Product product = this.aloneAlong.getProduct(productId);
		if(product == null) {
			throw new NullProductException();
		}
		if(product.getProductStock() < quantity) {
			throw new StockException();
		}
		product.setQuantity(quantity);
		model.put("product", product);
		model.put("pcId", product.getPcId());
		return "product";
	}
}